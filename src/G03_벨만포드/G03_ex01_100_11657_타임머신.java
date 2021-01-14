package G03_벨만포드;

// https://www.acmicpc.net/problem/11657
import java.util.*;
import java.io.*;

class Node {
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

public class G03_ex01_100_11657_타임머신 {
	static final int INF = (int) 1e9; // 10억 설정
	static int N, M;
	static int start = 1; // 시작지점 1 고정
	static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
	static long dis[]; // *주의* 자료형을 int 로 할 경우 오버플로우 발생

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G03_벨만포드/G03_ex01_100_11657_타임머신.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Node>());
		}
		
		// 단방향 인접리스트 구현
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());

			graph.get(from).add(new Node(to, value));
		}

		dis = new long[N + 1];

		Arrays.fill(dis, INF);

		// bellmanFord 음의 루프 발생 시, return 1
		if (bellmanFord(start) == 1) {
			System.out.println(-1);
		} else {
			for (int to = 2; to <= N; to++) {
				if (dis[to] == INF) {
					System.out.print(-1 + " ");
				} else {
					System.out.print(dis[to] + " ");
				}
				System.out.println();
			}
		}
	}

	private static int bellmanFord(int startN) {
		dis[startN] = 0;	// 시작점의 거리는 0으로 초기화
		int check_loop = 0;
		
		// (정점의 개수 - 1)번 동안 최단거리 초기화 작업을 반복
		for (int round = 1; round < N; round++) {
			check_loop = 0;
			
			// 최단거리 초기화
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
			
			// 더이상 최단거리 초기화가 일어나지 않았을 경우 반복문 종료
			if (check_loop != 1) {
				break;
			}

		}
		
		// (정점의 개수 - 1)번까지 계속 업데이트가 발생했을 경우
        // (정점의 개수)번도 업데이트 발생하면 음수 사이클이 일어난 것을 의미
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
