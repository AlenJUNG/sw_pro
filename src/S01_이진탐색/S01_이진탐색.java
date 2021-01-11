package S01_이진탐색;

import java.io.*;
import java.util.*;

// 01. 이진 탐색 (Binary Search Algorithm)
// 주의 1 : 정렬 되었거나 또는 정렬이 가능한 리스트에만 사용
// 주의 2 : 탈출 조건 명시 (start > end)

// Q) 10개의 데이터가 입력으로 주어졌을 때 X 데이터(7) 가 몇 번째에 있는지 구하라

public class S01_이진탐색 {
	static int N, arr[];
	static int X = 7;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/S01_이진탐색/S01_이진탐색.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = 10; // 총 데이터는 10개 > 문제 설정
		arr = new int[10];
		for (int i = 0; i < 10; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(binary_Search(arr, 0, N, X));

		br.close();

	}

	private static int binary_Search(int[] num, int start, int end, int target) {

		while (true) {
			// ** num안에 target이 없을 때 탈출 조건 반드시 작성
			if (start > end) {
				return -1;
			}

			// ** mid값 계산 (소수점이하는 자동으로 절삭)
			int mid = (start + end) / 2;

			if (num[mid] == target) {
				return mid;
			} else if (num[mid] > target) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
	}
}
