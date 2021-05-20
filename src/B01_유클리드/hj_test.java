package B01_유클리드;

import java.io.*;
import java.util.*;

// Q : 4가지 케이스에 대하여 GCD(최대공약수), LCM(최소공배수)를 각각 구하여라
public class hj_test {
	static int TC;
	static int ans_GCD, ans_LCM;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/B01_유클리드/B01_유클리드.txt"));
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
		
		// * 0이 포함되지 않을 때 반복문 동작
		while (a != 0) {
			// 0으로 정확히 나누어 떨어지지 않는다면 반복
			if (b % a != 0) {
				r = b % a;
				b = a;
				a = r;
			// 0으로 정확히 나누어 떨어지면 a 출력
			} else {
				return a;
			}
		}
		// * 0이 포함되어 있다면 0 return
		return 0;
	}

}
