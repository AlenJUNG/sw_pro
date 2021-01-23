package G06_LCA;

import java.io.*;
import java.util.*;

// G06_ex03_Adv_100_1761_정점들의거리 Update 중 (2021.01.23)

public class G06_ex03_Adv_100_1761_정점들의거리 {
	static int MAX_N = 40000;
	static int MAX_D = 16;
	static int N, M;
	static int parent[][], depth[], distance[];
	static ArrayList<ArrayList<Integer>> wire, distance_wire;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G06_LCA/G06_ex03_Adv_100_1761_정점들의거리.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		depth = new int[N + 1];
		distance = new int[N + 1];
		parent = new int[MAX_D + 1][N + 1];

		wire = new ArrayList<ArrayList<Integer>>();
		distance_wire = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= N; i++) {
			wire.add(new ArrayList<Integer>());
			distance_wire.add(new ArrayList<Integer>());
		}

		int from, to, cost;
		for (int i = 1; i <= N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());

			wire.get(from).add(to);
			wire.get(to).add(from);

			distance_wire.get(from).add(cost);
			distance_wire.get(to).add(cost);
		}

		BFS(1);

		M = Integer.parseInt(br.readLine());

		int a, b;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			int x = distance[LCA(a, b)];
			//a ~ b까지의 거리 = a까지 거리 + b까지의 거리 - 2 * (LCA까지의 거리)
			System.out.println(distance[a] + distance[b] - 2 * x);			
		}

		br.close();
	}

	private static void BFS(int root) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(root);
		depth[root] = 0;
		parent[0][root] = 0;
		distance[root] = 0;

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

				distance[next] = distance[now] + distance_wire.get(now).get(toIdx);

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
				b = parent[i][b];
			}
		}

		if (a == b) {
			return a;
		}

		for (int i = MAX_D; i >= 0; i--) {
			if (parent[i][b] != parent[i][a]) {
				a = parent[i][a];
				b = parent[i][b];
			}
		}

		return parent[0][a];
	}
}
