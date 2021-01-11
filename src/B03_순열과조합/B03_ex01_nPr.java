package B03_순열과조합;
// https://coding-factory.tistory.com/607

// 순열과 조합의 차이는 순서를 정하느냐 그렇지 않느냐의 차이
// 순열 : 중국집 메뉴 5개 중 2개의 메뉴를 순서대로 먹는 경우의 수 
//		 > (a, b) 와 (b, a)는 다른 메뉴
// 조합 : 중국집 메뉴 5개 중 2개의 메뉴를 주문하는 경우의 수 
//		 > (a, b) 와 (b, a)는 같은 메뉴

// 순열 > 서로 다른 n개중 r개를 골라 순서를 고려해 나열한 경우의 수
// nPr = n * n(n-1) * (n-1)...(n-r+1) : nPr은 중복 상관 없음

// Q) N개 중에서 R개를 순서대로 고를 때 경우의 수를 1000000007로 나눈 나머지를 구하시오

import java.util.*;
import java.io.*;

public class B03_ex01_nPr {
	static int N, R;
	static final long MOD = 1000000007L;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/B03_순열과조합/B03_ex01_nPr.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		System.out.println(permu(N, R));

	}

	private static long permu(int n, int r) {
		long ans = 1; // int 선언 시, 항상 터질 수 있다는 것을 염두에 둘 것

		// nPr = n * n(n-1) * (n-1)...(n-r+1) : nPr은 중복 상관 없음
		for (int i = n; i >= n - r + 1; i--) {
			ans *= i;
		}

		ans %= MOD;

		return ans;
	}

}
