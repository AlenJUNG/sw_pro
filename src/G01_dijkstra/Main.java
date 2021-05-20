package G01_dijkstra;

// Update 2021.05.20 Update : G01_ex03_Adv_100_1238_��Ƽ

import java.util.*;
import java.io.*;

public class Main {
	// dijkstra ����� ��� �� ���� ��ȣ���� �̾Ƴ��� ���� ���� > PQ
	static class Node implements Comparable<Node> {
		int f, t, c;

		public Node(int f, int t, int c) {
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
	// MAX�� = �ִ밣�� ��(10000) * �ִ� �Ÿ�(100) + 1
	static final int INF = 1000001;

	static int D_to[], D_from[];
	static ArrayList<ArrayList<Node>> gra_to, gra_from;
	static int N, M, X, ans;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G01_dijkstra/G01_ex03_Adv_100_1238_��Ƽ.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		/*
		 * X ���忡�� �����غ� �� 
		 * 1. �׷��� �ܹ��� 2�� ���� 
		 * 2. �Ÿ� �迭�� 2�� ���� 
		 * 3. �Ÿ� �迭�� INF �ֱ� 
		 * 4. dijkstra * �޼ҵ� �ۼ� = PQ ����� ���� dijkstra 
		 * 5. d[] max �� ã�Ƽ� �� �ֱ�
		 */

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		// �Ÿ� ���� �� INF �ʱ�ȭ
		D_to = new int[N + 1];
		D_from = new int[N + 1];

		Arrays.fill(D_to, INF);
		Arrays.fill(D_from, INF);

		gra_to = new ArrayList<ArrayList<Node>>();
		gra_from = new ArrayList<ArrayList<Node>>();

		for (int i = 0; i <= N; i++) {
			gra_to.add(new ArrayList<Node>());
			gra_from.add(new ArrayList<Node>());
		}
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			// ���������� �Է� : X > ���ư��� �� ã��
			gra_from.get(from).add(new Node(from, to, cost));
			// �������� �Է� : X > �� ���� �� ã��
			gra_to.get(to).add(new Node(to, from, cost));
		}

		diskstra(gra_from, D_from, X);
		diskstra(gra_to, D_to, X);

		ans = 0;
		for (int i = 1; i <= N; i++) {
			if (i == X) {
				continue;
			}
			ans = Math.max(ans, D_to[i] + D_from[i]);
		}

		bw.write(ans + "\n");
		
		br.close();
		bw.flush();
		bw.close();

	}

	private static void diskstra(ArrayList<ArrayList<Node>> gra, int[] d, int x) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean visited[];
		visited = new boolean[N + 1];
		// �������� x �Է�
		pq.offer(new Node(0, x, 0));
		// �������� �ʱ�Ÿ��� 0 �Է� > �������� �̹� INF ó���Ǿ� ����
		d[x] = 0;

		while (!pq.isEmpty()) {
			// pq�⿡ ����� ��尡 �������� ������ ����
			Node now = pq.poll();
			int now_node = now.t;
			
			// �ߺ�üũ
			if (visited[now_node]) {
				continue;
			}
			visited[now_node] = true;
			
			// gra.get(now_node) ��ü�� ���� ����� ��� ���� = next
			for (Node next : gra.get(now_node)) {
				int next_node = next.t; // ���� ����ȣ
				int cost = next.c; // ���� ������ �Ÿ�
				// * diskstra �ּҰŸ� ���ϱ� : �߿�
				if (d[next_node] > d[now_node] + cost) {
					d[next_node] = d[now_node] + cost;
					pq.offer(new Node(now_node, next_node, d[next_node]));
				}
			}
		}

	}

}
