package G01_dijkstra;

import java.io.*;
import java.util.*;

public class G01_ex06_Adv_����A0030_����������_�� {
	static class Edge implements Comparable<Edge> {
		int start;
		int end;
		long cost; // cost�� long���� ������ ��

		public Edge(int start, int end, long cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge other) {
			if (this.cost < other.cost) {
				return -1;
			}
			return 1;
		}
	}

	static int N, E, C, K, M;
	static ArrayList<Edge> path[];
	static long D1[], D2[];
	static int center1[], center2[];
	static long INF = 500000000001L;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G01_dijkstra/G01_ex06_Adv_����A0030_����������_��.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int TC = Integer.parseInt(br.readLine());
		StringTokenizer st;

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // ��� �� (N)
			E = Integer.parseInt(st.nextToken()); // ���� �� (E)
			C = Integer.parseInt(st.nextToken()); // ��������� �� (C)
			K = Integer.parseInt(st.nextToken()); // ������� + ������ �� (K)
			M = Integer.parseInt(st.nextToken()); // ������ͺ� ����� �� (M)

			path = new ArrayList[N + 1];
			D1 = new long[N + 1];
			center1 = new int[N + 1];	// ù ��° ���� ���� �迭

			for (int i = 0; i <= N; i++) {
				path[i] = new ArrayList<Edge>();
			}
			Arrays.fill(D1, INF);

			int x, y, c;
			for (int i = 1; i <= E; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				
				// ����� �׷��� ���� ����
				path[x].add(new Edge(x, y, c));
				path[y].add(new Edge(y, x, c));
			}

			dijkstra(-1, D1, center1); // 1��° ���ͽ�Ʈ��

			int cntCenter[] = new int[C + 1];
			int max = 0;
			int maxCenter = 0;
			long ans = 0;

			for (int i = C + 1; i <= K; i++) { // �������� ����� �� üũ
				cntCenter[center1[i]]++;
				ans += D1[i];
				if (max < cntCenter[center1[i]]) {
					max = cntCenter[center1[i]];
					maxCenter = center1[i]; // �ʰ� �����Ͼ� ���� ã��
				}
			}

			if (max > 0) { // �ִ� �����Ͼ� ���� �ѱ� ���
				D2 = new long[N + 1];
				center2 = new int[N + 1];
				Arrays.fill(D2, INF);
				// �ι� ° ���ͽ�Ʈ�� (�ʰ� �����Ͼ� ���� ����)
				dijkstra(maxCenter, D2, center2);

				// ��
				long diff[] = new long[cntCenter[maxCenter]];
				int cnt = 0;
				for (int i = C + 1; i <= K; i++) {
					if (center1[i] == maxCenter) {
						// ù �ּҰ��� �� ��° �ּҰ� ���̰��
						diff[cnt++] = Math.abs(D2[i] - D1[i]);
					}
				}

				Arrays.sort(diff);
				long mi = 0;
				for (int i = 0; i < max - M; i++) {
					mi += diff[i]; // ���̸�ŭ ����
				}
				ans += mi;
			}
			System.out.println("#" + tc + " " + ans);
		}
	}

	private static void dijkstra(int k, long[] d, int[] center) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean check[] = new boolean[N + 1];
		
		for (int i = 1; i <= C; i++) {	// i ������� ���
			if (i != k) {	// ������� ��尡 
				pq.add(new Edge(0, i, 0));
				d[i] = 0;
			}
		}

		while (!pq.isEmpty()) {
			Edge curE = pq.poll();
			int cur = curE.end;
			if (check[cur]) {
				continue;
			}
			check[cur] = true;

			for (Edge e : path[cur]) {
				if (d[e.end] > d[cur] + e.cost) {
					d[e.end] = d[cur] + e.cost;
					pq.add(new Edge(e.start, e.end, d[e.end]));
					int start = e.start;
					if (e.start > C) {
						start = center[cur];
					}
					center[e.end] = start;
				}
			}
		}

	}

}