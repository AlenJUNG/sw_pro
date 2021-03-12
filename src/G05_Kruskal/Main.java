package G05_Kruskal;

import java.util.*;
import java.io.*;

public class Main {
	
	
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_ex02_100_1647_도시분할계획.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		
		
		br.close();
		bw.flush();
		bw.close();
	}
}

















