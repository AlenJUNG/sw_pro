package D04_인덱스트리;

import java.io.*;
import java.util.*;

public class Main_210624 {
	static int TC, N, M, size;
	static long tree[], ans;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D04_인덱스트리/D04_ex01_연습P0019_구간합.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());

			size = 1;
			while (size < N) {
				size *= 2;
			}

			tree = new long[size * 2];

			for (int i = 1; i <= N; i++) {
				int id = i + size - 1;
				tree[id] = i;
			}

			for (int i = size - 1; i > 0; i--) {
				tree[i] = tree[2 * i] + tree[2 * i + 1];
			}

			int a, b, c;
			ans = 0;
			for (int i = 1; i <= M; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());

				if (a == 0) {
					change(b, c);
				} else {
					ans += getSum(b, c);
				}

			}

			ans %= 1000000007;

			bw.write("#" + tc + " " + ans + "\n");

		}

		br.close();
		bw.flush();
		bw.close();
	}

	private static long getSum(int b, int c) {
		long res = 0;
		int left = size + b - 1;
		int right = size + c - 1;

		while (left <= right) {
			if (left % 2 == 1) {
				res += tree[left];
				left++;
			}
			if (right % 2 == 0) {
				res += tree[right];
				right--;
			}
			left /= 2;
			right /= 2;
		}

		return res;

	}

	private static void change(int b, int c) {
		int id = size + b - 1;
		tree[id] = c;
		id /= 2;

		while (id > 0) {
			tree[id] = tree[2 * id] + tree[2 * id + 1];
			id /= 2;
		}
	}

}
