package D04_�ε���Ʈ��;

import java.io.*;
import java.util.*;

/*
 * ���� : ��ũ
 * ���� : 210714
 * �õ� : 3
 */

public class Solution_210714 {
	// class�� �빮�ڷ� ����
	static class Tank {
		int id, x, y, s;
		// id�� y��ǥ ���󺧸��� ���� ����
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
		System.setIn(new FileInputStream("src/D04_�ε���Ʈ��/D04_ex02_P0058_��ũ_�߻�.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		TC = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine().trim());

			tanks = new Tank[N + 1];
			// tree �迭�� ������ ����
			size = 1;
			while (size < N) {
				size *= 2;
			}
			// tree �迭 ����
			tree = new long[size * 2];
			
			// Tank ��ǥ �Է�
			int a, b, c;
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());

				tanks[i] = new Tank(0, a, b, c);
			}
			
			// y�� ���󺧸��� ���� y��ǥ �������� ����
			Arrays.sort(tanks, 1, N + 1, new Comparator<Tank>() {

				@Override
				public int compare(Tank o1, Tank o2) {
					return o2.y - o1.y;
				}
			});
			
			// * �ٽ� : y�� ���󺧸� : why? ��ǥ������ ���� > ���󺧸��� ���� �ٷ� Tree Index�� �ȴ�
			for (int i = 1; i <= N; i++) {
				tanks[i].id = i;
			}
			
			// x�� �������� ����
			Arrays.sort(tanks, 1, N + 1, new Comparator<Tank>() {

				@Override
				public int compare(Tank o1, Tank o2) {
					return o2.x - o1.x;
				}
			});
			
			// x, y ������ ���� ���������� ���� ����
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

	// start ���� end ���� ������ ������Ʈ
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

	// idx�� score �� �Է��ϰ� root���� ������Ʈ
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
