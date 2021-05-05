package D04_인덱스트리;

/* N = 6, (8 3 4 1 2 9), 1~4 구간 사이의 최대값 구하기
 * 
 */

import java.util.*;
import java.io.*;

public class D04_bs00_최대값구하기 {
	static int TC, N, start, end, arr[];
	static int size, tree[], ans;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D04_인덱스트리/D04_bs00_최대값구하기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());

			arr = new int[N + 1];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			

			size = 1;
			while (size < N) {
				size *= 2;
			}
			tree = new int[size * 2];

			for (int i = 1; i <= N; i++) {
				int x = size + i - 1;
				tree[x] = arr[i];
			}
			
			for (int i = size - 1; i > 0; i--) {
				tree[i] = Math.max(tree[i * 2], tree[i * 2 + 1]);
			}

			
			ans = 0;
			ans = getMax(start, end);
			bw.write("#" + tc + " " + ans + "\n");
			
			upDate(2, 10);
			
			ans = 0;
			ans = getMax(start, end);
			bw.write("#2 2번째 값을 10으로 바꾼 후 => " + ans + "\n");

		}
		br.close();
		bw.flush();
		bw.close();
	}

	private static int getMax(int st, int ed) {
		int res = 0;
		int s = st + size - 1;
		int e = ed + size - 1;
		while (s <= e) {
			if (s % 2 == 1) {
				res = Math.max(res, tree[s]);
				s++;
			}
			if (e % 2 == 0) {
				res = Math.max(res, tree[e]);
				e--;
			}
			s /= 2;
			e /= 2;
		}
		return res;
	}
	
	private static void upDate(int id, int value) {
		int idx = id + size - 1;
		tree[idx] = value;
		idx /= 2;
		
		while(idx > 0) {
			tree[idx] = Math.max(tree[idx * 2], tree[idx * 2 + 1]);
			idx /= 2;
		}
	}

}
