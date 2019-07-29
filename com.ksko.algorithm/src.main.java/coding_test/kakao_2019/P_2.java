package coding_test.kakao_2019;

public class P_2 {

	public static void main(String[] args) {
		//스테이지 
		int n = 5;
		int[] array = {2, 1, 2, 6, 2, 4, 3, 3};
		int members = array.length;
		int[] level = new int[n+2];
		double[] value = new double[n+2];
		double[][] result = new double[2][n+2];
		
		for (int i = 0; i < array.length; i++) {
			level[array[i]]+=1;
		}
		
		for (int i = 1; i < level.length-1; i++) {
			//System.out.println("인원 : " + members + " , 도전중인 인원 : " + level[i]);
			if(level[i] == 0) {
				value[i] = 0;
			}else {
				value[i] = (double)level[i] / (double)members;
				members -= level[i];
			}
			//System.out.println(value[i]);
			result[0][i] = i;
			result[1][i] = value[i];
		}
		
		for (int i = n; i > 0; --i) {
			for (int j = n; j > 0; --j) {
				
				if(result[1][i] <= result[1][j]) {
					
					if(result[1][i] == result[1][j] && result[0][i] < result[0][j]) {
						continue;
					}
					
					double index = result[0][j];
					double tempValue = result[1][j];
					
					result[1][j] = result[1][i];
					result[0][j] = result[0][i];
					
					result[0][i] = index;
					result[1][i] = tempValue;
		
				}
			}
		}
		
		//System.out.println("-----------------------------------");
		int[] results = new int[n];
		for (int i = 1; i < result[0].length-1; i++) {
			//System.out.println((int)result[0][i]);
			results[i-1] = (int)result[0][i];
		}
		
		for (int i = 0; i < results.length; i++) {
			System.out.println(results[i]);
		}
		
	}

}
