package O04_�������Ϳͱ�����;

import java.io.*;
import java.util.*;

// Q) 2��°���� 4��°���� ������ ���Ͻÿ�.

public class hj_test_������ {
	static int arr[], sumArr[];

	public static void main(String[] args) {
		arr = new int[] { 10, 20, 30, 40, 50 };
		sumArr = new int[5];
		
		// ������ ���ϱ�
		sumArr[0] = arr[0];
		for (int i = 0; i <= 3; i++) {
			sumArr[i + 1] = sumArr[i] + arr[i + 1];
		}
		
		
		// �Ϲ� ����
		int end = 4;
		int start = 2;
		
		// �迭 ����
		int e = end - 1;
		int s = start - 1;
		
		// ans = e���� ������ - (s - 1)���� ������
		int ans = sumArr[e] - sumArr[s - 1];
		
		System.out.println(ans);
	}

}
