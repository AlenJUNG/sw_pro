package O04_투포인터와구간합;
// 2021.05.20 Update

import java.util.*;
import java.io.*;

public class hj_test {
	static int N, M, arr[];
	static int intervalSum, end, cntM;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = 5; // 데이터의 개수
		M = 5; // 찾고자하는 부분합
		
		// 배열 1 ~ N까지를 습관화할 것
		arr = new int[] { 0, 1, 2, 3, 2, 5 }; // 전체 수열		
				
		cntM = 0;
		intervalSum = 0;
		end = 1;		
		
		// start를 1부터 N까지
		for(int start = 1; start <= N; start++) {
			// 조건 1 : end가 N 범위 이내여야할 것
			// 조건 2 : intervalSum이 M보다 작아야할 것
			while(end <= N && intervalSum < M) {
				intervalSum += arr[end];
				end++;
			}
			
			if(intervalSum == M) {
				cntM++;
			}
			
			intervalSum -= arr[start];
		}
		
		System.out.println(cntM);
		
		

	}

}
