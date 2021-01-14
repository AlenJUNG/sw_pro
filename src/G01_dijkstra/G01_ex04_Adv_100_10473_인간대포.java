package G01_dijkstra;
// 수정 중
// https://www.acmicpc.net/problem/10473

import java.util.*;
import java.io.*;

public class G01_ex04_Adv_100_10473_인간대포 {
	static class Daepo implements Comparable<Daepo> {
		int idx;
		double time;

		public Daepo(int idx, double time) {
			this.idx = idx;
			this.time = time;
		}

		public int getIdx() {
			return this.idx;
		}

		public double getTime() {
			return this.time;
		}

		@Override
		public int compareTo(Daepo other) {
			if (this.time < other.time) { // 속도 가중치
				return -1;
			}
			return 1;
		}
	}

	static final double INF = (double) 1e9; // 최대값 > double 생성
	static int N; // 대포 수
	static double node[][]; // node[N+2][2] => node[시작 > 대포들 > 도착][x좌표, y좌표]
	static boolean visited[];
	static double D[]; // dijkstra 거리 구하기
	static ArrayList<ArrayList<Daepo>> map1;
	static ArrayList<Daepo>[] map;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G01_dijkstra/G01_ex04_Adv_100_10473_인간대포.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 시작점
		StringTokenizer st = new StringTokenizer(br.readLine());
		double sx = Double.parseDouble(st.nextToken());
		double sy = Double.parseDouble(st.nextToken());

		// 도착점
		st = new StringTokenizer(br.readLine());
		double ex = Double.parseDouble(st.nextToken());
		double ey = Double.parseDouble(st.nextToken());

		N = Integer.parseInt(br.readLine()); // 대포수
		visited = new boolean[N + 2]; // 방문여부
		node = new double[N + 2][2];
		// 시작점과 도착점 입력하고 시작
		node[0][0] = sx;
		node[0][1] = sy;
		node[N + 1][0] = ex;
		node[N + 1][1] = ey;

		// N + 2 주의
		map = new ArrayList[N + 2];
		D = new double[N + 2];
		for (int i = 0; i <= N + 1; i++) {
			map[i] = new ArrayList<Daepo>();
			D[i] = INF; // 거리 테이블 초기화
		}

		// 현준 입력
		map1 = new ArrayList<ArrayList<Daepo>>();
		for (int i = 0; i <= N + 1; i++) {
			map1.add(new ArrayList<Daepo>());
		}

		// 대포들 좌표 입력
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			node[i][0] = Double.parseDouble(st.nextToken());
			node[i][1] = Double.parseDouble(st.nextToken());
		}

		double tx, ty; // target_x, target_y
		double distance, only_walk, use_daepo;

		// * 출발점 <-> 대포 1, 2, 3, 4, 도착점, 완전탐색하여 map에 입력
		for (int i = 1; i <= N + 1; i++) {
			tx = node[i][0];
			ty = node[i][1];

			distance = Math.sqrt(Math.pow((sx - tx), 2) + Math.pow((sy - ty), 2));
			only_walk = distance / 5.0;
			// 0 = 도착점 <-> 전체 각 노드 걸어갔을 때 시간 입력
			map[0].add(new Daepo(i, only_walk));
		}

		double min_time;

		for (int from = 1; from <= N + 1; from++) {
			for (int to = from + 1; to <= N + 1; to++) {
				sx = node[from][0];
				sy = node[from][1];
				ex = node[to][0];
				ey = node[to][1];

				distance = Math.sqrt(Math.pow((sx - ex), 2) + Math.pow((sy - ey), 2));
				// 걸어갔을 때 시간 vs 대포를 이용했을 때 시간
				only_walk = distance / 5.0;
				use_daepo = 2.0 + Math.abs(50 - distance) / 5.0;
				// 최소시간을 min에 입력
				min_time = Math.min(only_walk, use_daepo);

				// 양방향 입력
				map[from].add(new Daepo(to, min_time));
				map[to].add(new Daepo(from, min_time));

				map1.get(from).add(new Daepo(to, min_time));
				map1.get(to).add(new Daepo(from, min_time));
			}
		}

		dijkstra(0, map1, D);
//		dijkstra1(0);

		System.out.println(String.format("%.6f", D[N + 1]));

	}
	
	// answer
	private static void dijkstra1(int start) {
		//다익스트라 실행
		PriorityQueue<Daepo> pq = new PriorityQueue<Daepo>();
		pq.clear();
		pq.add(new Daepo(0, 0.0));	// 출발점을 0으로 설정했고, 이 때 거리는 0.0
		D[0] = 0;
		
		while(!pq.isEmpty()) {
			Daepo curr = pq.poll();
			
			if(visited[curr.idx]) {
				continue; //이미 방문했으면 건너뜀
			}
			
			visited[curr.idx] = true;
			
			for(int i = 0; i < map[curr.idx].size(); i++) {
				Daepo next = map[curr.idx].get(i);
				
				if(visited[next.idx]) {
					continue;
				}
				
				if(D[next.idx] > curr.time + next.time) {
					D[next.idx] = curr.time + next.time;
					pq.add(new Daepo(next.idx, D[next.idx]));
				}
			}
		}		
	}
	
	// hj
	private static void dijkstra(int start, ArrayList<ArrayList<Daepo>> gra, double[] d) {
		PriorityQueue<Daepo> pq = new PriorityQueue<>();
		pq.offer(new Daepo(start, 0.0));
		d[start] = 0;

		while (!pq.isEmpty()) {
			Daepo dp = pq.poll();
			int now_idx = dp.getIdx();
			double now_time = dp.getTime();

			if (d[now_idx] < now_time) {
				continue;
			}

			for (int toIdx = 0; toIdx < gra.get(now_idx).size(); toIdx++) {
				double temp = d[now_idx] + gra.get(now_idx).get(toIdx).getTime();
				if (d[gra.get(now_idx).get(toIdx).getIdx()] > temp) {
					d[gra.get(now_idx).get(toIdx).getIdx()] = temp;
					pq.offer(new Daepo(gra.get(now_idx).get(toIdx).getIdx(), gra.get(now_idx).get(toIdx).getTime()));
				}
			}
		}

	}

}
