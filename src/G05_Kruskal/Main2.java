package G05_Kruskal;

import java.util.*;
import java.io.*;

public class Main2 {
	static class Point {
		int idx, x, y, z;

		public Point(int idx, int x, int y, int z) {
			this.idx = idx;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

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

	static int N;
	static int parent[];
	static Point point[];
	static long ans;
	static PriorityQueue<Node> pq;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_ex12_100_2887_행성터널.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		point = new Point[N + 1];
		parent = new int[N + 1];
		pq = new PriorityQueue<>();

		int a, b, c;
		for (int i = 0; i <= N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			point[i] = new Point(i, a, b, c);
		}

		Arrays.sort(point, 0, N, new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				if (o1.x < o2.x) {
					return -1;
				}
				return 1;
			}
		});

		for (int i = 0; i <= N - 2; i++) {
			int value = Math.abs(point[i].x - point[i + 1].x);
			pq.add(new Node(point[i].idx, point[i + 1].idx, value));
		}

		//
		Arrays.sort(point, 0, N, new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				if (o1.y < o2.y) {
					return -1;
				}
				return 1;
			}
		});

		for (int i = 0; i <= N - 2; i++) {
			int value = Math.abs(point[i].y - point[i + 1].y);
			pq.add(new Node(point[i].idx, point[i + 1].idx, value));
		}

		Arrays.sort(point, 0, N, new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				if (o1.z < o2.z) {
					return -1;
				}
				return 1;
			}
		});

		for (int i = 0; i <= N - 2; i++) {
			int value = Math.abs(point[i].z - point[i + 1].z);
			pq.add(new Node(point[i].idx, point[i + 1].idx, value));
		}

		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		ans = 0;
		int mstCnt = 0;
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			if (mstCnt == N - 1) {
				break;
			}

			if (find(now.from) == find(now.to)) {
				continue;
			} else {
				union(now.from, now.to);
				ans += now.cost;
				mstCnt++;
			}
		}

		bw.write(ans + "\n");

		br.close();
		bw.flush();
		bw.close();
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a > b) {
			parent[a] = b;
		} else {
			parent[b] = a;
		}

	}

	private static int find(int x) {
		if (x == parent[x]) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

}
