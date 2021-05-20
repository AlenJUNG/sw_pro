package S01_이진탐색;
// 2021.05.20 템플릿 Update

import java.io.*;
import java.util.*;

public class S01_ex00_부품찾기 {
	static int N, M;
	static int arr[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/S01_이진탐색/S01_ex00_부품찾기.txt"));
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
			// 답이 없는 경우 고려할 것
			if (start > end) {
				System.out.println("NO");
				return;
			}

			int mid = start + (end - start) / 2;
			
			if (a[mid] == target) {
//				System.out.println(mid); 몇 번째에 값이 있는지 보려면 선택
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
