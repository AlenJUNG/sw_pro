package G01_dijkstra;

import java.util.*;
import java.io.*;

// 기본 문제
public class G01_ex05_Adv_100_1753_최단경로_하 {
	static class Node implements Comparable<Node> {
		int idx, dis;

		public Node(int idx, int dis) {
			this.idx = idx;
			this.dis = dis;
		}

		public int getIdx() {
			return this.idx;
		}

		public int getDis() {
			return this.dis;
		}

		@Override
		public int compareTo(Node other) {
			if (this.dis < other.dis) {
				return -1;
			}
			return 1;
		}
	}

	static final int INF = 100000000;
	static int V, E, S;
	static int D[];
	static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();

	public static void main(String[] args) throws IOException {

		System.setIn(new FileInputStream("src/G01_dijkstra/G01_ex05_Adv_100_1753_최단경로_하.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(br.readLine());

		D = new int[V + 1];
		Arrays.fill(D, INF);

		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<Node>());
		}

		for (int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());

			graph.get(from).add(new Node(to, value));
		}

		dijkstra(S, graph, D);

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			if (D[i] == INF) {
				sb.append("INF" + "\n");
			} else {
				sb.append(D[i] + "\n");
			}
		}

		bw.write(sb.toString());

		bw.close();
		br.close();

	}

	private static void dijkstra(int start, ArrayList<ArrayList<Node>> gra, int[] d) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		d[start] = 0;

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int now_idx = node.getIdx();
			int now_dis = node.getDis();

			if (d[now_idx] < now_dis) {
				continue;
			}

			for (int toIdx = 0; toIdx < gra.get(now_idx).size(); toIdx++) {
				int temp = d[now_idx] + gra.get(now_idx).get(toIdx).getDis();
				if (d[gra.get(now_idx).get(toIdx).getIdx()] > temp) {
					d[gra.get(now_idx).get(toIdx).getIdx()] = temp;
					pq.offer(new Node(gra.get(now_idx).get(toIdx).getIdx(), temp));
				}
			}
		}
	}

	private static void dijkstra2(int start, ArrayList<ArrayList<Node>> gra, int[] d) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		d[start] = 0;
		boolean visited[] = new boolean[V + 1];

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int now_idx = node.getIdx();

			if (visited[now_idx]) {
				continue;
			}
			visited[now_idx] = true;

			for (Node toNode : gra.get(now_idx)) {
				if (d[toNode.idx] > d[now_idx] + toNode.dis) {
					d[toNode.idx] = d[now_idx] + toNode.dis;
					pq.offer(new Node(toNode.idx, d[toNode.idx]));
				}
			}
		}
	}
}