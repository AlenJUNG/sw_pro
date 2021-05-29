package G01_dijkstra;

// 2021.05.29 PASS

import java.io.*;
import java.util.*;

public class G01_ex11_Adv_P0089_위대한항로 {
	static class Node implements Comparable<Node> {
		int v;
		long dis;

		public Node(int v, long dis) {
			this.v = v;
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

	static int TC, N, M, K;
	static int friend[];
	static final int INF = 2000000000 + 1; // E * cost
	static long D[];
	static ArrayList<Node> graph[];

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G01_dijkstra/G01_ex11_Adv_P0089_위대한항로.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			friend = new int[N + 1];
			D = new long[N + 1];
			graph = new ArrayList[N + 1];

			for (int i = 0; i <= N; i++) {
				graph[i] = new ArrayList<Node>();
				D[i] = INF;
			}

			// 역방향 dijkstra
			for (int i = 1; i <= M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				long cost = Integer.parseInt(st.nextToken());

				graph[to].add(new Node(from, cost));
			}

			// 식량 입력
			for (int i = 1; i <= K; i++) {
				st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken());
				int food = Integer.parseInt(st.nextToken());
				friend[num] = food;
			}

			// dijkstra 시작점을 N으로 한다
			dijkstra(graph, D, N);

			bw.write("#" + tc + " " + D[1] + "\n");

		}

		br.close();
		bw.flush();
		bw.close();

	}

	private static void dijkstra(ArrayList<Node>[] gra, long[] d, int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		d[start] = 0;
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node now = pq.poll();
			int now_node = now.v;
			long now_dis = now.dis;
			
			// 식량을 보급 받을 수 있는 것까지 고려하여 경로계산할 것
			if (d[now_node] < now_dis - friend[now_node]) {
				continue;
			}
	
			for (Node next : gra[now_node]) {
				int next_node = next.v;
				long dis = next.dis;
				
				// * 핵심코드 : 최적 경로가 발견되었을 때
				if (d[next_node] > d[now_node] + dis - friend[next_node]) {
					// 최적경로 값이 음수인 경우에는 '0'으로 처리함
					// why? 시작점에서 가져가야하는 식량 계산 가능을 위해
					if (d[now_node] + dis - friend[next_node] < 0) {
						d[next_node] = 0;
					// 그렇지 않으면 그냥 계산
					} else {
						d[next_node] = d[now_node] + dis - friend[next_node];
					}

					pq.offer(new Node(next_node, d[next_node]));
				}
			}
		}
	}
}
