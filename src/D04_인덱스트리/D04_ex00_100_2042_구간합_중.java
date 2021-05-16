package D04_인덱스트리;
// https://subbak2.tistory.com/17

import java.util.*;
import java.io.*;

/*
 * 첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000,000)과 M(1 ≤ M ≤ 10,000), K(1 ≤ K ≤ 10,000) 가 주어진다. 
 * M은 수의 변경이 일어나는 횟수이고, K는 구간의 합을 구하는 횟수이다. 그리고 둘째 줄부터 N+1번째 줄까지 N개의 수가 주어진다. 
 * 그리고 N+2번째 줄부터 N+M+K+1번째 줄까지 세 개의 정수 a, b, c가 주어지는데, 
 * a가 1인 경우 b(1 ≤ b ≤ N)번째 수를 c로 바꾸고 
 * a가 2인 경우에는 b(1 ≤ b ≤ N)번째 수부터 
 * c(b ≤ c ≤ N)번째 수까지의 합을 구하여 출력하면 된다.
 */

public class D04_ex00_100_2042_구간합_중 {
	static int N, M, K, size;
	static long ans;
	static int MAX_N = 1000000;
	static long idx_Tree[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D04_인덱스트리/D04_ex00_100_2042_구간합_중.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// 인덱스트리 크기 특징 및 선언 (N보다 큰 최소 2^N)
		size = 1;
		while (size < N) {
			size *= 2;
		}

		idx_Tree = new long[size * 2];

		for (int i = 1; i <= N; i++) {
			long n = Long.parseLong(br.readLine());
			int idx = size + i - 1;
			idx_Tree[idx] = n;
		}

		// 인덱스트리 전체 업데이트
		for (int i = size - 1; i > 0; i--) {
			idx_Tree[i] = idx_Tree[i * 2] + idx_Tree[i * 2 + 1];
		}

		ans = 1;
		for (int i = 1; i <= M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int opt = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			/*
			 * a가 1인 경우 b(1 ≤ b ≤ N)번째 수를 c로 바꾸고 a가 2인 경우에는 b(1 ≤ b ≤ N)번째 수부터 c(b ≤ c ≤
			 * N)번째 수까지의 합을 구하여 출력하면 된다.
			 */
			if (opt == 1) {
				change(a, b);
			} else {
				int endid = (int) b;
				ans = getSum(a, endid);
				bw.append(ans + " ");
				bw.newLine();
			}
		}

		br.close();
		bw.flush();
		bw.close();

	}

	private static long getSum(int a, int b) {
		long res = 0;
		int start = size + a - 1;
		int end = size + b - 1;

		while (start <= end) {
			if (start % 2 == 1) {
				res += idx_Tree[start];
				start++;
			}
			if (end % 2 == 0) {
				res += idx_Tree[end];
				end--;
			}
			start /= 2;
			end /= 2;
		}
		return res;
	}

	// 인덱스트리 값 수정
	private static void change(int no, long x) {
		int idx = size + no - 1;

		// 굳이 차이를 넣지 않아도 값을 통째로 넣고 업데이트 해도 됌
		idx_Tree[idx] = x;
		idx /= 2;

		while (idx > 0) {
			idx_Tree[idx] = idx_Tree[idx * 2] + idx_Tree[idx * 2 + 1];
			idx /= 2;
		}
		return;
		
	}

}
