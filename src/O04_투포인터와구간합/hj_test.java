package O04_�������Ϳͱ�����;
// 2021.05.20 Update

import java.util.*;
import java.io.*;

public class hj_test {
	static int N, M, arr[];
	static int intervalSum, end, cntM;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = 5; // �������� ����
		M = 5; // ã�����ϴ� �κ���
		
		// �迭 1 ~ N������ ����ȭ�� ��
		arr = new int[] { 0, 1, 2, 3, 2, 5 }; // ��ü ����		
				
		cntM = 0;
		intervalSum = 0;
		end = 1;		
		
		// start�� 1���� N����
		for(int start = 1; start <= N; start++) {
			// ���� 1 : end�� N ���� �̳������� ��
			// ���� 2 : intervalSum�� M���� �۾ƾ��� ��
			while(end <= N && intervalSum < M) {
				intervalSum += arr[end];
				end++;
			}
			
			if(intervalSum == M) {
				cntM++;
			}
			
			intervalSum -= arr[start];
		}
		
		System.out.println(cntM);
		
		

	}

}
