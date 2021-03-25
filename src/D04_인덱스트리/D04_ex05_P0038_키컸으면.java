package D04_인덱스트리;

import java.io.*;
import java.util.*;

public class D04_ex05_P0038_키컸으면 {
	static class Node{
		int s, e, i, h, t;
		public Node(int s, int e, int i, int h, int t) {
			this.s = s;
			this.e = e;
			this.i = i;
			this.h = h;
			this.t = t;
		}
	}
	
	static int T, N, Q, size;
	static Node arr[];
	static int idx_Tree[], ans[];
	
	
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/D04_인덱스트리/D04_ex05_P0038_키컸으면.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for(int tc = 1 ; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			
			arr = new Node[N + Q];
			ans = new int[N + Q];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i <= N - 1; i++) {
				int height = Integer.parseInt(st.nextToken());
				arr[i] = new Node(0, 0, i, height, 0);
			}
			
			int S, E, value;
			for(int i = N; i <= N + Q - 1; i++) {
				st = new StringTokenizer(br.readLine());
				S = Integer.parseInt(st.nextToken());
				E = Integer.parseInt(st.nextToken());
				value = Integer.parseInt(st.nextToken());
				arr[i] = new Node(S, E, i, value, 1);
			}
			
//			System.out.println("O");
			
			Arrays.sort(arr, 0, N + Q, new Comparator<Node>() {

				@Override
				public int compare(Node o1, Node o2) {
					if(o1.h > o2.h) {
						return -1;
					}else if(o1.h == o2.h) {
						if(o1.t > o2.t) {
							return -1;
						}
					}
					return 1;
				}
			});
			
//			System.out.println("O");
			// 인덱스 트리의 생성/입력/업데이트
			size = 1;
			while(size < N) {
				size *= 2;
			}
			
			idx_Tree = new int[2*size];
			
			int type, X;
			for(int i = 0; i <= N + Q - 1; i++) {
				type = arr[i].t;
				X = arr[i].h;
				
				// 질의면
				if(type == 1) {
					ans[arr[i].i] = getSum(arr[i].s, arr[i].e);
					continue;
				}else {
					idx_Tree[size + arr[i].i] = 1;
					update();
				}				
				
			}
			
			bw.write("#"+tc+" ");
			for(int i = N; i <= N + Q - 1; i++) {
				bw.write(ans[i]+" ");
			}
			bw.newLine();
			
		}
		
		
		
		br.close();
		bw.flush();
		bw.close();
	}


	private static int getSum(int a, int b) {
		int res = 0;
		int left = size + a;
		int right = size + b;
		
		while(left <= right) {
			if(left % 2 == 1) {
				res += idx_Tree[left];
				left++;
			}
			if(right % 2 == 0) {
				res += idx_Tree[right];
				right--;
			}
			left /= 2;
			right /= 2;
		}
		
		return res;
	}


	private static void update() {
		for(int i = size - 1; i >= 1; i--) {
			idx_Tree[i] = idx_Tree[2*i] + idx_Tree[2*i + 1];
		}
		
	}

}
