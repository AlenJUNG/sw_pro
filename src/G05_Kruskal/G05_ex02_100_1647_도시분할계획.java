package G05_Kruskal;

/* https://www.acmicpc.net/problem/1647
 * ������ N���� ���� �� ������ �����ϴ� M���� ��� �̷���� �ִ�. ���� ��� �������ε��� �ٴ� �� �ִ� ���� ���̴�. �׸��� �� �渶�� ���� �����ϴµ� ��� ������ �ִ�. 
 * ������ ������ ������ �� ���� �и��� ������ ������ ��ȹ�� ������ �ִ�. 
 * ������ ������ ���� �� �и��� ���� �ȿ� ������ ���� ����ǵ��� �����ؾ� �Ѵ�. 
 * �� �и��� ���� �ȿ� �ִ� ������ �� �� ���̿� ��ΰ� �׻� �����ؾ� �Ѵٴ� ���̴�. 
 * �������� ���� �ϳ� �̻� �־�� �Ѵ�.
 * �׷��� ������ ������ ��ȹ�� ����ٰ� ���� �ȿ� ���� �ʹ� ���ٴ� ������ �ϰ� �Ǿ���. 
 * �ϴ� �и��� �� ���� ���̿� �ִ� ����� �ʿ䰡 �����Ƿ� ���� �� �ִ�. 
 * �׸��� �� �и��� ���� �ȿ����� ������ �� �� ���̿� ��ΰ� �׻� �����ϰ� �ϸ鼭 ���� �� ���� �� �ִ�. 
 * ������ ������ �� ������ �����ϵ��� ����� ��� ���ְ� ������ ���� �������� ���� �ּҷ� �ϰ� �ʹ�.
 */

import java.util.*;
import java.io.*;

public class G05_ex02_100_1647_���ú��Ұ�ȹ {
	static int N, M, max; // N�� 10�� ����, M�� 100�� ����
	static long ans;
	static int parent[];
	static PriorityQueue<Node> pq;

	static class Node implements Comparable<Node> {
		int f, t, c;

		public Node(int f, int t, int c) {
			this.f = f;
			this.t = t;
			this.c = c;
		}

		@Override
		public int compareTo(Node other) {
			if (this.c < other.c) {
				return -1;
			}
			return 1;
		}
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_ex02_100_1647_���ú��Ұ�ȹ.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		pq = new PriorityQueue<Node>();
		int from, to, cost;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());

			pq.offer(new Node(from, to, cost));
			pq.offer(new Node(to, from, cost));
		}

		ans = 0;
		max = 0;
		while (!pq.isEmpty()) {
			Node now = pq.poll();

			if (find(now.f) == find(now.t)) {
				continue;
			} else {
				union(now.f, now.t);
				ans += now.c;
				max = now.c; // �ٽ� ���̵�� : pq ���� �� ������ now.c ���� �ڵ������� Max cost ���� �ȴ�
			}
		}
		
		// �ٽ� ���̵�� : ��ü �׷������� 2���� MST�� ������ ��
		// �ּ����� ������� 2���� ���� Ʈ���� ����
		// > ũ�罺Į �˰��� MST - ���� ����� ū ���� ����
		bw.write(ans - max + "\n");

		br.close();
		bw.flush();
		bw.close();
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a > b) {
			parent[a] = b;
		} else {
			parent[b] = a;
		}

	}

	private static int find(int x) {
		if (x == parent[x]) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

}
