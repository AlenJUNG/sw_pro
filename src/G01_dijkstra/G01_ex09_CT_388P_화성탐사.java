package G01_dijkstra;

import java.io.*;
import java.util.*;

public class G01_ex09_CT_388P_화성탐사 {
	static int move_x[] = { -1, 0, 0, 1 };
	static int move_y[] = { 0, -1, 1, 0 };

	// 거리가 짧은 것이 높은 우선순위를 가지도록 설정
	static class Node implements Comparable<Node> {
		int x, y, c;

		public Node(int x, int y, int c) {
			this.x = x;
			this.y = y;
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

	static final int INF = 9 * 125 * 125;
	static int TC, N, ans;
	static int d[][], map[][]; // * Point 문제에 맞게 설정해준다
	static boolean check[][];
	static PriorityQueue<Node> pq;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G01_dijkstra/G01_ex09_CT_388P_화성탐사.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());

			map = new int[N + 1][N + 1];
			d = new int[N + 1][N + 1];

			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					int temp = Integer.parseInt(st.nextToken());
					map[i][j] = temp; // * 전체 Map 정보 입력 x, y 좌표별 cost 값이 된다
					d[i][j] = INF; // dijkstra를 위한 INF 처리 > 이중 Arrays.fill 안 먹음
				}
			}

			// 시작노드 1, 1부터 다익스트라 시작
			dijkstra(1, 1);

			bw.write("#" + tc + " " + d[N][N] + "\n");

		}

		br.close();
		bw.flush();
		bw.close();
	}

	private static void dijkstra(int start_x, int start_y) {
		pq = new PriorityQueue<>();
		// Node(x, y, map[x][y])
		pq.offer(new Node(start_x, start_y, map[start_x][start_y]));
		// cost값 확정 배열인 d배열 초기화 = map[x][y]
		d[start_x][start_y] = map[start_x][start_y];
		check = new boolean[N + 1][N + 1];

		while (!pq.isEmpty()) {
			Node now = pq.poll();
			
			// 이미 처리된 노드라면 무시
			if (check[now.x][now.y]) {
				continue;
			}
			check[now.x][now.y] = true;

			// 현재 노드와 인접한 노드들 확인
			for (int i = 0; i < 4; i++) {
				int nx = now.x + move_x[i];
				int ny = now.y + move_y[i];
				
				// 맵의 범위를 벗어나는 경우는 무시
				if (nx < 1 || nx > N || ny < 1 || ny > N) {
					continue;
				}

				// 현재 노드를 거쳐, 다른 노드로 이동하는 거리가 더 짧은 경우
				if (d[nx][ny] > now.c + map[nx][ny]) {
					d[nx][ny] = now.c + map[nx][ny];
					pq.offer(new Node(nx, ny, d[nx][ny]));
				}
			}
		}

	}
}
