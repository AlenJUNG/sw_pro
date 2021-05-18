
/* [사전 테스트 52 : 농장]
 * [문제정보]
 * 1. 길은 양방향, 웜홀은 단방향
 * 2. 웜홀은 시간을 되돌림
 * 3. 현재 위치에서 다시 현재로 돌아왔을 때 과거로 돌아갈 수 있는지 알아보는 프로그램 작성할 것
 * [input]
 * 3 3 1 > N, M, W : 농장수, 길의 수, 웜홀 수
 * 1 2 2 > M + 1까지 길의 수
 * 1 3 4
 * 2 3 1 
 * 3 1 3 > W개수만큼 웜홀 수
 * [Output]
 * #1 NO
 * #2 YES
 */

import java.io.*;
import java.util.*;

public class Solution {
	static class Node {
		int idx;
		int distance;

		public Node(int idx, int distance) {
			this.idx = idx;
			this.distance = distance;
		}

		public int getIdx() {
			return this.idx;
		}

		public int getDistance() {
			return this.distance;
		}
	}

	static int TC, N, M, W;
	static final int INF = (int) 1e9;
	static ArrayList<ArrayList<Node>> graph;
	static long dis[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/pretest/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		TC = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			graph = new ArrayList<ArrayList<Node>>();
			for (int i = 0; i <= N; i++) {
				graph.add(new ArrayList<Node>());
			}

			for (int i = 1; i <= M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());

				graph.get(from).add(new Node(to, value));
				graph.get(to).add(new Node(from, value));
			}

			for (int i = 1; i <= W; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());

				graph.get(from).add(new Node(to, -value));
			}

			dis = new long[N + 1];

			Arrays.fill(dis, INF);

			int check = 0;
			// 음의 순환이 발생한 경우
			for (int i = 1; i <= N; i++) {
				if (bellmanFord(i) == 1) {
					check = 1;
					break;
				}
			}

			if (check == 1) {
				bw.write("#" + tc + " YES\n");
			} else {
				bw.write("#" + tc + " NO\n");
			}

		}
		br.close();
		bw.flush();
		bw.close();
	}

	private static int bellmanFord(int start) {
		dis[start] = 0;
		int check_loop = 0;

		for (int i = 1; i < N; i++) {
			check_loop = 0;

			for (int next = 1; next <= N; next++) {
				for (Node node : graph.get(next)) {
					if (dis[next] == INF) {
						break;
					}
					if (dis[node.idx] > dis[next] + node.distance) {
						dis[node.idx] = dis[next] + node.distance;
						check_loop = 1;
					}
				}

			}

			if (check_loop != 1) {
				break;
			}
		}

		if (check_loop == 1) {
			for (int next = 1; next <= N; next++) {
				for (Node node : graph.get(next)) {
					if (dis[next] == INF) {
						break;
					}

					if (dis[node.idx] > dis[next] + node.distance) {
						return 1;
					}
				}
			}
		}
		return 0;
	}

}
