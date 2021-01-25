package G06_LCA;

/* LCA �ð� ���⵵ : O(logN)
 * ������ �Բ� �����ϴ� ��� : O(MlogN) = 10000 * log 10000 = 133,800 (0.00133) * 30 = 4,014,000
 * >> �뷫 0.4��
 */

/* (2021.01.25)
 * 1. LCA �Լ����� math.pow ��� 1 << i ��Ʈ�������� �ð� ���⵵ ����
 * 2. LCA �Լ����� parent[i][b] �� parent[0][b] ���� �߸� �ۼ��Ͽ���
 */


import java.io.*;
import java.util.*;

public class G06_ex06_P0051_������Ű������ {
	static int MAX_N = 100_000;
	static int MAX_D = 17;
	static int T, N, Q;
	static int a, b, k;
	static int parent[][], H[], depth[], MH[];
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G06_LCA/G06_ex06_P0051_������Ű������.txt"));
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
			
			for (int i = 1; i <= Q; i++) {	// 3�� �ݺ�
				st = new StringTokenizer(br.readLine());
				k = Integer.parseInt(st.nextToken());	// ���� ������ ��
				a = Integer.parseInt(st.nextToken());	// �� ó�� ��
				
				// Opt 01.
				// a�� �ϳ���� �Ʒ� while ���� ���� ����
				// i, i+1 ����� ��� �ݺ��� ex) 3�� ���� LCA�� ã������ 2�� �ݺ�
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
		depth[start] = 0;	// �����ص� ����
		parent[0][start] = 0;	// �����ص� ����
		
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
				// * �� ���������� key Point
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
	
//  * Option : DFS ��� > Stack mem �ʰ� ���� ����
//	private static void DFS(int start) {
//		// 0�̰ų� depth�� �̸� �Է��� �Ǿ� ������ return
//		if (start == 0 || depth[start] > 0) {
//			return;
//		}
//		DFS(parent[0][start]);
//		depth[start] = depth[parent[0][start]] + 1;
//		// �Ʒ� �ڵ� �߿�
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
			// ** (1 << i) �� 2^i�� �ǹ�, �ð� ���⵵�� ���̴� key Code
			if (depth[b] - depth[a] >= (1 << i)) {	
				b = parent[i][b];	// > fail : 0���� i���� �ߺ���
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
