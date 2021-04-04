package O02_����_Comparable;
/* ����. https://dublin-java.tistory.com/12
 * 1. Comparator vs Comparable 
 * 2. ����ǥ����
 * 3. �迭�� ����
 */

import java.util.Arrays;
import java.util.Comparator;


public class O02_����_������_���ٽ�ȭ {
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
		
		// point �迭 �� Point Class x ���� �������� ���� ��,
		
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
