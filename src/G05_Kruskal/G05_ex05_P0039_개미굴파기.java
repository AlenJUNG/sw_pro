package G05_Kruskal;

// 210227 어려움, 현재 못 풀었음

import java.io.*;
import java.util.*;

public class G05_ex05_P0039_개미굴파기 {
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
	static int TC, N, ans;
	static int parent[];
	static Node nodes[];	// graph 배열 세우기 > 순서대로 들어감
	static ArrayList<Node> graph[];	// 간선 연결용
	static PriorityQueue<Node> pq;	// cost가 작은 값부터 빼려는 용도
	
	static boolean isMSTedge[], visited[], finished[];
	static boolean isCycle;	

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_ex05_P0039_개미굴파기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		TC = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= TC; t++) {
			
			N = Integer.parseInt(br.readLine());
			
			isMSTedge = new boolean[N+1];
			visited = new boolean[N+1];
			finished = new boolean[N+1];
			
			nodes = new Node[N+1];
			parent = new int[N + 1];
			graph = new ArrayList[N + 1];
			pq = new PriorityQueue<Node>();
			
			for(int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<Node>();
			}
			
			int from, to, cost;
			for(int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				cost = Integer.parseInt(st.nextToken());
				
				pq.offer(new Node(from, to, cost));
				
				graph[from].add(new Node(from, to, cost));	// 배열 from 에다가 넣는 것 주의
				graph[to].add(new Node(to, from, cost));		// 배열 to 에다가 넣는 것 주의
			}
			
			int k = 0;
			while(!pq.isEmpty()) {
				nodes[k] = pq.poll();
				k+=1;
			}
			
			// 초기화
			for(int i = 1; i <= N; i++) {
				parent[i] = i;
			}
			
			// MST
			long MSTcost = doMST();
			
			int s = 0;
			int e = 0;
			int c = 0;
			boolean isDisConn = false;
			int disConnCnt = 0;
			
			// 애시당초 단절된 그래프
			for(int i = 1; i <= N; i++) {
				if(isMSTedge[i] == false) {
					s = nodes[i].f;
					e = nodes[i].t;
					c = nodes[i].c;
					disConnCnt++;
					
					if(disConnCnt > 1) {
						isDisConn = true;
						break;
					}
				}
			}
			
			ans = 0;
			
			if(!isDisConn) {
				// cycle에서 중복된 비용 간선을 찾는다
				DFS(s, e, c);
			}
			
			bw.write("#" + t + " " + ans);
			bw.newLine();
			
		}

		br.close();
		bw.flush();
		bw.close();
	}


	private static void DFS(int from, int to, int c) {
		visited[from] = true;
		
		for(int i = 0; i < graph[from].size(); i++) {
			Node next = graph[from].get(i);
			
			// Cycle이 발견 되었다
			if(isCycle) {
				return;
			}
			
			// 무향 간선일 때 DFS 체크 요건
			if(to == next.t) {
				continue;
			}
			
			// 비용이 같으면 중복된 MST
			if(c == next.c) {
				ans++;
			}
			
			if(visited[next.t] == false) {
				DFS(next.t, from, c);
				if(c == next.c && isCycle == false) {
					ans--;
				}
			}else if(finished[next.t] == false) {
				isCycle = true;
				return;
			}
		}
		
		finished[from] = true;
		
	}


	private static long doMST() {
		long cost = 0;
		int edgeCnt = 0;
		
		for(int i = 1; i <= N; i++) {
			Node now = nodes[i];
			
			if(findRoot(now.f) == findRoot(now.t)) {
				continue;
			}else {
				union(now.f, now.t);
				cost += now.c;
				edgeCnt++;
				isMSTedge[i] = true;
			}
			
			if(edgeCnt == N - 1) {
				break;
			}
		}
		
		return cost;
		
	}


	private static void union(int a, int b) {
		a = findRoot(a);
		b = findRoot(b);
		
		if(a < b) {
			parent[b] = a;
		}else {
			parent[a] = b;
		}		
	}


	private static int findRoot(int x) {
		if(x == parent[x]) {
			return x;
		}
		return parent[x] = findRoot(parent[x]);
	}

}
