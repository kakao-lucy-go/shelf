package baekjoon;

import java.util.Scanner;

public class P_1992 {

	public static int[][] array;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		array = new int[n][n];
		sc.nextLine();
		for (int i = 0; i < n; i++) {
			
			String temp = sc.nextLine();
			String[] temp2 = temp.split("");
			for (int j = 0; j < temp2.length; j++) {
				array[i][j] = Integer.parseInt(temp2[j]);
			}
		}
		quad(0,0,n);
		
	}
	
	public static void quad(int x, int y, int size) {

		//System.out.println("size : " + size);
		
		int sum = 0;
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				sum += array[i][j];
			}
		}
		if(sum == 0 ) {
							
			System.out.print(0);
			
		}else if(sum == (size*size)) {
			System.out.print(1);
		}else {
			if(size == 2) {
				System.out.print("(" + array[x][y] + array[x][y+1] + array[x+1][y] + array[x+1][y+1] + ")");
			}else {
				System.out.print("(");
				quad(x, y, size/2);
				quad(x, y+(size/2), size/2);
				quad(x + (size/2), y, size/2);
				quad(x + (size/2), y+(size/2), size/2);
				System.out.print(")");

			}
		}
		
	}

}
