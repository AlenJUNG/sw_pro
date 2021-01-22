package test;

import java.io.*;
import java.util.*;


public class Main {
	static int MAX_N = 100000;
	static int MAX_D = 17;
	static int N, M;
	static int parent[][];
	static int depth[];
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/test/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		
		parent = new int[MAX_D + 1][MAX_N + 1];
		depth = new int[N + 1];
		
		graph = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		/********************** 입력부 끝 **********************/
		
		BFS(1);
		
		M = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			System.out.println(LCA(a, b));
		}
	
		br.close();
	}

	

	private static void BFS(int root) {
		Queue<Integer> q = new LinkedList<>();
		
		q.offer(root);
		parent[0][root]	= 0;
		depth[root]	 = 0;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int next : graph.get(now)) {
				if(next == parent[0][now]) {
					continue;
				}
				
				q.offer(next);
				parent[0][next] = now;
				depth[next] = depth[now] + 1;
				
				for(int i = 1; i <= MAX_D; i++) {
					if(parent[i - 1][next] == 0) {
						break;
					}
					parent[i][next] = parent[i - 1][parent[i - 1][next]];
				}
			}
		}
	}
	
	private static int LCA(int a, int b) {
		if(a > b) {
			int temp;
			temp = a;
			a = b;
			b = temp;
		}
		
		for(int i = MAX_D; i >= 0; i--) {
			if(depth[b] - depth[a] >= Math.pow(2, i)) {
				b = parent[i][b];
			}
		}
		
		if(a == b) {
			return a;
		}
		
		for(int i = MAX_D; i >= 0; i--) {
			if(parent[i][b] != parent[i][a]) {
				a = parent[i][a];
				b = parent[i][b];
			}
		}
		
		return parent[0][a];
	}
	
}
















