package O04_ex01_특정합연속수열개수;

import java.util.*;
import java.io.*;

// * 투 포인터의 활용 알고리즘
// 1. 시작점과(start) 끝점(end)이 첫 번째 원소의 인덱스(0)을 가리키도록 함
// 2. 현재 부분합이 M과 같다면 카운트
// 3. 현재 부분합이 M보다 작다면 end += 1
// 4. 현재 부분합이 M보다 크거나 같다면 start += 1
// 5. 모든 경우를 확인할 때까지 2 ~ 4번 과정 반복

public class O04_ex01_특정합연속수열개수 {
	static int N, M;
	static int arr[];

	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream(null));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = 5; // 데이터의 개수
		M = 5; // 찾고자하는 부분합
		arr = new int[] { 1, 2, 3, 2, 5 }; // 전체 수열

		int cnt = 0;
		int intervalSum = 0;
		int end = 0; // * end를 초기값 0으로 맞춰준다

		for (int start = 0; start < N; start++) {
			// end 이동 조건 2개 동안 반복
			while (intervalSum < M && end < N) {
				intervalSum += arr[end];
				end += 1;
			}
			// 부분합 찾으면 cnt 증가
			if (intervalSum == M) {
				cnt += 1;
			}
			// start를 이동시키기 위해 초기값 제거
			intervalSum -= arr[start];
		}
		System.out.println(cnt);
	}
}
