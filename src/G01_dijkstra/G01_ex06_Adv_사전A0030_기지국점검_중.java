package G01_dijkstra;

import java.io.*;
import java.util.*;

public class G01_ex06_Adv_사전A0030_기지국점검_중 {
	static class Edge implements Comparable<Edge> {
		int start;
		int end;
		long cost; // cost는 long임을 주의할 것

		public Edge(int start, int end, long cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge other) {
			if (this.cost < other.cost) {
				return -1;
			}
			return 1;
		}
	}

	static int N, E, C, K, M;
	static ArrayList<Edge> path[];
	static long D1[], D2[];
	static int center1[], center2[];
	static long INF = 500000000001L;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G01_dijkstra/G01_ex06_Adv_사전A0030_기지국점검_중.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int TC = Integer.parseInt(br.readLine());
		StringTokenizer st;

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 노드 수 (N)
			E = Integer.parseInt(st.nextToken()); // 간선 수 (E)
			C = Integer.parseInt(st.nextToken()); // 기술센터의 수 (C)
			K = Integer.parseInt(st.nextToken()); // 기술센터 + 기지국 수 (K)
			M = Integer.parseInt(st.nextToken()); // 기술센터별 기술자 수 (M)

			path = new ArrayList[N + 1];
			D1 = new long[N + 1];
			center1 = new int[N + 1];	// 첫 번째 센터 개수 배열

			for (int i = 0; i <= N; i++) {
				path[i] = new ArrayList<Edge>();
			}
			Arrays.fill(D1, INF);

			int x, y, c;
			for (int i = 1; i <= E; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				
				// 양방향 그래프 간선 연결
				path[x].add(new Edge(x, y, c));
				path[y].add(new Edge(y, x, c));
			}

			dijkstra(-1, D1, center1); // 1번째 다익스트라

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
			System.out.println("#" + tc + " " + ans);
		}
	}

	private static void dijkstra(int k, long[] d, int[] center) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean check[] = new boolean[N + 1];
		
		for (int i = 1; i <= C; i++) {	// i 기술센터 노드
			if (i != k) {	// 기술센터 노드가 
				pq.add(new Edge(0, i, 0));
				d[i] = 0;
			}
		}

		while (!pq.isEmpty()) {
			Edge curE = pq.poll();
			int cur = curE.end;
			if (check[cur]) {
				continue;
			}
			check[cur] = true;

			for (Edge e : path[cur]) {
				if (d[e.end] > d[cur] + e.cost) {
					d[e.end] = d[cur] + e.cost;
					pq.add(new Edge(e.start, e.end, d[e.end]));
					int start = e.start;
					if (e.start > C) {
						start = center[cur];
					}
					center[e.end] = start;
				}
			}
		}

	}

}
