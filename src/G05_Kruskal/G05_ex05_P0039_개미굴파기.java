package G05_Kruskal;

// 210227 �����, ���� �� Ǯ����

import java.io.*;
import java.util.*;

public class G05_ex05_P0039_���̱��ı� {
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
	static Node nodes[];	// graph �迭 ����� > ������� ��
	static ArrayList<Node> graph[];	// ���� �����
	static PriorityQueue<Node> pq;	// cost�� ���� ������ ������ �뵵
	
	static boolean isMSTedge[], visited[], finished[];
	static boolean isCycle;	

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_ex05_P0039_���̱��ı�.txt"));
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
				
				graph[from].add(new Node(from, to, cost));	// �迭 from ���ٰ� �ִ� �� ����
				graph[to].add(new Node(to, from, cost));		// �迭 to ���ٰ� �ִ� �� ����
			}
			
			int k = 0;
			while(!pq.isEmpty()) {
				nodes[k] = pq.poll();
				k+=1;
			}
			
			// �ʱ�ȭ
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
			
			// �ֽô��� ������ �׷���
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
				// cycle���� �ߺ��� ��� ������ ã�´�
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
			
			// Cycle�� �߰� �Ǿ���
			if(isCycle) {
				return;
			}
			
			// ���� ������ �� DFS üũ ���
			if(to == next.t) {
				continue;
			}
			
			// ����� ������ �ߺ��� MST
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
