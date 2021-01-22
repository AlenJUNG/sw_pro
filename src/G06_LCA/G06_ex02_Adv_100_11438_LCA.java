package G06_LCA;
import java.io.*;
import java.util.*;

//https://code0xff.tistory.com/135
//https://www.acmicpc.net/problem/11438

public class G06_ex02_Adv_100_11438_LCA {
	static int MAX_N = 100000; // 노드 수
	static int MAX_D = 17; // 2^MAX_D > MAX_N 미리 계산해서 넣자
	static int N, M;
	static int parent[][]; // parent[MAX_D][MAX_N]
	static int depth[];
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G06_LCA/G06_ex02_Adv_100_11438_LCA.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		parent = new int[MAX_D + 1][MAX_N + 1];
		depth = new int[N + 1];

		graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}

		// 무방향인지 양방향인지 항상 주의
		for (int i = 1; i <= N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		/********************** 입력부 끝 **********************/
		// STEP 01 : 모든 노드에 대한 깊이(depth) 계산
		BFS(1);

		M = Integer.parseInt(br.readLine());

		for (int i = 1; i <= M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// STEP 02 : 최소공통조상을 찾을 두 노드 확인
			System.out.println(LCA(a, b));
		}

		br.close();
	}

	// STEP 01 : 모든 노드에 대한 깊이(depth) 계산
	private static void BFS(int root) {
		Queue<Integer> q = new LinkedList<>();

		q.offer(root);
		parent[0][root] = 0;
		depth[root] = 0;

		while (!q.isEmpty()) {
			int now = q.poll();

			for (int next : graph.get(now)) {
				// v의 연결 정점이 부모면 continue
				if (next == parent[0][now]) {
					continue;
				}
				// v의 연결 정점이 부모가 아닐 경우
				q.offer(next); // 연결정점 q에 넣기
				parent[0][next] = now; // 연결정점의 바로 윗 부모정보를 parent[2^0승]에 입력
				depth[next] = depth[now] + 1; // ** 중요 : 연결정점 깊이 정보 입력

				// DP : 각 정점별 거리별 부모 정보 입력
				for (int i = 1; i <= MAX_D; i++) {
					if (parent[i - 1][next] == 0) {
						break;
					}
					// 연결정점의 부모정보가 0인 경우 = root인 경우 > break
					parent[i][next] = parent[i - 1][parent[i - 1][next]];
				}
			}
		}
	}

	// STEP 02 : 최소공통조상을 찾을 두 노드 확인
	private static int LCA(int a, int b) {
		// A. 두 노드의 depth 맞추기
		// A-1 : b가 더 깊도록 설정
		if (a > b) {
			int temp;
			temp = a;
			a = b;
			b = temp;
		}
		// A-2 : 먼저 깊이가 동일하도록 설정
		for (int i = MAX_D; i >= 0; i--) {
			if (depth[b] - depth[a] >= Math.pow(2, i)) {
				b = parent[i][b];
			}
		}

		// 만약 b의 깊이 조정을 한 직후 a와 b가 같다면 a를 출력 후 return
		if (a == b) {
			return a;
		}
		
		// MAX_D > 0 까지 부모가 같지 않은 경우를 찾아 저장하기
		for (int i = MAX_D; i >= 0; i--) {
			if (parent[i][b] != parent[i][a]) {
				a = parent[i][a];
				b = parent[i][b];
			}
		}
		// parent[0][b] 를 return 해도 상관없음
		return parent[0][a];
	}

}
