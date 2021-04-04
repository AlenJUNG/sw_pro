package G03_��������;

import java.util.*;
import java.io.*;

public class Main {
	static int TC, V, E, A;
	// INF�� LongMax�� �����ϸ� ����, Ʋ���Բ� ���� ����
	// ���� ���� E 3000 �� x �Ƿε� ��ġ 1000000 x 10 ���� ������
	static long INF = 300000000001L;
	static ArrayList<ArrayList<Node>> graph;
	// �ִ� �Ƿε� E 3000 �� x �Ƿε� ��ġ 1000000 > long type ����
	static long cost[];
	static int angel[];

	static class Node {
		int idx, cost;

		public Node(int idx, int cost) {
			this.idx = idx; // to
			this.cost = cost; // cost
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/G03_��������/G03_ex02_P0071_����Ž��.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {

			st = new StringTokenizer(br.readLine());

			V = Integer.parseInt(st.nextToken()); // ���
			E = Integer.parseInt(st.nextToken()); // ����
			A = Integer.parseInt(st.nextToken()); // õ��

			cost = new long[V + 1];
			angel = new int[V + 1];
			graph = new ArrayList<ArrayList<Node>>();

			// �ִܰŸ� �ʱ�ȭ : �������� 0, �������� V - 1
			for (int i = 0; i <= V; i++) {
				cost[i] = INF; // �⺻ ���ø�
				angel[i] = 0; // �� ���� ��������
				graph.add(new ArrayList<Node>());
			}

			// ����� ��������Ʈ ����
			int f, t, d;
			for (int i = 1; i <= E; i++) {
				st = new StringTokenizer(br.readLine());
				f = Integer.parseInt(st.nextToken());
				t = Integer.parseInt(st.nextToken());
				d = Integer.parseInt(st.nextToken());

				graph.get(f).add(new Node(t, d));
				graph.get(t).add(new Node(f, d));
			}

			// �ܹ��� angel graph ����Ʈ ���� �Է� > ���� ����
			for (int i = 1; i <= A; i++) {
				st = new StringTokenizer(br.readLine());
				f = Integer.parseInt(st.nextToken());
				t = Integer.parseInt(st.nextToken());
				d = Integer.parseInt(st.nextToken());

				graph.get(f).add(new Node(t, -d));
			}

			if (bellmanFord(0)) {
				bw.write("#"+tc+" "+"BUG" + "\n");
			} else if (cost[V - 1] > 100_000_000_000L) {
				bw.write("#"+tc+" "+"NO" + "\n");
			} else {
				bw.write("#"+tc+" "+cost[V - 1] + " " + angel[V - 1] + "\n");
			}

			// bw.write(cost[V-1]+"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	// loop �ִٸ� true
	public static boolean bellmanFord(int start) {
		cost[0] = 0;
		boolean check_loop = false;

		for (int i = 1; i <= V - 1; i++) {
			check_loop = false; // ������ �� false�� ����

			// ���� 1���� N���� ��������Ʈ ����Ž��
			for (int now = 0; now <= V - 1; now++) {
				for (Node next : graph.get(now)) {
					if (cost[now] == INF) {
						break;
					}
					if (cost[next.idx] > cost[now] + next.cost) {
						cost[next.idx] = cost[now] + next.cost;
						if (next.cost < 0) { // cost�� �����̸� angel ������Ʈ
							angel[next.idx] = angel[now] + 1;
						} else {
							angel[next.idx] = angel[now];
						}
						check_loop = true;

					} else if (cost[next.idx] == cost[now] + next.cost) {
						if (next.cost < 0) {
							angel[next.idx] = Math.min(angel[next.idx], angel[now] + 1);
						} else {
							angel[next.idx] = Math.min(angel[next.idx], angel[now]);
						}
					}
				}
			}

			// �� �̻� �ִܰŸ� �ʱ�ȭ�� �Ͼ�� ������ �ٷ� �ִܰŸ� �ʱ�ȭ �۾� ����
			if (!check_loop) {
				break;
			}
		}

		if (check_loop) {
			for (int now = 0; now <= V - 1; now++) {
				for (Node next : graph.get(now)) {
					if (cost[now] == INF) {
						break;
					}
					if (cost[next.idx] > cost[now] + next.cost) {
						return true;
					}
				}
			}
		}

		return false;

	}
}
