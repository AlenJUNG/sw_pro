package G05_Kruskal;

import java.util.*;
import java.io.*;

public class G05_ex09_CT실전01_여행계획 {
	static int N, M, ans;
	static int parent[];
	
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_ex09_CT실전01_여행계획.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N + 1];
		
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		int res;
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				res = Integer.parseInt(st.nextToken());
				if(res == 0 || i == j) {
					continue;
				}else {
					union(i, j);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int t = st.countTokens();
		boolean check = false;
		for(int i = 1; i <= t; i++) {
			if(i == 1) {
				res = Integer.parseInt(st.nextToken());
				continue;
			}
			if(find(i-1) != find(i)) {
				check = true;
				break;
			}
		}
		
		if(check) {
			bw.write("NO"+"\n");
		}else {
			bw.write("YES"+"\n");
		}
		
		
		br.close();
		bw.flush();
		bw.close();
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a > b) {
			parent[a] = b;
		}else {
			parent[b] = a;
		}
		
	}

	private static int find(int x) {
		if( x == parent[x]) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

}
