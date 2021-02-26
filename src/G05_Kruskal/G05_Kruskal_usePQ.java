package G05_Kruskal;

import java.io.*;
import java.util.*;

// N : ��� ��, M : ���� �� > MST cost�� ���϶�

public class G05_Kruskal_usePQ {
	static class Node implements Comparable<Node> {
		int from, to, dis;

		public Node(int from, int to, int dis) {
			this.from = from;
			this.to = to;
			this.dis = dis;
		}

		@Override
		public int compareTo(Node other) {
			if (this.dis < other.dis) {
				return -1;
			}
			return 1;
		}

	}

	static int N, M, ans;
	static int parent[];
	static PriorityQueue<Node> pq;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_Kruskal.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];

		pq = new PriorityQueue<>();
		
		int from, to, distance;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			distance = Integer.parseInt(st.nextToken());
			
			pq.offer(new Node(from, to, distance));
		}
		
		/**************** �Էº� �� ****************/
		
		// SPTE01. ����迭�� ��� �ڽ��� ���� ��� �־���
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		ans = 0;
		
		// SPTE02. ����迭�� ��� �ڽ��� ���� ��� �־���
		// PQ�� ���� �������� ���� �޼ҵ� ���� �ʿ� ���� ex) Collections.sort
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			
			// �׻� ���������� �־��ִ� ������ ���� ��
			if (findRoot(now.from) == findRoot(now.to)) {
				continue;
			// * ������ ���� ������ ������ �̾��ش�
			} else {
				union(now.from, now.to);
				ans += now.dis; // �������� ���ϴ� cost �� ���ϱ�
			}
		}
		
		bw.write(ans + "\n");

		br.close();
		bw.flush();
		bw.close();
	}

	private static void union(int a, int b) {
		a = findRoot(a);
		b = findRoot(b);

		if (a > b) {
			parent[a] = parent[b];
		} else {
			parent[b] = parent[a];
		}
	}

	private static int findRoot(int x) {
		if (x == parent[x]) {
			return x;
		}
		return parent[x] = findRoot(parent[x]);
	}

}
