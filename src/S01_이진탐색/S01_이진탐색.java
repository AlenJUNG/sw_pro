package S01_����Ž��;

import java.io.*;
import java.util.*;

// 01. ���� Ž�� (Binary Search Algorithm)
// ���� 1 : ���� �Ǿ��ų� �Ǵ� ������ ������ ����Ʈ���� ���
// ���� 2 : Ż�� ���� ��� (start > end)

// Q) 10���� �����Ͱ� �Է����� �־����� �� X ������(7) �� �� ��°�� �ִ��� ���϶�

public class S01_����Ž�� {
	static int N, arr[];
	static int X = 7;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/S01_����Ž��/S01_����Ž��.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = 10; // �� �����ʹ� 10�� > ���� ����
		arr = new int[10];
		for (int i = 0; i < 10; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(binary_Search(arr, 0, N, X));

		br.close();

	}

	private static int binary_Search(int[] num, int start, int end, int target) {

		while (true) {
			// ** num�ȿ� target�� ���� �� Ż�� ���� �ݵ�� �ۼ�
			if (start > end) {
				return -1;
			}

			// ** mid�� ��� (�Ҽ������ϴ� �ڵ����� ����)
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
