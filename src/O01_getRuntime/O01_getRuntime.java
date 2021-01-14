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
		System.out.println("�ð�����(ms) : "+ (afterTime - beforeTime));
		
		long secDiffTime = (afterTime - beforeTime)/1000;
		System.out.println("�ð�����(s) : "+secDiffTime);
		
		long minDiffTime = secDiffTime/60;
		System.out.println("�ð�����(m) : "+minDiffTime);
		
		// ���迡�� ������ �Ʒ��� ���� �Ѵ� ex) 15 > 1.5��
		long sw_pro_time = (afterTime - beforeTime) / 100;
		System.out.println("�ð�����(pro_time) : " + sw_pro_time);

	}

}
