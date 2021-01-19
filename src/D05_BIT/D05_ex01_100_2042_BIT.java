package D05_BIT;
// https://www.acmicpc.net/problem/2042

import java.io.*;
import java.util.*;

public class D05_ex01_100_2042_BIT {
	static int N, M, K;
	static long arr[], tree[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D05_BIT/D05_ex01_100_2042_BIT.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 데이터의 개수(n), 변경 횟수(m), 구간 합 계산 횟수(k)
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// 전체 데이터의 개수는 최대 1,000,000개
		arr = new long[1000001];
		tree = new long[1000001];

		for (int i = 1; i <= N; i++) {
			long x = Long.parseLong(br.readLine());
			arr[i] = x;
			update(i, x);
		}

		for (int i = 1; i <= M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int check = Integer.parseInt(st.nextToken());
			// 업데이트(update) 연산인 경우
			if (check == 1) {
				int idx = Integer.parseInt(st.nextToken());
				long value = Long.parseLong(st.nextToken());
				update(idx, value - arr[idx]); // 바뀐 크기(dif)만큼 적용
				arr[idx] = value; // i번째 수를 value로 업데이트
				// 구간 합(interval sum) 연산인 경우
			} else {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				System.out.println(intervalSum(start, end));
			}
		}
	}

	// i번째 수를 dif만큼 더하는 함수
	private static void update(int i, long dif) {
		while (i <= N) {
			tree[i] += dif;
			i += (i & -i);
		}
	}

	// start부터 end까지의 구간 합을 계산하는 함수
	private static long intervalSum(int start, int end) {
		return prefixSum(end) - prefixSum(start - 1);
	}

	// i번째 수까지의 누적 합을 계산하는 함수
	private static long prefixSum(int i) {
		long result = 0;
		while (i > 0) {
			result += tree[i];
			// 0이 아닌 마지막 비트만큼 빼가면서 이동
			i -= (i & -i);
		}
		return result;
	}
}