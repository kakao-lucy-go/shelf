import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) throws IOException{
		
		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		int answers = 0;
		int computers = Integer.parseInt(bf.readLine());
		int[][] array = new int[computers+1][computers+1];
		for (int i = 0; i < array.length; i++) {
			Arrays.fill(array[i], 987654321);
		}
		
		int networks = Integer.parseInt(bf.readLine());
		for (int i = 0; i < networks; i++) {
			String[] temp = bf.readLine().split(" ");
			array[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])] = 1;
			array[Integer.parseInt(temp[1])][Integer.parseInt(temp[0])] = 1;
		}
		bf.close();
		
		//경유
		for (int i = 1; i < array.length; i++) {
			//x
			for (int j = 1; j < array.length; j++) {
				if(array[i][j] == 987654321) {
					continue;
				}
				
				
				//y
				for (int j2 = 1; j2 < array.length; j2++) {
					if(array[j2][i] == 987654321) {
						continue;
					}
					
					if(j!=j2) {
						//System.out.println(j + " -> " + i + " -> " + j2);
						
						//직행이 없다.
						if(array[j][i] + array[i][j2] < array[j][j2]) {
							array[j][j2] = array[j][i] + array[i][j2];
						}
						
					}
				}
			}
		}
		
		for (int i = 2; i < array.length; i++) {
			if(array[1][i] < 987654321) {
				answers+=1;
			}
		}
		
		System.out.println(answers);
		
		
	}
	
	
}
