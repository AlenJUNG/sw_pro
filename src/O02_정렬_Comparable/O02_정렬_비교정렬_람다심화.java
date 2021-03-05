package O02_정렬_Comparable;
/* 참고. https://dublin-java.tistory.com/12
 * 1. Comparator vs Comparable 
 * 2. 람다표현식
 * 3. 배열의 정렬
 */

import java.util.Arrays;
import java.util.Comparator;


public class O02_정렬_비교정렬_람다심화 {
	static class Point{
		int v, x, y, z;
		public Point(int v, int x, int y, int z) {
			this.v = v;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	static Point point[];
	public static void main(String[] args) {
		point = new Point[10];
		
		// point 배열 내 Point Class x 기준 오름차순 정렬 시,
		
		Arrays.sort(point, 0, 10, new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				if (o1.x < o2.x) {
					return -1;
				}
				return 1;
			}
		});

	}

}
