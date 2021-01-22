package G01_dijkstra;

import java.io.*;
import java.util.*;

/* �ִ� 40���� test case �Է½� java 2��
 * 0.05�� 50ms 500�� ũ��
 */

public class G01_ex07_Adv_P0076_ȿ�����ε��ΰǼ�_�߻� {
	static class Node implements Comparable<Node> {
		int idx, dis;

		public Node(int idx, int dis) {
			this.idx = idx;
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

	// 10�� + 1 > �ִ밣���� (10��) * �ִ� ��������ġ(1��)���� ǥ�� ���ɼ����� ū ��
	static final int INF = 1000000001;	// edge * cost
	static int N, M, ans;
	static int D[], reverse_D[];
	static ArrayList<ArrayList<Node>> graph;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G01_dijkstra/G01_ex07_Adv_P0076_ȿ�����ε��ΰǼ�_�߻�.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;

		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		ans = 0;	// ��� ���� �ʱ�ȭ

		D = new int[N + 1];
		reverse_D = new int[N + 1];

		Arrays.fill(D, INF);
		Arrays.fill(reverse_D, INF);

		graph = new ArrayList<ArrayList<Node>>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Node>());
		}
		
		// �Է°��� ���⼺�� ���� ������ ����� ���� ����ġ�� ����
		int from, to, value;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			value = Integer.parseInt(st.nextToken());

			graph.get(from).add(new Node(to, value));
			graph.get(to).add(new Node(from, value));
		}
		
		// ��/���������� ���ͽ�Ʈ�� ����
		dijkstra(1, N, D);
		dijkstra(N, 1, reverse_D);

		// ���������� ����� ����ȭ �� > MAX
		int max = D[N];

		// end���� ������ ���� Ž�� > �������� ����
		// �Ķ��Ʈ�� ��ġ�� �⺻ ���� > Ž���ؾ��ϴ� �� ����
		Arrays.sort(reverse_D);

		/*
		 * * �����غ��� 
		 * 1. max = D[mid] + reverseD[mid] 
		 * 2. max = D[mid] + (mid ~ mid + 1 ����� ���� ��) + reverseD[mid + 1]  
		 * 3. �Ÿ� (1 ~ N) = �Ÿ� (1 ~ A) + 1 (A ~ B) + �Ÿ� (B ~ N)
		 * ** ���� Ž���� �ð� ���⵵�� ���ϼ��� ������? �츮�� �Ʒ� ���� �����ϴ� ���� ������ �ʿ��ϱ� ������ > �Ķ��Ʈ�� ��ġ�� �����Ѵ�
		 * if ( �Ÿ� (1 ~ N) - (�Ÿ� (1 ~ A) + 1 (A ~ B)) > �Ÿ� (B ~ N) )
		 */
		
		for (int i = 2; i < N; i++) {
			int target = max - (D[i] + 1);

			// reverse_D idx 1 ~ N -2 �Ķ��Ʈ�� ��ġ > ����Ž��
			int start = 1;
			// * �������� : �迭 Ư�� > ���ĵ� �� �������� INF�̱� ���� > INF ����
			// �����߱� ������ reverse_D[N - 1] : ������, reverse_D[N] = INF <> �ʱ� 0 ��
			int end = N - 2;
			int mid = 0;

			// �Ʒ���� whlie�� ���� ��, mid�� �����ϴ� �� + 1�� ����Ű�� �ȴ�.
			while (start < end) {
				mid = (start + end) / 2;

				if (target <= reverse_D[mid]) {
					end = mid;
				} else {
					start = mid + 1;
				}
			}

			// * �ѹ� �� > �����ϴ� �� +1 �� mid���� Ȯ��
			// �׷��ٸ� target > reverse_D[mid]�� �����ϴ� ������ mid--
			if (target <= reverse_D[mid]) {
				mid--;
			}

			// �� �ݿ�
			ans += mid;
		}
		
		bw.write("#"+t+" "+ans+"\n");
		}
		
		br.close();
		bw.flush();
		bw.close();
		
	}

	private static void dijkstra(int start, int end, int[] d) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		d[start] = 0;
//		boolean check[] = new boolean[N+1];

		while (!pq.isEmpty()) {
			Node now = pq.poll();

//			if(check[now.idx]) {
//				continue;
//			}
//			
//			check[now.idx] = true;

			if (now.idx == end) {
				break;
			}

			if (d[now.idx] < now.dis) {
				continue;
			}

			for (Node next : graph.get(now.idx)) {
				if (d[next.idx] > d[now.idx] + next.dis) {
					d[next.idx] = d[now.idx] + next.dis;
					pq.offer(new Node(next.idx, d[next.idx]));
				}
			}

		}

	}
}
