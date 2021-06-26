package D04_¿Œµ¶Ω∫∆Æ∏Æ;

import java.io.*;
import java.util.*;

public class Solution_210626 {
	static int TC, size, N, tree[];
	static long ans;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D04_¿Œµ¶Ω∫∆Æ∏Æ/D04_ex07_P0041_K_HeapΩ¥∆€¿Ã∫•∆Æ.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		TC = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine().trim());

			size = 1;
			while (size < 100000) {
				size *= 2;
			}

			tree = new int[size * 2];

			int a, b;
			ans = 0;
			bw.write("#" + tc + " ");;
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());

				int id = size + b - 1;

				if (a == 1) {					
					update(id, 1);
				} else {
					int temp = search(b);
					update(temp, -1);
					ans = temp - size + 1;
					bw.write(ans + " ");
				}
			}

			bw.newLine();

		}

		br.close();
		bw.flush();
		bw.close();

	}

	private static void update(int id, int x) {
		tree[id] += x;
		id /= 2;
		while (id > 0) {
			tree[id] += x;
			id /= 2;
		}		
	}

	private static int search(int x) {
		int node = 1;

		while (node < size) {
			int child_node = node * 2;

			if (x > tree[child_node]) {
				x = x - tree[child_node];
				node = child_node + 1;
			} else {
				node = child_node;
			}
		}
		return node;
	}
}

/*
 * 3 10 1 2 1 4 1 5 1 8 1 9 1 8 1 1 2 6 2 5 2 1 5 1 6 1 7 1 2 2 2 1 100 7 1 1 1
 * 1 2 1 1 1 2 1 1 1 1 1
 */
