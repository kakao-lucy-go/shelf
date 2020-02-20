package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class P_14888 {

	static int[] array;
	static int[] cals;
	static long max = -999999999;
	static long min = 999999999;
	
	public static void main(String[] args) throws IOException {

		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is));

		int temp = Integer.parseInt(bf.readLine());
		array = new int[temp];
		String[] temp2 = bf.readLine().split(" ");
		for (int i = 0; i < temp; i++) {
			array[i] = Integer.parseInt(temp2[i]);
		}
		temp2 = bf.readLine().split(" ");
		cals = new int[4];
		for (int i = 0; i < cals.length; i++) {
			cals[i] = Integer.parseInt(temp2[i]);
		}
		bf.close();
		
		cal(array[0], 0);
		System.out.println(max);
		System.out.println(min);
	}
	
	//0 : +, 1 : -, 2 : *, 3 : /
	static void cal( long total, int index) {
		
		//끝까지 오면
		if(index == array.length-1) {
			if(total > max) {
				max = total;
				
			}
			if(total < min) {
				min = total;
			}
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if(cals[i]>0) {
				/*int[] newCalcul = new int[4];
				for (int j = 0; j < newCalcul.length; j++) {
					newCalcul[j] = calcul[j];
				}*/
				cals[i]-=1;
				long newTotal = total;
				newTotal = getTotal(newTotal, i, array[index+1]);
				cal(newTotal, index+1);
				cals[i]+=1;
			}
		}
	}

	static long getTotal(long to, int i, int next) {
		long newTotal = to;
		if(i==0) {
			newTotal += next;
		}else if(i==1) {
			newTotal -= next;
		}else if(i==2) {
			newTotal *= next;
		}else if(i==3) {
			if(newTotal<0) {
				//음수면 양수로 바꾸고 나눈다음 다시 음수로 변환
				newTotal *= -1;
				newTotal /= next;
				newTotal *= -1;
			}else {
				newTotal /= next;
			}
		}
		return newTotal;
	}

}
