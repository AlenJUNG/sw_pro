package G01_dijkstra;
// https://www.acmicpc.net/problem/1238

import java.util.*;
import java.io.*;

public class G01_ex03_Adv_100_1238_파티 {
	static class Node implements Comparable<Node> {
		int idx;
		int distance;

		public Node(int idx, int distance) {
			this.idx = idx;
			this.distance = distance;
		}

		public int getIdx() {
			return this.idx;
		}

		public int getDistance() {
			return this.distance;
		}

		@Override
		public int compareTo(Node others) {
			if (this.distance < others.distance) {
				return -1;
			}
			return 1;
		}
	}

	static final int INF = (int) 1e9;
	static int N, M, S;
	static ArrayList<ArrayList<Node>> f_Graph = new ArrayList<ArrayList<Node>>();
	static ArrayList<ArrayList<Node>> t_Graph = new ArrayList<ArrayList<Node>>();
	static int f_Dis[], t_Dis[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G01_dijkstra/G01_ex03_Adv_100_1238_파티.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= N; i++) {
			f_Graph.add(new ArrayList<Node>());
			t_Graph.add(new ArrayList<Node>());
		}

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());

			f_Graph.get(from).add(new Node(to, value));
			t_Graph.get(to).add(new Node(from, value));
		}

		f_Dis = new int[N + 1];
		t_Dis = new int[N + 1];

		Arrays.fill(f_Dis, INF);
		Arrays.fill(t_Dis, INF);

		dijkstra(S, f_Dis, f_Graph);
		dijkstra(S, t_Dis, t_Graph);

		int ans = Integer.MIN_VALUE;

		for (int i = 1; i <= N; i++) {
			if (i == S) {
				continue;
			}
			ans = Math.max(ans, f_Dis[i] + t_Dis[i]);
		}
		System.out.println(ans);
	}

	private static void dijkstra(int startN, int[] dis, ArrayList<ArrayList<Node>> graph) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(startN, 0));
		dis[startN] = 0;

		while (!pq.isEmpty()) {
			Node v = pq.poll();
			int now = v.getIdx();
			int cost = v.getDistance();

			if (dis[now] < cost) {
				continue;
			}

			for (int toIdx = 0; toIdx < graph.get(now).size(); toIdx++) {
				int value = dis[now] + graph.get(now).get(toIdx).getDistance();

				if (value < dis[graph.get(now).get(toIdx).getIdx()]) {
					dis[graph.get(now).get(toIdx).getIdx()] = value;
					pq.offer(new Node(graph.get(now).get(toIdx).getIdx(), graph.get(now).get(toIdx).getDistance()));
				}
			}
		}
	}
}
