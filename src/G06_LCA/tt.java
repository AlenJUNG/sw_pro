package G06_LCA;
// 왜 패스가 안되지? 확인 210201

import java.io.*;
import java.util.*;

public class tt {

	static int MAX_N = 100000; // 노드 수
	static int MAX_D = 17; // 2^MAX_D > MAX_N 미리 계산해서 넣자
	static int N, M;
	static int parent[][]; // parent[MAX_D][MAX_N]
	static int depth[];
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G06_LCA/G06_ex02_Adv_100_11438_LCA.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		parent = new int[MAX_D + 1][N + 1];
		depth = new int[N + 1];
		
		graph = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		int from, to; 
		for(int i = 1; i <= N - 1 ; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			graph.get(from).add(to);
			graph.get(to).add(from);
		}
		
		BFS(1);
		
		M = Integer.parseInt(br.readLine());
		
		int a, b, x;
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			x = LCA(a, b);
			bw.write(x+"\n");
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
		
		for(int i = MAX_D; i >= 0 ; i--) {
			if(depth[b] - depth[a] >= (1 << i)) {
				b = parent[i][b];
			}
		}
		
		if(a == b) {			return a;
		}
		
		for(int i = MAX_D; i >= 0 ; i--) {
			if(parent[i][a] != parent[i][b]) {
				a = parent[i][a];
				b = parent[i][b];
			}
		}
		
		return parent[0][a];
	}

	private static void BFS(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		depth[start] = 0;
		parent[0][start] = 0;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int next : graph.get(now)) {
				if(next == parent[0][now]) {
					continue;
				}
				q.offer(next);
				depth[next] = depth[now] + 1;
				parent[0][next] = now;
				
				for(int i = 1; i <= MAX_D; i++) {
					if(parent[i-1][next] == 0) {
						parent[i][next] = parent[i-1][parent[i-1][next]];
					}
				}
			}
		}
		
	}
}



























