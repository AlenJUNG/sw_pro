package O04_ex01_Ư���տ��Ӽ�������;

import java.util.*;
import java.io.*;

// * �� �������� Ȱ�� �˰���
// 1. ��������(start) ����(end)�� ù ��° ������ �ε���(0)�� ����Ű���� ��
// 2. ���� �κ����� M�� ���ٸ� ī��Ʈ
// 3. ���� �κ����� M���� �۴ٸ� end += 1
// 4. ���� �κ����� M���� ũ�ų� ���ٸ� start += 1
// 5. ��� ��츦 Ȯ���� ������ 2 ~ 4�� ���� �ݺ�

public class O04_ex01_Ư���տ��Ӽ������� {
	static int N, M;
	static int arr[];

	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream(null));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = 5; // �������� ����
		M = 5; // ã�����ϴ� �κ���
		arr = new int[] { 1, 2, 3, 2, 5 }; // ��ü ����

		int cnt = 0;
		int intervalSum = 0;
		int end = 0; // * end�� �ʱⰪ 0���� �����ش�

		for (int start = 0; start < N; start++) {
			// end �̵� ���� 2�� ���� �ݺ�
			while (intervalSum < M && end < N) {
				intervalSum += arr[end];
				end += 1;
			}
			// �κ��� ã���� cnt ����
			if (intervalSum == M) {
				cnt += 1;
			}
			// start�� �̵���Ű�� ���� �ʱⰪ ����
			intervalSum -= arr[start];
		}
		System.out.println(cnt);
	}
}