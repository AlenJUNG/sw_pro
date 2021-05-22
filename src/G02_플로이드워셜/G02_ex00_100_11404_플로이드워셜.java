package G02_플로이드워셜;

import java.io.*;
import java.util.*;

/* 2021.05.22, 플로이드워셜 기초
 * https://www.acmicpc.net/problem/11404
 */

public class G02_ex00_100_11404_플로이드워셜 {
	static final int INF = 100 * 100000 + 1;	// 최대 정점 수 + 간선당 최대 비용
	static int N, M;
	static int arr[][];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G02_플로이드워셜/G02_ex00_100_11404_플로이드워셜.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		arr = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			Arrays.fill(arr[i], INF);	// 이중배열 INF 일괄입력
			arr[i][i] = 0;	// 동일한 정점까지 거리는 0으로 설정
		}

		int from, to, cost;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			
			// * 주의 : 시작도시와 도착도시를 연결하는 노선은 하나가 아닐 수 있음
			// 동일한 노선이 어려개면 비용이 작은 값으로 업데이트 필요
			arr[from][to] = Math.min(arr[from][to], cost);

		}

		floyd();

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				// * 주의 : 만약, i에서 j로 갈 수 없는 경우에는 그 자리에 0 입력
				if (arr[i][j] == INF) {
					arr[i][j] = 0;
				}
				bw.write(arr[i][j] + " ");
			}
			bw.newLine();
		}

		br.close();
		bw.flush();
		bw.close();

	}
	
	// main 함수 내 구현하지 말고 static 함수로 빼야 속도가 빠름 
	private static void floyd() {
		// k → a → b 순서 지켜야함
		for (int k = 1; k <= N; k++) {
			for (int a = 1; a <= N; a++) {
				for (int b = 1; b <= N; b++) {
					// Overflow 주의
					arr[a][b] = Math.min(arr[a][b], arr[a][k] + arr[k][b]);
				}
			}
		}

	}

}
