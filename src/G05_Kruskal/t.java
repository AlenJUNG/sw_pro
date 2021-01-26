package G05_Kruskal;

import java.util.*;
import java.io.*;

public class t {
	static class Node implements Comparable<Node> {
		int from, to, cost;

		public Node(int from, int to, int cost) {
			this.from = from;
			this.to = to;
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

	static int N, M;
	static int root[];
	static ArrayList<Node> graph;
	static int ans;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_Kruskal.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 1. �迭 �ʱⰪ �־���
		root = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			root[i] = i; // root[i] = 1�� �ؼ� ������
		}

		graph = new ArrayList<Node>();

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph.add(new Node(f, t, c)); // ����� ���� Ȯ��
			graph.add(new Node(t, f, c));
		}

		// 2. cost �������� ����
		Collections.sort(graph);

		ans = 0;

		for (int i = 0; i < graph.size(); i++) {
			int from = graph.get(i).from;
			int to = graph.get(i).to;
			int cost = graph.get(i).cost;

			// ����Ŭ�� �߻����� �ʴ� ��츸(���μ��� ��) ������ �̾���
			// ������ �̾��ָ鼭 cost�� ���� ���� �� ����
			// ������ MST ��
			if (findRoot(from) != findRoot(to)) {
				Union(from, to);
				System.out.println(cost);
				ans += cost;
			}
		}

		System.out.println(ans);

	}

	private static int findRoot(int x) {
		// x �� ��Ʈ ������ ��� x ����
		if (x == root[x]) {
			return x;
			// x �� ��Ʈ ������ �ƴѰ�� root[x]�� ��Ʈ������ �� ������ ���
		} else {
			root[x] = findRoot(root[x]);
			return root[x];
		}

	}

	private static void Union(int a, int b) {
		a = findRoot(a);
		b = findRoot(b);

		if (a < b) { // b�� ������ �� ũ��
			root[b] = a; // b�� ���� ���� ���� ����
		} else { // a�� ������ �� ũ��
			root[a] = b; // a�� ���� ���� ���� ����
		}

	}

}
