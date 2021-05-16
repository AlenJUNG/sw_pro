package D04_�ε���Ʈ��;
// https://subbak2.tistory.com/17

import java.util.*;
import java.io.*;

/*
 * ù° �ٿ� ���� ���� N(1 �� N �� 1,000,000)�� M(1 �� M �� 10,000), K(1 �� K �� 10,000) �� �־�����. 
 * M�� ���� ������ �Ͼ�� Ƚ���̰�, K�� ������ ���� ���ϴ� Ƚ���̴�. �׸��� ��° �ٺ��� N+1��° �ٱ��� N���� ���� �־�����. 
 * �׸��� N+2��° �ٺ��� N+M+K+1��° �ٱ��� �� ���� ���� a, b, c�� �־����µ�, 
 * a�� 1�� ��� b(1 �� b �� N)��° ���� c�� �ٲٰ� 
 * a�� 2�� ��쿡�� b(1 �� b �� N)��° ������ 
 * c(b �� c �� N)��° �������� ���� ���Ͽ� ����ϸ� �ȴ�.
 */

public class D04_ex00_100_2042_������_�� {
	static int N, M, K, size;
	static long ans;
	static int MAX_N = 1000000;
	static long idx_Tree[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D04_�ε���Ʈ��/D04_ex00_100_2042_������_��.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// �ε���Ʈ�� ũ�� Ư¡ �� ���� (N���� ū �ּ� 2^N)
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

		// �ε���Ʈ�� ��ü ������Ʈ
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
			 * a�� 1�� ��� b(1 �� b �� N)��° ���� c�� �ٲٰ� a�� 2�� ��쿡�� b(1 �� b �� N)��° ������ c(b �� c ��
			 * N)��° �������� ���� ���Ͽ� ����ϸ� �ȴ�.
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

	// �ε���Ʈ�� �� ����
	private static void change(int no, long x) {
		int idx = size + no - 1;

		// ���� ���̸� ���� �ʾƵ� ���� ��°�� �ְ� ������Ʈ �ص� ��
		idx_Tree[idx] = x;
		idx /= 2;

		while (idx > 0) {
			idx_Tree[idx] = idx_Tree[idx * 2] + idx_Tree[idx * 2 + 1];
			idx /= 2;
		}
		return;
		
	}

}
