package G06_LCA;
import java.io.*;
import java.util.*;

public class G06_ex04_Adv_100_15480_LCA客孽府 {
	static int MAX_N = 100000;
	static int MAX_D = 17;
	static int N, M, R, U, V;
	static int parent[][], depth[];
	static ArrayList<Integer> wire[];
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/G06_LCA/G06_ex04_Adv_100_15480_LCA客孽府.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		parent = new int[MAX_N + 1][MAX_D + 1];
		depth = new int[N+1];
		wire = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			wire[i] = new ArrayList<Integer>();
		}
		
		for(int i = 1; i <= N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			wire[a].add(b);
			wire[b].add(a);
		}
		
		BFS(1, 0);
		
		M = Integer.parseInt(br.readLine());
		for(int i = 1; i<= M; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st1.nextToken());
			U = Integer.parseInt(st1.nextToken());
			V = Integer.parseInt(st1.nextToken());
			
			int case1 = LCA(R, U);
			int case2 = LCA(V, U);
			int case3 = LCA(R, V);
			int ans = case1;
			
			if(depth[ans] < depth[case2]) {
				ans = case2;
			}
			if(depth[ans] < depth[case3]) {
				ans = case3;
			}
		
			
			bw.write(ans+"\n");
		}
		
		br.close();
		bw.flush();
		bw.close();

	}
	private static int LCA(int u, int v) {
		if(depth[u] > depth[v]) {
			int temp;
			temp = u;
			u = v;
			v = temp;
		}
		
		for(int i = MAX_D ; i >= 0 ; i--) {
			if(depth[v] - depth[u] >= Math.pow(2, i)) {
				v = parent[i][v];
			}
		}
		
		if(u == v) {
			return u;
		}
				
		for(int i = MAX_D ; i >= 0 ; i--) {
			if(parent[i][u] != parent[i][v]){
				u = parent[i][u];
				v = parent[i][v];
			}
		}
		
		return parent[0][u];
	}
	private static void BFS(int root, int dep) {
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.offer(root);
		parent[0][root] = dep;
		depth[root] = dep;
		
		while(!q.isEmpty()) {
			int v = q.poll();
			
			for(int next : wire[v]) {
				if(next == parent[0][v]) {
					continue;
				}
				q.offer(next);
				parent[0][next] = v;
				depth[next] = depth[v] + 1;
				
				for(int i = 1; i <= MAX_D + 1; i++) {
					if(parent[i-1][next] == 0) {
						break;
					}
					parent[i][next] = parent[i-1][parent[i-1][next]];
				}
			}
		}
		
	}

}