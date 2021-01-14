package G02_플로이드워셜;

import java.util.*;
import java.io.*;

public class G02_ex01_미래도시 {
	static final int INF = (int) 1e9; // 무한을 의미하는 값으로 10억을 설정
	// 노드의 개수(N), 간선의 개수(M), 거쳐 갈 노드(MID), 최종 목적지 노드(END)
	static int START = 1;
	static int N, M, END, MID;
	static int graph[][];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G02_플로이드워셜/G02_ex01_미래도시.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new int[N + 1][N + 1];

		// 최단 거리 테이블을 모두 무한 + 자기 자신에서 자기 자신으로 가는 비용은 0으로 초기화
		for (int from = 1; from <= N; from++) {
			for (int to = 1; to <= N; to++) {
				if (from == to) {
					graph[from][to] = 0;
				} else {
					graph[from][to] = INF;
				}
			}
		}

		// 각 간선에 대한 정보를 입력 받아, 그 값으로 초기화
		for (int i = 1; i <= M; i++) {
			// A와 B가 서로에게 가는 비용은 1이라고 설정
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			graph[from][to] = graph[to][from] = 1;
		}

		st = new StringTokenizer(br.readLine());
		END = Integer.parseInt(st.nextToken());
		MID = Integer.parseInt(st.nextToken());

		// 점화식에 따라 플로이드 워셜 알고리즘을 수행
		for (int mid = 1; mid <= N; mid++) {
			for (int from = 1; from <= N; from++) {
				for (int to = 1; to <= N; to++) {
//					graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
					int cost = graph[from][mid] + graph[mid][to];
					if (graph[from][to] > cost) {
						graph[from][to] = cost;
					}
				}
			}
		}

		int ans = graph[1][MID] + graph[MID][END];

		if (ans >= INF) {
			System.out.println(-1);
		} else {
			System.out.println(ans);
		}
	}
}
