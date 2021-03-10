package G03_��������;

import java.util.*;
import java.io.*;

public class G03_ex02_P0071_����Ž�� {
	static int V, E, A;
	// INF�� LongMax�� �����ϸ� ����, Ʋ���Բ� ���� ����
	// ���� ���� E 3000 �� x �Ƿε� ��ġ 1000000 x 10 ���� ������
	static long INF = 300000000001L;
	static ArrayList<Node> graph;
	// �ִ� �Ƿε� E 3000 �� x �Ƿε� ��ġ 1000000 > long type ����
	static long cost[];
	static int angel[];
	
	static class Node{
		int f, t, c;
		Node(int f, int t, int c){
			this.f = f;	// from
			this.t = t;	// to
			this.c = c;	// cost
		}
	}

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/G03_��������/G03_ex02_P0071_����Ž��.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());	// ���
		E = Integer.parseInt(st.nextToken());	// ����
		A = Integer.parseInt(st.nextToken());	// õ��
		
		graph = new ArrayList<Node>();
		cost = new long[V];
		angel = new int[V];
		
		// �ִܰŸ� �ʱ�ȭ : �������� 0, �������� V - 1
		for(int i = 0; i < V; i++) {
			cost[i] = INF;	// �⺻ ���ø�
			angel[i] = 0;	// �� ���� ��������
		}
		
		// ����� �Ϲ� graph ����Ʈ ���� �Է�
		for(int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()); 
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			graph.add(new Node(from, to, dist));
			graph.add(new Node(to, from, dist));
		}
		
		// �ܹ��� angel graph ����Ʈ ���� �Է� > ��������
		for(int i = 1; i <= A; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()); 
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			graph.add(new Node(from, to, -dist));
		}
		
		/*********************** �Էº� ���� ***********************/ 
		
		// ��� ��尪 ����
		cost[0] = 0;
		
		// ��� ���� �� ��ŭ �ݺ�
		for(int i = 0; i < V; i++) {
			for(Node node : graph) {
				int from = node.f;
				int to = node.t;
				int c = node.c;
				
				// * ���� * �Ʒ� ���ø��� �ð����⵵�� �������� �ݵ�� �����ؾ���
//				if(cost[from] == INF) {
//					continue;
//				}
				// �Ϲ����� �������� ���ø� : �ش� ������ ���� cost�� �� ���� ���
				if(cost[from] + c < cost[to]) {	// ������ �ִ� ������ �ڵ����� ������ 
					cost[to] = cost[from] + c;	// cost �� ������Ʈ
					if(c < 0) {	// cost�� �����̸� angel ������Ʈ
						angel[to] = angel[from] + 1;
					}else {
						angel[to] = angel[from];
					}
				// * �� �κ� �� �ٽ� : �� ���� ���� ����*
				}else if(cost[to] == cost[from] + c) {
					if(c < 0) {
						angel[to] = Math.min(angel[to], angel[from] + 1);
					}else {
						angel[to] = Math.min(angel[to], angel[from]);
					}
				}
			}
		}
		
		// �� ���� �� cycle�� ������ �� ������ �Ǹ� ���� loop �����Ѵٰ� ��
		boolean iscycle = false;
		
		for(Node node : graph) {
			int from = node.f;
			int to = node.t;
			int c = node.c;
			
			if(cost[to] > cost[from] + c) {
				iscycle = true;
				break;
			}
		}
		
		if(iscycle) {
			System.out.println("BUG");
		// ���������� ���� �� �ִ� ������ ū ��� "No" > ������ �湮�� �� ���� ���
		}else if(cost[V - 1] > 100000000000L) { 
			System.out.println("No");
		}else {
			System.out.println(cost[V - 1] + " " + angel[V - 1]);
		}		
		
		
	}
}