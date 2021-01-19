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

		// �������� ����(n), ���� Ƚ��(m), ���� �� ��� Ƚ��(k)
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// ��ü �������� ������ �ִ� 1,000,000��
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
			// ������Ʈ(update) ������ ���
			if (check == 1) {
				int idx = Integer.parseInt(st.nextToken());
				long value = Long.parseLong(st.nextToken());
				update(idx, value - arr[idx]); // �ٲ� ũ��(dif)��ŭ ����
				arr[idx] = value; // i��° ���� value�� ������Ʈ
				// ���� ��(interval sum) ������ ���
			} else {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				System.out.println(intervalSum(start, end));
			}
		}
	}

	// i��° ���� dif��ŭ ���ϴ� �Լ�
	private static void update(int i, long dif) {
		while (i <= N) {
			tree[i] += dif;
			i += (i & -i);
		}
	}

	// start���� end������ ���� ���� ����ϴ� �Լ�
	private static long intervalSum(int start, int end) {
		return prefixSum(end) - prefixSum(start - 1);
	}

	// i��° �������� ���� ���� ����ϴ� �Լ�
	private static long prefixSum(int i) {
		long result = 0;
		while (i > 0) {
			result += tree[i];
			// 0�� �ƴ� ������ ��Ʈ��ŭ �����鼭 �̵�
			i -= (i & -i);
		}
		return result;
	}
}