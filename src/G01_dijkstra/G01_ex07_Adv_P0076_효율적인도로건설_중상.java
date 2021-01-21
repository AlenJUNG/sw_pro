package G01_dijkstra;

import java.io.*;
import java.util.*;

/* 최대 40개의 test case 입력시 java 2초
 * 0.05초 50ms 500만 크기
 */

public class G01_ex07_Adv_P0076_효율적인도로건설_중상 {
	static class Node implements Comparable<Node> {
		int v, cost;

		public Node(int v, int cost) {
			this.v = v;
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

	// 10억 + 1 > 최대간선수 (10만) * 최대 간선가중치(1만)으로 표현 가능수보다 큰 값
	static final int MAX = 1000000001;
	static int T, N, M;
	static int cost_s[] = new int[50001]; // 최대 노드의 수 : 시작 S
	static int cost_e[] = new int[50001]; // 최대 노드의 수 : 끝 E
	static ArrayList<Node>[] graph;
	static int answer;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G01_dijkstra/G01_ex07_Adv_P0076_효율적인도로건설_중상.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {

			Arrays.fill(cost_s, MAX); // 순방향 배열 s를 Max (무한대) 값으로 채움
			Arrays.fill(cost_e, MAX); // 역방향 배열 e를 Max (무한대) 값으로 채움

			answer = 0; // 결과 누적 초기화

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			graph = new ArrayList[N + 1];
			for (int i = 1; i < N + 1; i++) {
				graph[i] = new ArrayList<Node>();
			}
			// 입력값을 방향성이 없기 때문에 양방향 간선 가충치로 저장
			for (int i = 1; i < M + 1; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());

				graph[a].add(new Node(b, c));
				graph[b].add(new Node(a, c));
			}

			// find 함수로 정/역방향으로 다익스트라 수행
			find(1, N, cost_s);
			find(N, 1, cost_e);

			// 정방향으로 산출된 최적화 값을 Max로 지정
			int max = cost_s[N];

			// 기본 정렬 오름차순 작은값부터 정렬은 결국 끝에서부터의 값이다?
			Arrays.sort(cost_e);
			for (int i = 2; i < N; i++) {
				// 끝(누적치) - (현재 기준 누적치 + 신규간선(1)) = 차이를 만족하는 간선 가중치 값?
				int target = max - (cost_s[i] + 1);
				int start = 1;
				int end = N - 2; // 끝은 N과 2개 거리로 떨어진 녀석
				int mid = 0;

				// 파라메트릭 서치
				while (start < end) { // 역전되지 않을 때까지 탐색
					mid = (start + end) / 2;
					if (cost_e[mid] >= target) {
						end = mid;
					} else {
						start = mid + 1;
					}
				}

				// 파라메트릭 서치로 위의 값에 충족하는 누적된 노드 값이 나옴
				if (cost_e[mid] >= target) {
					mid--;
				}
				answer += mid;
			}

			bw.write("#" + t + " " + answer);
			bw.newLine();
		}

		bw.flush();
		bw.close();
	}

	// find 함수 - 다익스트라 수행
	private static void find(int start, int end, int costArr[]) {
		costArr[start] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<Node>();

		// 초기값 설정
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			// 최종 순회까지 업데이트 되었다면 중단
			if (curr.v == end) {
				break;
				// 현재 Cost 값보다 크다면 갱신 안함
			}
			if (curr.cost > costArr[curr.v]) {
				continue;
			}
			// 자신을 기준으로 탐색할 수 있는 자식들을 체크
			for (Node child : graph[curr.v]) {
				// 현재 값보다 값이 작다면 갱신하고 Queue에 넣어 실행
				if (costArr[child.v] > curr.cost + child.cost) {
					costArr[child.v] = curr.cost + child.cost;
					pq.offer(new Node(child.v, costArr[child.v]));
				}
			}
		}
	}
}