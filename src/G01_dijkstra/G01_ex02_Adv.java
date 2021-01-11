package G01_dijkstra;

import java.util.*;
import java.io.*;

public class G01_ex02_Adv {
	static class Node implements Comparable<Node> {
		int idx;
		int dis;

		public Node(int idx, int dis) {
			this.idx = idx;
			this.dis = dis;
		}

		public int getIdx() {
			return this.idx;
		}

		public int getDistance() {
			return this.dis;
		}

		// �Ÿ�(���)�� ª�� ���� ���� �켱������ �������� ����
		@Override
		public int compareTo(Node other) {
			if (this.dis < other.dis) {
				return -1;
			}
			return 1;
		}

	}

	static final int INF = (int) 1e9; // ������ �ǹ��ϴ� ������ 10���� ����
	// ����� ����(N), ������ ����(M), ���� ��� ��ȣ(Start)
	// ����� ������ �ִ� 100,000����� ����
	static int N, M, start;
	// Array ���� 01 : �� ��忡 ����Ǿ� �ִ� ��忡 ���� ������ ��� �迭
	static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
	static int distance[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G01_dijkstra/G01_ex02_Adv.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Node>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());

			graph.get(from).add(new Node(to, value));
		}

		distance = new int[N + 1];

		Arrays.fill(distance, INF);

		dijkstra(start, graph, distance);

		// ��� ���� ���� ���� �ִ� �Ÿ��� ���
		for (int i = 1; i <= N; i++) {
			// ������ �� ���� ���, ����(INFINITY)�̶�� ���
			if (distance[i] == INF) {
				System.out.println("INFINITY");
			}
			// ������ �� �ִ� ��� �Ÿ��� ���
			else {
				System.out.printf("start node(%d) to node(%d) = %d\n", start, i, distance[i]);
			}
		}

	}

	private static void dijkstra(int start, ArrayList<ArrayList<Node>> gra, int d[]) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		// ���� ���� ���� ���� �ִ� ��δ� 0���� �����Ͽ�, ť�� ����
		pq.offer(new Node(start, 0));
		distance[start] = 0;

		while (!pq.isEmpty()) { // ť�� ������� �ʴٸ�
			// ���� �ִ� �Ÿ��� ª�� ��忡 ���� ���� ������
			Node node = pq.poll();
			int now_idx = node.getIdx(); // ���� ���
			int now_dis = node.getDistance(); // ���� �������� ���
			// ���� ��尡 �̹� ó���� ���� �ִ� ����� ����
			if (d[now_idx] < now_dis) {
				continue;
			}
			// ���� ���� ����� �ٸ� ������ ������ Ȯ��
			for (int toIdx = 0; toIdx < gra.get(now_idx).size(); toIdx++) {
				int temp = d[now_idx] + gra.get(now_idx).get(toIdx).getDistance();
				// ���� ��带 ���ļ�, �ٸ� ���� �̵��ϴ� �Ÿ��� �� ª�� ���
				if (temp < d[gra.get(now_idx).get(toIdx).getIdx()]) {
					d[gra.get(now_idx).get(toIdx).getIdx()] = temp;
					pq.offer(new Node(gra.get(now_idx).get(toIdx).getIdx(), temp));
				}
			}
		}
	}

}
