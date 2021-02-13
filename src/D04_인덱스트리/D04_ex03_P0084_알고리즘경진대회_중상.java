package D04_인덱스트리;

import java.io.*;
import java.util.*;

public class D04_ex03_P0084_알고리즘경진대회_중상 {
	static int TC, N; // N = S사 건물의 층 수
	static int floorArray[][]; // [N][0] = 각 층에 근무하는 직원의 수, [N][1] = 각 층의 초대 가능층의 수
	static int gcdArray[];
	static long sumArray[];
	static long totalTeams;
	static int size;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D04_인덱스트리/D04_ex3_P0084_알고리즘경진대회_중상.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {

			N = Integer.parseInt(br.readLine());

			floorArray = new int[N + 1][2];
			totalTeams = 0L;	// ans

			
			// leaf Node sizing
			size = 1;
			while (size < N) {
				size *= 2;
			}
			// 직원 수 인덱스트리
			sumArray = new long[size * 2 + 1];
			// gcd 인덱스트리
			gcdArray = new int[size * 2 + 1];			

			StringTokenizer st1 = new StringTokenizer(br.readLine());
			StringTokenizer st2 = new StringTokenizer(br.readLine());

			for (int i = 1; i <= N; i++) {
				floorArray[i][0] = Integer.parseInt(st1.nextToken()); // 직원 수
				floorArray[i][1] = Integer.parseInt(st2.nextToken()); // 초대 가능 층
				
				// pos = leaf start 지점
				int pos = i + size - 1;
				// leaf 1번째부터 값 입력
				sumArray[pos] = floorArray[i][0];
				gcdArray[pos] = floorArray[i][0];
			}

			// Leaf에서 Top 까지 구간합 업데이트
			for (int i = 1; i <= N; i += 2) {
				int index = i + size - 1;
				
				while (index > 1) {
					int tempIndex = index / 2;
					// 직원 수 구간합 업데이트
					sumArray[tempIndex] = sumArray[tempIndex * 2] + sumArray[tempIndex * 2 + 1];
					// ** keyPoint GCD : 최대공약수 업데이트
					gcdArray[tempIndex] = getGCD(gcdArray[tempIndex * 2], gcdArray[tempIndex * 2 + 1]);
					index /= 2;
				}
			}
			
			// 층이 1개가 아니면
			if (N != 1) {
				// 층수 1부터 N까지
				for (int i = 1; i <= N; i++) {
					// scope = i층 초대 가능 층
					int scope = floorArray[i][1];
					// 초대 가능 층이 0이면 그 층의 대회 참가하는 팀의 최소 수는 1
					if (scope == 0) {
						totalTeams += 1;
						continue;
					}

					int start = 0;
					int end = 0;

					if (i - scope < 1) {
						start = 1;
					} else {
						start = i - scope;
					}

					if (i + scope > N) {
						end = N;
					} else {
						end = i + scope;
					}

					int finalGCD = getFinalGCD(start, end);
					long totalPersons = getSum(start, end);

					long tempTeams = (long) totalPersons / finalGCD;
					totalTeams += tempTeams;

				}
			// 층이 1개면 답은 1
			} else {
				totalTeams = 1L;
			}

			bw.write("#" + tc + " " + totalTeams + "\n");

		}

		br.close();
		bw.flush();
		bw.close();

	}

	// 구간 합을 구하는 함수
	private static long getSum(int start, int end) {
		long result = 0;

		int left = start + size - 1;
		int right = end + size - 1;

		while (left <= right) {
			if (left % 2 == 1) {
				result += sumArray[left];
				left++;
			}
			if (right % 2 == 0) {
				result += sumArray[right];
				right--;
			}

			left /= 2;
			right /= 2;
		}

		return result;
	}

	// 구간의 gcd를 구하는 함수
	private static int getFinalGCD(int start, int end) {
		int result = 0;
		int left = start + size - 1;
		int right = end + size - 1;

		while (left <= right) {
			if (left % 2 == 1) {
				result = getGCD(gcdArray[left], result);
				left++;
			}
			if (right % 2 == 0) {
				result = getGCD(gcdArray[right], result);
				right--;
			}

			left /= 2;
			right /= 2;

		}

		return result;
	}

	private static int getGCD(int a, int b) {
		if (a > b) {
			int temp;
			temp = a;
			a = b;
			b = temp;

		}
		int r = 0;

		while (true) {
			if (a == 0) {
				return b;
			}
			r = b % a;
			b = a;
			a = r;
		}
	}

}
