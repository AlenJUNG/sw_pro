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
		
		// �Ÿ�(���)�� ª�� ���� ���� �켱������ �������� ����
		@Override
		public int compareTo(Edge other) {
			if(this.distance < other.distance) {
				return -1;
			}
			return 1;
		}
	}
	
	// ����� ����(V)�� ����(Union ����)�� ����(E)
	// ����� ������ �ִ� 100,000����� ����
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
		
		// �θ� ���̺�󿡼� �θ� �ڱ� �ڽ����� �ʱ�ȭ
		for(int i = 1; i <= V; i++) {
			parent[i] = i;
		}
		
		// ��� ������ ���� ������ �Է� �ޱ�
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			edges.add(new Edge(cost, a, b));
		}
		
		// ������ ��� ������ ����
		Collections.sort(edges);
		
		// ������ �ϳ��� Ȯ���ϸ�
		for(int i = 0; i < edges.size(); i++) {
			int cost = edges.get(i).getDis();
			int a = edges.get(i).getNode_A();
			int b = edges.get(i).getNode_B();
			
			// ����Ŭ�� �߻����� �ʴ� ��쿡�� ���տ� ����
			if(findParent(a) != findParent(b)) {
				unionParent(a, b);
				result += cost;
			}
		}
		System.out.println(result);
	}

	// Ư�� ���Ұ� ���� ������ ã��
	private static int findParent(int x) {
		// ��Ʈ��尡 �ƴ϶�� ��Ʈ��带 ã�� ������ ��������� ȣ��
		if(x == parent[x]) {
			return x;
		}
		parent[x] = findParent(parent[x]);
		return parent[x]; 
	}
	
	// �� ���Ұ� ���� ������ ��ġ��
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