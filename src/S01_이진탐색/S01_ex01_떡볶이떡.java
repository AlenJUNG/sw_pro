package S01_����Ž��;

// ������ 201 page �������� 3
import java.io.*;
import java.util.*;

public class S01_ex01_�����̶� {
	static final int INF = (int) 1e9;
	static int N, M, ans;
	static int arr[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/S01_����Ž��/S01_ex01_�����̶�.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// ���� ����(N)�� ��û�� ���� ����(M)
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr); // �������� ������ �Ǿ� ����

		// ���� Ž���� ���� �������� ���� ����
		System.out.println(binarySerach(arr, 0, INF));

		br.close();
	}

	// ����Ž�� ����
	private static int binarySerach(int[] map, int start, int end) {

		while (start <= end) {
			int mid = start + (end - start) / 2;
			long temp = 0;

			// �߶��� �� ���� �� ���
			for (int i = 0; i < N; i++) {
				if (map[i] > mid) {
					temp += map[i] - mid;
				}
			}
			// ���� ���� ������ ��� �� ���� �ڸ���(���� �κ� Ž��)
			if (temp < M) {
				end = mid - 1;
			// ���� ���� ����� ��� �� �ڸ���(������ �κ� Ž��)
			} else {
				ans = mid; // �ִ��� �� �߶��� ���� �����̹Ƿ�, ���⿡�� ans�� ���
				start = mid + 1;
			}

		}
		return ans;
	}
}
