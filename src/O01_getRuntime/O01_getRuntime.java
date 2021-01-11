package O01_getRuntime;

public class O01_getRuntime {

	public static void main(String[] args) {
		long beforeTime = System.currentTimeMillis();
        
		int sum = 0;
		for (int i = 0; i < 10000000; i++) {
		    for (int j = 0; j < 500; j++) {
		        sum += i*j;
		    }
		}
		System.out.println(sum);
		    
		long afterTime = System.currentTimeMillis();
		System.out.println("시간차이(ms) : "+ (afterTime - beforeTime));
		
		long secDiffTime = (afterTime - beforeTime)/1000;
		System.out.println("시간차이(s) : "+secDiffTime);
		
		long minDiffTime = secDiffTime/60;
		System.out.println("시간차이(m) : "+minDiffTime);

	}

}
