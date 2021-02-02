package B01_유클리드;

import java.io.*;
import java.util.*;

// https://coding-factory.tistory.com/599
// 유클리드 호제법
public class B01_유클리드 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/B01_유클리드/B01_유클리드.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 2개 숫자의 최대공약수 구하기
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		System.out.println(GCD(a, b));

		// 3개 숫자의 최대공약수 구하기
		st = new StringTokenizer(br.readLine());

		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());

		int res = GCD(c, d);
		System.out.println(GCD(res, e));

		// 3개 숫자의 최대공약수 구하기 > 자료구조를 배열로 선언, 3개 이상 숫자도 가능
		int group[] = new int[3];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < group.length; i++) {
			group[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < group.length - 1; i++) {
			int x = GCD(group[i], group[i + 1]);
			group[i + 1] = x;
		}
		System.out.println(group[group.length - 1]);

		// 최소공배수 구하기
		st = new StringTokenizer(br.readLine());
		int f = Integer.parseInt(st.nextToken());
		int g = Integer.parseInt(st.nextToken());
		System.out.println(LCM(f, g));

	}

	// "r이 0이면 그때 b가 최대공약수" 원리
	private static int GCD(int a, int b) {
		// 항상 a가 더 크게 좌우정렬
		if (a < b) {
			int temp;
			temp = a;
			a = b;
			b = temp;
		}
		// 나눈 나머지가 0이 될때까지 무한 반복
		while (true) {
			int r = a % b;

			if (r == 0) {
				return b;
			}

			a = b;
			b = r;
		}
	}
	
	// 최소공배수 = a * b / GCD(a, b) > GCD를 먼저 구현할 것
	private static int LCM(int f, int g) {
		return f * g / GCD(f, g);
	}

}
