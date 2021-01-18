package test;

import java.io.*;
import java.util.*;

public class Main {
	static int MAX_N = 100000;
	static int MAX_D = 17;
	static int N, M, R, U, V;
	static int parent[][], depth[];
	static ArrayList<Integer> wire[];

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/test/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		parent = new int[MAX_D + 1][MAX_N + 1];
		depth = new int[N + 1];

		wire = new ArrayList[N + 1];
		for (int i = 1; i <= N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			wire[from].add(to);
			wire[to].add(from);
		}

		BFS(1);
		
		M = Integer.parseInt(br.readLine());
		for(int i = 1; i<= M; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st1.nextToken());
			U = Integer.parseInt(st1.nextToken());
			V = Integer.parseInt(st1.nextToken());
			
			int case1 = LCA(R, U);
		}
	}

	private static int LCA(int a, int b) {
		if(a > b) {
			int temp;
			temp = a;
			a = b;
			b = temp;
		}
		
		for(int i = MAX_D; i >=0 ; i--) {
			if(depth[b] - depth[a] >= Math.pow(2, i)) {
				b = parent[i][b];
			}
		}
		
		if(a == b) {
			return a;
		}
		
		for(int i = MAX_D; i >=0 ; i--) {
			if(parent[i][b] != parent[i][a]) {
				b = parent[i][b];
				a = parent[i][a];
			}
		}
		
		return parent[0][b];
	}

	private static void BFS(int root) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(root);
		parent[0][root] = 0;
		depth[root] = 0;

		while (!q.isEmpty()) {
			int v = q.poll();

			for (int next : wire[v]) {
				if (next == parent[0][v]) {
					continue;
				}

				q.offer(next);
				parent[0][next] = v;
				depth[next] = depth[v] + 1;

				for (int i = 1; i <= MAX_D + 1; i++) {
					if (parent[i - 1][next] == 0) {
						break;
					}
					parent[i][next] = parent[i - 1][parent[i - 1][next]];
				}

			}
		}

	}

}
