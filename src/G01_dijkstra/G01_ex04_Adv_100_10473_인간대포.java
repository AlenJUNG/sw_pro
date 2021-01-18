package G01_dijkstra;
// https://www.acmicpc.net/problem/10473

import java.util.*;
import java.io.*;

public class G01_ex04_Adv_100_10473_�ΰ����� {
	static class Daepo implements Comparable<Daepo> {
		int idx;
		double time;

		public Daepo(int idx, double time) {
			this.idx = idx;
			this.time = time;
		}

		public int getIdx() {
			return this.idx;
		}

		public double getTime() {
			return this.time;
		}

		@Override
		public int compareTo(Daepo other) {
			if (this.time < other.time) { // �ӵ� ����ġ
				return -1;
			}
			return 1;
		}
	}

	static final double INF = (double) 1e9; // �ִ밪 > double ����
	static int N; // ���� ��
	static double node[][]; // node[N+2][2] => node[���� > ������ > ����][x��ǥ, y��ǥ]
	static double D[]; // dijkstra �ӵ� ����ġ
	static ArrayList<ArrayList<Daepo>> map;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G01_dijkstra/G01_ex04_Adv_100_10473_�ΰ�����.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// ������
		StringTokenizer st = new StringTokenizer(br.readLine());
		double sx = Double.parseDouble(st.nextToken());
		double sy = Double.parseDouble(st.nextToken());

		// ������
		st = new StringTokenizer(br.readLine());
		double ex = Double.parseDouble(st.nextToken());
		double ey = Double.parseDouble(st.nextToken());

		N = Integer.parseInt(br.readLine()); // ������

		node = new double[N + 2][2];
		// �������� ������ �Է��ϰ� ����
		node[0][0] = sx;
		node[0][1] = sy;
		node[N + 1][0] = ex;
		node[N + 1][1] = ey;

		// N + 2 ����
		D = new double[N + 2];

		map = new ArrayList<ArrayList<Daepo>>();
		for (int i = 0; i <= N + 1; i++) {
			map.add(new ArrayList<Daepo>());
			D[i] = INF; // �Ÿ� ���̺� �ʱ�ȭ
		}

		// ������ ��ǥ node �Է�
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			node[i][0] = Double.parseDouble(st.nextToken());
			node[i][1] = Double.parseDouble(st.nextToken());
		}

		/************** �Էº� ���� **************/

		double tx, ty; // target_x, target_y
		double distance, only_walk, use_daepo;

		// STEP01 : ����� <-> ���� 1, 2, 3, 4, ������, ����Ž���Ͽ� map�� �Է�
		// * ���������� �� �������� ������ �ɾ�߸� �Ѵ�.
		for (int to = 1; to <= N + 1; to++) {
			tx = node[to][0];
			ty = node[to][1];

			distance = Math.sqrt(Math.pow((sx - tx), 2) + Math.pow((sy - ty), 2));
			only_walk = distance / 5.0;
			// 0 = ������ <-> ��ü �� ��� �ɾ�� �� �ð� �Է�
			map.get(0).add(new Daepo(to, only_walk));
		}

		// STEP02 : dijkstra ���� ��尣 ���� Ȯ��
		// * ������� ������ ������ ��尣 ����Ž���Ͽ� ���� Ȯ�� : only_walk vs use_daepo
		double min_time;

		for (int from = 1; from <= N + 1; from++) {
			for (int to = from + 1; to <= N + 1; to++) {
				sx = node[from][0];
				sy = node[from][1];
				ex = node[to][0];
				ey = node[to][1];

				distance = Math.sqrt(Math.pow((sx - ex), 2) + Math.pow((sy - ey), 2));

				// �ɾ�� �� �ð� vs ������ �̿����� �� �ð�
				only_walk = distance / 5.0;
				use_daepo = 2.0 + Math.abs(50 - distance) / 5.0;

				// �ּҽð��� min_time�� �Է�
				min_time = Math.min(only_walk, use_daepo);

				// ����� �Է�
				map.get(from).add(new Daepo(to, min_time));
				map.get(to).add(new Daepo(from, min_time));
			}
		}

		// STEP03 : Run dijkstra
		dijkstra(0, map, D);
		
		System.out.println(Math.floor(D[N + 1] * 1000000) / 1000000);
//		System.out.println(String.format("%.6f", D[N + 1]));

		br.close();
	}

	private static void dijkstra(int start, ArrayList<ArrayList<Daepo>> gra, double[] d) {
		PriorityQueue<Daepo> pq = new PriorityQueue<>();
		pq.offer(new Daepo(start, 0.0));
		d[start] = 0;

		while (!pq.isEmpty()) {
			Daepo dp = pq.poll();
			int now_idx = dp.getIdx();
			double now_time = dp.getTime();

			if (d[now_idx] < now_time) {
				continue;
			}

			for (int toIdx = 0; toIdx < gra.get(now_idx).size(); toIdx++) {
				double temp = d[now_idx] + gra.get(now_idx).get(toIdx).getTime();
				if (d[gra.get(now_idx).get(toIdx).getIdx()] > temp) {
					d[gra.get(now_idx).get(toIdx).getIdx()] = temp;
					pq.offer(new Daepo(gra.get(now_idx).get(toIdx).getIdx(), gra.get(now_idx).get(toIdx).getTime()));
				}
			}
		}
	}
}
