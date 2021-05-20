package D04_인덱스트리;

/* N = 6, (8 3 4 1 2 9), 1 ~ 4 구간 사이의 최대값, 최소값, gcd 구하기
 * 
 */

import java.util.*;
import java.io.*;

public class test {
	static int TC, N, start, end, arr[];
	static int size, tree[], ans_max, ans_min, ans_gcd;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D04_인덱스트리/D04_bs00_최대값구하기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());

			arr = new int[N + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			// 트리의 생성
			size = 1;
			while (size < N) {
				size *= 2;
			}

			tree = new int[size * 2];
			
			// 트리에 값 입력
			for (int i = 1; i <= N; i++) {
				int id = i + size - 1;
				tree[id] = arr[i];
			}
			
			// 구간 최대값 구하기
			// 1. bottom ~ top 최대값 업데이트
			for (int i = size - 1; i > 0; i--) {
				tree[i] = Math.max(tree[i * 2], tree[i * 2 + 1]);
			}
			
			// 구간 입력
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 구간 합 구하기
			ans_max = 0;
			ans_max = getMax(a, b);
			bw.write("#" + tc + " ans_max = " + ans_max + "\n");

			// 굳이 필요한 것 같진 않음
//			for (int i = N + 1; i <= size; i++) {
//				int id = i + size - 1;
//				tree[id] = 999;
//			}
			
			// 구간 최소값 구하기
			// bottom ~ top 최소값 업데이트
			for (int i = size - 1; i > 0; i--) {
				tree[i] = Math.min(tree[i * 2], tree[i * 2 + 1]);
			}

			ans_min = 0;
			ans_min = getMin(a, b);
			bw.write("#" + tc + " ans_min = " + ans_min + "\n");

			//구간 gcd 구하기
			// bottom ~ top gcd 구하기
			for (int i = size - 1; i > 0; i--) {
				tree[i] = GCD(tree[i * 2], tree[i * 2 + 1]);
			}
//			System.out.println(GCD(0, 1));

			ans_gcd = 0;
			ans_gcd = get_gcd(a, b);
			bw.write("#" + tc + " ans_gcd = " + ans_gcd + "\n");

		}

		br.close();
		bw.flush();
		bw.close();
	}

	private static int get_gcd(int a, int b) {
		int res = 0;
		int l = a + size - 1;
		int r = b + size - 1;

		while (l <= r) {
			if (l % 2 == 1) {
				res = GCD(res, tree[l]);
				l++;
			}
			if (r % 2 == 0) {
				res = GCD(res, tree[r]);
				r--;
			}
			l /= 2;
			r /= 2;
		}

		return res;
	}

	// GCD 다시 하기
	private static int GCD(int a, int b) {
		if (a > b) {
			int temp;
			temp = a;
			a = b;
			b = temp;

		}

		int r = 0;

		while (true) {
			if (a == 0) {
				return b;
			}
			r = b % a;
			b = a;
			a = r;
		}
	}

	private static int getMin(int a, int b) {
		int res = 9999;
		int l = a + size - 1;
		int r = b + size - 1;

		while (l <= r) {
			if (l % 2 == 1) {
				res = Math.min(res, tree[l]);
				l++;
			}
			if (r % 2 == 0) {
				res = Math.min(res, tree[r]);
				r--;
			}
			l /= 2;
			r /= 2;
		}
		return res;
	}

	private static int getMax(int a, int b) {
		int res = 0;
		int l = size + a - 1;
		int r = size + b - 1;

		while (l <= r) {
			if (l % 2 == 1) {
				res = Math.max(res, tree[l]);
				l++;
			}
			if (r % 2 == 0) {
				res = Math.max(res, tree[r]);
				r--;
			}
			l /= 2;
			r /= 2;
		}

		return res;
	}

}
