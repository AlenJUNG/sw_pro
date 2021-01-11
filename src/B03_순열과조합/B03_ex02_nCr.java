package B03_����������;
//https://coding-factory.tistory.com/607

//������ ������ ���̴� ������ ���ϴ��� �׷��� �ʴ����� ����
//���� : �߱��� �޴� 5�� �� 2���� �޴��� ������� �Դ� ����� �� 
//		 > (a, b) �� (b, a)�� �ٸ� �޴�
//���� : �߱��� �޴� 5�� �� 2���� �޴��� �ֹ��ϴ� ����� �� 
//		 > (a, b) �� (b, a)�� ���� �޴� 

// 1. ������ ���� �ٸ� N�� �߿��� R���� ������ ������� ��
// 2. ������ ����� ������ R���� ���� ����� ���� ��� ���� ���� ����
// nCr = nPr / r!
// ������ ������ '�Ľ�Į�� �ﰢ��'�� �̿��ϸ�
// nCr = n-1_C_r-1 + n-1_C_r

// Q) N�� �߿��� R���� ������ ������� �� �� ����� ���� 1000000007�� ���� �������� ���Ͻÿ�

import java.util.*;
import java.io.*;

public class B03_ex02_nCr {
	static int N, R;
	static final long MOD = 1000000007L;
	static int ans;
	static final int MAX = 1000; // MAX�� N + 1 ������ ������ ��
	static long[][] D = new long[MAX + 1][MAX + 1];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/B03_����������/B03_ex02_nCr.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		get_nCr_pascal(N, R, D);

		System.out.println(D[N][R] % MOD);

	}

	// ������ ������ '�Ľ�Į�� �ﰢ��'�� �̿��Ͽ� nCr ���ϱ�
	// nCr = n-1_C_r-1 + n-1_C_r
	private static long get_nCr_pascal(int n, int r, long[][] d) {
		d[0][0] = 1;

		for (int i = 1; i < MAX; i++) {
			d[i][0] = 1;
			for (int j = 1; j <= i; j++) {
				d[i][j] = d[i - 1][j - 1] + d[i - 1][j];
			}
		}

		return d[n][r];
	}
}