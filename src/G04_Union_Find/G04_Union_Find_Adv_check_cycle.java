package G04_Union_Find;

import java.util.*;
import java.io.*;

public class G04_Union_Find_Adv_check_cycle {
	// ����� ����(V)�� ����(Union ����)�� ����(E)
	// ����� ������ �ִ� 100,000����� ����
	static int V, E;
	static int parent[] = new int[100001];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G04_Union_Find/G04_Union_Find.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		// �θ� ���̺� �θ� �ڱ� �ڽ����� �ʱ�ȭ
		for (int i = 1; i <= V; i++) {
			parent[i] = i;
		}

		// ����Ŭ �߻� ����
		boolean cycle = false;

		// union ���� ���� ����
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			// ����Ŭ�� �߻��� ��� break ����
			if (findParent(a) == findParent(b)) {
				cycle = true;
				break;
			}
			// ����Ŭ�� �߻����� �ʾҴٸ� ������(Union) ���� ����
			unionParent(a, b);
		}

		if (cycle) {
			System.out.println("����Ŭ �߻�");
		} else {
			System.out.println("����Ŭ �̹߻�");
		}
	}

	// �� ���Ұ� ���� ������ ��ġ��
	private static void unionParent(int a, int b) {
		a = findParent(a); // a�� ��Ʈ��� ã��
		b = findParent(b); // b�� ��Ʈ��� ã��

		// ���� ���� ��Ʈ��尡 �θ� ��
		if (a < b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}

	// Ư�� ���Ұ� ���� ���� ã��
	private static int findParent(int x) {
		// x == parent[x], ��, ��Ʈ����� �״�� return
		if (x == parent[x]) {
			return x;
		// ��Ʈ��尡 �ƴ϶�� ��Ʈ��带 ã�� ������ ���ȣ��
		}else {
			return parent[x] = findParent(parent[x]);
		}		
	}
}