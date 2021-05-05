package D04_인덱스트리;

import java.io.*;
import java.util.*;

public class D04_ex04_P0088_화살_중상 {
	static class Building {
		int h, pow, idx;

		Building(int h, int pow, int idx) {
			this.h = h;
			this.pow = pow;
			this.idx = idx;
		}
	}

	static int TC, N, size, now, k;
	static long ans;
	static int[] tree;
	static Building building[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D04_인덱스트리/D04_ex04_P0088_화살_중상.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());

			// * 배열의 크기는 N + 1로 설정하고 1부터 N까지 입력하여 연산
			building = new Building[N + 1];

			st = new StringTokenizer(br.readLine());
			// 0. 데이터 입력
			for (int i = 1; i <= N; i++) {
				// 중요 : 어떠한 데이터 값이라도 들어가 있어야 함
				building[i] = new Building(0, 0, 0);
				building[i].h = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				building[i].pow = Integer.parseInt(st.nextToken());
				building[i].idx = i;
			}

			// 1. 빌딩높이순으로 내림차순 정렬(* 크기가 같은 경우 먼저 입력된 순서)
			Arrays.sort(building, 1, N + 1, new Comparator<Building>() {
				@Override
				public int compare(Building a, Building b) {
					if (a.h == b.h) {
						return a.idx - b.idx;
					} else {
						return b.h - a.h;
					}
				}
			});

			// 2. 인덱스트리 생성
			size = 1;
			while (size < N) {
				size *= 2;
			}
			tree = new int[size * 2];

			ans = 0;
			for (int i = 1; i <= N; i++) {
				// 3.1 본인보다 인덱스트리 상 왼쪽 빌딩 수 + 힘 + 1
				now = sum(1, building[i].idx - 1) + building[i].pow + 1;
				// 3.2 타켓빌딩이 있는 경우 높이 출력
				// * k가 N보다 크다면 값을 더하지 않는다 > 값에 맞는 빌딩이 존재하지 않음
				k = find(now);
				if (k <= N) {
					ans += k;
				}
				// 3.3 본인위치 인덱스트리 갱신
				update(building[i].idx, 1);
			}

			bw.write("#" + tc + " " + ans + "\n");

		}

		br.close();
		bw.flush();
		bw.close();

	}

	private static int find(int target) {
		int node = 1;

		// 조건 중요
		if (tree[node] < target) {
			return 0;
		}

		while (node < size) {
			int child_node = node * 2;
			if (target > tree[child_node]) {
				target = target - tree[child_node];
				node = child_node + 1;
			} else {
				node = child_node;
			}
		}
		return node - size + 1;
	}

	private static void update(int idx, int val) {
		idx = size + idx - 1;

		while (idx > 0) {
			tree[idx] += val;
			idx /= 2;
		}
	}

	private static int sum(int s, int e) {
		int sum = 0;
		s = size + s - 1;
		e = size + e - 1;

		while (s <= e) {
			if (s % 2 == 1) {
				sum += tree[s];
				s++;
			}
			if (e % 2 == 0) {
				sum += tree[e];
				e--;
			}
			s /= 2;
			e /= 2;
		}
		return sum;
	}

}

