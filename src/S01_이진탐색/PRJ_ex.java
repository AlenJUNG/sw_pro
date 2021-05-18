package S01_이진탐색;

import java.io.*;
import java.util.*;

/* 3, 5, 7, 17 이 몇 번째 위치하는지 찾아라
 * arr = {1, 4, 5, 7, 7, 10, 15, 17, 20 , 21}
 * findNo = {3, 5, 7, 17}
 */

public class PRJ_ex {
	static int arr[] = {1, 4, 5, 7, 7, 10, 15, 17, 20 , 21};
	static int findNo[] = {3, 5, 7, 17};
	
	public static void main(String[] args) throws IOException{

		for(int i = 1; i <= findNo.length; i++) {
			System.out.println(binarySearch(arr, 1, 10, findNo[i - 1]));
		}

	}

	private static int binarySearch(int[] a, int s, int e, int target) {
		// 일반 순서에 따른 배열 순서로 치환
		// 경우에 따라서는 필요 없을 수 있음
		int start = s - 1;
		int end = e - 1;

		while (true) {
			// 답이 없는 경우 -1 출력
			if (start > end) {
				return -1;
			}
			
			// ** mid값 계산 (소수점이하는 자동으로 절삭)
            // int mid = (start + end) / 2;	의 경우
			// start + end 값이 int 최대값 초과시 overflow 나기 때문에
			// ** 아래 code로 사전에 방지할 것
			
			int mid = start + ((end - start) / 2);

			if (a[mid] == target) {
				return mid;
			} else if (a[mid] < target) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
	}

}
