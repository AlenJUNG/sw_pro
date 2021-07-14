package D04_인덱스트리;

import java.io.*;
import java.util.*;

/*
 * 문제 : 탱크
 * 일자 : 210714
 * 시도 : 3
 */

public class Solution_210714 {
	// class는 대문자로 설정
	static class Tank {
		int id, x, y, s;
		// id는 y좌표 리라벨링을 위해 설정
		public Tank(int id, int x, int y, int s) {
			this.id = id;
			this.x = x;
			this.y = y;
			this.s = s;
		}
	}
	
	static Tank tanks[];
	static int TC, N, size;
	static long tree[], ans;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D04_인덱스트리/D04_ex02_P0058_탱크_중상.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		TC = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine().trim());

			tanks = new Tank[N + 1];
			// tree 배열의 사이즈 설정
			size = 1;
			while (size < N) {
				size *= 2;
			}
			// tree 배열 선언
			tree = new long[size * 2];
			
			// Tank 좌표 입력
			int a, b, c;
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());

				tanks[i] = new Tank(0, a, b, c);
			}
			
			// y축 리라벨링을 위한 y좌표 내림차순 정렬
			Arrays.sort(tanks, 1, N + 1, new Comparator<Tank>() {

				@Override
				public int compare(Tank o1, Tank o2) {
					return o2.y - o1.y;
				}
			});
			
			// * 핵심 : y축 리라벨링 : why? 좌표압축을 위해 > 리라벨링한 값이 바로 Tree Index가 된다
			for (int i = 1; i <= N; i++) {
				tanks[i].id = i;
			}
			
			// x축 내림차순 정렬
			Arrays.sort(tanks, 1, N + 1, new Comparator<Tank>() {

				@Override
				public int compare(Tank o1, Tank o2) {
					return o2.x - o1.x;
				}
			});
			
			// x, y 양축의 연속 구간합으로 정답 도출
			ans = 0;
			for (int i = 1; i <= N; i++) {
				int idx = tanks[i].id;
				int score = tanks[i].s;

				update(idx, score);
				ans += getSum(1, idx - 1);
			}

			bw.write("#" + tc + " " + ans + "\n");

		}

		br.close();
		bw.flush();
		bw.close();

	}

	// start 부터 end 까지 구간합 업데이트
	private static long getSum(int start, int end) {
		long res = 0;
		start = size + start - 1;
		end = size + end - 1;

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

	// idx에 score 값 입력하고 root까지 업데이트
	private static void update(int idx, int score) {
		idx = size + idx - 1;
		tree[idx] = score;
		idx /= 2;

		while (idx > 0) {
			tree[idx] += score;
			idx /= 2;
		}
	}

}
