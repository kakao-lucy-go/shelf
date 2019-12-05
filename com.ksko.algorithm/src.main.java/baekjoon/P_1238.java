import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) throws IOException{
		
		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		String[] temp = bf.readLine().split(" ");
		int n = Integer.parseInt(temp[0]);//마을 수
		int m = Integer.parseInt(temp[1]);//도로 수 (단방향)
		int x = Integer.parseInt(temp[2]);//목적지 
		int[][] array = new int[n+1][n+1];
		int[] result = new int[n+1];
		
		for (int i = 0; i < array.length; i++) {
			Arrays.fill(array[i], 0);
		}
		for (int i = 0; i < m; i++) {
			temp = bf.readLine().split(" ");
			array[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])]=Integer.parseInt(temp[2]);
		}
		bf.close();
		
		//경유
		for (int i = 1; i < array.length; i++) {
			
			//출발점
			for (int j = 1; j < array.length; j++) {
				if(array[j][i] == 0) {
					continue;
				}
				
				//도착점
				for (int j2 = 1; j2 < array.length; j2++) {
					if(array[i][j2] == 0) {
						continue;
					}
					
					if(j != j2) {
						if(array[j][i] + array[i][j2] < array[j][j2] || array[j][j2] == 0) {
							array[j][j2] = array[j][i] + array[i][j2];
						}
					}
				}
			}
		}
		
		int answers = 0;
		for (int i = 1; i < result.length; i++) {
			result[i] = array[i][x] + array[x][i];
			if(answers < result[i]) {
				answers = result[i];
			}
		}
		
		System.out.println(answers);
		
	}
	
	
}
