package G01_dijkstra;

import java.util.*;
import java.io.*;

public class G01_ex04_Adv_P0005_인터스텔라_hj {
	static class Node implements Comparable<Node>{
		int node;
		long cost;
		int k;
		public Node(int node, long cost, int k) {
			this.node = node;
			this.cost = cost;
			this.k = k;
		}
		@Override
		public int compareTo(Node other) {
			if(this.cost < other.cost) {
				return -1;
			}
			return 1;
		}		
	}
	static int N, M, K, TC, start, end;
	static long D[][], ans;
	static final long INF = 20000000001L;
	static ArrayList<Node> graph[];
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/G01_dijkstra/G01_ex04_Adv_P0005_인터스텔라.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		TC = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			D = new long[3][N + 1];
			graph = new ArrayList[N + 1];
			for(int i = 0; i <= N; i++) {
				graph[i] = new ArrayList<Node>();
				for(int j = 0 ; j <= 2; j++) {
					D[j][i] = INF;
				}
			}
			
			int from, to, value;
			for(int i = 1; i <= M; i++) {
				st = new StringTokenizer(br.readLine());
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				value = Integer.parseInt(st.nextToken());
				
				graph[from].add(new Node(to, value, 0));
			}
			
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken()); 
			end = Integer.parseInt(st.nextToken());
			
			dijkstra(D, graph, start, end);
			
			
		}
		
		br.close();
		bw.flush();
		bw.close();

	}

	private static void dijkstra(long[][] d, ArrayList<Node>[] gra, int s, int e) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		d[0][s] = 0;
		// node, cost, k : 초기 k는 2
		pq.offer(new Node(s, 0, 2));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			int now_node = now.node;
			long now_dis	= now.cost;
			
			if(d[0][now_node] < now_dis) {
				continue;
			}
			
			for(Node next : gra[now_node]) {
				int next_node = next.node;
				long next_dis = next.cost;
				
				for(int i = 0; i <= 2; i++) {
					if(d[i][next_node] > d[i][now_node] + next_dis) {
						d[i][next_node] = d[i][now_node] + next_dis;
						pq.offer(new Node(next_node, d[i][next_node]));
					}
				}				
			}
		}
		
	}

}
