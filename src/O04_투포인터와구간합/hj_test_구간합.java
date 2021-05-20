package O04_투포인터와구간합;

import java.io.*;
import java.util.*;

// Q) 2번째에서 4번째까지 구간합 구하시오.

public class hj_test_구간합 {
	static int arr[], sumArr[];

	public static void main(String[] args) {
		arr = new int[] { 10, 20, 30, 40, 50 };
		sumArr = new int[5];
		
		// 구간합 구하기
		sumArr[0] = arr[0];
		for (int i = 0; i <= 3; i++) {
			sumArr[i + 1] = sumArr[i] + arr[i + 1];
		}
		
		
		// 일반 순서
		int end = 4;
		int start = 2;
		
		// 배열 순서
		int e = end - 1;
		int s = start - 1;
		
		// ans = e까지 구간합 - (s - 1)까지 구간합
		int ans = sumArr[e] - sumArr[s - 1];
		
		System.out.println(ans);
	}

}
