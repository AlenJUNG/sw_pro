package G01_dijkstra;

import java.io.*;
import java.util.*;

/* 최대 40개의 test case 입력시 java 2초
 * 0.05초 50ms 500만 크기
 */

public class G01_ex07_Adv_P0076_효율적인도로건설_중상 {
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

	// 10억 + 1 > 최대간선수 (10만) * 최대 간선가중치(1만)으로 표현 가능수보다 큰 값
	static final int INF = 1000000001;	// edge * cost
	static int N, M, ans;
	static int D[], reverse_D[];
	static ArrayList<ArrayList<Node>> graph;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G01_dijkstra/G01_ex07_Adv_P0076_효율적인도로건설_중상.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;

		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		ans = 0;	// 결과 누적 초기화

		D = new int[N + 1];
		reverse_D = new int[N + 1];

		Arrays.fill(D, INF);
		Arrays.fill(reverse_D, INF);

		graph = new ArrayList<ArrayList<Node>>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Node>());
		}
		
		// 입력값을 방향성이 없기 때문에 양방향 간선 가중치로 저장
		int from, to, value;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			value = Integer.parseInt(st.nextToken());

			graph.get(from).add(new Node(to, value));
			graph.get(to).add(new Node(from, value));
		}
		
		// 정/역방향으로 다익스트라 수행
		dijkstra(1, N, D);
		dijkstra(N, 1, reverse_D);

		// 정방향으로 산출된 최적화 값 > MAX
		int max = D[N];

		// end에서 역방향 저장 탐색 > 오름차순 정렬
		// 파라메트릭 서치의 기본 조건 > 탐색해야하는 값 정렬
		Arrays.sort(reverse_D);

		/*
		 * * 생각해보자 
		 * 1. max = D[mid] + reverseD[mid] 
		 * 2. max = D[mid] + (mid ~ mid + 1 연결된 간선 값) + reverseD[mid + 1]  
		 * 3. 거리 (1 ~ N) = 거리 (1 ~ A) + 1 (A ~ B) + 거리 (B ~ N)
		 * ** 완전 탐색의 시간 복잡도를 줄일수는 없을까? 우리는 아래 식을 만족하는 쌍의 개수만 필요하기 때문에 > 파라메트릭 서치를 실행한다
		 * if ( 거리 (1 ~ N) - (거리 (1 ~ A) + 1 (A ~ B)) > 거리 (B ~ N) )
		 */
		
		for (int i = 2; i < N; i++) {
			int target = max - (D[i] + 1);

			// reverse_D idx 1 ~ N -2 파라메트릭 서치 > 범위탐색
			int start = 1;
			// * 범위주의 : 배열 특성 > 정렬된 값 마지막은 INF이기 때문 > INF 제외
			// 정렬했기 때문에 reverse_D[N - 1] : 도착점, reverse_D[N] = INF <> 초기 0 값
			int end = N - 2;
			int mid = 0;

			// 아래대로 whlie문 종료 시, mid는 만족하는 값 + 1을 가리키게 된다.
			while (start < end) {
				mid = (start + end) / 2;

				if (target <= reverse_D[mid]) {
					end = mid;
				} else {
					start = mid + 1;
				}
			}

			// * 한번 더 > 만족하는 값 +1 이 mid임을 확인
			// 그렇다면 target > reverse_D[mid]를 만족하는 개수는 mid--
			if (target <= reverse_D[mid]) {
				mid--;
			}

			// 값 반영
			ans += mid;
		}
		
		bw.write("#"+t+" "+ans+"\n");
		}
		
		br.close();
		bw.flush();
		bw.close();
		
	}

	private static void dijkstra(int start, int end, int[] d) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		d[start] = 0;
//		boolean check[] = new boolean[N+1];

		while (!pq.isEmpty()) {
			Node now = pq.poll();

//			if(check[now.idx]) {
//				continue;
//			}
//			
//			check[now.idx] = true;

			if (now.idx == end) {
				break;
			}

			if (d[now.idx] < now.dis) {
				continue;
			}

			for (Node next : graph.get(now.idx)) {
				if (d[next.idx] > d[now.idx] + next.dis) {
					d[next.idx] = d[now.idx] + next.dis;
					pq.offer(new Node(next.idx, d[next.idx]));
				}
			}

		}

	}
}
