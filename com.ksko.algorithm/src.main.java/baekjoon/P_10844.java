package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P_10844 {

	public static void main(String[] args) throws IOException {
		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		
		int n = Integer.parseInt(bf.readLine());
		
		
		long[][] array = new long[101][10];
		Arrays.fill(array[1],1);
		array[1][0]=0;
		
		for (int i = 2; i < n+1; i++) {
			array[i][0] = array[i-1][1]% 1000000000; 
			
			for (int j = 1; j < 9; j++) {
				array[i][j] = (array[i-1][j-1] + array[i-1][j+1])% 1000000000;
			}
			
			array[i][9] = array[i-1][8]% 1000000000;
		}
		
		
		long answer = 0;
		for (int i = 0; i < 10; i++) {
			answer += array[n][i]% 1000000000;
		}
		
		System.out.println(answer% 1000000000);
		bf.close();
		
	}

}
