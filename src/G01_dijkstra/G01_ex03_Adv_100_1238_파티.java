package G01_dijkstra;
// https://www.acmicpc.net/problem/1238

// 그림 참고 : https://pangtrue.tistory.com/272

import java.util.*;
import java.io.*;

public class G01_ex03_Adv_100_1238_파티 {
	// pq 처리를 위한 Comparable 사용 : 인접노드 idx, dis 변수 설정
	static class Node implements Comparable<Node> {
		int idx, dis;

		public Node(int idx, int dis) {
			this.idx = idx;
			this.dis = dis;
		}

		@Override
		public int compareTo(Node other) {
			if (this.dis < other.dis) {
				return -1;
			}
			return 1;
		}
	}

	static final int INF = 1000001;	// 최대값 : N * M + 1
	static int N, M, X;
	static int to_D[], from_D[];
	static ArrayList<ArrayList<Node>> to_Graph = new ArrayList<ArrayList<Node>>();
	static ArrayList<ArrayList<Node>> from_Graph = new ArrayList<ArrayList<Node>>();
	static int ans;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G01_dijkstra/G01_ex03_Adv_100_1238_파티.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		// 거리 배열 선언 및 INF 처리
		to_D = new int[N + 1];
		from_D = new int[N + 1];
		Arrays.fill(to_D, INF);
		Arrays.fill(from_D, INF);
		
		// List 생성
		for (int i = 0; i <= N; i++) {
			to_Graph.add(new ArrayList<Node>());
			from_Graph.add(new ArrayList<Node>());
		}		

		// 인접 List 입력
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());

			to_Graph.get(from).add(new Node(to, distance));
			from_Graph.get(to).add(new Node(from, distance));
		}

		dijkstra(X, to_Graph, to_D);
		dijkstra(X, from_Graph, from_D);

		ans = 0;

		for (int i = 1; i <= N; i++) {
			ans = Math.max(ans, to_D[i] + from_D[i]);
		}

		System.out.println(ans);

	}

	private static void dijkstra(int start, ArrayList<ArrayList<Node>> gra, int[] d) {
		// pq 선언 및 시작점 입력
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		// 시작점 거리 0 입력, 시작시점에 나머지는 INF가 들어가있음
		d[start] = 0;
		// 방문처리를 위한 check 배열 입력
		boolean check[] = new boolean[N + 1];

		while (!pq.isEmpty()) {
			// 인접리스트 꺼내면서 확인
			Node now = pq.poll();
			
			// 방문확인 및 미방문 노드 방문처리
			if (check[now.idx]) {
				continue;
			}
			check[now.idx] = true;
			
			// * now.idx 노드의 인접노드 완전탐색
			for (Node next : gra.get(now.idx)) {
				// 인접노드간 거리가 가장 가까운 노드를 찾아 계속하여 최단거리 테이블 갱신
				if (d[next.idx] > d[now.idx] + next.dis) {
					d[next.idx] = d[now.idx] + next.dis;
					pq.offer(new Node(next.idx, d[next.idx]));
				}
			}
		}
	}
}
