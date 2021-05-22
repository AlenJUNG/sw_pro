package G02_�÷��̵����;

import java.io.*;
import java.util.*;

/* 2021.05.22, �÷��̵���� ����
 * https://www.acmicpc.net/problem/11404
 */

public class G02_ex00_100_11404_�÷��̵���� {
	static final int INF = 100 * 100000 + 1;	// �ִ� ���� �� + ������ �ִ� ���
	static int N, M;
	static int arr[][];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G02_�÷��̵����/G02_ex00_100_11404_�÷��̵����.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		arr = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			Arrays.fill(arr[i], INF);	// ���߹迭 INF �ϰ��Է�
			arr[i][i] = 0;	// ������ �������� �Ÿ��� 0���� ����
		}

		int from, to, cost;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			
			// * ���� : ���۵��ÿ� �������ø� �����ϴ� �뼱�� �ϳ��� �ƴ� �� ����
			// ������ �뼱�� ������� ����� ���� ������ ������Ʈ �ʿ�
			arr[from][to] = Math.min(arr[from][to], cost);

		}

		floyd();

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				// * ���� : ����, i���� j�� �� �� ���� ��쿡�� �� �ڸ��� 0 �Է�
				if (arr[i][j] == INF) {
					arr[i][j] = 0;
				}
				bw.write(arr[i][j] + " ");
			}
			bw.newLine();
		}

		br.close();
		bw.flush();
		bw.close();

	}
	
	// main �Լ� �� �������� ���� static �Լ��� ���� �ӵ��� ���� 
	private static void floyd() {
		// k �� a �� b ���� ���Ѿ���
		for (int k = 1; k <= N; k++) {
			for (int a = 1; a <= N; a++) {
				for (int b = 1; b <= N; b++) {
					// Overflow ����
					arr[a][b] = Math.min(arr[a][b], arr[a][k] + arr[k][b]);
				}
			}
		}

	}

}
