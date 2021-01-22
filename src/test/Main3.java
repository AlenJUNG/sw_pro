package test;

import java.io.*;
import java.util.*;

public class Main3 {
	static class Node implements Comparable<Node> {
		int start;
		int end;
		long cost;

		public Node(int start, int end, long cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node other) {
			if (this.cost < other.cost) {
				return -1;
			}
			return 1;
		}

	}

	static final long INF = 1000000000001L;	// 개수 주의 = Edge * Cost
	static int TC;
	static int N, E, C, K, M;
	static ArrayList<ArrayList<Node>> graph;
	static long D1[], D2[];
	static int center1[], center2[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/test/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {

			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken()); // 노드 수
			E = Integer.parseInt(st.nextToken()); // 간선 수
			C = Integer.parseInt(st.nextToken()); // 기술센터 수
			K = Integer.parseInt(st.nextToken()); // 기술센터 + 기지국 수
			M = Integer.parseInt(st.nextToken()); // 기술센터별 기술자
			
			graph = new ArrayList<ArrayList<Node>>();
			for (int i = 0; i <= N; i++) {
				graph.add(new ArrayList<Node>());
			}

			D1 = new long[N + 1];
			Arrays.fill(D1, INF);
			center1 = new int[N + 1]; // 1번째 다익스트라 시 사용되는 도착노드에서 파견된 엔지니어 기술센터 정보

			int from, to, cost;

			for (int i = 1; i <= E; i++) {
				st = new StringTokenizer(br.readLine());
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				cost = Integer.parseInt(st.nextToken());

				graph.get(from).add(new Node(from, to, cost));	// 양방향 입력 주의
				graph.get(to).add(new Node(to, from, cost));	// 양방향 입력 주의
			}

			dijkstra(0, D1, center1); // 1번 째 다익스트라
			
			
			int cntCenter[] = new int[C + 1];
			int max = 0;
			int maxCenter = 0;
			long ans = 0;

			for (int i = C + 1; i <= K; i++) { // 기지국별 기술자 수 체크
				cntCenter[center1[i]]++;
				ans += D1[i];
				if (max < cntCenter[center1[i]]) {
					max = cntCenter[center1[i]];
					maxCenter = center1[i]; // 초과 엔지니어 센터 찾기
				}
			}

			if (max > 0) { // 최대 엔지니어 수를 넘긴 경우
				D2 = new long[N + 1];
				center2 = new int[N + 1];
				Arrays.fill(D2, INF);
				// 두번 째 다익스트라 (초과 엔지니어 센터 빼고)
				dijkstra(maxCenter, D2, center2);

				// 비교
				long diff[] = new long[cntCenter[maxCenter]];
				int cnt = 0;
				for (int i = C + 1; i <= K; i++) {
					if (center1[i] == maxCenter) {
						// 첫 최소값과 두 번째 최소값 차이계산
						diff[cnt++] = Math.abs(D2[i] - D1[i]);
					}
				}

				Arrays.sort(diff);
				long mi = 0;
				for (int i = 0; i < max - M; i++) {
					mi += diff[i]; // 차이만큼 더함
				}
				ans += mi;
			}
			System.out.println("#" + t + " " + ans);
		}
		br.close();
	}
	

	// * k에 주의 : 1번째 다익 시에는 상관없지만 2번째 다익시에는 엔지니어 초과나는 센터를 빼고 돌림을 명심
	private static void dijkstra(int k, long[] d, int[] center) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean check[] = new boolean[N + 1];

		// 기술센터 노드를 시작점으로 한 번에 삽입 후 다익스트라 돌림
		for (int i = 1; i <= C; i++) {
			if (i != k) {
				pq.offer(new Node(0, i, 0)); // * 시작점은 0으로 setting
				d[i] = 0;
			}
		}				
		
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			
			if (check[now.end]) {
				continue;
			}

			check[now.end] = true;

			for (Node next : graph.get(now.end)) {
				if (d[next.end] > d[now.end] + next.cost) {
					d[next.end] = d[now.end] + next.cost;
					pq.offer(new Node(next.start, next.end, d[next.end]));

					// 방문한 기지국의 엔지니어는 어느 "기술센터"에서 왔는지 기록하는 과정
					int start = next.start; // 어디서 왔니? 도착노드의 출발노드 입력

					if (next.start > C) { // 출발노드가 기술센터가 아니라면
						start = center[now.end]; // 출발노드의 유래를 start에 입력
					}
					// 도착노드가 어느 기술센터로부터 왔는지 입력됌
					center[next.end] = start;
				}
			}
		}		
	}
}
