package G06_LCA;
// G06_ex06_P0051_조상이키컸으면 작성중

import java.io.*;
import java.util.*;

public class test {
	static int MAX_N = 10000;
	static int MAX_D = 14;
	static int T, N, Q;
	static int a, b, k;
	static int parent[][], H[], depth[], MH[];
	static ArrayList<ArrayList<Integer>> wire;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G06_LCA/G06_ex06_P0051_조상이키컸으면.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());

			parent = new int[MAX_D + 1][N + 1];
			depth = new int[N + 1];
			H = new int[N + 1];
			MH = new int[N + 1];

			wire = new ArrayList<ArrayList<Integer>>();
			for (int i = 0; i <= N; i++) {
				wire.add(new ArrayList<Integer>());
			}

			int root_node, now_height; // 조상 번호, 본인 키
			for (int child_node = 1; child_node <= N; child_node++) {
				st = new StringTokenizer(br.readLine());
//				parent[0][i] = Integer.parseInt(st.nextToken());
//				H[i] = Integer.parseInt(st.nextToken());

				root_node = Integer.parseInt(st.nextToken());
				now_height = Integer.parseInt(st.nextToken());

				parent[0][child_node] = root_node;
				H[child_node] = now_height;

				wire.get(root_node).add(child_node);
				wire.get(child_node).add(root_node);

			}

			BFS(1);

			StringBuilder sb = new StringBuilder();
			sb.append("#" + tc);

			// Q 최종 구할 케이스 수 > 다수의 공통조상 구하기
			for (int i = 1; i <= Q; i++) {
				st = new StringTokenizer(br.readLine());

				k = Integer.parseInt(st.nextToken()); // 모임에 속한 구성원 개수
				// 구할 첫 번째 구성원 번호
				a = Integer.parseInt(st.nextToken());

				// 첫 번째 구성원 번호는 받았으니 2번 부터 k번까지 LCA 돌림
				for (int j = 2; j <= k; j++) {
					b = Integer.parseInt(st.nextToken());
					a = LCA(a, b);
				}
				// 최종 값 Max Height[a]
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
		depth[start] = 0;
		parent[0][start] = 0;
		H[start] = 2;

		while (!q.isEmpty()) {
			int now = q.poll();

			for (int toIdx = 0; toIdx < wire.get(now).size(); toIdx++) {
				int next = wire.get(now).get(toIdx);

				if (next == parent[0][now]) {
					continue;
				}

				q.offer(next);
				depth[next] = depth[now] + 1;
				parent[0][next] = now;
				MH[next] = Math.max(H[next], MH[now]);

				for (int i = 1; i <= MAX_D; i++) {
					if (parent[i - 1][next] == 0) {
						break;
					}
					parent[i][next] = parent[i - 1][parent[i - 1][next]];
				}

			}
		}

	}

	private static int LCA(int a, int b) {
		if (depth[a] > depth[b]) {
			int temp;
			temp = a;
			a = b;
			b = temp;
		}

		for (int i = MAX_D; i >= 0; i--) {
			if (depth[b] - depth[a] >= Math.pow(2, i)) {
				b = parent[0][b];
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
