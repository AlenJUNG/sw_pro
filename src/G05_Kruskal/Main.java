package G05_Kruskal;

import java.util.*;
import java.io.*;

public class Main {
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

	static int N, M, ans;
	static int parent[];
	static ArrayList<Node> al;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_Kruskal.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];
		al = new ArrayList<Node>();

		int a, b, value;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			value = Integer.parseInt(st.nextToken());
			al.add(new Node(a, b, value));
			al.add(new Node(b, a, value));
		}

		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		Collections.sort(al);

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
		
		for(Node now : al) {
			
		}
		
		bw.write(ans+"\n");

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
