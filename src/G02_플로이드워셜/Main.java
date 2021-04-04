package G02_플로이드워셜;

import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 10001;
	static int d[];
	static int graph[][];
	static int N, M, X, K;
	
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/G02_플로이드워셜/G02_ex01_미래도시.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		d = new int[N + 1];
		graph = new int[N + 1][N + 1];
		
		for(int i = 1 ; i <= N; i ++) {
			for(int j = 1; j <= N; j++) {
				if(i == j) {
					graph[i][j] = 0;
				}else {
					graph[i][j] = INF;
				}
			}
		}
		
		int f, t;
		for(int i = 1 ; i <= M; i ++) {
			st = new StringTokenizer(br.readLine());
			f = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
				
			graph[f][t] = 1;
			graph[t][f] = 1;
		
		}
		
		for(int mid = 1; mid <= N; mid++) {
			for(int from = 1; from <= N; from++) {
				for(int to = 1; to <= N; to++) {
					graph[from][to] = Math.min(graph[from][to], graph[from][mid] + graph[mid][to]);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		long ans = graph[1][K] + graph[K][X];
		
		bw.write(ans + "\n");
		
		br.close();
		bw.flush();
		bw.close();
	}

}
