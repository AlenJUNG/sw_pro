package G03_��������;

import java.io.*;
import java.util.*;

// Update 2021.05.22, G03_ex01_100_11657_Ÿ�Ӹӽ�
// https://www.acmicpc.net/problem/11657

public class hj_test {
	static class Node {
		int t, c;

		public Node(int t, int c) {
			this.t = t;
			this.c = c;
		}
	}

	static int N, M;
	// 500 * 6000 * (+-10000)�� int���̸� overflow �� �� ����, long ����
	static final int INF = 60000001;	 
	static ArrayList<Node> graph[];
	static long D[];	// ���� overflow ����

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G03_��������/G03_ex01_100_11657_Ÿ�Ӹӽ�.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		D = new long[N + 1];		
		graph = new ArrayList[N + 1];
		
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<Node>();
			D[i] = INF;
		}

		int from, to, cost;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());

			graph[from].add(new Node(to, cost));
		}
		
		boolean check = bellmanFord(graph, D, 1);

		// �������尡 ���̸� ���� �߻� = �ð��� ������ ���� ������ ���� �� ����
		if (check) {
			bw.write(-1 + "\n");	// * �߿� * : ���� ��½ÿ��� " " ���� �ʱ�
		} else {
			for (int i = 2; i <= N; i++) {
				// �ش� ���÷� ���� ��ΰ� ������ -1
				if (D[i] == INF) {
					bw.write(-1 + "\n");
					// �ش� ���÷� ���� ��ΰ� ������ �ִܽð� ���
				} else {
					bw.write(D[i] + "\n");
				}
			}
		}

		br.close();
		bw.flush();
		bw.close();

	}
	private static boolean bellmanFord(ArrayList<Node>[] gra, long[] d, int start) {
		// �������� s�� �������� 1�� ����
		d[start] = 0; // ��ΰ� ���Ѵ��� �� ������ �ʱ�ȭ "0"
		// ������Ʈ�� �߻����� �ʴ´ٸ� �ݺ��� ���� ���� (���û���)
		boolean update = false;

		// * (������ ���� - 1) ��ŭ �ִܰŸ� �ʱ�ȭ �۾� �ݺ�
		// ���� ����Ŭ (�ִܰŸ��� ������ ������ �������� ���)�� ������
		// ��� N - 1 �� ������ ������ �ִܰ�� Ȯ����
		for (int i = 1; i <= N - 1; i++) {
			update = false; // * ������ ������ false�� ����

			// ���� 1���� N���� ��������Ʈ ����Ž��
			for (int now = 1; now <= N; now++) {
				for (Node next : graph[now]) {
					/* ��� �������� ����Ǿ� �ִٴ� ������ ���� ��
					 * �ٸ� ������ ���̿��� ����Ŭ�� �߻��� �� �ִ� ���
					 * ���� ����Ŭ�� ������ �ľ��Ϸ���
					 * if(d[now] == INF) continue ���� ����
					 */
					if (d[now] == INF) {
						continue;
					}
					if (d[next.t] > d[now] + next.c) {
						d[next.t] = d[now] + next.c;
						update = true;
					}
				}
			}

			// �� �̻� �ִܰŸ� �ʱ�ȭ�� �Ͼ�� ������ �ٷ� �ִܰŸ� �ʱ�ȭ �۾� ���� (���û���)
			if (!update) {
				break;
			}
		}

		// (������ ���� - 1) ������ ����Ž�� ��� ��� ������Ʈ�� �߻��ߴ�?
		// 1. update�� �ִ� �� ������ > update == true
		// 2. * (������ ����) �� �ٽ� ����Ž�� ��, ������Ʈ �߻��ϸ�
		//      ���� ��ȯ�� �ִٰ� �Ǵ��ϰ� �ٷ� �Լ� ����
		if (update) {
			for (int now = 1; now <= N; now++) {
				for (Node next : graph[now]) {
					if (d[now] == INF) {
						continue;
					}
					if (d[next.t] > d[now] + next.c) {
						return true;
					}
				}
			}
		}

		return false;

	}
	
	// �迭�� Ǯ��
	private static boolean bellmanFord1(ArrayList<Node>[] gra, long[] d, int start) {
		boolean update = false;

		d[start] = 0;

		for (int i = 1; i <= N - 1; i++) {
			update = false;

			for (int now = 1; now <= N; now++) {
				for (Node next : graph[now]) {
					int next_node = next.t;
					int next_cost = next.c;

					if (d[now] == INF) {
						continue;
					}

					if (d[next_node] > d[now] + next_cost) {
						d[next_node] = d[now] + next_cost;
						update = true;
					}
				}
			}

			// ����
			if (!update) {
				break;
			}
		}

		// ���� ����Ŭ �Ǻ�
		boolean NegativeCycle = false;

		for (int now = 1; now <= N; now++) {
			for (Node next : graph[now]) {
				int next_node = next.t;
				int next_cost = next.c;

				if (d[now] == INF) {
					continue;
				}

				if (d[next_node] > d[now] + next_cost) {
					// d[next_node] = d[now] + next_cost;
					NegativeCycle = true;
					break;
				}
			}
		}

		return NegativeCycle;

	}

}
