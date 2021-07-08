package G05_Kruskal;

import java.io.*;
import java.util.*;

/*
 * Update : 2021.07.08
 * ȥ�� ������ Ǯ��� ���� ���� �� ����
 */

public class G05_ex14_P0079_���ð�ȹ {
	// ���� ����
	static class city {
		// water�� 1�̸� ������
		int water, x, y;

		public city(int water, int x, int y) {
			this.water = water;
			this.x = x;
			this.y = y;
		}
	}

	// ���� ����
	static class edge implements Comparable<edge> {
		int water, city; // ������ ���� no, �������� ����� ���� no
		long dis; // �� ���� ������ �Ÿ�

		public edge(int water, int city, long dis) {
			this.water = water;
			this.city = city;
			this.dis = dis;
		}

		// �� ���� ������ �Ÿ� �������� ����
		@Override
		public int compareTo(edge other) {
			if (this.dis < other.dis) {
				return -1;
			}
			return 1;
		}
	}

	static int TC, N, M, x, y, visitCnt;
	static long tree[][]; // �� ������ �������� ����
	static boolean visited[];
	static city cityInfo[];
	static PriorityQueue<edge> pq; // * ���� ���� : ������ - �Ϲݵ��� ���������� ����
	static long dis, ans;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_ex14_P0079_���ð�ȹ.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			cityInfo = new city[N + 1];
			
			tree = new long[N + 1][N + 1];
			visited = new boolean[N + 1];
			pq = new PriorityQueue<>();
			ans = visitCnt = 0;
			
			// 0. �ּҰ��� ���ϱ� ���� tree �迭 max �� �ʱ� ����
			for(int i = 0; i <= N; i++) {
				for(int j = 0; j <= N; j++) {
					tree[i][j] = ((long)100001 * (long)100001 + (long)100001 * (long)100001);
				}
			}

			// 1. ���� ���� ����
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				cityInfo[i] = new city(0, x, y); // �ʱⰪ ������ ���� �������� �ƴ� ������ �ޱ�
			}

			// 2. ���ð� ���� ���� > ��ü ���� ����
			for (int i = 1; i <= N - 1; i++) {
				for (int j = i + 1; j <= N; j++) {
					long disX = cityInfo[i].x - cityInfo[j].x;
					long disY = cityInfo[i].y - cityInfo[j].y;
					// * ���� long = (long) int * int > ����ȯ �ʿ�
					long dis = (long) disX * disX + (long) disY * disY;
					// * �߿� : from ���� ����, ���� �ִ� ��� ���ذ� �� �ּҰ� ����� ����
					dis = Math.min(tree[x][y], dis);
					tree[i][j] = dis;
					tree[j][i] = dis;
				}
			}

			// 3. ������ ���� �ʱ� ����
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				int water_no = Integer.parseInt(st.nextToken());
				cityInfo[water_no].water = 1; // ������ ǥ��
				// 3-1. �������� ��츸 pq�� �ֱ�
				for (int city_no = 1; city_no <= N; city_no++) {
					pq.offer(new edge(water_no, city_no, tree[water_no][city_no]));
				}
			}

			visitCnt = M; // * �������� �湮�� ���÷� ��ó���� : �ð� ���⵵ �ּ�ȭ

			// 4. pq�� ����� ������ �̰� > ���� ���� ������� ����� ���ô� ������ ó�� > ����ջ�
			while (!pq.isEmpty()) {
				edge now = pq.poll();
				int water_N = now.water;
				int city_N = now.city;

				// �������� �̾����� ���� ������ ���
				if (cityInfo[water_N].water == 1 && cityInfo[city_N].water != 1) {
					cityInfo[city_N].water = 1; // �������� �ݿ�
					visitCnt++; // �湮���� �ݿ� +1
					ans += tree[water_N][city_N];

					// ��� ���ø� �� �湮�ߴٸ�, ���� : �ð����⵵ ����ȭ
					if (visitCnt == N) {
						break;
					}

					// �������� �̾��� ���� - �Ϲݵ��� �������� ��� pq�� �ֱ�
					// ��, ������ - �������� �������� ����
					for (int i = 1; i <= N; i++) {
						if (cityInfo[city_N].water == 1 && cityInfo[i].water != 1) {
							pq.offer(new edge(city_N, i, tree[city_N][i]));
						}
					}
				}
			}

			bw.write("#" + tc + " " + ans + "\n");
		}

		br.close();
		bw.flush();
		bw.close();

	}

}
