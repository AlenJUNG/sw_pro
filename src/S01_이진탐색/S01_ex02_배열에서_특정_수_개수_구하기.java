package S01_이진탐색;

import java.io.*;
import java.util.*;

public class S01_ex02_배열에서_특정_수_개수_구하기 {
	static int N, X, min_ans, max_ans, res;
	static int arr[];
	static boolean check;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/S01_이진탐색/S01_ex02_배열에서_특정_수_개수_구하기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 문제에서 정렬이 되어 있다고 가정
		// 최대 index - 최소 index
		res = getMax_binarySearch(arr, 0, N - 1) - getMin_binarySearch(arr, 0, N - 1);
		
		// 동일값이 없었다면 -1 출력 예외처리
		if (check) {
			System.out.println(res);
		} else {
			System.out.println(-1);
		}

	}

	private static int getMax_binarySearch(int[] map, int start, int end) {
		while (start <= end) {
			int mid = start + (end - start) / 2;
			
			if (X < arr[mid]) {
				end = mid - 1;
			} else {
				// 동일값이 있는 경우 true 처리
				if (arr[mid] == X) {
					check = true;
				}
				// 우측 탐색 > Max Index 구하기
				max_ans = mid;	// 우측 탐색 결과 업데이트
				start = mid + 1;
			}
		}

		return max_ans;
	}

	private static int getMin_binarySearch(int[] map, int start, int end) {
		while (start <= end) {
			int mid = start + (end - start) / 2;
			// 좌측 탐색 > Min Index 구하기
			if (X > arr[mid]) {
				start = mid + 1;
				min_ans = mid;	// 좌측 탐색 결과 업데이트
			} else {
				if (arr[mid] == X) {
					check = true;
				}
				end = mid - 1;
			}
		}

		return min_ans;
	}
}
