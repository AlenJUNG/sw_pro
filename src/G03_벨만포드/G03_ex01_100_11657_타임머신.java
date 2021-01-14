package G03_��������;

// https://www.acmicpc.net/problem/11657
import java.util.*;
import java.io.*;

class Node {
	int idx;
	int distance;

	public Node(int idx, int distance) {
		this.idx = idx;
		this.distance = distance;
	}

	public int getIdx() {
		return this.idx;
	}

	public int getDistance() {
		return this.distance;
	}
}

public class G03_ex01_100_11657_Ÿ�Ӹӽ� {
	static final int INF = (int) 1e9; // 10�� ����
	static int N, M;
	static int start = 1; // �������� 1 ����
	static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
	static long dis[]; // *����* �ڷ����� int �� �� ��� �����÷ο� �߻�

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G03_��������/G03_ex01_100_11657_Ÿ�Ӹӽ�.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Node>());
		}
		
		// �ܹ��� ��������Ʈ ����
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());

			graph.get(from).add(new Node(to, value));
		}

		dis = new long[N + 1];

		Arrays.fill(dis, INF);

		// bellmanFord ���� ���� �߻� ��, return 1
		if (bellmanFord(start) == 1) {
			System.out.println(-1);
		} else {
			for (int to = 2; to <= N; to++) {
				if (dis[to] == INF) {
					System.out.print(-1 + " ");
				} else {
					System.out.print(dis[to] + " ");
				}
				System.out.println();
			}
		}
	}

	private static int bellmanFord(int startN) {
		dis[startN] = 0;	// �������� �Ÿ��� 0���� �ʱ�ȭ
		int check_loop = 0;
		
		// (������ ���� - 1)�� ���� �ִܰŸ� �ʱ�ȭ �۾��� �ݺ�
		for (int round = 1; round < N; round++) {
			check_loop = 0;
			
			// �ִܰŸ� �ʱ�ȭ
			for (int next = 1; next <= N; next++) {
				for (Node node : graph.get(next)) {
					if (dis[next] == INF) {
						break;
					}

					if (dis[node.idx] > dis[next] + node.distance) {
						dis[node.idx] = dis[next] + node.distance;
						check_loop = 1;
					}
				}
			}
			
			// ���̻� �ִܰŸ� �ʱ�ȭ�� �Ͼ�� �ʾ��� ��� �ݺ��� ����
			if (check_loop != 1) {
				break;
			}

		}
		
		// (������ ���� - 1)������ ��� ������Ʈ�� �߻����� ���
        // (������ ����)���� ������Ʈ �߻��ϸ� ���� ����Ŭ�� �Ͼ ���� �ǹ�
		if (check_loop == 1) {
			for (int next = 1; next <= N; next++) {
				for (Node node : graph.get(next)) {
					if (dis[next] == INF) {
						break;
					}

					if (dis[node.idx] > dis[next] + node.distance) {
						return 1;
					}
				}
			}
		}

		return 0;
	}
}
