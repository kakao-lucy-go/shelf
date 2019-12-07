package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P_2231 {

	public static void main(String[] args) throws IOException{
		
		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		
		int n = Integer.parseInt(bf.readLine());
		bf.close();
		
		int[] array = new int[n+1];
		Arrays.fill(array, 987654321);
		for (int i = 1; i < array.length; i++) {
			String[] temp = (i+"").split("");
			int sum = i;
			for (int j = 0; j < temp.length; j++) {
				sum += Integer.parseInt(temp[j]);
			}
			if(sum < array.length && array[sum] > sum) {
				array[sum] = i;
			}
		}
		
		if(array[n] == 987654321) {
			System.out.println(0);
		}else {
			System.out.println(array[n]);
		}
	}	
}
