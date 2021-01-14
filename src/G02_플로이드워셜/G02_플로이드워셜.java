package G02_플로이드워셜;

import java.io.*;
import java.util.*;

public class G02_플로이드워셜 {
	static final int INF = (int) 1e9; // 무한을 의미하는 값으로 10억을 설정
	// 노드의 개수(N), 간선의 개수(M)
	// 노드의 개수는 최대 500개라고 가정
	static int N, M;
	// 2차원 배열(그래프 표현)를 만들기
	static int graph[][];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G02_플로이드워셜/G02_플로이드워셜.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		graph = new int[3000][3000];

		// 이중 포문 의 Arrays.fill 사용법 익히기
		// 최단 거리 테이블을 모두 무한으로 초기화
		for (int i = 0; i < 3000; i++) {
			Arrays.fill(graph[i], INF);
		}

		// 자기 자신에서 자기 자신으로 가는 비용은 0으로 초기화
		for (int from = 1; from <= N; from++) {
			for (int to = 1; to <= N; to++) {
				if (from == to) {
					graph[from][to] = 0;
				}
			}
		}

		// 각 간선에 대한 정보를 입력 받아, 그 값으로 초기화
		for (int i = 1; i <= M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			graph[from][to] = value;
		}

		// 점화식에 따라 플로이드 워셜 알고리즘을 수행
		for (int mid = 1; mid <= N; mid++) {
			for (int from = 1; from <= N; from++) {
				for (int to = 1; to <= N; to++) {
					graph[from][to] = Math.min(graph[from][to], graph[from][mid] + graph[mid][to]);
				}
			}
		}

		// 수행된 결과를 출력
		for (int from = 1; from <= N; from++) {
			for (int to = 1; to <= N; to++) {
				if (graph[from][to] == INF) {
					System.out.println("INFINITY");
				} else {
					System.out.print(graph[from][to] + " ");
				}
			}
			System.out.println();
		}
	}
}
