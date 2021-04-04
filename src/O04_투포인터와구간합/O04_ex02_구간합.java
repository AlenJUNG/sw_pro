package O04_투포인터와구간합;

import java.util.*;
import java.io.*;

// Q) 3번째 수부터 4번째 수까지의 합을 구하려면 어떻게 해야할까?

// 접두사 합(Prefix Sum) 알고리즘
// 미리 배열에 저장해놓고 나중에 뽑아쓴다. > right - (left - 1)

public class O04_ex02_구간합 {
	static int N, M;
	static int arr[], prefixSum[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = 5;
		arr = new int[] { 10, 20, 30, 40, 50 };
		prefixSum = new int[6];

		// 접두사 합(Prefix Sum) 배열 계산
		int sumValue = 0;

		for (int i = 0; i < N; i++) {
			sumValue += arr[i];
			// i번째까지의 구간합을 prefixSum[i + 1]에 저장
			prefixSum[i + 1] = sumValue;
		}

		// 구간합 계산 (세번째 수부터 네번째 수까지)
		int left = 3;
		int right = 4;
		System.out.println(prefixSum[right] - prefixSum[left - 1]);

	}

}