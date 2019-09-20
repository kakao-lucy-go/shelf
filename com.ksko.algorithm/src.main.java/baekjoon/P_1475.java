import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P_1475 {

	public static void main(String[] args)  throws IOException{
		
		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is));
		String n = bf.readLine();
		bf.close();
		int[] counts = new int[10];
		Arrays.fill(counts,0);

		char[] array = n.toCharArray();


		for (int i = 0; i < array.length; i++) {

			int index = array[i];
			counts[index-48] += 1;
		}

		int total = counts[9] + counts[6];
		counts[6] = total/2;
		counts[9] = total-counts[6];


		Arrays.sort(counts);
		System.out.println(counts[9]);


	}

}
