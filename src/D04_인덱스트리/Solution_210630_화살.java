package D04_인덱스트리;

import java.io.*;
import java.util.*;

public class Solution_210630_화살 {
	static class Buildings {
		int id, no, h, p;

		public Buildings(int id, int no, int h, int p) {
			this.id = id;
			this.no = no;
			this.h = h;
			this.p = p;
		}
	}

	static int N, TC, size;
	static Buildings building[];
	static int tree[];
	static long ans;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D04_인덱스트리/D04_ex04_P0088_화살_중상.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st1 = null;
		StringTokenizer st2 = null;

		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			building = new Buildings[N + 1];

			st1 = new StringTokenizer(br.readLine());
			st2 = new StringTokenizer(br.readLine());

			int height, power;
			for (int i = 1; i <= N; i++) {
				height = Integer.parseInt(st1.nextToken());
				power = Integer.parseInt(st2.nextToken());
				building[i] = new Buildings(0, i, height, power);
			}

			Arrays.sort(building, 1, N + 1, new Comparator<Buildings>() {
				@Override
				public int compare(Buildings o1, Buildings o2) {
					if (o1.h > o2.h) {
						return -1;
					} else if (o1.h == o2.h) {
						if (o1.no < o2.no) {
							return -1;
						} else {
							return 1;
						}
					}
					return 1;
				}
			});
			
			
			for (int i = 1; i <= N; i++) {
				building[i].id = i;
			}

			size = 1;
			while (size < N) {
				size *= 2;
			}
			System.out.println("check");
			tree = new int[size * 2];
			ans = 0;
			for (int i = 1; i <= N; i++) {
				if (i == 1) {
					update(building[i].no, 1);
					continue;
				}
				// 1이 아닌경우
				int leftSum = getSum(1, building[i].no - 1);
				int x = leftSum + building[i].p + 1;

				if (x > tree[1]) {
					update(building[i].no, 1);
					continue;
				}

				ans += search(x);
				update(building[i].no, 1);
			}

			bw.write("#" + tc + " " + ans + "\n");

		}

		br.close();
		bw.flush();
		bw.close();

	}

	private static long search(int x) {
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
		return node - size + 1;
	}

	private static int getSum(int start, int end) {
		int res = 0;
		int s = size + start - 1;
		int e = size + end - 1;

		while (s <= e) {
			if (s % 2 == 1) {
				res += tree[s];
				s++;
			}
			if (e % 2 == 0) {
				res += tree[e];
				e--;
			}
			s /= 2;
			e /= 2;
		}

		return res;

	}

	private static void update(int no, int x) {
		int id = size + no - 1;
		tree[id] += 1;
		id /= 2;
		while (id > 0) {
			tree[id] += 1;
			id /= 2;
		}
	}

}
