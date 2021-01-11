package B03_����������;
// https://coding-factory.tistory.com/607

// ������ ������ ���̴� ������ ���ϴ��� �׷��� �ʴ����� ����
// ���� : �߱��� �޴� 5�� �� 2���� �޴��� ������� �Դ� ����� �� 
//		 > (a, b) �� (b, a)�� �ٸ� �޴�
// ���� : �߱��� �޴� 5�� �� 2���� �޴��� �ֹ��ϴ� ����� �� 
//		 > (a, b) �� (b, a)�� ���� �޴�

// ���� > ���� �ٸ� n���� r���� ��� ������ ����� ������ ����� ��
// nPr = n * n(n-1) * (n-1)...(n-r+1) : nPr�� �ߺ� ��� ����

// Q) N�� �߿��� R���� ������� �� �� ����� ���� 1000000007�� ���� �������� ���Ͻÿ�

import java.util.*;
import java.io.*;

public class B03_ex01_nPr {
	static int N, R;
	static final long MOD = 1000000007L;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/B03_����������/B03_ex01_nPr.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		System.out.println(permu(N, R));

	}

	private static long permu(int n, int r) {
		long ans = 1; // int ���� ��, �׻� ���� �� �ִٴ� ���� ���ο� �� ��

		// nPr = n * n(n-1) * (n-1)...(n-r+1) : nPr�� �ߺ� ��� ����
		for (int i = n; i >= n - r + 1; i--) {
			ans *= i;
		}

		ans %= MOD;

		return ans;
	}

}
