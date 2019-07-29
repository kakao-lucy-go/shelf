package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class P_2579 {

	public static int answer = 0;
	public static int[] array;
	public static int stairs ;
	public static void main(String[] args) throws IOException {
		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		
		stairs = Integer.parseInt(bf.readLine());
		int[] weight = new int[stairs+1]; 
		array = new int[stairs+1];
		//계단 점수 세팅
		for (int i = 1; i < weight.length; i++) {
			weight[i] = Integer.parseInt(bf.readLine());
		}
		weight[0] = 0;
	
		bf.close();

		//시작점
		array[0] = weight[0];
		
		array[1] = weight[1];
		
		array[2] = getMax(weight[2] , weight[1] + weight[2]);
		array[3] = getMax(weight[2] + weight[3] , weight[3] + weight[1]);
		
		for (int i = 4; i < weight.length; i++) {
			array[i] = getMax(array[i-3] + weight[i-1] +weight[i], array[i-2] + weight[i]);
		}
		
		System.out.println(array[stairs]);
		
	}
	
	public static int getMax(int a, int b) {return a>b?a:b;}
}
