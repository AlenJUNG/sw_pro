package O04_�������Ϳͱ�����;

import java.util.*;
import java.io.*;

// Q) 3��° ������ 4��° �������� ���� ���Ϸ��� ��� �ؾ��ұ�?

// ���λ� ��(Prefix Sum) �˰���
// �̸� �迭�� �����س��� ���߿� �̾ƾ���. > right - (left - 1)

public class O04_ex02_������ {
	static int N, M;
	static int arr[], prefixSum[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = 5;
		arr = new int[] { 10, 20, 30, 40, 50 };
		prefixSum = new int[6];

		// ���λ� ��(Prefix Sum) �迭 ���
		int sumValue = 0;

		for (int i = 0; i < N; i++) {
			sumValue += arr[i];
			// i��°������ �������� prefixSum[i + 1]�� ����
			prefixSum[i + 1] = sumValue;
		}

		// ������ ��� (����° ������ �׹�° ������)
		int left = 3;
		int right = 4;
		System.out.println(prefixSum[right] - prefixSum[left - 1]);

	}

}