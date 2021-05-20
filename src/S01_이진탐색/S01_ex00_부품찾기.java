package S01_����Ž��;
// 2021.05.20 ���ø� Update

import java.io.*;
import java.util.*;

public class S01_ex00_��ǰã�� {
	static int N, M;
	static int arr[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/S01_����Ž��/S01_ex00_��ǰã��.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		arr = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= M; i++) {
			int x = Integer.parseInt(st.nextToken());
			binarySearch(arr, 1, N, x);
		}

		br.close();
		bw.flush();
		bw.close();

	}

	private static void binarySearch(int[] a, int start, int end, int target) {
		while (true) {
			// ���� ���� ��� ����� ��
			if (start > end) {
				System.out.println("NO");
				return;
			}

			int mid = start + (end - start) / 2;
			
			if (a[mid] == target) {
//				System.out.println(mid); �� ��°�� ���� �ִ��� ������ ����
				System.out.println("YES");
				return;
			} else if (a[mid] < target) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}

	}

}
