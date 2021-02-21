package D04_인덱스트리;
// 세그먼트 트리

import java.util.*;
import java.io.*;

public class D04_ex00_100_2042_구간합_중 {
	static long index_Tree[];
	static long ans;
	static int N, M, K, size;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D04_인덱스트리/D04_ex00_100_2042_구간합_중.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		size = 1;
		while (size < N) {
			size *= 2;
		}

		index_Tree = new long[size * 2];

		long x;
		for (int i = 1; i <= N; i++) {
			x = Long.parseLong(br.readLine());
			index_Tree[i + size - 1] = x;
		}

		for (int i = size - 1; i > 0; i--) {
			index_Tree[i] = index_Tree[i * 2] + index_Tree[i * 2 + 1];
		}

		// a가 1인 경우 b번째 수를 c로 바꾸기, 2인 경우 구간합 출력

		int opt, a, b;
		for (int i = 1; i <= M + K; i++) {
			st = new StringTokenizer(br.readLine());
			opt = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			if (opt == 1) {
				change_update_Tree(a, b);
			} else {
				ans = getSum(a, b);
				bw.write(ans + "\n");
			}
		}

		br.close();
		bw.flush();
		bw.close();
	}

	private static long getSum(int s, int e) {
		long res = 0;
		
		int start = s + size - 1;
		int end = e + size - 1;

		while (start <= end) {
			if (start % 2 == 1) {
				res += index_Tree[start];
				start++;
			}
			if (end % 2 == 0) {
				res += index_Tree[end];
				end--;
			}
			start /= 2;
			end /= 2;
		}

		return res;
	}

	private static void change_update_Tree(int target, int value) {
		target = target + size - 1;
		int plus = (int) (value - index_Tree[target]);

		while (target >= 1) {
			index_Tree[target] += plus;
			target /= 2;
		}

	}

}
