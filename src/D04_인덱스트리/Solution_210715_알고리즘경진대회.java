package D04_인덱스트리;

import java.io.*;
import java.util.*;

/*
 * 문제 : 알고리즘경진대회
 * 일자 : 210715
 * 시도 : 3
 */

public class Solution_210715_알고리즘경진대회 {

	static int TC, N, A[], G[], gcd_tree[], size;
	static long sum_tree[], ans;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D04_인덱스트리/D04_ex3_P0084_알고리즘경진대회_중상.txt"));
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
				A[i] = a; // 층별 직원 수
				G[i] = b; // 팀 구성 가능한 층
			}

			// 트리 사이징
			size = 1;
			while (size < N) {
				size *= 2;
			}

			gcd_tree = new int[size * 2];
			sum_tree = new long[size * 2];

			// 트리 선언
			int id;
			for (int i = 1; i <= N; i++) {
				id = i + size - 1;
				gcd_tree[id] = A[i];
				sum_tree[id] = A[i];
			}

			// 초기 전체 업데이트 : 초기 업데이트는 괜찮음
			for (int i = size - 1; i > 0; i--) {
				gcd_tree[i] = GCD(gcd_tree[2 * i], gcd_tree[2 * i + 1]);
				sum_tree[i] = sum_tree[2 * i] + sum_tree[2 * i + 1];
			}

			ans = 0;
			// 만약 층이 1개일 경우, 예외 케이스 생각할 것
			if (N == 1) {
				ans = 1;
			} else {
				int start, end, gcd;
				for (int i = 1; i <= N; i++) {
					start = i - G[i];
					end = i + G[i];

					if (start < 1)
						start = 1;

					if (end > N)
						end = N;
					
					gcd = getGCD(start, end);
					// 구간합으로 시간복잡도를 줄이려는 노력
					ans += getSum(start, end) / gcd;
				}

			}

			bw.write("#" + tc + " " + ans + "\n");
		}

		br.close();
		bw.flush();
		bw.close();

	}

	private static long getSum(int start, int end) {
		long res = 0;
		int s = start + size - 1;
		int e = end + size - 1;

		while (s <= e) {
			if (s % 2 == 1) {
				res += sum_tree[s];
				s++;
			}
			if (e % 2 == 0) {
				res += sum_tree[e];
				e--;
			}
			s /= 2;
			e /= 2;
		}
		return res;
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
