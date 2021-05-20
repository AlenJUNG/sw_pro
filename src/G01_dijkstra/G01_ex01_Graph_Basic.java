package G01_dijkstra;

// Update 2021.05.21
// �迭�� ���� ����Ʈ ����

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
	static ArrayList<Node> graph[];	// �迭 ��ü�� Int�� �ƴ� ArrayList�� ����

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/G01_dijkstra/G01_ex03_Adv_100_1238_��Ƽ.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		// * Ŭ������ ���� �迭�� ��� �ʱⰪ�� null �̱� ������ �ݵ�� ������� �Ҵ� �ʿ�
		// 1. �迭�� ArrayList ������ �Ҵ�
		graph = new ArrayList[N + 1];
		// 2. 0 ~ N �� �迭�� ����Ʈ ����
		for(int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<Node>();
		}
		
		int from, to, value;
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			value = Integer.parseInt(st.nextToken());
			// 3. �� �迭�� �������� �ǹ��ϰ� �ش� �迭�� ����� ����Ʈ�� �������� �ǹ�
			graph[from].add(new Node(from, to, value));
		}
		
		System.out.println("Check");
				
	}

}
