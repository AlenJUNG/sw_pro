package G05_Kruskal;

import java.io.*;
import java.util.*;

/*
 * Update : 210708, try : 1
 * MST 기본 문제
 */

public class G05_ex00_100_1922_네트워크연결 {
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
			} else if (this.cost == other.cost) {
				if (this.from <= other.from) {
					return -1;
				} else {
					return 1;
				}
			} else {
				return 1;
			}
		}
	}

	static long ans;
	static int N, M, parent[];
	static PriorityQueue<Node> pq;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_ex00_100_1922_네트워크연결.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
//		double T1 = System.currentTimeMillis();
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		pq = new PriorityQueue<>();

		int f, t, v;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			f = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());

			pq.offer(new Node(f, t, v));
			pq.offer(new Node(t, f, v));
		}

		ans = 0;
		int cntEdge = 0;
		while (!pq.isEmpty()) {
			if (cntEdge == N - 1) {
				break;
			}
			Node now = pq.poll();

			int a = now.from;
			int b = now.to;
			int value = now.cost;

			if (find(a) == find(b)) {
				continue;
			} else {
				union(a, b);
				ans += value;
				cntEdge++;
			}
		}

		bw.write(ans + "\n");
//		double T2 = System.currentTimeMillis();
//		System.out.println((T2 - T1)/1000);
		
		br.close();
		bw.flush();
		bw.close();
		
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a > b) {
			parent[a] = b;
		}else {
			parent[b] = a;
		}
		
	}

	private static int find(int x) {
		if(x == parent[x]) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

}
