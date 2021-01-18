package G07_��������;

import java.util.*;
import java.io.*;

public class G07_ex01_Adv_Ŀ��ŧ�� {
	static int N; // ����� ����
	static int indegree[]; // ��������
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static int time[]; // �� ���ǽð�

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G07_��������/G07_ex01_Adv_Ŀ��ŧ��.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		// ����� �ϴ� ���� ����� �迭 ũ�� ����
		indegree = new int[501];
		time = new int[501];

		// graph �ʱ�ȭ
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}

		// ���� �׷����� ��� ���� ������ �Է¹ޱ�
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			// ù ��° ���ڴ� �ð� ����
			time[i] = Integer.parseInt(st.nextToken());
			int from;
			// ��������(indegree) �� �׷��� ���İ��� �Է�
			while (true) {
				from = Integer.parseInt(st.nextToken());
				// ��������
				if (from == -1) {
					break;
				}
				indegree[i] += 1;
				graph.get(from).add(i);
			}
		}
		topologySort();
	}

	private static void topologySort() {
		// �˰��� ���� ����� ���� �迭 ����
		int result[] = new int[501];
		// time > result �迭����
		for (int i = 1; i <= N; i++) {
			result[i] = time[i];
		}

		// q ����
		Queue<Integer> q = new LinkedList<>();

		// ó�� ������ �� ���������� 0�� ��带 q�� ����
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				q.offer(i);
			}
		}

		while (!q.isEmpty()) {
			int now = q.poll();

			for (int toIdx = 0; toIdx < graph.get(now).size(); toIdx++) {
				// �ش� ���ҿ� ����� ������ �ð��� ����
				result[graph.get(now).get(toIdx)] = result[now] + time[graph.get(now).get(toIdx)];
				// * �Ʒ� �ڵ�� �Ƹ� �ʱ� ���������� 0�� ��尡 ������ �� ��� ��ȿ�� �ڵ� ���� > ������ ��
//				result[graph.get(now).get(toIdx)]
//						= Math.max(result[graph.get(now).get(toIdx)], result[now] + time[graph.get(now).get(toIdx)]);
				// �ش� ���ҿ� ����� ������ ������������ 1����
				indegree[graph.get(now).get(toIdx)] -= 1;
				// ���Ӱ� ���������� 0�� �Ǵ� ��带 q�� ����
				if (indegree[graph.get(now).get(toIdx)] == 0) {
					q.offer(graph.get(now).get(toIdx));
				}
			}
		}

		// ���� ���� ���� ��� ���

		for (int i = 1; i <= N; i++) {
			System.out.println(result[i]);
		}
	}
}
