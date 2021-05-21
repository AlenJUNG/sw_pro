package G03_벨만포드;

import java.io.*;
import java.util.*;

// Update 2021.05.22, G03_ex01_100_11657_타임머신
// https://www.acmicpc.net/problem/11657

public class hj_test {
	static class Node {
		int t, c;

		public Node(int t, int c) {
			this.t = t;
			this.c = c;
		}
	}

	static int N, M;
	// 500 * 6000 * (+-10000)은 int형이면 overflow 날 수 있음, long 권장
	static final int INF = 60000001;	 
	static ArrayList<Node> graph[];
	static long D[];	// 음수 overflow 조심

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G03_벨만포드/G03_ex01_100_11657_타임머신.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		D = new long[N + 1];		
		graph = new ArrayList[N + 1];
		
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<Node>();
			D[i] = INF;
		}

		int from, to, cost;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());

			graph[from].add(new Node(to, cost));
		}
		
		boolean check = bellmanFord(graph, D, 1);

		// 벨만포드가 참이면 루프 발생 = 시간을 무한히 오래 전으로 돌릴 수 있음
		if (check) {
			bw.write(-1 + "\n");	// * 중요 * : 숫자 출력시에는 " " 하지 않기
		} else {
			for (int i = 2; i <= N; i++) {
				// 해당 도시로 가는 경로가 없으면 -1
				if (D[i] == INF) {
					bw.write(-1 + "\n");
					// 해당 도시로 가는 경로가 있으면 최단시간 출력
				} else {
					bw.write(D[i] + "\n");
				}
			}
		}

		br.close();
		bw.flush();
		bw.close();

	}
	private static boolean bellmanFord(ArrayList<Node>[] gra, long[] d, int start) {
		// 시작지점 s는 문제에서 1로 고정
		d[start] = 0; // 모두가 무한대일 때 시작점 초기화 "0"
		// 업데이트가 발생하지 않는다면 반복문 종료 가능 (선택사항)
		boolean update = false;

		// * (정점의 개수 - 1) 만큼 최단거리 초기화 작업 반복
		// 음의 사이클 (최단거리가 무한히 음수로 내려가는 경우)이 없으면
		// 적어도 N - 1 번 돌리면 무조건 최단경로 확정됌
		for (int i = 1; i <= N - 1; i++) {
			update = false; // * 시작할 때마다 false로 시작

			// 정점 1부터 N까지 인접리스트 완전탐색
			for (int now = 1; now <= N; now++) {
				for (Node next : graph[now]) {
					/* 모든 정점들이 연결되어 있다는 보장이 없을 때
					 * 다른 정점들 사이에서 사이클이 발생할 수 있는 경우
					 * 음의 사이클의 유무를 파악하려면
					 * if(d[now] == INF) continue 조건 삭제
					 */
					if (d[now] == INF) {
						continue;
					}
					if (d[next.t] > d[now] + next.c) {
						d[next.t] = d[now] + next.c;
						update = true;
					}
				}
			}

			// 더 이상 최단거리 초기화가 일어나지 않으면 바로 최단거리 초기화 작업 종료 (선택사항)
			if (!update) {
				break;
			}
		}

		// (정점의 개수 - 1) 번까지 완전탐색 결과 계속 업데이트가 발생했다?
		// 1. update가 있는 것 같으면 > update == true
		// 2. * (정점의 개수) 번 다시 완전탐색 시, 업데이트 발생하면
		//      음의 순환이 있다고 판단하고 바로 함수 종료
		if (update) {
			for (int now = 1; now <= N; now++) {
				for (Node next : graph[now]) {
					if (d[now] == INF) {
						continue;
					}
					if (d[next.t] > d[now] + next.c) {
						return true;
					}
				}
			}
		}

		return false;

	}
	
	// 배열로 풀이
	private static boolean bellmanFord1(ArrayList<Node>[] gra, long[] d, int start) {
		boolean update = false;

		d[start] = 0;

		for (int i = 1; i <= N - 1; i++) {
			update = false;

			for (int now = 1; now <= N; now++) {
				for (Node next : graph[now]) {
					int next_node = next.t;
					int next_cost = next.c;

					if (d[now] == INF) {
						continue;
					}

					if (d[next_node] > d[now] + next_cost) {
						d[next_node] = d[now] + next_cost;
						update = true;
					}
				}
			}

			// 선택
			if (!update) {
				break;
			}
		}

		// 음의 사이클 판별
		boolean NegativeCycle = false;

		for (int now = 1; now <= N; now++) {
			for (Node next : graph[now]) {
				int next_node = next.t;
				int next_cost = next.c;

				if (d[now] == INF) {
					continue;
				}

				if (d[next_node] > d[now] + next_cost) {
					// d[next_node] = d[now] + next_cost;
					NegativeCycle = true;
					break;
				}
			}
		}

		return NegativeCycle;

	}

}
