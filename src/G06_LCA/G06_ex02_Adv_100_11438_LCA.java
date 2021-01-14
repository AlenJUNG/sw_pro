package G06_LCA;
import java.io.*;
import java.util.*;

// https://code0xff.tistory.com/135
// https://www.acmicpc.net/problem/11438

public class G06_ex02_Adv_100_11438_LCA {
	static int MAX_N = 100000;	//	문제에서 정점 최대 입력치 입력, 향후 변경 가능
	static int MAX_D = 17;	//	// 2^n 승이 MAX_N을 넘도록 설정
	static int N, M;		//	N : 노드의 개수, M : 공통 조상을 알고싶은 쌍의 개수
	static int parent[][];	// 부모 정보
	static int depth[];		// 각 노드까지의 깊이 > 0부터 시작
	static ArrayList<Integer> wire[];	// 그래프 정보 : 각 배열에 ArrayList 할당
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/G06_LCA/G06_ex02_Adv_100_11438_LCA.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		parent = new int[MAX_D + 1][MAX_N + 1];
		depth = new int[N + 1];
		
		wire = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++) {
			wire[i] = new ArrayList<Integer>();
		}
		
		for(int i = 1; i <= N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			wire[a].add(b);
			wire[b].add(a);
		}

		/********************** 입력부 끝 **********************/
				
		// DFS or BFS 가능
		// STEP 01. 모든 노드에 대한 깊이 검색/값 저장
//		DFS(1, 0);
		BFS(1, 0);
		
		// STEP 02. 최소 공통 조상을 찾을 두 노드 확인 > LCA 돌린 후 출력
		M = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= M; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st1.nextToken());
			int b = Integer.parseInt(st1.nextToken());
			
			bw.write(LCA(a, b)+"\n");
		}		
		
		/****************** 입출력 close 처리 ******************/
		br.close();
		bw.flush();
		bw.close();

	}
	
	// STEP 01 : 모든 노드에 대한 깊이(depth) 계산
	private static void BFS(int node, int dep) {
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.offer(node);			// 최초 node q에 넣기
		parent[0][node] = 0;	// 최초 node 부모 정보 0 입력
		depth[node] = dep;		// 최초 node 깊이 정보 0 입력
		
		while(!q.isEmpty()) {	// * ! 잘 확인하기
			int v = q.poll();
			
			for(int next : wire[v]) {
				if(next == parent[0][v]) {	// v의 연결 정점이 부모면 continue
					continue;
				}
				// v의 연결 정점이 부모가 아닐 경우
				q.offer(next);				//	연결정점 q에 넣기
				parent[0][next] = v;		//	연결정점의 바로 윗 부모정보를 parent[2^0승]에 입력 
				depth[next] = depth[v] + 1;	//  ** 중요 : 연결정점 깊이 정보 입력
				
				// DP : 각 정점별 거리별 부모 정보 입력
				for(int i = 1; i <= MAX_D; i++) {
					// 연결정점의 부모정보가 0인 경우 = root인 경우 > break
					if(parent[i - 1][next] == 0) {
						break;
					}
					// 연결정점이 root가 아닌 경우 > 점화식 수행
					parent[i][next] = parent[i - 1][ parent[i - 1][next] ];
				}				
			}
		}		
	}
	
	// STEP 01 : 모든 노드에 대한 깊이(depth) 계산
	private static void DFS(int v, int dep) {
		// 문제에서 정해진 노드가 아닌 경우 return 처리 
		if(depth[v] != -1) {
			return;
		}
						
		depth[v] = dep;	// 정점의 depth를 tree 배열에 입력
		
		// A. 정점과 연결되어 있는 모든 정점들에 대하여
		for(int next : wire[v]) {
			if(depth[next] != -1) {	// B. 미방문 정점이 아니면 PASS
				continue;
			}
			
			parent[0][next] = v;	// C. 부모 정보 저장 : next의 부모 정보는 parent[0][next]에 있음
			
			// DP
			for(int i = 1; i <= MAX_D ; i++) {
				if(parent[i - 1][next] == 0) {	// 부모정보가 0이라면 break
					break;
				}
				// 부모정보가 0이 아닌 경우
				parent[i][next] = parent[i - 1][ parent[i - 1][next] ];
			}
			
			DFS(next, dep + 1);
		}
	}
	
	// STEP 02 : 최소공통조상을 찾을 두 노드 확인
	private static int LCA(int a, int b) {
		// A. 두 노드의 depth 맞추기
			// A-1 : b가 더 깊도록 설정
		if(depth[a] > depth[b]) {
			int temp;
			temp = a;
			a = b;
			b = temp;
		}
		
			// A-2 : 먼저 깊이가 동일하도록 설정
		for(int i = MAX_D; i >= 0; i--) {
			if(depth[b] - depth[a] >= Math.pow(2, i)) {
				b = parent[i][b];
			}
		}
		
//		만약 b의 깊이 조정을 한 직후 a와 b가 같다면 a를 출력 후 return
		if(a == b) {
			return a;
		}
		
		// MAX_D > 0 까지 부모가 같지 않은 경우를 찾아 저장하기
		for(int i = MAX_D; i >= 0; i--) {
			if(parent[i][a] != parent[i][b]) {
				a = parent[i][a];
				b = parent[i][b];
			}
		}
		
		return parent[0][a];	// parent[0][b] 를 return 해도 상관없음
	}

}
