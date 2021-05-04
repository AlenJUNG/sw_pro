package D04_인덱스트리;
// 디버깅 해야함 값이 안 맞음

import java.util.*;
import java.io.*;

public class D04_ex02_P0058_탱크_중상 {
	static class Tank {
		int x, y, s;

		public Tank(int x, int y, int s) {
			this.x = x;
			this.y = y;
			this.s = s;
		}
	}	
	static Tank tArr[] = new Tank[100000];
	static int yArr[] = new int[100000];
	static int yIdx[] = new int[1000001]; // 좌표 순서가 몇번째인지 저장할 거임
	static long indexTree[] = new long[262144]; // 문제에서 4 byte를 넘어갈 수 있다고 했으니 long으로 선언
	// 10만에 제일가까운 2의 제곱 * 2 사이즈로 만듦
	static int TC, N;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D04_인덱스트리/D04_ex02_P0058_탱크_중상.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		TC = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine().trim());

			int x, y, s;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				s = Integer.parseInt(st.nextToken());

				tArr[i] = new Tank(x, y, s);
				yArr[i] = y;
			}

			Arrays.sort(tArr, 0, N, new Comparator<Tank>() {

				@Override
				public int compare(Tank o1, Tank o2) {
					return o2.x - o1.x; // x 기준 내림차순으로 정렬
				}
			});

			Arrays.sort(yArr, 0, N);
			for (int i = 0; i < N; i++) {
				yIdx[yArr[i]] = i;
			}

			int size = 1;
			int idx, a, b;
			long res;
			int ans = 0;

			while (size > N) {
				size *= 2;
			}

			for (int i = 0; i < N; i++) {
				idx = size + yIdx[tArr[i].y];
				a = idx + 1;
				b = N - 1 + size;

				while (idx > 0) {
					indexTree[idx] += (long) tArr[i].s;
					idx /= 2;
				}

				res = 0L;

				while (a <= b) {
					if (a % 2 == 1) {
						res += indexTree[a];
						a++;
					}
					if (b % 2 == 0) {
						res += indexTree[b];
						b--;
					}

					a /= 2;
					b /= 2;
				}

				ans += res;
			}

			bw.write("#" + tc + " " + ans + "\n");

		}

		br.close();
		bw.flush();
		bw.close();
	}

}
