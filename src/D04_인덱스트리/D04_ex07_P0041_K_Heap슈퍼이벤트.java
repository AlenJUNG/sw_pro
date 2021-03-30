package D04_¿Œµ¶Ω∫∆Æ∏Æ;

import java.io.*;
import java.util.*;

public class D04_ex07_P0041_K_HeapΩ¥∆€¿Ã∫•∆Æ {
	static int TC;
	static int Q;
	static int idx_Tree[];
	static int size;
	static int MAX_NO = 100000;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D04_¿Œµ¶Ω∫∆Æ∏Æ/D04_ex07_P0041_K_HeapΩ¥∆€¿Ã∫•∆Æ.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		TC = Integer.parseInt(br.readLine().trim());
		size = 1;
		while (size < MAX_NO) {
			size *= 2;
		}

		for (int tc = 1; tc <= TC; tc++) {
			bw.append("#" + tc);

			Q = Integer.parseInt(br.readLine().trim());

			idx_Tree = new int[size * 2];

			for (int q = 1; q <= Q; q++) {
				st = new StringTokenizer(br.readLine().trim());
				int type = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				int idx = size + value;

				if (type == 1) {
					idx_Tree[idx] += 1;
					update(idx);
				} else if (type == 2) {
					bw.append(" " + search(value));
				}

			}
			bw.newLine();

		}

		br.close();
		bw.flush();
		bw.close();

	}

	private static int search(int no) {
		int idx = 1;

		while (idx < size) {
			int temp = idx * 2;

			if (idx_Tree[temp] < no) {
				no = no - idx_Tree[temp];
				idx = temp + 1;
			} else {
				idx = temp;
			}
		}

		idx_Tree[idx]--;
		update(idx);

		return idx - size;
	}

	private static void update(int idx) {
		for (int i = size - 1; i > 0; i--) {
			idx_Tree[i] = idx_Tree[i * 2] + idx_Tree[i * 2 + 1];
		}

	}

}
