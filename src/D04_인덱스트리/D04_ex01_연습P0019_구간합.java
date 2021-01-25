package D04_�ε���Ʈ��;

import java.util.*;
import java.io.*;

public class D04_ex01_����P0019_������ {
	static int T, N, Q;
	static int size;
	static long arr[];
	static long ans;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D04_�ε���Ʈ��/D04_ex01_����P0019_������.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine());
			Q = Integer.parseInt(br.readLine());

			// STEP01. �ε��� ũ�� ��� �� ����
			// �ε��� Ʈ��(���� Ʈ��)�� �߿��� ���� �̿�
			// * 2�� �������� N���� ū ���� ���� leaf node size�� �� �� �ִ�.
			// * 1 ~ leaf node - 1�� ũ��� 2^(Index[leaf node start ����]) - 1
			size = 1;
			while (size < N) {
				size *= 2; // size�� �ε��� Ʈ�� leaf node�� �������� �ȴ�.
			}

			arr = new long[size * 2 + 1];

			// STEP02. leaf node���� 1 ~ N value �Է�
			for (int i = size; i <= size + N; i++) {
				arr[i] = i - size + 1;
			}

			// STEP03. 1 ~ leaf node -1�� �ε��� �������� ���� > �ε��� Ʈ�� �ϼ�
			// �ε��� Ʈ��(���� Ʈ��)�� �߿��� ���� �̿�
			// * �θ��� = �� �ڽ� ����� ��
			for (int i = size - 1; i >= 1; i--) {
				arr[i] = arr[i * 2] + arr[i * 2 + 1];
			}

			int opt, x, y;
			ans = 0;
			for (int i = 1; i <= Q; i++) {
				st = new StringTokenizer(br.readLine());
				opt = Integer.parseInt(st.nextToken());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());

				if (opt == 0) { // x��° ���� y�� ������Ʈ
					update(x, y);
				} else { // x��° ������ y��° �������� ���� ����
					ans += getSum(x, y);
				}
			}

			System.out.println(ans % 1000000007);

		}
		br.close();

	}

	private static long getSum(int start, int end) {
		long sum = 0; // * ���������� �ʱ�ȭ �ݵ�� �ʿ�

		int s, e;
		s = start + size - 1; // ���� ���� start idx ���ϱ�
		e = end + size - 1; // ���� ���� end idx ���ϱ�

		while (s <= e) {
			if (s % 2 == 1) { // s�� Ȧ���� ��쿡��
				sum += arr[s]; // 1. �� ��ġ�� �� ���ϰ�
				s++; // 2. ���������� �� ĭ �̵� (�θ��尡 ���������� �ٲ�)
			}
			if (e % 2 == 0) { // e�� ¦���� ��쿡��
				sum += arr[e]; // 1. �� ��ġ�� �� ���ϰ�
				e--; // 2. �������� �� ĭ �̵� (�θ��尡 �������� �ٲ�)
			}
			s /= 2; // ���� ������� �θ���� �̵�
			e /= 2; // ���� ������� �θ���� �̵�
		}

		return sum;

	}

	// target ��° ���� value�� ����
	private static void update(int target, int value) {
		target = target + size - 1; // target : ������Ʈ�� �ε��� ���ϱ�
		int plus = (int) (value - arr[target]); // ** plus = ������ �� - ���� ��

		// bottom - up : ���� ������ "����" ��ŭ ������Ʈ�� �ε��� ~ root ������ ������ ���� ����
		while (target >= 1) {
			arr[target] += plus;
			target /= 2;
		}
	}

}
