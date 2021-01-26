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

		// 1. 배열 초기값 넣어줌
		root = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			root[i] = i; // root[i] = 1로 해서 오류남
		}

		graph = new ArrayList<Node>();

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph.add(new Node(f, t, c)); // 양방향 간선 확인
			graph.add(new Node(t, f, c));
		}

		// 2. cost 오름차순 정렬
		Collections.sort(graph);

		ans = 0;

		for (int i = 0; i < graph.size(); i++) {
			int from = graph.get(i).from;
			int to = graph.get(i).to;
			int cost = graph.get(i).cost;

			// 사이클이 발생하지 않는 경우만(서로소일 때) 간선을 이어줌
			// 간선을 이어주면서 cost의 값을 구할 수 있음
			// 최적의 MST 값
			if (findRoot(from) != findRoot(to)) {
				Union(from, to);
				System.out.println(cost);
				ans += cost;
			}
		}

		System.out.println(ans);

	}

	private static int findRoot(int x) {
		// x 가 루트 조상인 경우 x 리턴
		if (x == root[x]) {
			return x;
			// x 가 루트 조상이 아닌경우 root[x]가 루트조상이 될 때까지 재귀
		} else {
			root[x] = findRoot(root[x]);
			return root[x];
		}

	}

	private static void Union(int a, int b) {
		a = findRoot(a);
		b = findRoot(b);

		if (a < b) { // b의 조상이 더 크면
			root[b] = a; // b의 조상에 작은 값을 씌움
		} else { // a의 조상이 더 크면
			root[a] = b; // a의 조상에 작은 값을 씌움
		}

	}

}
