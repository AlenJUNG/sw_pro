package B00_Math;

// https://coding-factory.tistory.com/250
// [Java] 자바 소수점 n번째 자리까지 반올림하기

public class B00_Math_math {

	public static void main(String[] args) {
		double pie = 3.14159265358979;
		double money = 4424.243423;
		
		/* Math.round() 예제
		 * Math함수의 round()함수는 실수의 소수점 첫번째 자리를 반올림하여 정수로 리턴시켜줍니다. 
		 * 하지만 이 메서드를 잘 활용하면 소수점 몇번째 자리까지 나타내는것도 가능합니다. 
		 * 예를들어 33.777*100을 하면 3377.7가 되겠죠. 
		 * 여기서 round를 적용시키면 3378이라는 정수가 리턴될것입니다. 
		 * 여기서 다시 100.0을 나눠주면 실수로 적용되어 나옵니다. (33.78이 나오게 됩니다.) 
		 * 소수점 둘째 자리까지 나타내고싶으시면 100.0을 곱하였다가 나눠주시면 되고 
		 * 소수점 셋째 자리까지 나타내고싶으시면 1000.0을 곱하였다가 나눠주면 됩니다. 
		 * (참고로 반올림이 아닌 올림을 하고싶다면 Math.ceil(); 버림은 Math.floor();입니다.) 
		 */
		
		System.out.println(Math.round(pie));
		System.out.println(Math.round(pie * 100) / 100.0);
		System.out.println(Math.round(pie * 1000) / 1000.0);
		
		/* String.format() 예제
		 * String.format()방식을 활용한 방법도 있습니다. 
		 * String 클래스의 format 메소드는 리턴되는 문자열 형태를 지정하는 함수인데 
		 * 이 함수를 활용하면 Math.round() 함수와 같이 소수점 n번째 자리까지 반올림하여 나타낼 수 있습니다.
		 */
		
		System.out.println(String.format("%.2f", pie));
		System.out.println(String.format("%.3f", pie));
		System.out.println(String.format("%,.3f", money));
	}

}
