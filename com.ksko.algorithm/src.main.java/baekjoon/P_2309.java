package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P_2309 {
	static int[] heights = new int[9];

	public static void main(String[] args) throws IOException{
		
		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		
		for (int i = 0; i < 9; i++) {
			heights[i] = Integer.parseInt(bf.readLine());
			//System.out.println(heights[i]);
		}
		bf.close();
		
		
		f1:for (int i = 0; i < 9; i++) {
			for (int j = i+1; j < 9; j++) {
				if(getSum(i,j) == 100) {
					heights[i] = heights[j] = 987654321;
					break f1;
				}
			}
		}
		
		Arrays.sort(heights);
		for (int i = 0; i < 7; i++) {
			System.out.println(heights[i]);
		}
	}
	
	public static int getSum(int i, int j) {
		int sum = 0;
		for (int k = 0; k < heights.length; k++) {
			if(k != i && k!=j) sum+=heights[k];
		}
		return sum;
	}
	
}
