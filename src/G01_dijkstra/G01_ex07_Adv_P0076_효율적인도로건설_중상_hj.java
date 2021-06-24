package G01_dijkstra;

// 2021.06.12 Ǯ�� ��, ���� Ǯ�� ����

import java.io.*;
import java.util.*;

public class G01_ex07_Adv_P0076_ȿ�����ε��ΰǼ�_�߻�_hj {
	static final int INF = 50000 * 10000 + 1;

	static class Node implements Comparable<Node> {
		int node, dis;

		public Node(int node, int dis) {
			this.node = node;
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

	static int TC, N, M, ans, D;
	static int D_to[], D_from[];
	static ArrayList<Node> graph_to[], graph_from[];

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G01_dijkstra/G01_ex07_Adv_P0076_ȿ�����ε��ΰǼ�_�߻�.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			D_to = new int[N + 1];
			D_from = new int[N + 1];

			graph_to = new ArrayList[N + 1];
			graph_from = new ArrayList[N + 1];

			for (int i = 0; i <= N; i++) {
				graph_to[i] = new ArrayList<Node>();
				graph_from[i] = new ArrayList<Node>();
				D_to[i] = INF;
				D_from[i] = INF;
			}

			int from, to, distance;
			for (int i = 1; i <= M; i++) {
				st = new StringTokenizer(br.readLine());
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				distance = Integer.parseInt(st.nextToken());

				graph_to[from].add(new Node(to, distance));
				graph_from[to].add(new Node(from, distance));
			}

			dijkstra(graph_to, D_to, 1, N);
			dijkstra(graph_from, D_from, N, 1);

			D = D_to[N];

			// �̺�Ž�� �غ�

			Arrays.sort(D_to);
			Arrays.sort(D_from);

			/*
			 * �ű� �ּҰŸ� = A + 1 + B < (����) �ּҰŸ� A + B < D - 1 B < D - 1 - A D_from[B] < D - 1
			 * - D_to[A]
			 */
			ans = 0;
			// *��� : a�� �������� �����ϴ� ���� �̺�Ž������ ������ �ż��ϰ� ã��
			// 1���� N - 1 ������ A�� ����̸� �������� ��
			for (int a = 1; a <= N - 1; a++) {
				// a�� 0���� ũ�� INF ���� ���� ���� ������
				if (0 < D_to[a] && D_to[a] < INF) {
					
					int base = D - 1 - D_to[a];
					if (base > 0) {
						int start = 1;
						int end = N - 1;
						int mid = 0;
						
						while (start < end) {
							mid = (start + end) / 2;
							if (D_from[mid] < base) {
								start = mid + 1;
							} else {
								end = mid;
							}
						}
						ans += end - 1;
					}
				}
			}

			bw.write("#" + tc + " " + ans + "\n");
		}

		br.close();
		bw.flush();
		bw.close();

	}

	private static void dijkstra(ArrayList<Node>[] gra, int[] d, int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		d[start] = 0;
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(now.node == end) {
				pq.clear();
				break;
			}
			
//			if (d[now.node] < now.dis) {
//				continue;
//			}

			for (Node next : gra[now.node]) {
				if (d[next.node] > d[now.node] + next.dis) {
					d[next.node] = d[now.node] + next.dis;
					pq.offer(new Node(next.node, d[next.node]));
				}
			}
		}

	}

}
/*
2
5 4
1 2 1
2 3 2
3 4 1
4 5 2
10 12
1 5 6
1 6 12
1 8 8
2 4 1
3 5 3
4 6 4
5 7 5
6 8 5
6 10 1
7 9 8
8 9 1
9 10 6
*/
