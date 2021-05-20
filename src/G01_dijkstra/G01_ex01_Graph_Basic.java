package G01_dijkstra;

// Update 2021.05.21
// 배열의 인접 리스트 사용법

import java.io.*;
import java.util.*;

public class G01_ex01_Graph_Basic {
	static class Node{
		int f, t, c;
		public Node(int f, int t, int c) {
			this.f = f;
			this.t = t;
			this.c = c;
		}
	}
	static int TC, N, M, X;
	static ArrayList<Node> graph[];	// 배열 자체를 Int가 아닌 ArrayList로 선언

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/G01_dijkstra/G01_ex03_Adv_100_1238_파티.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		// * 클래스형 변수 배열의 경우 초기값이 null 이기 때문에 반드시 사용전에 할당 필요
		// 1. 배열에 ArrayList 사이즈 할당
		graph = new ArrayList[N + 1];
		// 2. 0 ~ N 각 배열에 리스트 생성
		for(int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<Node>();
		}
		
		int from, to, value;
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			value = Integer.parseInt(st.nextToken());
			// 3. 각 배열은 시작점을 의미하고 해당 배열에 저장된 리스트는 도착점을 의미
			graph[from].add(new Node(from, to, value));
		}
		
		System.out.println("Check");
				
	}

}
