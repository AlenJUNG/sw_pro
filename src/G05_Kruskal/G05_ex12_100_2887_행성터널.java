package G05_Kruskal;

/* https://www.acmicpc.net/problem/2887
 * 해설 : https://steady-coding.tistory.com/117
 * 핵심 : 모든 행성의 (x, y, z) 한번에 보지 말고, x, y, z 좌표를 각각 오름차순 정렬해서 그 좌표만을 가지고 행성 간의 비용을 구하기
 * 주요 : 기준별 정렬하기
 */

import java.util.*;
import java.io.*;

public class G05_ex12_100_2887_행성터널 {
	static class Point {
		int num, x, y, z;

		Point(int num, int x, int y, int z) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

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

	static ArrayList<Node> al;
	static int N, ans;
	static int parent[];
	static Point point[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_ex12_100_2887_행성터널.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		point = new Point[N + 1];

		int x, y, z;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());

			point[i] = new Point(i, x, y, z);
		}

		al = new ArrayList<Node>();

		Arrays.sort(point, 0, N, new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				if (o1.x < o2.x) {
					return -1;
				}
				return 1;
			}
		});

		for (int i = 0; i < N - 1; i++) {
			int cost = Math.abs(point[i].x - point[i + 1].x);
			al.add(new Node(point[i].num, point[i + 1].num, cost));
		}

		Arrays.sort(point, 0, N, new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				if (o1.y < o2.y) {
					return -1;
				}
				return 1;
			}
		});

		for (int i = 0; i < N - 1; i++) {
			int cost = Math.abs(point[i].y - point[i + 1].y);
			al.add(new Node(point[i].num, point[i + 1].num, cost));
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

		for (int i = 0; i < N - 1; i++) {
			int cost = Math.abs(point[i].z - point[i + 1].z);
			al.add(new Node(point[i].num, point[i + 1].num, cost));
		}

		Collections.sort(al);

		// 부모 초기화
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		ans = 0;
		for (int i = 0; i < al.size(); i++) {
			Node now = al.get(i);

			if (find(now.f) == find(now.t)) {
				continue;
			} else {
				union(now.f, now.t);
				ans += now.c;
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
