package G02_�÷��̵����;

import java.io.*;
import java.util.*;

public class G02_�÷��̵���� {
	static final int INF = (int) 1e9; // ������ �ǹ��ϴ� ������ 10���� ����
	// ����� ����(N), ������ ����(M)
	// ����� ������ �ִ� 500����� ����
	static int N, M;
	// 2���� �迭(�׷��� ǥ��)�� �����
	static int graph[][];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G02_�÷��̵����/G02_�÷��̵����.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		graph = new int[3000][3000];

		// ���� ���� �� Arrays.fill ���� ������
		// �ִ� �Ÿ� ���̺��� ��� �������� �ʱ�ȭ
		for (int i = 0; i < 3000; i++) {
			Arrays.fill(graph[i], INF);
		}

		// �ڱ� �ڽſ��� �ڱ� �ڽ����� ���� ����� 0���� �ʱ�ȭ
		for (int from = 1; from <= N; from++) {
			for (int to = 1; to <= N; to++) {
				if (from == to) {
					graph[from][to] = 0;
				}
			}
		}

		// �� ������ ���� ������ �Է� �޾�, �� ������ �ʱ�ȭ
		for (int i = 1; i <= M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			graph[from][to] = value;
		}

		// ��ȭ�Ŀ� ���� �÷��̵� ���� �˰����� ����
		for (int mid = 1; mid <= N; mid++) {
			for (int from = 1; from <= N; from++) {
				for (int to = 1; to <= N; to++) {
					graph[from][to] = Math.min(graph[from][to], graph[from][mid] + graph[mid][to]);
				}
			}
		}

		// ����� ����� ���
		for (int from = 1; from <= N; from++) {
			for (int to = 1; to <= N; to++) {
				if (graph[from][to] == INF) {
					System.out.println("INFINITY");
				} else {
					System.out.print(graph[from][to] + " ");
				}
			}
			System.out.println();
		}
	}
}
