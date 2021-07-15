package D04_�ε���Ʈ��;

import java.io.*;
import java.util.*;

/*
 * ���� : 84 �˰��������ȸ
 * ���� : 210715
 * �õ� : 3
 */

public class Solution_210715_�˰��������ȸ {

	static int TC, N, A[], G[], gcd_tree[], size;
	static long sum_tree[];
	static long ans;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D04_�ε���Ʈ��/D04_ex3_P0084_�˰��������ȸ_�߻�.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st1 = null;
		StringTokenizer st2 = null;

		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			A = new int[N + 1];
			G = new int[N + 1];

			st1 = new StringTokenizer(br.readLine());
			st2 = new StringTokenizer(br.readLine());
			int a, b;
			for (int i = 1; i <= N; i++) {
				a = Integer.parseInt(st1.nextToken());
				b = Integer.parseInt(st2.nextToken());
				A[i] = a;
				G[i] = b;
			}

			size = 1;
			while (size < N) {
				size *= 2;
			}

			gcd_tree = new int[size * 2];
			sum_tree = new long[size * 2];

			int id;
			for (int i = 1; i <= N; i++) {
				id = i + size - 1;
				gcd_tree[id] = A[i];
				sum_tree[id] = A[i];
			}

			for (int i = size - 1; i > 0; i--) {
				gcd_tree[i] = GCD(gcd_tree[2 * i], gcd_tree[2 * i + 1]);
				sum_tree[i] = sum_tree[2 * i] + sum_tree[2 * i + 1];
			}

			System.out.println("check");

			ans = 0;
			int start, end, gcd;
			for (int i = 1; i <= N; i++) {
				start = i - G[i];
				end = i + G[i];
				
				if (start < 1)
					start = 1;
				
				if (end > N)
					end = N;

				gcd = getGCD(start, end);

				// �ο����� gcd�� ������ �� ���� ���� �ջ���
				for (int j = start; j <= end; j++) {
					ans += A[j] / gcd;
					update_sum_tree(j, gcd);
					update_gcd_tree(j, gcd);
				}
			}

			bw.write("#" + tc + " " + ans + "\n");
		}

		br.close();
		bw.flush();
		bw.close();

	}

	private static void update_gcd_tree(int id, int gcd) {
		int idx = id + size - 1;
		gcd_tree[idx] -= gcd;
		idx /= 2;

		while (idx > 0) {
			gcd_tree[idx] = GCD(gcd_tree[idx * 2], gcd_tree[idx * 2 + 1]);
			idx /= 2;
		}

	}

	private static void update_sum_tree(int id, int gcd) {
		int idx = id + size - 1;
		sum_tree[idx] -= gcd;
		idx /= 2;

		while (idx > 0) {
			sum_tree[idx] -= gcd;
			idx /= 2;
		}

	}

	private static int getGCD(int start, int end) {
		int res = 0;
		int s = start + size - 1;
		int e = end + size - 1;

		while (s <= e) {
			if (s % 2 == 1) {
				res = GCD(res, gcd_tree[s]);
				s++;
			}
			if (e % 2 == 0) {
				res = GCD(res, gcd_tree[e]);
				e--;
			}
			s /= 2;
			e /= 2;
		}
		return res;
	}

	private static int GCD(int a, int b) {
		if (a > b) {
			int temp;
			temp = a;
			a = b;
			b = temp;
		}

		if (a == 0) {
			return b;
		}

		int r = 0;
		while (true) {
			r = b % a;
			if (r == 0) {
				return a;
			}
			b = a;
			a = r;
		}
	}
}
