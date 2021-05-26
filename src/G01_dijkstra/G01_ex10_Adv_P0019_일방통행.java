package G01_dijkstra;

package G01_ex10_Adv_P0019_일방통행;

import java.io.*;
import java.util.*;

public class G01_ex10_Adv_P0019_일방통행 {
	static class Node implements Comparable<Node> {
		int f, t;
		long c;

		public Node(int f, int t, long c) {
			this.f = f;
			this.t = t;
			this.c = c;

		}

		@Override
		public int compareTo(Node other) {
			if (this.c < other.c) {
				return -1;
			}
			return 1;
		}
	}

//	static final long INF = 30000000000001L;
	static final long INF = 2500000000001L;
	static int TC, N, M, type[];
	static long D[], ans;
	static ArrayList<Node> graph[];

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G01_ex10_Adv_P0019_일방통행/G01_ex10_Adv_P0019_일방통행.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			D = new long[N + 1];
			graph = new ArrayList[N + 1];
			type = new int[N + 1];

			for (int i = 0; i <= N; i++) {
				graph[i] = new ArrayList<Node>();
				D[i] = INF;
			}

//			StringTokenizer id_st = new StringTokenizer(br.readLine());

			String id_st = br.readLine();
			for (int i = 1; i <= N; i++) {
				char temp = id_st.charAt(i - 1);
				if (temp == 'R') {
					type[i] = 1;
				} else if (temp == 'B') {
					type[i] = 2;
				} else if (temp == 'W') {
					type[i] = 3;
				} else {
					type[i] = 4;
				}
			}

			int from, to;
			long cost;
			for (int i = 1; i <= M; i++) {
				st = new StringTokenizer(br.readLine());

				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				cost = Long.parseLong(st.nextToken());

				graph[from].add(new Node(from, to, cost));
			}

			dijkstra(D, graph);
			
			if(D[N] == INF) {
				bw.write("#" + tc + " " + -1 + "\n");
			}else {
				bw.write("#" + tc + " " + D[N] + "\n");
			}
			
		}

		br.close();
		bw.flush();
		bw.close();

	}

	private static void dijkstra(long[] d, ArrayList<Node>[] gra) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean visited[] = new boolean[N + 1];
		
		d[1] = 0;
		pq.add(new Node(0, 1, 0));
		
		while(!pq.isEmpty()) {
			
			Node now = pq.poll();
			System.out.println(now.t);
			
			
		}
		
	}

	private static void dijkstra1(long[] d, ArrayList<Node>[] gra, int x) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		boolean visited[] = new boolean[N + 1];

		d[x] = 0;
		pq.offer(new Node(0, x, 0));

		int red_card = 0;
		int blue_card = 0;
		int next_node = 0;

		while (!pq.isEmpty()) {
			Node now = pq.poll();
			


//			if (type[now.t] == 1) {
//				if (red_card == 0) {
//					continue;
//				} else {
//					red_card = 0;
//				}
//			}
//
//			if (type[now.t] == 2) {
//				if (blue_card == 0) {
//					continue;
//				} else {
//					blue_card = 0;
//				}
//			}

			for (Node next : gra[now.t]) {
				next_node = next.t;
				long value = next.c;
				if (d[next_node] > d[now.t] + value) {
					if (type[next_node] == 3) {
						red_card = 1;
						blue_card = 1;
					}
					d[next_node] = d[now.t] + value;
					pq.offer(new Node(now.t, next_node, value));
				}
			}
		}
	}
}

/*
3
6 9
RBWBGB
1 2 3
2 5 1
3 1 2
2 3 7
2 4 5
5 4 1
4 3 1
4 6 10
3 4 4
3 2
RBB
1 2 10
2 3 10
5 5
BRWBG
4 5 3
1 2 1
3 2 6
2 3 5
2 4 2
*/

