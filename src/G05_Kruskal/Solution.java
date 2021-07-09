package G05_Kruskal;

import java.io.*;
import java.util.*;

/*
 * 일시 : 2021.07.09
 * 시도 : 2
 * 잠자는사이에
 */

public class Solution {
	static class Node {
		int id, from, to;

		public Node(int id, int from, int to) {
			this.id = id;
			this.from = from;
			this.to = to;
		}
	}

	static int TC, N;
	static int in_degree[], parent[], ans_from, ans_to;
	static ArrayList<Node> al;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_ex13_모의P0001_잠자는사이에.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());

			parent = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				parent[i] = i;
			}

			in_degree = new int[N + 1];
			al = new ArrayList<Node>();

			int a, b;
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				in_degree[b]++;
				al.add(new Node(i, a, b));
			}

			ans_from = 0;
			ans_to = 0;

			int from, to;
			for (int i = 0; i <= al.size() - 1; i++) {
				from = al.get(i).from;
				to = al.get(i).to;

				if (in_degree[to] != 2) {
					if (find(from) == find(to)) {
						ans_from = from;
						ans_to = to;
						continue;
					} else {
						union(from, to);
					}
				}
			}

			for (int i = 0; i <= al.size() - 1; i++) {
				from = al.get(i).from;
				to = al.get(i).to;

				if (in_degree[to] == 2) {
					if (find(from) == find(to)) {
						ans_from = from;
						ans_to = to;
						continue;
					} else {
						union(from, to);
					}
				}
			}

			bw.write("#" + tc + " " + ans_from + " " + ans_to + "\n");
		}

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
