package G05_Kruskal;

import java.util.*;
import java.io.*;

public class Main {
	static class Node implements Comparable<Node> {
		int node, cost;

		public Node(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node other) {
			if (this.cost < other.cost) {
				return -1;
			}
			return 1;
		}
	}

	static int N, M, dis[][];
	static long ans;
	static ArrayList<Node> graph[];
	static final int INF = 10000 + 1;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_ex00_100_1922_네트워크연결.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		dis = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				dis[i][j] = dis[j][i] = INF;
			}
		}

		graph = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<Node>();
		}

		int f, t, c;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			f = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			graph[f].add(new Node(t, c));
			graph[t].add(new Node(f, c));
		}

		ans = 0;
		prim();

		bw.write(ans + "\n");

		br.close();
		bw.flush();
		bw.close();

	}

	private static void prim() {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		boolean visit[];
		visit = new boolean[N + 1];
		int visitCnt = 0;
		pq.offer(new Node(1, 0)); // 첫시작 1하고 연결된 노드들 중

		while (!pq.isEmpty()) {
			Node now = pq.poll();

			if (visit[now.node]) {
				continue;
			}
			visit[now.node] = true;

			ans += now.cost;
			visitCnt++;
			
			for (Node next : graph[now.node]) {
				if (visit[next.node] == false && dis[now.node][next.node] > next.cost) {
					dis[now.node][next.node] = next.cost;
					pq.offer(new Node(next.node, dis[now.node][next.node]));
				}
			}
		}

	}
}
