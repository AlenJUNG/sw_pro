package B03_순열과조합;
//https://coding-factory.tistory.com/607

//순열과 조합의 차이는 순서를 정하느냐 그렇지 않느냐의 차이
//순열 : 중국집 메뉴 5개 중 2개의 메뉴를 순서대로 먹는 경우의 수 
//		 > (a, b) 와 (b, a)는 다른 메뉴
//조합 : 중국집 메뉴 5개 중 2개의 메뉴를 주문하는 경우의 수 
//		 > (a, b) 와 (b, a)는 같은 메뉴 

// 1. 조합은 서로 다른 N개 중에서 R개를 순서와 상관없이 고름
// 2. 순열의 경우의 수에서 R개를 고르는 경우의 수가 모두 같은 경우로 간주
// nCr = nPr / r!
// 조합의 성질인 '파스칼의 삼각형'을 이용하면
// nCr = n-1_C_r-1 + n-1_C_r

// Q) N개 중에서 R개를 순서와 상관없이 고를 때 경우의 수를 1000000007로 나눈 나머지를 구하시오

import java.util.*;
import java.io.*;

public class B03_ex02_nCr {
	static int N, R;
	static final long MOD = 1000000007L;
	static int ans;
	static final int MAX = 1000; // MAX를 N + 1 값으로 설정할 것
	static long[][] D = new long[MAX + 1][MAX + 1];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/B03_순열과조합/B03_ex02_nCr.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		get_nCr_pascal(N, R, D);

		System.out.println(D[N][R] % MOD);

	}

	// 조합의 성질인 '파스칼의 삼각형'을 이용하여 nCr 구하기
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