package G01_dijkstra;
// ���� ��
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
	static boolean visited[];
	static double D[]; // dijkstra �Ÿ� ���ϱ�
	static ArrayList<ArrayList<Daepo>> map1;
	static ArrayList<Daepo>[] map;

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
		visited = new boolean[N + 2]; // �湮����
		node = new double[N + 2][2];
		// �������� ������ �Է��ϰ� ����
		node[0][0] = sx;
		node[0][1] = sy;
		node[N + 1][0] = ex;
		node[N + 1][1] = ey;

		// N + 2 ����
		map = new ArrayList[N + 2];
		D = new double[N + 2];
		for (int i = 0; i <= N + 1; i++) {
			map[i] = new ArrayList<Daepo>();
			D[i] = INF; // �Ÿ� ���̺� �ʱ�ȭ
		}

		// ���� �Է�
		map1 = new ArrayList<ArrayList<Daepo>>();
		for (int i = 0; i <= N + 1; i++) {
			map1.add(new ArrayList<Daepo>());
		}

		// ������ ��ǥ �Է�
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			node[i][0] = Double.parseDouble(st.nextToken());
			node[i][1] = Double.parseDouble(st.nextToken());
		}

		double tx, ty; // target_x, target_y
		double distance, only_walk, use_daepo;

		// * ����� <-> ���� 1, 2, 3, 4, ������, ����Ž���Ͽ� map�� �Է�
		for (int i = 1; i <= N + 1; i++) {
			tx = node[i][0];
			ty = node[i][1];

			distance = Math.sqrt(Math.pow((sx - tx), 2) + Math.pow((sy - ty), 2));
			only_walk = distance / 5.0;
			// 0 = ������ <-> ��ü �� ��� �ɾ�� �� �ð� �Է�
			map[0].add(new Daepo(i, only_walk));
		}

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
				// �ּҽð��� min�� �Է�
				min_time = Math.min(only_walk, use_daepo);

				// ����� �Է�
				map[from].add(new Daepo(to, min_time));
				map[to].add(new Daepo(from, min_time));

				map1.get(from).add(new Daepo(to, min_time));
				map1.get(to).add(new Daepo(from, min_time));
			}
		}

		dijkstra(0, map1, D);
//		dijkstra1(0);

		System.out.println(String.format("%.6f", D[N + 1]));

	}
	
	// answer
	private static void dijkstra1(int start) {
		//���ͽ�Ʈ�� ����
		PriorityQueue<Daepo> pq = new PriorityQueue<Daepo>();
		pq.clear();
		pq.add(new Daepo(0, 0.0));	// ������� 0���� �����߰�, �� �� �Ÿ��� 0.0
		D[0] = 0;
		
		while(!pq.isEmpty()) {
			Daepo curr = pq.poll();
			
			if(visited[curr.idx]) {
				continue; //�̹� �湮������ �ǳʶ�
			}
			
			visited[curr.idx] = true;
			
			for(int i = 0; i < map[curr.idx].size(); i++) {
				Daepo next = map[curr.idx].get(i);
				
				if(visited[next.idx]) {
					continue;
				}
				
				if(D[next.idx] > curr.time + next.time) {
					D[next.idx] = curr.time + next.time;
					pq.add(new Daepo(next.idx, D[next.idx]));
				}
			}
		}		
	}
	
	// hj
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
