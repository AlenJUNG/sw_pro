package G05_Kruskal;

// https://hiruby.tistory.com/387

import java.io.*;
import java.util.*;

public class G05_ex10_100_10775_공항 {
	static int G, P, ans;
	static int parent[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_ex10_100_10775_공항.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());

		parent = new int[G + 1];
		for (int i = 1; i <= G; i++) {
			parent[i] = i;
		}

		int temp = 0;
		ans = 0;
		for (int i = 1; i <= P; i++) {
			temp = Integer.parseInt(br.readLine());
			// 현재 비행기의 탑승구의 루트 확인
			int root = find(temp);
			// 현재 루트가 0이면 종료
			if (root == 0) {
				break;
			}			
			union(root, root - 1);
			ans += 1;
		}

		bw.write(ans + "\n");

		br.close();
		bw.flush();
		bw.close();
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a > b) {
			parent[a] = b;
		} else {
			parent[b] = a;
		}

	}

	private static int find(int x) {
		if (x == parent[x]) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

}
