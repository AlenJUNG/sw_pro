package B01_��Ŭ����;

import java.io.*;
import java.util.*;

// �ּҰ����(LCM), �ִ�����(GDC) ���ϱ�
public class B01_��Ŭ����_hj {
	static int TC;
	static int arr[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/B01_��Ŭ����/B01_��Ŭ����.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st;
		StringBuilder sb;

		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			sb = new StringBuilder();

			int a, b, aa;
			a = Integer.parseInt(st.nextToken());
			aa = a;
			while (st.hasMoreTokens()) {
				b = Integer.parseInt(st.nextToken());
				a = GDC(a, b);
				aa = LCM(aa, b);
			}

			bw.write("#" + tc + " " + a + " " + aa + "\n");

		}

		br.close();
		bw.flush();
		bw.close();

	}

	private static int LCM(int a, int b) {
		return a * b / GDC(a, b);
	}

	private static int GDC(int a, int b) {
		if (a > b) {
			int temp;
			temp = a;
			a = b;
			b = temp;
		}

		int r = 0;
		while (a != 0) {
			if (b % a != 0) {
				r = b % a;
				b = a;
				a = r;
			} else {
				return a;
			}
		}

		return b;
	}

}
