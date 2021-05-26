package G01_dijkstra;

import java.io.*;
import java.util.*;

/* 길 지나는데 걸리는 최대시간 : 100
 * 인원 : 1000
 * 길 : 10000
 */

public class Main2 {
	static class Node implements Comparable<Node>{
		int f, t, c;
		public Node(int f, int t, int c) {
			this.f = f;
			this.t = t;
			this.c = c;
		}
		@Override
		public int compareTo(Node other) {
			if(this.c < other.c) {
				return -1;
			}
			return 1;
		}
	}
	static final int INF = 2000000002;
	static int N, M, X;
	static int D_from[], D_to[];
	static ArrayList<Node> graph_from[], graph_to[];
	static int ans;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/G01_dijkstra/G01_ex03_Adv_100_1238_파티.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		D_from = new int[N + 1];
		D_to = new int[N + 1];
		
		graph_from = new ArrayList[N + 1];
		graph_to = new ArrayList[N + 1];
		
		for(int i = 0; i <= N; i++) {
			graph_from[i] = new ArrayList<Node>();
			graph_to[i] = new ArrayList<Node>();
			
			D_from[i] = INF;
			D_to[i] = INF;
		}
		
		int from, to, cost;
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			
			graph_from[from].add(new Node(from, to, cost));
			graph_to[to].add(new Node)
		}
		
		
		br.close();
		bw.flush();
		bw.close();

	}

}
