package G05_Kruskal;

import java.io.*;
import java.util.*;

/*
 * ¿œΩ√ : 2021.07.05
 * µø∏Õ¿«µø∏Õ¿∫ µø∏Õ
 */

public class Solution {
	static int N, TC, Q, parent[], ans;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_ex06_ø¨Ω¿P0023_µø∏Õ¿«µø∏Õ¿∫µø∏Õ_¡ﬂªÛ.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			Q = Integer.parseInt(br.readLine());

			parent = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				parent[i] = i;
			}

			ans = 0;
			int opt, a, b;
			for (int i = 1; i <= Q; i++) {
				st = new StringTokenizer(br.readLine());
				opt = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());

				if (opt == 0) {
					if (find(a) == find(b)) {
						continue;
					} else {
						union(a, b);
					}
				} else {
					if (find(a) == find(b)) {
						ans += 1;
					}
				}
			}

			bw.write("#" + tc + " " + ans + "\n");
		}

		br.close();
		bw.flush();
		bw.close();
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a > b) {
			parent[b] = parent[a];
		}else {
			parent[a] = parent[b];
		}
		
	}

	private static int find(int root) {
		if(root == parent[root]) {
			return root;
		}
		return parent[root] = find(parent[root]);
	}

}
