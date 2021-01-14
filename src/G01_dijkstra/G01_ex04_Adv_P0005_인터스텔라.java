package G01_dijkstra;

import java.util.*;
import java.io.*;

public class G01_ex04_Adv_P0005_인터스텔라 {
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

	static final long INF = 200000000000L;
	static int N, M, B;
	static int S = 1;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
	static long dis[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G01_dijkstra/G01_ex04_Adv_P0005_인터스텔라.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Node>());
		}

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());

			graph.get(from).add(new Node(to, value));
			graph.get(to).add(new Node(from, value));
		}

		dis = new long[N + 1];

		Arrays.fill(dis, INF);

		dijkstra(S, graph, dis);

		for (int i = 1; i <= N; i++) {
			System.out.println(dis[i]);
		}

	}

	private static void dijkstra(int startN, ArrayList<ArrayList<Node>> gra, long[] d) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.offer(new Node(startN, 0));
		d[startN] = 0;

		while (!pq.isEmpty()) {
			Node node = pq.poll();

			int now_idx = node.getIdx();
			int now_dis = node.getDistance();

			if (d[now_idx] < now_dis) {
				continue;
			}

			if (now_idx == N) { // 문제특
				return;
			}

			for (int toIdx = 0; toIdx < gra.get(now_idx).size(); toIdx++) {
				long temp = d[now_idx] + gra.get(now_idx).get(toIdx).getDistance();

				if (temp < d[gra.get(now_idx).get(toIdx).getIdx()]) {
					d[gra.get(now_idx).get(toIdx).getIdx()] = temp;
					pq.offer(new Node(gra.get(now_idx).get(toIdx).getIdx(), gra.get(now_idx).get(toIdx).getDistance()));
				}
			}
		}
	}
}