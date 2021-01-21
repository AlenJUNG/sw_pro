package G01_dijkstra;
// https://www.acmicpc.net/problem/1238

// 그림 참고 : https://pangtrue.tistory.com/272

import java.util.*;
import java.io.*;

public class G01_ex03_Adv_100_1238_파티 {
	static class Node implements Comparable<Node> {
		int idx, dis;

		public Node(int idx, int dis) {
			this.idx = idx;
			this.dis = dis;
		}

		@Override
		public int compareTo(Node other) {
			if (this.dis < other.dis) {
				return -1;
			}
			return 1;
		}
	}

	static final int INF = 1000001;
	static int N, M, X;
	static int to_D[], from_D[];
	static ArrayList<ArrayList<Node>> to_Graph = new ArrayList<ArrayList<Node>>();
	static ArrayList<ArrayList<Node>> from_Graph = new ArrayList<ArrayList<Node>>();
	static int ans;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G01_dijkstra/G01_ex03_Adv_100_1238_파티.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		to_D = new int[N + 1];
		from_D = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			to_Graph.add(new ArrayList<Node>());
			from_Graph.add(new ArrayList<Node>());
		}

		Arrays.fill(to_D, INF);
		Arrays.fill(from_D, INF);

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());

			to_Graph.get(from).add(new Node(to, distance));
			from_Graph.get(to).add(new Node(from, distance));
		}

		dijkstra(X, to_Graph, to_D);
		dijkstra(X, from_Graph, from_D);

		ans = 0;

		for (int i = 1; i <= N; i++) {
			ans = Math.max(ans, to_D[i] + from_D[i]);
		}

		System.out.println(ans);

	}

	private static void dijkstra(int start, ArrayList<ArrayList<Node>> gra, int[] d) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		d[start] = 0;
		boolean check[] = new boolean[N + 1];

		while (!pq.isEmpty()) {
			Node now = pq.poll();

			if (check[now.idx]) {
				continue;
			}

			check[now.idx] = true;

			for (Node next : gra.get(now.idx)) {
				if (d[next.idx] > d[now.idx] + next.dis) {
					d[next.idx] = d[now.idx] + next.dis;
					pq.offer(new Node(next.idx, d[next.idx]));
				}
			}
		}
	}
}
