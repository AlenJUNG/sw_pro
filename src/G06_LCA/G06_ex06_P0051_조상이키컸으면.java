package G06_LCA;

/* LCA 시간 복잡도 : O(logN)
 * 쿼리가 함께 존재하는 경우 : O(MlogN) = 10000 * log 10000 = 133,800 (0.00133) * 30 = 4,014,000
 * >> 대략 0.4초
 */

/* (2021.01.25)
 * 1. LCA 함수에서 math.pow 대신 1 << i 비트연산으로 시간 복잡도 줄임
 * 2. LCA 함수에서 parent[i][b] 를 parent[0][b] 으로 잘못 작성하였음
 */


import java.io.*;
import java.util.*;

public class G06_ex06_P0051_조상이키컸으면 {
	static int MAX_N = 100_000;
	static int MAX_D = 17;
	static int T, N, Q;
	static int a, b, k;
	static int parent[][], H[], depth[], MH[];
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G06_LCA/G06_ex06_P0051_조상이키컸으면.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());

			parent = new int[MAX_D + 1][MAX_N + 1];
			depth = new int[N + 1];
			H = new int[N + 1];
			MH = new int[N + 1];
			
			graph = new ArrayList<ArrayList<Integer>>();
			for(int i = 0; i <= N; i++) {
				graph.add(new ArrayList<Integer>());
			}
			
			int p_info, child_h;
			for (int child_info = 1; child_info <= N; child_info++) {
				st = new StringTokenizer(br.readLine());
				p_info = Integer.parseInt(st.nextToken());
				child_h = Integer.parseInt(st.nextToken());
				graph.get(child_info).add(p_info);
				graph.get(p_info).add(child_info);
				H[child_info] = child_h;
				MH[child_info] = child_h;
			}
			
			BFS(1);

//			for (int i = 1; i <= N; i++) {
//				DFS(i);
//			}
//
//			for (int k = 1; k <= MAX_D; k++) {
//				for (int i = 1; i <= N; i++) {
//					parent[k][i] = parent[k - 1][parent[k - 1][i]];
//				}
//			}
			
			sb = new StringBuilder();
			sb.append("#" + tc);
			
			for (int i = 1; i <= Q; i++) {	// 3번 반복
				st = new StringTokenizer(br.readLine());
				k = Integer.parseInt(st.nextToken());	// 모임 구성원 수
				a = Integer.parseInt(st.nextToken());	// 맨 처음 수
				
				// Opt 01.
				// a가 하나라면 아래 while 문을 돌지 않음
				// i, i+1 관계로 계속 반복함 ex) 3개 값의 LCA를 찾으려면 2번 반복
				while(k-- > 1) {
					b = Integer.parseInt(st.nextToken());
					a = LCA(a, b);
					
					if(a == 1) {
						break;
					}
				}
				
				// Opt 02.
//				for (int j = 2; j <= k; j++) {
//					b = Integer.parseInt(st.nextToken());
//					a = LCA(a, b);
//				}
				
				sb.append(" " + MH[a]);
			}
			bw.write(sb.toString());
			bw.newLine();

		}

		br.close();
		bw.flush();
		bw.close();
		
	}

	private static void BFS(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		depth[start] = 0;	// 생략해도 무방
		parent[0][start] = 0;	// 생략해도 무방
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int toIdx = 0; toIdx < graph.get(now).size(); toIdx++) {
				int next = graph.get(now).get(toIdx);
				
				if(next == parent[0][now]) {
					continue;
				}
				
				q.offer(next);
				depth[next] = depth[now] + 1;
				parent[0][next] = now;
				// * 이 문제에서는 key Point
				MH[next] = Math.max(MH[next], MH[now]);
				
				for(int i = 1; i <= MAX_D; i++) {
					if(parent[i-1][next] == 0) {
						break;
					}
					parent[i][next] = parent[i-1][parent[i-1][next]];
				}
			}
		}
	}
	
//  * Option : DFS 방법 > Stack mem 초과 위험 있음
//	private static void DFS(int start) {
//		// 0이거나 depth가 미리 입력이 되어 있으면 return
//		if (start == 0 || depth[start] > 0) {
//			return;
//		}
//		DFS(parent[0][start]);
//		depth[start] = depth[parent[0][start]] + 1;
//		// 아래 코드 중요
//		MH[start] = Math.max(H[start], MH[parent[0][start]]);
//
//	}

	private static int LCA(int a, int b) {
		if (depth[a] > depth[b]) {
			int temp;
			temp = a;
			a = b;
			b = temp;
		}

		for (int i = MAX_D; i >= 0; i--) {
			// ** (1 << i) 은 2^i를 의미, 시간 복잡도를 줄이는 key Code
			if (depth[b] - depth[a] >= (1 << i)) {	
				b = parent[i][b];	// > fail : 0인지 i인지 잘보기
			}
		}

		if (a == b) {
			return a;
		}

		for (int i = MAX_D; i >= 0; i--) {
			if (parent[i][a] != parent[i][b]) {
				a = parent[i][a];
				b = parent[i][b];
			}
		}

		return parent[0][a];
	}

}
