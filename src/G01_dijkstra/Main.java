package G01_dijkstra;

// Update 2021.05.20 Update : G01_ex03_Adv_100_1238_파티

import java.util.*;
import java.io.*;

public class Main {
	// dijkstra 연결된 노드 중 낮은 번호부터 뽑아내기 위해 정렬 > PQ
	static class Node implements Comparable<Node> {
		int f, t, c;

		public Node(int f, int t, int c) {
			this.f = f;
			this.t = t;
			this.c = c;
		}

		@Override
		public int compareTo(Node other) {
			if (this.c < other.c) {
				return -1;
			}
			return 1;
		}
	}
	// MAX값 = 최대간선 수(10000) * 최대 거리(100) + 1
	static final int INF = 1000001;

	static int D_to[], D_from[];
	static ArrayList<ArrayList<Node>> gra_to, gra_from;
	static int N, M, X, ans;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G01_dijkstra/G01_ex03_Adv_100_1238_파티.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		/*
		 * X 입장에서 생각해볼 것 
		 * 1. 그래프 단방향 2개 생성 
		 * 2. 거리 배열도 2개 생성 
		 * 3. 거리 배열에 INF 넣기 
		 * 4. dijkstra * 메소드 작성 = PQ 사용한 향상된 dijkstra 
		 * 5. d[] max 값 찾아서 답 넣기
		 */

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		// 거리 선언 후 INF 초기화
		D_to = new int[N + 1];
		D_from = new int[N + 1];

		Arrays.fill(D_to, INF);
		Arrays.fill(D_from, INF);

		gra_to = new ArrayList<ArrayList<Node>>();
		gra_from = new ArrayList<ArrayList<Node>>();

		for (int i = 0; i <= N; i++) {
			gra_to.add(new ArrayList<Node>());
			gra_from.add(new ArrayList<Node>());
		}
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			// 정상적으로 입력 : X > 돌아가는 길 찾기
			gra_from.get(from).add(new Node(from, to, cost));
			// 역순으로 입력 : X > 로 오는 길 찾기
			gra_to.get(to).add(new Node(to, from, cost));
		}

		diskstra(gra_from, D_from, X);
		diskstra(gra_to, D_to, X);

		ans = 0;
		for (int i = 1; i <= N; i++) {
			if (i == X) {
				continue;
			}
			ans = Math.max(ans, D_to[i] + D_from[i]);
		}

		bw.write(ans + "\n");
		
		br.close();
		bw.flush();
		bw.close();

	}

	private static void diskstra(ArrayList<ArrayList<Node>> gra, int[] d, int x) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean visited[];
		visited = new boolean[N + 1];
		// 시작지점 x 입력
		pq.offer(new Node(0, x, 0));
		// 시작지점 초기거리값 0 입력 > 나머지는 이미 INF 처리되어 있음
		d[x] = 0;

		while (!pq.isEmpty()) {
			// pq기에 연결된 노드가 오름차순 순으로 나옴
			Node now = pq.poll();
			int now_node = now.t;
			
			// 중복체크
			if (visited[now_node]) {
				continue;
			}
			visited[now_node] = true;
			
			// gra.get(now_node) 자체가 노드며 연결된 모든 노드들 = next
			for (Node next : gra.get(now_node)) {
				int next_node = next.t; // 다음 노드번호
				int cost = next.c; // 다음 노드까지 거리
				// * diskstra 최소거리 구하기 : 중요
				if (d[next_node] > d[now_node] + cost) {
					d[next_node] = d[now_node] + cost;
					pq.offer(new Node(now_node, next_node, d[next_node]));
				}
			}
		}

	}

}
