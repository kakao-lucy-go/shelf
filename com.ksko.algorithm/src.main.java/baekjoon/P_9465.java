package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class P_9465 {

	public static void main(String[] args)  throws IOException{
		
		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is));
		
		int testcase = Integer.parseInt(bf.readLine());
		
		for (int u = 0; u < testcase; u++) {
			
			int len = Integer.parseInt(bf.readLine());
			
			int[][] array = new int[2][100001];
			int[][] d = new int[2][100001];
			
			for (int i = 0; i < 2; i++) {
				String temp = bf.readLine();
				String[] temp2 = temp.split(" ");
				int count=0;
				for (int j = 1; j <= len; j++) {
					array[i][j] = Integer.parseInt(temp2[count]);
					count++;
				}
			} 
			
			d[0][0] = d[1][0] = 0;
			d[0][1] = array[0][1];
			d[1][1] = array[1][1];
			
			for (int i = 2; i < len+1; i++) {
				d[0][i] = max(d[1][i-1],d[1][i-2]) + array[0][i];
				d[1][i] = max(d[0][i-1],d[0][i-2]) + array[1][i];
			}
		
			System.out.println(max(d[0][len], d[1][len]));
			//System.out.println(d[0][len] + " , " + d[1][len]);
		}
		
	}
	
	public static int max(int a, int b) {return a>b?a:b;}
	
	

}