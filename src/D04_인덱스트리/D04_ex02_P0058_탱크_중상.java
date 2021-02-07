package D04_�ε���Ʈ��;

import java.io.*;
import java.util.*;

public class D04_ex02_P0058_��ũ_�߻� {
	static class Tank {
		int x, y, s;

		public Tank(int x, int y, int s) {
			this.x = x;
			this.y = y;
			this.s = s;
		}
	}

	static Tank tArr[];
	static int yArr[], yIdx[];
	static long idxTree[], ans;	// �������� 4 byte�� �Ѿ �� �ִٰ� ������ long���� ����
	static int TC, N, a, b, c;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D04_�ε���Ʈ��/D04_ex02_P0058_��ũ_�߻�.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		TC = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= TC; tc++) {

			N = Integer.parseInt(br.readLine().trim());

			tArr = new Tank[N + 1];
			yArr = new int[N + 1];
			yIdx = new int[N + 1];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());

				tArr[i] = new Tank(a, b, c);
				yArr[i] = b;
			}

			// �������� ���� : 0 ~ N - 1 ���� ����
			Arrays.sort(tArr, 0, N, new Comparator<Tank>() {

				@Override
				public int compare(Tank o1, Tank o2) {
					return o2.x - o1.x;
				}
			});

			// �������� ���� : 0 ~ N - 1 ���� ����
			Arrays.sort(yArr, 0, N);
			
			// * �߿� : 100���� ������ 10���� ������ �ٿ� ����ϱ� ���� ��ǥ�� �ٽ� ������
			for (int i = 0; i < N; i++) {
				yIdx[yArr[i]] = i; // yArr[i]�� �� ��° ��ġ���� ������� ���� > �� ��° y���ΰ�?
			}
			
			// �ε���Ʈ�� ���� > 1���� �迭�� ����
			int size = 1;
			while (size < 100000) {
				size *= 2;
			}
			// * Why? ���� idxTree ũ�Ⱑ(size * 2) 262144 �ΰ�? 
			// 10���� ������ �ٿ��� ������ N�� 10������ �� 
			idxTree = new long[size * 2];
			
			int tempIdx, left, right;
			long temp_ans;
			ans = 0;
			
			// x��ǥ�� ���������� ��ũ �迭���� ��ũ���� �ϳ��� ���鼭 ���� ���
			// ��ũ�� ���� N������ ������ �ݺ�
			for (int i = 0; i < N; i++) {
				// Re�󺧸� : ��ũ ������� ������������ ��ȯ�� y��ǥ�� �ε����� ��ȯ
				tempIdx = yIdx[tArr[i].y] + size;
				// ���� ǥ��
				left = tempIdx + 1;
				right = N + size - 1;

				// ��� 1���� Update ��
				while (tempIdx > 0) {
					idxTree[tempIdx] += (long) tArr[i].s;
					tempIdx /= 2;
				}

				temp_ans = 0L;
				while (left <= right) {
					if (left % 2 == 1) {
						temp_ans += idxTree[left];
						left++;
					}
					if (right % 2 == 0) {
						temp_ans += idxTree[right];
						right--;
					}

					left /= 2;
					right /= 2;
				}
				ans += temp_ans;
			}

			bw.write("#" + tc + " " + ans + "\n");
		}

		br.close();
		bw.flush();
		bw.close();

	}

}
