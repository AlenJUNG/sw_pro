package G06_LCA;
import java.io.*;
import java.util.*;

//https://code0xff.tistory.com/135
//https://www.acmicpc.net/problem/11438

public class G06_ex02_Adv_100_11438_LCA {
	static int MAX_N = 100000; // ��� ��
	static int MAX_D = 17; // 2^MAX_D > MAX_N �̸� ����ؼ� ����
	static int N, M;
	static int parent[][]; // parent[MAX_D][MAX_N]
	static int depth[];
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G06_LCA/G06_ex02_Adv_100_11438_LCA.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		parent = new int[MAX_D + 1][MAX_N + 1];
		depth = new int[N + 1];

		graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}

		// ���������� ��������� �׻� ����
		for (int i = 1; i <= N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		/********************** �Էº� �� **********************/
		// STEP 01 : ��� ��忡 ���� ����(depth) ���
		BFS(1);

		M = Integer.parseInt(br.readLine());

		for (int i = 1; i <= M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// STEP 02 : �ּҰ��������� ã�� �� ��� Ȯ��
			System.out.println(LCA(a, b));
		}

		br.close();
	}

	// STEP 01 : ��� ��忡 ���� ����(depth) ���
	private static void BFS(int root) {
		Queue<Integer> q = new LinkedList<>();

		q.offer(root);
		parent[0][root] = 0;
		depth[root] = 0;

		while (!q.isEmpty()) {
			int now = q.poll();

			for (int next : graph.get(now)) {
				// v�� ���� ������ �θ�� continue
				if (next == parent[0][now]) {
					continue;
				}
				// v�� ���� ������ �θ� �ƴ� ���
				q.offer(next); // �������� q�� �ֱ�
				parent[0][next] = now; // ���������� �ٷ� �� �θ������� parent[2^0��]�� �Է�
				depth[next] = depth[now] + 1; // ** �߿� : �������� ���� ���� �Է�

				// DP : �� ������ �Ÿ��� �θ� ���� �Է�
				for (int i = 1; i <= MAX_D; i++) {
					if (parent[i - 1][next] == 0) {
						break;
					}
					// ���������� �θ������� 0�� ��� = root�� ��� > break
					parent[i][next] = parent[i - 1][parent[i - 1][next]];
				}
			}
		}
	}

	// STEP 02 : �ּҰ��������� ã�� �� ��� Ȯ��
	private static int LCA(int a, int b) {
		// A. �� ����� depth ���߱�
		// A-1 : b�� �� ���� ����
		if (a > b) {
			int temp;
			temp = a;
			a = b;
			b = temp;
		}
		// A-2 : ���� ���̰� �����ϵ��� ����
		for (int i = MAX_D; i >= 0; i--) {
			if (depth[b] - depth[a] >= Math.pow(2, i)) {
				b = parent[i][b];
			}
		}

		// ���� b�� ���� ������ �� ���� a�� b�� ���ٸ� a�� ��� �� return
		if (a == b) {
			return a;
		}
		
		// MAX_D > 0 ���� �θ� ���� ���� ��츦 ã�� �����ϱ�
		for (int i = MAX_D; i >= 0; i--) {
			if (parent[i][b] != parent[i][a]) {
				a = parent[i][a];
				b = parent[i][b];
			}
		}
		// parent[0][b] �� return �ص� �������
		return parent[0][a];
	}

}
