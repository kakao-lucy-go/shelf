import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;


public class P_2577 {

	public static void main(String[] args)  throws IOException{
		
		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is));
		
		int a = Integer.parseInt(bf.readLine());
		int b = Integer.parseInt(bf.readLine());
		int c = Integer.parseInt(bf.readLine());
		int[] count = new int[10];
		Arrays.fill(count, 0);
		long total = a*b*c;
		
		String str = total + "";
		String[] str2 = str.split("");
		for (int i = 0; i < str2.length; i++) {
			count[Integer.parseInt(str2[i])]+=1;
		}
		
		
		for (int i = 0; i < count.length; i++) {
			System.out.println(count[i]);
		}
		
	}
}
