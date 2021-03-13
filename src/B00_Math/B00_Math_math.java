package B00_Math;

// https://coding-factory.tistory.com/250
// [Java] �ڹ� �Ҽ��� n��° �ڸ����� �ݿø��ϱ�

public class B00_Math_math {

	public static void main(String[] args) {
		double pie = 3.14159265358979;
		double money = 4424.243423;
		
		/* Math.round() ����
		 * Math�Լ��� round()�Լ��� �Ǽ��� �Ҽ��� ù��° �ڸ��� �ݿø��Ͽ� ������ ���Ͻ����ݴϴ�. 
		 * ������ �� �޼��带 �� Ȱ���ϸ� �Ҽ��� ���° �ڸ����� ��Ÿ���°͵� �����մϴ�. 
		 * ������� 33.777*100�� �ϸ� 3377.7�� �ǰ���. 
		 * ���⼭ round�� �����Ű�� 3378�̶�� ������ ���ϵɰ��Դϴ�. 
		 * ���⼭ �ٽ� 100.0�� �����ָ� �Ǽ��� ����Ǿ� ���ɴϴ�. (33.78�� ������ �˴ϴ�.) 
		 * �Ҽ��� ��° �ڸ����� ��Ÿ��������ø� 100.0�� ���Ͽ��ٰ� �����ֽø� �ǰ� 
		 * �Ҽ��� ��° �ڸ����� ��Ÿ��������ø� 1000.0�� ���Ͽ��ٰ� �����ָ� �˴ϴ�. 
		 * (����� �ݿø��� �ƴ� �ø��� �ϰ�ʹٸ� Math.ceil(); ������ Math.floor();�Դϴ�.) 
		 */
		
		System.out.println(Math.round(pie));
		System.out.println(Math.round(pie * 100) / 100.0);
		System.out.println(Math.round(pie * 1000) / 1000.0);
		
		/* String.format() ����
		 * String.format()����� Ȱ���� ����� �ֽ��ϴ�. 
		 * String Ŭ������ format �޼ҵ�� ���ϵǴ� ���ڿ� ���¸� �����ϴ� �Լ��ε� 
		 * �� �Լ��� Ȱ���ϸ� Math.round() �Լ��� ���� �Ҽ��� n��° �ڸ����� �ݿø��Ͽ� ��Ÿ�� �� �ֽ��ϴ�.
		 */
		
		System.out.println(String.format("%.2f", pie));
		System.out.println(String.format("%.3f", pie));
		System.out.println(String.format("%,.3f", money));
	}

}
