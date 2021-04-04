package G03_벨만포드;

import java.util.*;
import java.io.*;

public class Main {
	static int TC, V, E, A;
	// INF를 LongMax로 설정하면 터짐, 틀리게끔 문제 나옴
	// 길의 개수 E 3000 개 x 피로도 수치 1000000 x 10 으로 설정함
	static long INF = 300000000001L;
	static ArrayList<ArrayList<Node>> graph;
	// 최대 피로도 E 3000 개 x 피로도 수치 1000000 > long type 설정
	static long cost[];
	static int angel[];

	static class Node {
		int idx, cost;

		public Node(int idx, int cost) {
			this.idx = idx; // to
			this.cost = cost; // cost
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/G03_벨만포드/G03_ex02_P0071_던전탐험.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {

			st = new StringTokenizer(br.readLine());

			V = Integer.parseInt(st.nextToken()); // 노드
			E = Integer.parseInt(st.nextToken()); // 간선
			A = Integer.parseInt(st.nextToken()); // 천사

			cost = new long[V + 1];
			angel = new int[V + 1];
			graph = new ArrayList<ArrayList<Node>>();

			// 최단거리 초기화 : 시작정점 0, 도착정점 V - 1
			for (int i = 0; i <= V; i++) {
				cost[i] = INF; // 기본 템플릿
				angel[i] = 0; // 이 문제 고유조건
				graph.add(new ArrayList<Node>());
			}

			// 양방향 인접리스트 구현
			int f, t, d;
			for (int i = 1; i <= E; i++) {
				st = new StringTokenizer(br.readLine());
				f = Integer.parseInt(st.nextToken());
				t = Integer.parseInt(st.nextToken());
				d = Integer.parseInt(st.nextToken());

				graph.get(f).add(new Node(t, d));
				graph.get(t).add(new Node(f, d));
			}

			// 단방향 angel graph 리스트 정보 입력 > 고유 조건
			for (int i = 1; i <= A; i++) {
				st = new StringTokenizer(br.readLine());
				f = Integer.parseInt(st.nextToken());
				t = Integer.parseInt(st.nextToken());
				d = Integer.parseInt(st.nextToken());

				graph.get(f).add(new Node(t, -d));
			}

			if (bellmanFord(0)) {
				bw.write("#"+tc+" "+"BUG" + "\n");
			} else if (cost[V - 1] > 100_000_000_000L) {
				bw.write("#"+tc+" "+"NO" + "\n");
			} else {
				bw.write("#"+tc+" "+cost[V - 1] + " " + angel[V - 1] + "\n");
			}

			// bw.write(cost[V-1]+"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	// loop 있다면 true
	public static boolean bellmanFord(int start) {
		cost[0] = 0;
		boolean check_loop = false;

		for (int i = 1; i <= V - 1; i++) {
			check_loop = false; // 시작할 때 false로 시작

			// 정점 1부터 N까지 인접리스트 완전탐색
			for (int now = 0; now <= V - 1; now++) {
				for (Node next : graph.get(now)) {
					if (cost[now] == INF) {
						break;
					}
					if (cost[next.idx] > cost[now] + next.cost) {
						cost[next.idx] = cost[now] + next.cost;
						if (next.cost < 0) { // cost가 음수이면 angel 업데이트
							angel[next.idx] = angel[now] + 1;
						} else {
							angel[next.idx] = angel[now];
						}
						check_loop = true;

					} else if (cost[next.idx] == cost[now] + next.cost) {
						if (next.cost < 0) {
							angel[next.idx] = Math.min(angel[next.idx], angel[now] + 1);
						} else {
							angel[next.idx] = Math.min(angel[next.idx], angel[now]);
						}
					}
				}
			}

			// 더 이상 최단거리 초기화가 일어나지 않으면 바로 최단거리 초기화 작업 종료
			if (!check_loop) {
				break;
			}
		}

		if (check_loop) {
			for (int now = 0; now <= V - 1; now++) {
				for (Node next : graph.get(now)) {
					if (cost[now] == INF) {
						break;
					}
					if (cost[next.idx] > cost[now] + next.cost) {
						return true;
					}
				}
			}
		}

		return false;

	}
}
