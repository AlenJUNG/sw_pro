package test;

import java.io.*;
import java.util.*;

public class Main3 {
	static class Node implements Comparable<Node> {
		int start;
		int end;
		long cost;

		public Node(int start, int end, long cost) {
			this.start = start;
			this.end = end;
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

	static final long INF = 1000000000001L;	// ���� ���� = Edge * Cost
	static int TC;
	static int N, E, C, K, M;
	static ArrayList<ArrayList<Node>> graph;
	static long D1[], D2[];
	static int center1[], center2[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/test/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {

			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken()); // ��� ��
			E = Integer.parseInt(st.nextToken()); // ���� ��
			C = Integer.parseInt(st.nextToken()); // ������� ��
			K = Integer.parseInt(st.nextToken()); // ������� + ������ ��
			M = Integer.parseInt(st.nextToken()); // ������ͺ� �����
			
			graph = new ArrayList<ArrayList<Node>>();
			for (int i = 0; i <= N; i++) {
				graph.add(new ArrayList<Node>());
			}

			D1 = new long[N + 1];
			Arrays.fill(D1, INF);
			center1 = new int[N + 1]; // 1��° ���ͽ�Ʈ�� �� ���Ǵ� ������忡�� �İߵ� �����Ͼ� ������� ����

			int from, to, cost;

			for (int i = 1; i <= E; i++) {
				st = new StringTokenizer(br.readLine());
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				cost = Integer.parseInt(st.nextToken());

				graph.get(from).add(new Node(from, to, cost));	// ����� �Է� ����
				graph.get(to).add(new Node(to, from, cost));	// ����� �Է� ����
			}

			dijkstra(0, D1, center1); // 1�� ° ���ͽ�Ʈ��
			
			
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
			System.out.println("#" + t + " " + ans);
		}
		br.close();
	}
	

	// * k�� ���� : 1��° ���� �ÿ��� ��������� 2��° ���ͽÿ��� �����Ͼ� �ʰ����� ���͸� ���� ������ ���
	private static void dijkstra(int k, long[] d, int[] center) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean check[] = new boolean[N + 1];

		// ������� ��带 ���������� �� ���� ���� �� ���ͽ�Ʈ�� ����
		for (int i = 1; i <= C; i++) {
			if (i != k) {
				pq.offer(new Node(0, i, 0)); // * �������� 0���� setting
				d[i] = 0;
			}
		}				
		
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			
			if (check[now.end]) {
				continue;
			}

			check[now.end] = true;

			for (Node next : graph.get(now.end)) {
				if (d[next.end] > d[now.end] + next.cost) {
					d[next.end] = d[now.end] + next.cost;
					pq.offer(new Node(next.start, next.end, d[next.end]));

					// �湮�� �������� �����Ͼ�� ��� "�������"���� �Դ��� ����ϴ� ����
					int start = next.start; // ��� �Դ�? ��������� ��߳�� �Է�

					if (next.start > C) { // ��߳�尡 ������Ͱ� �ƴ϶��
						start = center[now.end]; // ��߳���� ������ start�� �Է�
					}
					// ������尡 ��� ������ͷκ��� �Դ��� �Է�
					center[next.end] = start;
				}
			}
		}		
	}
}
