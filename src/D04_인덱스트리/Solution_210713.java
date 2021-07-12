package D04_�ε���Ʈ��;

import java.io.*;
import java.util.*;

/*
 * ������ ��, �õ� ����
 * Update : 2021.07.13
 */

public class Solution_210713 {

	static int TC, N, Q, size;
	static long ans, tree[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D04_�ε���Ʈ��/D04_ex01_����P0019_������.txt"));
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

			// opt�� 0�̸� x��° ���� y�� ����
			// opt�� 1�̸� x��° ������ y��° �������� ���� ����
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

	// x��° ���� y�� ���� �� ������Ʈ
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
