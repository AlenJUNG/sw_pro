package G03_벨만포드;

import java.util.*;
import java.io.*;

public class G03_ex02_P0071_던전탐험 {
	static int V, E, A;
	// INF를 LongMax로 설정하면 터짐, 틀리게끔 문제 나옴
	// 길의 개수 E 3000 개 x 피로도 수치 1000000 x 10 으로 설정함
	static long INF = 300000000001L;
	static ArrayList<Node> graph;
	// 최대 피로도 E 3000 개 x 피로도 수치 1000000 > long type 설정
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
		System.setIn(new FileInputStream("src/G03_벨만포드/G03_ex02_P0071_던전탐험.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());	// 노드
		E = Integer.parseInt(st.nextToken());	// 간선
		A = Integer.parseInt(st.nextToken());	// 천사
		
		graph = new ArrayList<Node>();
		cost = new long[V];
		angel = new int[V];
		
		// 최단거리 초기화 : 시작정점 0, 도착정점 V - 1
		for(int i = 0; i < V; i++) {
			cost[i] = INF;	// 기본 템플릿
			angel[i] = 0;	// 이 문제 고유조건
		}
		
		// 양방향 일반 graph 리스트 정보 입력
		for(int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()); 
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			graph.add(new Node(from, to, dist));
			graph.add(new Node(to, from, dist));
		}
		
		// 단방향 angel graph 리스트 정보 입력 > 고유조건
		for(int i = 1; i <= A; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()); 
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			graph.add(new Node(from, to, -dist));
		}
		
		/*********************** 입력부 종료 ***********************/ 
		
		// 출발 노드값 설정
		cost[0] = 0;
		
		// 모든 정점 수 많큼 반복
		for(int i = 0; i < V; i++) {
			for(Node node : graph) {
				int from = node.f;
				int to = node.t;
				int c = node.c;
				
				// * 주의 * 아래 템플릿은 시간복잡도를 증가시켜 반드시 제거해야함
//				if(cost[from] == INF) {
//					continue;
//				}
				// 일반적인 벨만포드 템플릿 : 해당 간선을 통한 cost가 더 작은 경우
				if(cost[from] + c < cost[to]) {	// 간선이 있는 조건은 자동으로 만족됌 
					cost[to] = cost[from] + c;	// cost 값 업데이트
					if(c < 0) {	// cost가 음수이면 angel 업데이트
						angel[to] = angel[from] + 1;
					}else {
						angel[to] = angel[from];
					}
				// * 이 부분 이 핵심 : 본 문제 고유 조건*
				}else if(cost[to] == cost[from] + c) {
					if(c < 0) {
						angel[to] = Math.min(angel[to], angel[from] + 1);
					}else {
						angel[to] = Math.min(angel[to], angel[from]);
					}
				}
			}
		}
		
		// 위 종료 후 cycle을 돌렸을 때 갱신이 되면 무한 loop 존재한다고 봄
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
		// 도착지점이 가질 수 있는 값보다 큰 경우 "No" > 간선을 방문할 수 없는 경우
		}else if(cost[V - 1] > 100000000000L) { 
			System.out.println("No");
		}else {
			System.out.println(cost[V - 1] + " " + angel[V - 1]);
		}		
		
		
	}
}