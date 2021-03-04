package G05_Kruskal;

import java.io.*;
import java.util.*;

// * 입력은 여러 개의 테스트 케이스로 구분되어 있다.
// https://www.acmicpc.net/problem/6497

public class G05_ex11_100_6497_전력난 {
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

	static int N, M, total, ans;
	static int parent[];
	static PriorityQueue<Node> pq;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_ex11_100_6497_전력난.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			if(N == 0 & M == 0) {
				break;
			}
	
			parent = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				parent[i] = i;
			}
	
			pq = new PriorityQueue<Node>();
	
			int a = 0;
			int b = 0;
			int x = 0;
			
			for(int i = 1; i <= M; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				x = Integer.parseInt(st.nextToken());
				
				pq.offer(new Node(a, b, x));
			}
	
			total = 0;
			ans = 0;
	
			while (!pq.isEmpty()) {
				Node now = pq.poll();
				total += now.c;
	
				if (find(now.f) == find(now.t)) {
					continue;
				} else {
					union(now.f, now.t);
					ans += now.c;
				}
			}
	
			bw.write(total - ans + "\n");
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
