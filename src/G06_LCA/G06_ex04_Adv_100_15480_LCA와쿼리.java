package G06_LCA;

import java.io.*;
import java.util.*;

/* 쿼리별 LCA 규칙 찾기가 이 문제의 Key
 * BFS(1)을 고정으로 두고
 * 가장 깊은 LCA가 답
 */

public class G06_ex04_Adv_100_15480_LCA와쿼리 {
	// r u v : start, a , b

	static int MAX_N = 100000;
	static int MAX_D = 17;
	static int M, N, ans;
	static int parent[][], depth[];
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G06_LCA/G06_ex04_Adv_100_15480_LCA와쿼리.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		depth = new int[N + 1];
		parent = new int[MAX_D + 1][N + 1];
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

		M = Integer.parseInt(br.readLine());

		int R, U, V;
		for (int i = 1; i <= M; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st1.nextToken());
			U = Integer.parseInt(st1.nextToken());
			V = Integer.parseInt(st1.nextToken());

			// 3가지 케이스 중에 가장 깊은 LCA를 찾아라
			int case1 = LCA(R, U);
			int case2 = LCA(V, U);
			int case3 = LCA(R, V);
			int ans = case1;

			if (depth[ans] < depth[case2]) {
				ans = case2;
			}
			if (depth[ans] < depth[case3]) {
				ans = case3;
			}

			bw.write(ans + "\n");
		}

		br.close();
		bw.flush();
		bw.close();

	}

	private static int LCA(int a, int b) {
		if (depth[a] > depth[b]) {
			int temp;
			temp = a;
			a = b;
			b = temp;
		}

		for (int i = MAX_D; i >= 0; i--) {
			if (depth[b] - depth[a] >= (1 << i)) {
				b = parent[i][b];
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

	private static void BFS(int start) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(start);
		depth[start] = 0;
		parent[0][start] = 0;

		while (!q.isEmpty()) {
			int now = q.poll();

			for (int toIdx = 0; toIdx < graph.get(now).size(); toIdx++) {
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

}
