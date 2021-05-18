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
		int start = s - 1;
		int end = e - 1;
		
		while(true) {
			if(start > end) {
				return -1;
			}
			
			int mid = (start + end) / 2;
			
			if(a[mid] == target) {
				return mid;
			}else if(a[mid] < target) {
				end = mid - 1;
			}else {
				start = start + 1;
			}			
		}
	}

}
