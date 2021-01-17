package G07_��������;

import java.util.*;
import java.io.*;

public class G07_�������� {

	// ����� ����(V)�� ������ ����(E)
	// ����� ������ �ִ� 100,000����� ����
	public static int v, e;
	// ��� ��忡 ���� ���������� 0���� �ʱ�ȭ
	public static int[] indegree = new int[100001];
	// �� ��忡 ����� ���� ������ ��� ���� ���� ����Ʈ �ʱ�ȭ
	public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G07_��������/G07_��������.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());

		// �׷��� �ʱ�ȭ
		for (int i = 0; i <= v; i++) {
			graph.add(new ArrayList<Integer>());
		}

		// ���� �׷����� ��� ���� ������ �Է� �ޱ�
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b); // ���� A���� B�� �̵� ����
			// ���� ������ 1 ����
			indegree[b] += 1;
		}

		topologySort();
	}

	// �������� �Լ�
	private static void topologySort() {
		ArrayList<Integer> result = new ArrayList<>(); // �˰��� ���� ����� ���� ����Ʈ
		Queue<Integer> q = new LinkedList<>(); // ť ���̺귯�� ���

		// ó�� ������ ���� ���������� 0�� ��带 ť�� ����
		for (int i = 1; i <= v; i++) {
			if (indegree[i] == 0) {
				q.offer(i);
			}
		}

		// ť�� �� ������ �ݺ�
		while (!q.isEmpty()) {
			// ť���� ���� ������
			int now = q.poll();
			result.add(now);
			// �ش� ���ҿ� ����� ������ ������������ 1 ����
			for (int i = 0; i < graph.get(now).size(); i++) {
				indegree[graph.get(now).get(i)] -= 1;
				// ���Ӱ� ���������� 0�� �Ǵ� ��带 ť�� ����
				if (indegree[graph.get(now).get(i)] == 0) {
					q.offer(graph.get(now).get(i));
				}
			}
		}

		// ���� ������ ������ ��� ���
		for (int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i) + " ");
		}
	}

}
