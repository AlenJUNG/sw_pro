package G05_Kruskal;

import java.util.*;
import java.io.*;

public class G05_Kruskal {
	static class Edge implements Comparable<Edge>{
		int distance;
		int node_A;
		int node_B;
		
		public Edge(int distance, int node_A, int node_B) {
			this.distance = distance;
			this.node_A = node_A;
			this.node_B = node_B;
		}
		
		public int getDis() {
			return this.distance;
		}
		
		public int getNode_A() {
			return this.node_A;
		}
		
		public int getNode_B() {
			return this.node_B;
		}
		
		// 거리(비용)이 짧은 것이 높은 우선순위를 가지도록 설정
		@Override
		public int compareTo(Edge other) {
			if(this.distance < other.distance) {
				return -1;
			}
			return 1;
		}
	}
	
	// 노드의 개수(V)와 간선(Union 연산)의 개수(E)
	// 노드의 개수는 최대 100,000개라고 가정
	static int V, E;
	static int parent[];
	static ArrayList<Edge> edges;
	static int result = 0;
	
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_Kruskal.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		parent = new int[100001];
		edges = new ArrayList<Edge>();
		
		// 부모 테이블상에서 부모를 자기 자신으로 초기화
		for(int i = 1; i <= V; i++) {
			parent[i] = i;
		}
		
		// 모든 간선에 대한 정보를 입력 받기
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			edges.add(new Edge(cost, a, b));
		}
		
		// 간선을 비용 순으로 정렬
		Collections.sort(edges);
		
		// 간선을 하나씩 확인하며
		for(int i = 0; i < edges.size(); i++) {
			int cost = edges.get(i).getDis();
			int a = edges.get(i).getNode_A();
			int b = edges.get(i).getNode_B();
			
			// 사이클이 발생하지 않는 경우에만 집합에 포함
			if(findParent(a) != findParent(b)) {
				unionParent(a, b);
				result += cost;
			}
		}
		System.out.println(result);
	}

	// 특정 원소가 속한 집합을 찾기
	private static int findParent(int x) {
		// 루트노드가 아니라면 루트노드를 찾을 때까지 재귀적으로 호출
		if(x == parent[x]) {
			return x;
		}
		parent[x] = findParent(parent[x]);
		return parent[x]; 
	}
	
	// 두 원소가 속한 집합을 합치기
	private static void unionParent(int a, int b) {
		a = findParent(a);
		b = findParent(b);
		
		if(a < b) {
			parent[b] = a;
		}else {
			parent[a] = b;
		}	
	}
}