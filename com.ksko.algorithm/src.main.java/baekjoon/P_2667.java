import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class P_2667 {

	public static int area = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		String temp = sc.nextLine();
		int[][] apartments = new int[size][size];
		boolean[][] visited = new boolean[size][size];
		
		for (int i = 0; i < visited.length; i++) {
			Arrays.fill(visited[i], false);
		}

		for (int i = 0; i < size; i++) {
			String line = sc.nextLine();
			String[] lineArray = line.split("");
			for (int j = 0; j < lineArray.length; j++) {
				apartments[i][j] = Integer.parseInt(lineArray[j]);
			}
		}
		
		int area = 0;
		int count = 0;
		int[] x = {1, -1, 0, 0};
		int[] y = {0, 0, 1, -1};
		
		ArrayList<Integer> results = new ArrayList<Integer>();
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				
				if(visited[i][j] == false && apartments[i][j] == 1) {
					area ++;
					count = 0;
					count = bfs(visited, apartments, x, y, count, i, j);
					results.add(count);
					//System.out.println("============================================count : " + count);
					
				}
			}
			
		}

		Collections.sort(results);
		
		System.out.println(area);
		for (int i = 0; i < results.size(); i++) {
			System.out.println(results.get(i));
		}
	}
	
	public static int bfs(boolean[][] visited, int[][] aparts, int[] x, int[] y, int count, int nowX, int nowY) {
		//System.out.println("==========================count : " + count);

		visited[nowX][nowY] = true;
		count++;
		for (int i = 0; i < x.length; i++) {

				int newX = x[i] + nowX;
				int newY = y[i] + nowY;
				
				if(newX < 0 || newY < 0 || newX > aparts.length-1 || newY > aparts.length-1) continue;
				
				
				//System.out.println("newX : " + newX + ", newY : " + newY);
				if(visited[newX][newY] == false && aparts[newX][newY] == 1) {
					//System.out.println("this point : " + newX + ", " + newY + " : " + aparts[newX][newY]);

					visited[newX][newY] = true;
					count = bfs(visited, aparts, x, y, count, newX, newY);
				}
				
			
		}
		
		//System.out.println("break++++++++++++++++++++++++++++++++++++++ count : " + count);
		return count;
			
	}

}
