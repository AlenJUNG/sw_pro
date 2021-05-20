package D05_DP;

import java.io.*;
import java.util.*;

public class D05_ex01_교육A0012_보석털이 {
	static int TC, N, M;
	static int C[], W[];
	static long DP[][];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D05_DP/D05_ex01_교육A0012_보석털이.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // N개의 보석
			M = Integer.parseInt(st.nextToken()); // 무게합 M 이하여야함

			C = new int[N + 1]; // i번째 보석의 값어치
			W = new int[N + 1]; // i번째 보석의 무게

			DP = new long[N + 1][M + 1]; // * 답 1번째 보석부터 i번째 보석 중 가져간 보석의 무게 합이 j 이하일 때, 가져간 보석 값어치 합의 최대값

			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				C[i] = Integer.parseInt(st.nextToken());
				W[i] = Integer.parseInt(st.nextToken());
			}
			
			/* 초기값 > DP[0][j] = 0, DP[i][0] = 0
			 * 조건은 숨겨져 있음
			 * HJ : 모든 보석의 경우의 수를 넣으면서 무게합 이하까지 DP 생성
			 */

			for (int i = 1; i <= N; i++) {	// 1 ~ N번째 보석
				for (int j = 1; j <= M; j++) {	// 1 ~ 무게합 M까지 DP Bottom - Up
					// i - 1 번째, 즉 전의 보석 정보를 입력하고 새로 비교하기 위해 입력 (빌드업)
					DP[i][j] = DP[i - 1][j];
					// 설정한 무게 - 보석 무게가 음수가 아닌 경우 = 무게제한 안으로 들어올 때만 작동
					if (j - W[i] >= 0) {
						// * 핵심코드 
						DP[i][j] = Math.max(DP[i][j], DP[i - 1][j - W[i]] + C[i]);
					}
				}
			}

			bw.write("#" + tc + " " + DP[N][M] + "\n");

		}

		br.close();
		bw.flush();
		bw.close();
	}

}
