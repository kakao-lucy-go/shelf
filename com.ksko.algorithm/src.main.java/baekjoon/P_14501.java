package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class P_14501 {

	public static void main(String[] args) throws IOException{
		
		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		int days = Integer.parseInt(bf.readLine());
		int[] result = new int[days];
		//[소요 기간][돈]
		//[0][n]=소요기간
		//[1][n]=돈
		int[][] array = new int[2][days];
		
		for (int i = 0; i < days; i++) {
			String temp = bf.readLine();
			String[] temp2 = temp.split(" ");
			array[0][i] = Integer.parseInt(temp2[0]);
			array[1][i] = Integer.parseInt(temp2[1]);
			
			if(array[0][i] + i > days) {
				array[1][i]=-987654321;
			}
			result[i] = array[1][i];
		}
		
		for (int i = 0; i < days; i++) {
			for (int j = array[0][i] + i; j < days; j++) {
				
				if(result[j] < result[i] + array[1][j]) {
					result[j] = result[i] + array[1][j];
				}
			}
		}
		
		int answer = 0;
		for (int i = 0; i < days; i++) {
			if(answer < result[i]) {
				answer = result[i];
			}
			
		}
		
		System.out.println(answer);
		bf.close();
		
	}
	
	
}
