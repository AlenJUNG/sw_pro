package G03_��������;

// https://www.acmicpc.net/problem/11657
import java.io.*;
import java.util.*;

public class G03_ex01_100_11657_Ÿ�Ӹӽ� {
	static class Node {
		int idx, cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}

	static ArrayList<ArrayList<Node>> graph;
	static long d[]; // �ڷ����� int�� �� ��� �����÷ο� �߻�
	static int N, M;
	static int start = 1;
	static final int INF = 60000001; // M * cost �ִ�

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G03_��������/G03_ex01_100_11657_Ÿ�Ӹӽ�.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		d = new long[N + 1];
//		Arrays.fill(d, INF);

		// ���� ����Ʈ ���� & d �迭 ���Ѵ� �Է��� ���ÿ� ����
		graph = new ArrayList<ArrayList<Node>>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Node>());
			d[i] = INF;
		}

		// �ܹ��� ��������Ʈ ����
		int from, to, value;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			value = Integer.parseInt(st.nextToken());

			graph.get(from).add(new Node(to, value));
		}

		// �������尡 ���̸� ���� �߻� = �ð��� ������ ���� ������ ���� �� ����
		if (bellmanFord(start)) {
			bw.write(-1 + "\n");
		} else {
			for (int i = 2; i <= N; i++) {
				// �ش� ���÷� ���� ��ΰ� ������ -1
				if (d[i] == INF) {
					bw.write(-1 + "\n");
					// �ش� ���÷� ���� ��ΰ� ������ �ִܽð� ���
				} else {
					bw.write(d[i] + "\n");
				}
			}
		}

		br.close();
		bw.flush();
		bw.close();
	}

	private static boolean bellmanFord(int s) {
		// �������� s�� �������� 1�� ����
		d[s] = 0; // ��ΰ� ���Ѵ��� �� ������ �ʱ�ȭ "0"
		boolean check_loop = false;

		// * (������ ���� - 1) ��ŭ �ִܰŸ� �ʱ�ȭ �۾� �ݺ�
		for (int i = 1; i <= N - 1; i++) {
			check_loop = false; // ������ �� false�� ����

			// ���� 1���� N���� ��������Ʈ ����Ž��
			for (int now = 1; now <= N; now++) {
				for (Node next : graph.get(now)) {
					if (d[now] == INF) {
						break;
					}
					if (d[next.idx] > d[now] + next.cost) {
						d[next.idx] = d[now] + next.cost;
						check_loop = true;
					}
				}
			}

			// �� �̻� �ִܰŸ� �ʱ�ȭ�� �Ͼ�� ������ �ٷ� �ִܰŸ� �ʱ�ȭ �۾� ����
			if (!check_loop) {
				break;
			}
		}

		// (������ ���� - 1) ������ ����Ž�� ��� ��� ������Ʈ�� �߻��ߴ�?
		// 1. loop�� �ִ� �� ������ > check_loop == true
		// 2. * (������ ����) �� �ٽ� ����Ž�� ��, ������Ʈ �߻��ϸ�
		//      ���� loop�� �ִٰ� �Ǵ��ϰ� �ٷ� �Լ� ����
		if (check_loop) {
			for (int now = 1; now <= N; now++) {
				for (Node next : graph.get(now)) {
					if (d[now] == INF) {
						break;
					}
					if (d[next.idx] > d[now] + next.cost) {
						return true;
					}
				}
			}
		}

		return false;

	}

}
