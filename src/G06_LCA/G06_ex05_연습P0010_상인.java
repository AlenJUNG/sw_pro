package G06_LCA;

import java.io.*;
import java.util.*;

public class G06_ex05_연습P0010_상인 {
	static int MAX_N = 100000;
	static int MAX_D = 17;
	static int N, TC, ans;
	static int depth[], parent[][];
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G06_LCA/G06_ex05_연습P0010_상인.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {

			N = Integer.parseInt(br.readLine());

			depth = new int[N + 1];
			parent = new int[MAX_D + 1][MAX_N + 1];
			graph = new ArrayList<ArrayList<Integer>>();
			for (int i = 0; i <= N; i++) {
				graph.add(new ArrayList<Integer>());
			}

			int from, to;
			for (int i = 1; i <= N - 1; i++) {
				st = new StringTokenizer(br.readLine());
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());

				graph.get(from).add(to);
				graph.get(to).add(from);
			}

			BFS(1);

			ans = 0;

			int root_node;
			for (int i = 1; i <= N - 1; i++) {
				root_node = LCA(i, i + 1);
				ans += (depth[i] + depth[i + 1] - 2 * depth[root_node]);
			}

			bw.write("#"+tc+" "+ans + "\n");
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

		while (!q.isEmpty()) {
			int now = q.poll();

			for (int toIdx = 0; toIdx < graph.get(now).size(); toIdx++) {
				// 아래 코드로
				int next = graph.get(now).get(toIdx);

				if (next == parent[0][now]) {
					continue;
				}

				q.offer(next);
				depth[next] = depth[now] + 1;
				parent[0][next] = now;

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
		
		int t = depth[b] - depth[a];
		for (int i = MAX_D; i >= 0; i--) {
			if ((t & (1 << i)) > 0) {
				b = parent[i][b];
			}
		}
		
		
		// 아래처럼하면 제한시간 초과 뜸
//		for (int i = MAX_D; i >= 0; i--) {
//			if (depth[b] - depth[a] >= Math.pow(2, i)) {
//				b = parent[i][b];
//			}
//		}

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
