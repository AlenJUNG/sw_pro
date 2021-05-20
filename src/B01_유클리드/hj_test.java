package B01_��Ŭ����;

import java.io.*;
import java.util.*;

// Q : 4���� ���̽��� ���Ͽ� GCD(�ִ�����), LCM(�ּҰ����)�� ���� ���Ͽ���
public class hj_test {
	static int TC;
	static int ans_GCD, ans_LCM;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/B01_��Ŭ����/B01_��Ŭ����.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			ans_GCD = 0;
			ans_LCM = 0;

			st = new StringTokenizer(br.readLine());
			int a, b, aa;
			a = Integer.parseInt(st.nextToken());
			aa = a;
			while (st.hasMoreTokens()) {
				b = Integer.parseInt(st.nextToken());
				a = GCD1(a, b);
				aa *= b;
			}

			ans_GCD = a;
			ans_LCM = aa / ans_GCD;

			bw.write("#" + tc + " GCD = " + ans_GCD + ", LCM = " + ans_LCM + "\n");

		}

		br.close();
		bw.flush();
		bw.close();

	}
	
	private static int GCD1(int a, int b) {
		if(a > b) {
			int temp;
			temp = a;
			a = b;
			b = temp;
		}
		
		if(a == 0) {
			return 0;
		}
		
		while(true) {
			if(b % a == 0) {
				return a;
			}
			int r = b % a;
			b = a;
			a = r;
		}
	}

	private static int GCD(int a, int b) {
		if (a > b) {
			int temp;
			temp = a;
			a = b;
			b = temp;
		}

		int r = 0;
		
		// * 0�� ���Ե��� ���� �� �ݺ��� ����
		while (a != 0) {
			// 0���� ��Ȯ�� ������ �������� �ʴ´ٸ� �ݺ�
			if (b % a != 0) {
				r = b % a;
				b = a;
				a = r;
			// 0���� ��Ȯ�� ������ �������� a ���
			} else {
				return a;
			}
		}
		// * 0�� ���ԵǾ� �ִٸ� 0 return
		return 0;
	}

}
