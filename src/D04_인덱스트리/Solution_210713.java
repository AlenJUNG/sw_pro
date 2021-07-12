package D04_인덱스트리;

import java.io.*;
import java.util.*;

/*
 * 구간합 중, 시도 많이
 * Update : 2021.07.13
 */

public class Solution_210713 {

	static int TC, N, Q, size;
	static long ans, tree[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D04_인덱스트리/D04_ex01_연습P0019_구간합.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			Q = Integer.parseInt(br.readLine());

			size = 1;
			while (size < N) {
				size *= 2;
			}

			tree = new long[size * 2];

			// opt이 0이면 x번째 수를 y로 변경
			// opt이 1이면 x번째 수부터 y번째 수까지의 합을 구함
			int opt, x, y;
			ans = 0;
			for (int i = 1; i <= Q; i++) {
				st = new StringTokenizer(br.readLine());

				opt = Integer.parseInt(st.nextToken());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());

				if (opt == 0) {
					change(x, y);
				} else {
					ans += getSum(x, y);
				}

			}
			ans %= 1000000007;
			bw.write("#" + tc + " " + ans + "\n");

		}

		br.close();
		bw.flush();
		bw.close();

	}

	private static long getSum(int x, int y) {
		long res = 0;
		int start = size + x - 1;
		int end = size + y - 1;

		while (start <= end) {
			if (start % 2 == 1) {
				res += tree[start];
				start++;
			}
			if (end % 2 == 0) {
				res += tree[end];
				end--;
			}
			start /= 2;
			end /= 2;

		}
		return res;
	}

	// x번째 수를 y로 변경 후 업데이트
	private static void change(int x, int y) {
		int idx = size + x - 1;
		tree[idx] = y;
		idx /= 2;

		while (idx > 0) {
			tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
			idx /= 2;
		}
	}

}
