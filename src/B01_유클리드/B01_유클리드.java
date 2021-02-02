package B01_��Ŭ����;

import java.io.*;
import java.util.*;

// https://coding-factory.tistory.com/599
// ��Ŭ���� ȣ����
public class B01_��Ŭ���� {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/B01_��Ŭ����/B01_��Ŭ����.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 2�� ������ �ִ����� ���ϱ�
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		System.out.println(GCD(a, b));

		// 3�� ������ �ִ����� ���ϱ�
		st = new StringTokenizer(br.readLine());

		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());

		int res = GCD(c, d);
		System.out.println(GCD(res, e));

		// 3�� ������ �ִ����� ���ϱ� > �ڷᱸ���� �迭�� ����, 3�� �̻� ���ڵ� ����
		int group[] = new int[3];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < group.length; i++) {
			group[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < group.length - 1; i++) {
			int x = GCD(group[i], group[i + 1]);
			group[i + 1] = x;
		}
		System.out.println(group[group.length - 1]);

		// �ּҰ���� ���ϱ�
		st = new StringTokenizer(br.readLine());
		int f = Integer.parseInt(st.nextToken());
		int g = Integer.parseInt(st.nextToken());
		System.out.println(LCM(f, g));

	}

	// "r�� 0�̸� �׶� b�� �ִ�����" ����
	private static int GCD(int a, int b) {
		// �׻� a�� �� ũ�� �¿�����
		if (a < b) {
			int temp;
			temp = a;
			a = b;
			b = temp;
		}
		// ���� �������� 0�� �ɶ����� ���� �ݺ�
		while (true) {
			int r = a % b;

			if (r == 0) {
				return b;
			}

			a = b;
			b = r;
		}
	}
	
	// �ּҰ���� = a * b / GCD(a, b) > GCD�� ���� ������ ��
	private static int LCM(int f, int g) {
		return f * g / GCD(f, g);
	}

}
