package D04_인덱스트리;

import java.io.*;
import java.util.*;

/*
 * 문제 :
 * 일자 :
 * 시도 :
 */

public class Solution_210715_화살 {
	static class Building {
		int id, h, p;

		public Building(int id, int h, int p) {
			this.id = id;
			this.h = h;
			this.p = p;
		}
	}

	static int TC, N, size, target, tree[], height[], power[], score[];
	static Building buildings[];
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

			buildings = new Building[N + 1];
			height = new int[N + 1];
			power = new int[N + 1];
			score = new int[N + 1];

			st1 = new StringTokenizer(br.readLine());
			st2 = new StringTokenizer(br.readLine());
			int a, b;
			for (int i = 1; i <= N; i++) {
				a = Integer.parseInt(st1.nextToken());
				b = Integer.parseInt(st2.nextToken());

				buildings[i] = new Building(i, a, b);
				height[i] = a;
				power[i] = b;
				score[i] = i;
			}

			// height 내림차순 정렬
			// height 큰 순서대로 buildings 번호 계산 하여 트리에 넣어줌
			// height 높은 것부터 계산 score =
			// tree에 height 순서대로 building.no에 1을 넣어줌

			Arrays.sort(buildings, 1, N + 1, new Comparator<Building>() {

				@Override
				public int compare(Building o1, Building o2) {
					if (o2.h < o1.h) {
						return -1;
					} else if (o2.h == o1.h) {
						if (o2.id < o1.id) {
							return -1;
						}
					}
					return 1;
				}
			});

			size = 1;
			while (size < N) {
				size *= 2;
			}

			tree = new int[size * 2];

			ans = 0;
			target = 0;
			for (int i = 1; i <= N; i++) {

				int id = buildings[i].id;
				// search : 몇 번째 노드인가?
				int temp = getSum(1, id - 1) + buildings[id].p + 1;
				
				if (temp > tree[1]) {
					update(id);
					continue;
				}
				// 5번까지 검증함
				target = search(temp);
				ans += target;
				update(id);
			}

			bw.write("#" + tc + " " + ans + "\n");

		}

		br.close();
		bw.flush();
		bw.close();

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
		return node - size + 1;
	}
	
	
	
	private static void update(int id) {
		int idx = id + size - 1;
		tree[idx] += 1;
		idx /= 2;

		while (idx > 0) {
			tree[idx] += 1;
			idx /= 2;
		}
	}

	private static int getSum(int start, int end) {
		int res = 0;
		start = start + size - 1;
		end = end + size - 1;

		while (start <= end) {
			if (start % 2 == 1) {
				res += tree[start];
				start++;
			}
			if (end % 2 == 0) {
				res += tree[end];
				end--;
			}
			start /= 2;
			end /= 2;
		}
		return res;
	}

}
