package D04_�ε���Ʈ��;

import java.io.*;
import java.util.*;

public class D04_ex03_P0084_�˰��������ȸ_�߻� {
	static int TC, N; // N = S�� �ǹ��� �� ��
	static int floorArray[][]; // [N][0] = �� ���� �ٹ��ϴ� ������ ��, [N][1] = �� ���� �ʴ� �������� ��
	static int gcdArray[];
	static long sumArray[];
	static long totalTeams;
	static int size;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D04_�ε���Ʈ��/D04_ex3_P0084_�˰��������ȸ_�߻�.txt"));
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
			// ���� �� �ε���Ʈ��
			sumArray = new long[size * 2 + 1];
			// gcd �ε���Ʈ��
			gcdArray = new int[size * 2 + 1];			

			StringTokenizer st1 = new StringTokenizer(br.readLine());
			StringTokenizer st2 = new StringTokenizer(br.readLine());

			for (int i = 1; i <= N; i++) {
				floorArray[i][0] = Integer.parseInt(st1.nextToken()); // ���� ��
				floorArray[i][1] = Integer.parseInt(st2.nextToken()); // �ʴ� ���� ��
				
				// pos = leaf start ����
				int pos = i + size - 1;
				// leaf 1��°���� �� �Է�
				sumArray[pos] = floorArray[i][0];
				gcdArray[pos] = floorArray[i][0];
			}

			// Leaf���� Top ���� ������ ������Ʈ
			for (int i = 1; i <= N; i += 2) {
				int index = i + size - 1;
				
				while (index > 1) {
					int tempIndex = index / 2;
					// ���� �� ������ ������Ʈ
					sumArray[tempIndex] = sumArray[tempIndex * 2] + sumArray[tempIndex * 2 + 1];
					// ** keyPoint GCD : �ִ����� ������Ʈ
					gcdArray[tempIndex] = getGCD(gcdArray[tempIndex * 2], gcdArray[tempIndex * 2 + 1]);
					index /= 2;
				}
			}
			
			// ���� 1���� �ƴϸ�
			if (N != 1) {
				// ���� 1���� N����
				for (int i = 1; i <= N; i++) {
					// scope = i�� �ʴ� ���� ��
					int scope = floorArray[i][1];
					// �ʴ� ���� ���� 0�̸� �� ���� ��ȸ �����ϴ� ���� �ּ� ���� 1
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
			// ���� 1���� ���� 1
			} else {
				totalTeams = 1L;
			}

			bw.write("#" + tc + " " + totalTeams + "\n");

		}

		br.close();
		bw.flush();
		bw.close();

	}

	// ���� ���� ���ϴ� �Լ�
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

	// ������ gcd�� ���ϴ� �Լ�
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
