package G06_LCA;

import java.io.*;
import java.util.*;

public class G06_ex06_P0051_조상이키컸으면 {
	static int MAX_N = 10000;
	static int MAX_D = 14;
	static int T, N, Q;
	static int a, b, k;
	static int parent[][], H[], depth[], MH[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G06_LCA/G06_ex06_P0051_조상이키컸으면.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());

			parent = new int[MAX_D + 1][N + 1];
			depth = new int[N + 1];
			H = new int[N + 1];
			MH = new int[N + 1];

			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				parent[0][i] = Integer.parseInt(st.nextToken());
				H[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 1; i <= N; i++) {
				DFS(i);
			}

			for (int k = 1; k <= MAX_D; k++) {
				for (int i = 1; i <= N; i++) {
					parent[k][i] = parent[k - 1][parent[k - 1][i]];
				}
			}

			for (int i = 1; i <= Q; i++) {
				st = new StringTokenizer(br.readLine());
				k = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken());

				for (int j = 2; j <= k; j++) {
					b = Integer.parseInt(st.nextToken());
					a = LCA(a, b);
				}
				System.out.print(MH[a] + " ");
			}

		}

		br.close();
		
	}

	private static void DFS(int start) {
		// 0이거나 뎁스가 미리 입력이 되어 있으면 return
		if (start == 0 || depth[start] > 0) {
			return;
		}
		DFS(parent[0][start]);
		depth[start] = depth[parent[0][start]] + 1;
		// 아래 코드 중요
		MH[start] = Math.max(H[start], MH[parent[0][start]]);

	}

	private static int LCA(int a, int b) {
		if (depth[a] > depth[b]) {
			int temp;
			temp = a;
			a = b;
			b = temp;
		}

		for (int i = MAX_D; i >= 0; i--) {
			if (depth[b] - depth[a] >= Math.pow(2, i)) {
				b = parent[0][b];
			}
		}

		if (a == b) {
			return a;
		}

		for (int i = MAX_D; i >= 0; i--) {
			if (parent[i][a] != parent[i][b]) {
				a = parent[i][a];
				b = parent[i][b];
			}
		}

		return parent[0][a];
	}

}
