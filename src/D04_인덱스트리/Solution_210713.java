package D04_인덱스트리;

import java.io.*;
import java.util.*;

/*
 * 문제 : 슈퍼이벤트
 * 일자 : 210713
 * 시도 : 3
 * 노트 : 뽑았으면 빼고 신규 업데이트 필요
 */

public class Solution_210713 {

	static int TC, Q, size;
	static int tree[];
	static final int MAX = 100000;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D04_인덱스트리/D04_ex07_P0041_K_Heap슈퍼이벤트.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = null;
		StringTokenizer st = null;

		TC = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= TC; tc++) {
			Q = Integer.parseInt(br.readLine().trim());
			sb =  new StringBuilder();
			
			size = 1;
			while (size < MAX) {
				size *= 2;
			}

			tree = new int[size * 2];

			int opt, x;
			for (int i = 1; i <= Q; i++) {
				st = new StringTokenizer(br.readLine().trim());
				opt = Integer.parseInt(st.nextToken());
				x = Integer.parseInt(st.nextToken());

				if (opt == 1) {
					update(x, 1);
				} else {
					sb.append(search(x) + " ");
				}
			}
						
			bw.write("#" + tc + " " + sb.toString() + "\n");

		}

		br.close();
		bw.flush();
		bw.close();

	}

	private static int search(int x) {
		int node = 1;
		while(node < size) {
			int child_node = node * 2;
			if(x > tree[child_node]) {
				x = x - tree[child_node];
				node = child_node + 1;
			}else {
				node = child_node;
			}
		}
		
		update(node - size + 1, -1);
		
		return node - size + 1;
	}

	private static void update(int x, int n) {
		int idx = size + x - 1;
		tree[idx] += n;
		idx /= 2;

		while (idx > 0) {
			tree[idx] += n;
			idx /= 2;
		}
	}

}
