
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args)  throws IOException{
		
		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is));
		
		String[] temp = bf.readLine().split(" ");
		int x = Integer.parseInt(temp[0]);
		int y = Integer.parseInt(temp[1]);
		int w = Integer.parseInt(temp[2]);
		int h = Integer.parseInt(temp[3]);
		
		int min = y;
		if((h-y)<min) {
			min = (h-y);
		}
		if((w-x) <min) {
			min = (w-x);
		}
		if(x<min) {
			min = x;
		}
		System.out.println(min);
		
	}


}
