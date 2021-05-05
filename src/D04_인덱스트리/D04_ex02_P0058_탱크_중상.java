package D04_인덱스트리;

import java.util.*;
import java.io.*;

public class D04_ex02_P0058_탱크_중상 {
	static class Tank {
		int id, x, y, score;

		public Tank(int id, int x, int y, int score) {
			this.id = id;
			this.x = x;
			this.y = y;
			this.score = score;
		}
	}
	static int TC, N, size;
	static Tank tank[];
	static long ans;
	static long tree[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D04_인덱스트리/D04_ex02_P0058_탱크_중상.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		TC = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			
			tank = new Tank[N + 1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());

				tank[i] = new Tank(i, a, b, x);
			}

			// * y값이 연속이지 않아도 = 듬성듬성 증가해도 순서는 연속이어야함
			// y 오름차순
			Arrays.sort(tank, 1, N + 1, new Comparator<Tank>() {
				@Override
				public int compare(Tank o1, Tank o2) {
					return o1.y - o2.y;
				}
			});

			// y 오름차순 기준 리라벨링
			for (int i = 1; i <= N; i++) {
				tank[i].id = i;
			}
			
			// x 내림차순
			Arrays.sort(tank, 1, N + 1, new Comparator<Tank>() {
				@Override
				public int compare(Tank o1, Tank o2) {
					return o2.x - o1.x;
				}
			});
			
			// Idx Tree 생성
			size = 1;
			while (size < N) {
				size *= 2;
			}
			tree = new long[size * 2];

			ans = 0;
			
			// * 좌표압축 x 내림차순 순으로 뽑으면서 y순서대로 tree에 입력
			for (int i = 1; i <= N; i++) {
//				if (i == 1) {
//					** 주의 tank 값 입력 필요 : 바로 continue 하면 안됌
//					continue;
//				}
				
				// 1. 구간합 을 ans에 더하고
				ans += getSum(tank[i].id + 1, N);
				// 2. tank 값 트리에 입력 후 업데이트
				upDate(tank[i].id, tank[i].score);
			}

			bw.write("#" + tc + " " + ans + "\n");

		}

		br.close();
		bw.flush();
		bw.close();
	}

	private static void upDate(int id, int score) {
		int idx = id + size - 1;
		tree[idx] = score;
		idx /= 2;

		while (idx > 0) {
			tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
			idx /= 2;
		}
	}

	private static long getSum(int a, int b) {
		long res = 0;
		int s = a + size - 1;
		int e = b + size - 1;

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

}
