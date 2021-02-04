package G05_Kruskal;

// 1�� �����ϴ� ��� ���ǿ� ���� ���� ������ �͵��� ������ ���

import java.util.*;
import java.io.*;

public class G05_ex06_����P0023_�����ǵ���������_�߻� {
	static int TC, N, Q, opt, ans;
	static int parent[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_ex06_����P0023_�����ǵ���������_�߻�.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {

			// opt�� 0 > ���� �α�, 1 > ���� ���� > �´ٸ� ans++
			N = Integer.parseInt(br.readLine());
			Q = Integer.parseInt(br.readLine());

			parent = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				parent[i] = i;
			}

			opt = 0;
			ans = 0;
			int a, b;
			for (int i = 1; i <= Q; i++) {
				st = new StringTokenizer(br.readLine());
				opt = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());

				if (opt == 0) {
					union(a, b);
				} else {
					if (find(a) == find(b)) {
						ans++;
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

		if (a > b) {
			parent[a] = parent[b];
		} else {
			parent[b] = parent[a];
		}

	}

	private static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

}
