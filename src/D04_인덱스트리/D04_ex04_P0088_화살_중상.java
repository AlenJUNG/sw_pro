package D04_인덱스트리;

// N, H, P

import java.io.*;
import java.util.*;

public class D04_ex04_P0088_화살_중상 {

	static class Building {
		int h, p, idx;

		Building(int h, int p, int idx) {
			this.h = h;
			this.p = p;
			this.idx = idx;
		}
	}

	static int T, N, S, now, k;
	static long ans;
	static int tree[];
	static Building building[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D04_인덱스트리/D04_ex04_P0088_화살_중상.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine());

			building = new Building[N + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				building[i] = new Building(0, 0, 0); // 생략 가능?
				building[i].h = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				building[i].p = Integer.parseInt(st.nextToken());
				building[i].idx = i;
			}

			// 1. 빌딩 높이순 내림차순 정렬 (크기가 같은 경우 먼저 입력된 순서)
			Arrays.sort(building, 1, N + 1, new Comparator<Building>() {

				@Override
				public int compare(Building o1, Building o2) {
					if (o1.h == o1.h) {
						return o1.idx - o2.idx;
					} else {
						return o2.h - o1.h;
					}
				}
			});

			// 2. 인덱스트리 생성
			S = 1;
			while (S < N) {
				S *= 2;
			}

			tree = new int[2 * S];

			ans = 0;

			for (int i = 1; i <= N; i++) {
				// 3.1. 본인보다 인덱스트리 상 왼쪽 빌딩의 수 + 힘 + 1
				now = sum(1, building[i].idx - 1) + building[i].p + 1;

				k = find(now);
				if (k <= N) {
					ans += k;
				}

				update(building[i].idx, 1);

			}
			bw.write("#" + tc + " " + ans + "\n");
		}

		br.close();
		bw.flush();
		bw.close();
	}

	private static void update(int ind, int val) {
		ind = S + ind - 1;
		while (ind > 0) {
			tree[ind] += val;
			ind /= 2;
		}
	}

	private static int find(int target) {
		int i = 1;
		int l, r;

		if (tree[i] < target) {
			return 0;
		}

		while (i < S) {
			l = i * 2;
			r = i * 2 + 1;
			if (tree[l] < target) {
				target -= tree[l];
				i = r;
			} else {
				i = l;
			}
		}
		return i - S + 1;
	}

	private static int sum(int s, int e) {
		int sum = 0;
		s = S + s - 1;
		e = S + e - 1;
		while (s <= e) {
			if (s % 2 == 1) {
				sum += tree[s];
			}
			if (s % 2 == 0) {
				sum += tree[e];
			}
			s = (s + 1) / 2;
			e = (e + 1) / 2;
		}
		return sum;
	}

}
