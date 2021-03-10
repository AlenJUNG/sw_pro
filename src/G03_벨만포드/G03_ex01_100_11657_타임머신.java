package G03_벨만포드;

// https://www.acmicpc.net/problem/11657
import java.io.*;
import java.util.*;

public class G03_ex01_100_11657_타임머신 {
	static class Node {
		int idx, cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}

	static ArrayList<ArrayList<Node>> graph;
	static long d[]; // 자료형을 int로 할 경우 오버플로우 발생
	static int N, M;
	static int start = 1;
	static final int INF = 60000001; // M * cost 최대

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G03_벨만포드/G03_ex01_100_11657_타임머신.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		d = new long[N + 1];
//		Arrays.fill(d, INF);

		// 간선 리스트 생성 & d 배열 무한대 입력을 동시에 진행
		graph = new ArrayList<ArrayList<Node>>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Node>());
			d[i] = INF;
		}

		// 단방향 인접리스트 구현
		int from, to, value;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			value = Integer.parseInt(st.nextToken());

			graph.get(from).add(new Node(to, value));
		}

		// 벨만포드가 참이면 루프 발생 = 시간을 무한히 오래 전으로 돌릴 수 있음
		if (bellmanFord(start)) {
			bw.write(-1 + "\n");
		} else {
			for (int i = 2; i <= N; i++) {
				// 해당 도시로 가는 경로가 없으면 -1
				if (d[i] == INF) {
					bw.write(-1 + "\n");
					// 해당 도시로 가는 경로가 있으면 최단시간 출력
				} else {
					bw.write(d[i] + "\n");
				}
			}
		}

		br.close();
		bw.flush();
		bw.close();
	}

	private static boolean bellmanFord(int s) {
		// 시작지점 s는 문제에서 1로 고정
		d[s] = 0; // 모두가 무한대일 때 시작점 초기화 "0"
		boolean check_loop = false;

		// * (정점의 개수 - 1) 만큼 최단거리 초기화 작업 반복
		for (int i = 1; i <= N - 1; i++) {
			check_loop = false; // 시작할 때 false로 시작

			// 정점 1부터 N까지 인접리스트 완전탐색
			for (int now = 1; now <= N; now++) {
				for (Node next : graph.get(now)) {
					if (d[now] == INF) {
						break;
					}
					if (d[next.idx] > d[now] + next.cost) {
						d[next.idx] = d[now] + next.cost;
						check_loop = true;
					}
				}
			}

			// 더 이상 최단거리 초기화가 일어나지 않으면 바로 최단거리 초기화 작업 종료
			if (!check_loop) {
				break;
			}
		}

		// (정점의 개수 - 1) 번까지 완전탐색 결과 계속 업데이트가 발생했다?
		// 1. loop가 있는 것 같으면 > check_loop == true
		// 2. * (정점의 개수) 번 다시 완전탐색 시, 업데이트 발생하면
		//      음수 loop가 있다고 판단하고 바로 함수 종료
		if (check_loop) {
			for (int now = 1; now <= N; now++) {
				for (Node next : graph.get(now)) {
					if (d[now] == INF) {
						break;
					}
					if (d[next.idx] > d[now] + next.cost) {
						return true;
					}
				}
			}
		}

		return false;

	}

}
