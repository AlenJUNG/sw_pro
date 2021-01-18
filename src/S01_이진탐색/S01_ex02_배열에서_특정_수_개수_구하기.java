package S01_����Ž��;

import java.io.*;
import java.util.*;

public class S01_ex02_�迭����_Ư��_��_����_���ϱ� {
	static int N, X, min_ans, max_ans, res;
	static int arr[];
	static boolean check;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/S01_����Ž��/S01_ex02_�迭����_Ư��_��_����_���ϱ�.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// �������� ������ �Ǿ� �ִٰ� ����
		// �ִ� index - �ּ� index
		res = getMax_binarySearch(arr, 0, N - 1) - getMin_binarySearch(arr, 0, N - 1);
		
		// ���ϰ��� �����ٸ� -1 ��� ����ó��
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
				// ���ϰ��� �ִ� ��� true ó��
				if (arr[mid] == X) {
					check = true;
				}
				// ���� Ž�� > Max Index ���ϱ�
				max_ans = mid;	// ���� Ž�� ��� ������Ʈ
				start = mid + 1;
			}
		}

		return max_ans;
	}

	private static int getMin_binarySearch(int[] map, int start, int end) {
		while (start <= end) {
			int mid = start + (end - start) / 2;
			// ���� Ž�� > Min Index ���ϱ�
			if (X > arr[mid]) {
				start = mid + 1;
				min_ans = mid;	// ���� Ž�� ��� ������Ʈ
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
