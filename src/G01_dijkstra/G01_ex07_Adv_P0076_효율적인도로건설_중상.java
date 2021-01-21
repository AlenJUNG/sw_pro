package G01_dijkstra;

import java.io.*;
import java.util.*;

/* �ִ� 40���� test case �Է½� java 2��
 * 0.05�� 50ms 500�� ũ��
 */

public class G01_ex07_Adv_P0076_ȿ�����ε��ΰǼ�_�߻� {
	static class Node implements Comparable<Node> {
		int v, cost;

		public Node(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node other) {
			if (this.cost < other.cost) {
				return -1;
			}
			return 1;
		}
	}

	// 10�� + 1 > �ִ밣���� (10��) * �ִ� ��������ġ(1��)���� ǥ�� ���ɼ����� ū ��
	static final int MAX = 1000000001;
	static int T, N, M;
	static int cost_s[] = new int[50001]; // �ִ� ����� �� : ���� S
	static int cost_e[] = new int[50001]; // �ִ� ����� �� : �� E
	static ArrayList<Node>[] graph;
	static int answer;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G01_dijkstra/G01_ex07_Adv_P0076_ȿ�����ε��ΰǼ�_�߻�.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {

			Arrays.fill(cost_s, MAX); // ������ �迭 s�� Max (���Ѵ�) ������ ä��
			Arrays.fill(cost_e, MAX); // ������ �迭 e�� Max (���Ѵ�) ������ ä��

			answer = 0; // ��� ���� �ʱ�ȭ

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			graph = new ArrayList[N + 1];
			for (int i = 1; i < N + 1; i++) {
				graph[i] = new ArrayList<Node>();
			}
			// �Է°��� ���⼺�� ���� ������ ����� ���� ����ġ�� ����
			for (int i = 1; i < M + 1; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());

				graph[a].add(new Node(b, c));
				graph[b].add(new Node(a, c));
			}

			// find �Լ��� ��/���������� ���ͽ�Ʈ�� ����
			find(1, N, cost_s);
			find(N, 1, cost_e);

			// ���������� ����� ����ȭ ���� Max�� ����
			int max = cost_s[N];

			// �⺻ ���� �������� ���������� ������ �ᱹ ������������ ���̴�?
			Arrays.sort(cost_e);
			for (int i = 2; i < N; i++) {
				// ��(����ġ) - (���� ���� ����ġ + �ű԰���(1)) = ���̸� �����ϴ� ���� ����ġ ��?
				int target = max - (cost_s[i] + 1);
				int start = 1;
				int end = N - 2; // ���� N�� 2�� �Ÿ��� ������ �༮
				int mid = 0;

				// �Ķ��Ʈ�� ��ġ
				while (start < end) { // �������� ���� ������ Ž��
					mid = (start + end) / 2;
					if (cost_e[mid] >= target) {
						end = mid;
					} else {
						start = mid + 1;
					}
				}

				// �Ķ��Ʈ�� ��ġ�� ���� ���� �����ϴ� ������ ��� ���� ����
				if (cost_e[mid] >= target) {
					mid--;
				}
				answer += mid;
			}

			bw.write("#" + t + " " + answer);
			bw.newLine();
		}

		bw.flush();
		bw.close();
	}

	// find �Լ� - ���ͽ�Ʈ�� ����
	private static void find(int start, int end, int costArr[]) {
		costArr[start] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<Node>();

		// �ʱⰪ ����
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			// ���� ��ȸ���� ������Ʈ �Ǿ��ٸ� �ߴ�
			if (curr.v == end) {
				break;
				// ���� Cost ������ ũ�ٸ� ���� ����
			}
			if (curr.cost > costArr[curr.v]) {
				continue;
			}
			// �ڽ��� �������� Ž���� �� �ִ� �ڽĵ��� üũ
			for (Node child : graph[curr.v]) {
				// ���� ������ ���� �۴ٸ� �����ϰ� Queue�� �־� ����
				if (costArr[child.v] > curr.cost + child.cost) {
					costArr[child.v] = curr.cost + child.cost;
					pq.offer(new Node(child.v, costArr[child.v]));
				}
			}
		}
	}
}