package G05_Kruskal;

import java.io.*;
import java.util.*;

/*
 * Update : 2021.07.08
 * 혼자 힘으로 풀기는 쉽지 않을 것 같음
 */

public class G05_ex14_P0079_도시계획 {
	// 도시 정보
	static class city {
		// water가 1이면 수원지
		int water, x, y;

		public city(int water, int x, int y) {
			this.water = water;
			this.x = x;
			this.y = y;
		}
	}

	// 간선 정보
	static class edge implements Comparable<edge> {
		int water, city; // 수원지 도시 no, 수원지와 연결된 도시 no
		long dis; // 두 지점 사이의 거리

		public edge(int water, int city, long dis) {
			this.water = water;
			this.city = city;
			this.dis = dis;
		}

		// 두 지점 사이의 거리 오름차순 정렬
		@Override
		public int compareTo(edge other) {
			if (this.dis < other.dis) {
				return -1;
			}
			return 1;
		}
	}

	static int TC, N, M, x, y, visitCnt;
	static long tree[][]; // 두 지점의 연결정보 저장
	static boolean visited[];
	static city cityInfo[];
	static PriorityQueue<edge> pq; // * 간선 위주 : 수원지 - 일반도시 연결정보만 저장
	static long dis, ans;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_ex14_P0079_도시계획.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			cityInfo = new city[N + 1];
			
			tree = new long[N + 1][N + 1];
			visited = new boolean[N + 1];
			pq = new PriorityQueue<>();
			ans = visitCnt = 0;
			
			// 0. 최소값을 구하기 위해 tree 배열 max 값 초기 세팅
			for(int i = 0; i <= N; i++) {
				for(int j = 0; j <= N; j++) {
					tree[i][j] = ((long)100001 * (long)100001 + (long)100001 * (long)100001);
				}
			}

			// 1. 도시 정보 저장
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				cityInfo[i] = new city(0, x, y); // 초기값 세팅할 때는 수원지가 아닌 것으로 받기
			}

			// 2. 도시간 정보 저장 > 전체 간선 저장
			for (int i = 1; i <= N - 1; i++) {
				for (int j = i + 1; j <= N; j++) {
					long disX = cityInfo[i].x - cityInfo[j].x;
					long disY = cityInfo[i].y - cityInfo[j].y;
					// * 주의 long = (long) int * int > 형변환 필요
					long dis = (long) disX * disX + (long) disY * disY;
					// * 중요 : from 정점 기준, 갈수 있는 모든 기준값 중 최소값 양방향 저장
					dis = Math.min(tree[x][y], dis);
					tree[i][j] = dis;
					tree[j][i] = dis;
				}
			}

			// 3. 수원지 정보 초기 세팅
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				int water_no = Integer.parseInt(st.nextToken());
				cityInfo[water_no].water = 1; // 수원지 표시
				// 3-1. 수원지인 경우만 pq에 넣기
				for (int city_no = 1; city_no <= N; city_no++) {
					pq.offer(new edge(water_no, city_no, tree[water_no][city_no]));
				}
			}

			visitCnt = M; // * 수원지는 방문한 도시로 기처리함 : 시간 복잡도 최소화

			// 4. pq에 저장된 간선을 뽑고 > 가작 작은 비용으로 연결된 도시는 수원지 처리 > 비용합산
			while (!pq.isEmpty()) {
				edge now = pq.poll();
				int water_N = now.water;
				int city_N = now.city;

				// 수원지로 이어지지 않은 도시인 경우
				if (cityInfo[water_N].water == 1 && cityInfo[city_N].water != 1) {
					cityInfo[city_N].water = 1; // 수원지로 반영
					visitCnt++; // 방문도시 반영 +1
					ans += tree[water_N][city_N];

					// 모든 도시를 다 방문했다면, 종료 : 시간복잡도 최적화
					if (visitCnt == N) {
						break;
					}

					// 수원지로 이어진 도시 - 일반도시 연결정보 모두 pq에 넣기
					// 단, 수원지 - 수원지는 연결하지 않음
					for (int i = 1; i <= N; i++) {
						if (cityInfo[city_N].water == 1 && cityInfo[i].water != 1) {
							pq.offer(new edge(city_N, i, tree[city_N][i]));
						}
					}
				}
			}

			bw.write("#" + tc + " " + ans + "\n");
		}

		br.close();
		bw.flush();
		bw.close();

	}

}
