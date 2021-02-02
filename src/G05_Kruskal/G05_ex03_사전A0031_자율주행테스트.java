package G05_Kruskal;

import java.util.*;
import java.io.*;

public class G05_ex03_사전A0031_자율주행테스트 {
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

	static int TC, N, M, ans, start, end;
	static PriorityQueue<Node> graph, temp;
	static int parent[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_ex03_사전A0031_자율주행테스트.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			parent = new int[N + 1];
			graph = new PriorityQueue<>();
			temp = new PriorityQueue<>();

			int a, b, s;
			for (int i = 1; i <= M; i++) {
				st = new StringTokenizer(br.readLine());

				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				s = Integer.parseInt(st.nextToken());

				graph.offer(new Node(a, b, s));
				graph.offer(new Node(b, a, s));

				temp.offer(new Node(a, b, s));
				temp.offer(new Node(b, a, s));

			}

			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());

			for (int i = 1; i <= N; i++) {
				parent[i] = i;
			}

			ans = 1000000000; // 가능한 최대 - 최소 + 1 값
			int min = 0;
			int max = 0;
			
			while (!graph.isEmpty()) {
				Node now = graph.poll();
				min = now.cost;

				for (int i = 1; i <= N; i++) {
					parent[i] = i;
				}

				while (!temp.isEmpty()) {
					Node test = temp.poll();

					if (test.cost < min) {
						continue;
					}

					if (find(test.from) == find(test.to)) {
						continue;
					} else {
						union(test.from, test.to);
					}

					if (find(start) == find(end)) {
						max = test.cost;
						ans = Math.min(ans, max - min);
						break;
					}
				}
			}

			bw.write("#" + tc + " " + ans + "\n");

		}

		br.close();
		bw.flush();
		br.close();
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a > b) {
			parent[a] = parent[b];
		} else {
			parent[b] = parent[a];
		}

	}

	private static int find(int x) {
		if (x == parent[x]) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

}
