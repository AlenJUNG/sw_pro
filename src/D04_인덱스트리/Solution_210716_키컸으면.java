package D04_인덱스트리;

import java.io.*;
import java.util.*;

/*
 * 문제 : 
 * 일자 : 
 * 시도 : 
 */

public class Solution_210716_키컸으면 {
	static class Q_Info {
		int id, s, e, h;

		public Q_Info(int id, int s, int e, int h) {
			this.id = id;
			this.s = s;
			this.e = e;
			this.h = h;
		}
	}

	static class People_Info {
		int id, h;

		public People_Info(int id, int h) {
			this.id = id;
			this.h = h;
		}
	}

	static People_Info people[];
	static Q_Info question[];
	static int TC, N, Q, size, tree[];
	static int ans[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D04_인덱스트리/D04_ex05_P0038_키컸으면.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());

			people = new People_Info[N + 1];
			question = new Q_Info[Q + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				int x = Integer.parseInt(st.nextToken());
				people[i] = new People_Info(i, x);
			}

			for (int i = 1; i <= Q; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int height = Integer.parseInt(st.nextToken());

				question[i] = new Q_Info(i, start, end, height);
			}

			Arrays.sort(people, 1, N + 1, new Comparator<People_Info>() {

				@Override
				public int compare(People_Info o1, People_Info o2) {
					return o2.h - o1.h;
				}
			});

			Arrays.sort(question, 1, Q + 1, new Comparator<Q_Info>() {

				@Override
				public int compare(Q_Info o1, Q_Info o2) {
					// TODO Auto-generated method stub
					return o2.h - o1.h;
				}
			});

			size = 1;
			while (size < N) {
				size *= 2;
			}

			tree = new int[2 * size];

			int no = 1;
			ans = new int[Q + 1];

			for (int i = 1; i <= Q; i++) {
				int q_height = question[i].h;
				int input_height = people[no].h;

				while (input_height > q_height) {
					int id = people[no].id;
					Update(id);
					no++;
					input_height = people[no].h;
				}

				int qNo = question[i].id;
				int s = question[i].s;
				int e = question[i].e;
				ans[qNo] = getSum(s, e);
			}

			bw.write("#" + tc + " ");
			for (int i = 1; i <= Q; i++) {
				bw.write(ans[i] + " ");
			}
			bw.newLine();

		}

		br.close();
		bw.flush();
		bw.close();

	}

	private static int getSum(int start, int end) {
		int res = 0;
		int s = start + size - 1;
		int e = end + size - 1;

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

	// 해당 id에 +1하고 트리 업데이트
	private static void Update(int id) {
		int idx = id + size - 1;
		tree[idx]++;
		idx /= 2;

		while (idx > 0) {
			tree[idx]++;
			idx /= 2;
		}
	}

}
