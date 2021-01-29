package test;

import java.io.*;
import java.util.*;

// N : 노드의 개수 <= 4만개
// M : 거리를 알고 싶은 노드 쌍의 개수 <= 만개
public class Main {
	static class Node{
		int from, to, dis;
		public Node(int from, int to, int dis) {
			this.from = from;
			this.to = to;
			this.dis = dis;
		}
	}
	static int MAX_N = 40000;
	static int MAX_D = 16;
	static int parent[][], depth[], distance[];
	static int N, M, ans;
	static ArrayList<Node> graph[];

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/test/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		depth = new int[N + 1];
		distance = new int[N + 1];
		parent = new int[MAX_D + 1][N + 1];
		
		graph = new ArrayList[N + 1];
		
		for(int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<Node>();
		}
		
		int f, t, c;
		for(int i = 1; i <= N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			f = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			graph[f].add(new Node(f, t, c));
			graph[t].add(new Node(t, f, c));
		}
		
		BFS(1);
		
		M = Integer.parseInt(br.readLine());
		
		ans = 0;
		int a, b, lca;
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			lca = LCA(a, b);
			
			ans = distance[a] + distance[b] - 2*distance[lca];
			bw.write(ans+" ");
			bw.newLine();
		}
		
		br.close();
		bw.flush();
		bw.close();
		
	}

	private static int LCA(int a, int b) {
		if(depth[a] > depth[b]) {
			int temp;
			temp = a;
			a = b;
			b = temp;
		}
		
		for(int i = MAX_D; i >= 0; i--) {
			if(depth[b] - depth[a] >= (1 << i)) {
				b = parent[i][b];
			}
		}
		
		if(a == b) {
			return a;
		}
		
		for(int i = MAX_D; i >= 0; i--) {
			if(parent[i][a] != parent[i][b]) {
				a = parent[i][a];
				b = parent[i][b];
			}
		}
		
		return parent[0][a];
	}

	private static void BFS(int start) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(0, start, 0));
		depth[start] = 0;
		parent[0][start] = 0;
		distance[start] = 0;
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			for(int toIdx = 0; toIdx < graph[now.to].size(); toIdx++) {
				Node next = graph[now.to].get(toIdx);
				
				if(next.to == now.from) {
					continue;
				}
				
				q.offer(new Node(next.from, next.to, next.dis));
				depth[next.to] = depth[now.to] + 1;
				parent[0][next.to] = now.to;
				distance[next.to] = distance[now.to] + next.dis;
				
				for(int i = 1; i <= MAX_D; i++) {
					if(parent[i-1][next.to] == 0) {
						break;
					}
					parent[i][next.to] = parent[i-1][parent[i-1][next.to]];
				}
				
			}
		}
		
		
	}
}
