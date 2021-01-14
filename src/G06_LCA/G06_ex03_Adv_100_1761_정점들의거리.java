package G06_LCA;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G06_ex03_Adv_100_1761_정점들의거리 {
	static int N, M;
	static int parent[], depth[], distance[];
	static ArrayList<Integer> wire[];
	static ArrayList<Integer> distance_wire[];
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/G06_LCA/G06_ex03_Adv_100_1761_정점들의거리.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		parent = new int[N + 1];
		depth = new int[N + 1];
		distance = new int[N + 1];
		wire = new ArrayList[N + 1];
		distance_wire = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++) {
			wire[i] = new ArrayList<Integer>();
			distance_wire[i] = new ArrayList<Integer>();
		}
		
		for(int i = 1; i <= N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			wire[a].add(b);
			wire[b].add(a);
			
			distance_wire[a].add(c);	// 마지막에 거리 추가
			distance_wire[b].add(c);	// 마지막에 거리 추가
		}
		
		BFS(1, 0, 0);
		
//		System.out.println(distance[7]);
		
		M = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= M; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st1.nextToken());
			int b = Integer.parseInt(st1.nextToken());
			
			int lca = LCA(a, b);
			int ans = distance[a] + distance[b] - 2*distance[lca];
			//a ~ b까지의 거리 = a까지 거리 + b까지의 거리 - 2 * (LCA까지의 거리)
			
			bw.write(ans + "\n");
			
		}
		
		br.close();
		bw.flush();
		bw.close();

	}

	private static int LCA(int a, int b) {
		while(depth[a] != depth[b]) {
			if(depth[a] > depth[b]) {
				a = parent[a];				
			}else {
				b = parent[b];
			}
		}
		
		if(a == b) {
			return a;
		}
		
		while(a != b) {
			a = parent[a];
			b = parent[b];
		}
		
		return a;
	}

	private static void BFS(int root, int dep, int dis) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(root);
		parent[root] = 0;
		depth[root] = 0;
		distance[root] = 0;
		
		while(!q.isEmpty()) {
			int v = q.poll();
			
			for(int i = 0; i < wire[v].size(); i++) {
				int next = wire[v].get(i);
				if(next == parent[v]) {
					continue;
				}				
				q.offer(next);
				parent[next] = v;
				depth[next] = depth[v] + 1;
				distance[next] = distance[v] + distance_wire[v].get(i);
			}			
		}
		
	}

}

















