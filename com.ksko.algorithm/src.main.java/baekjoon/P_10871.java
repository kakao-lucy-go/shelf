
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class P_10871 {

	public static void main(String[] args) throws IOException {
        InputStream is = System.in;
        BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String str = bf.readLine();
        String[] str2 = str.split(" ");
        int n = Integer.parseInt(str2[0]);
        int x = Integer.parseInt(str2[1]);
        str = bf.readLine();
        str2 = str.split(" ");
        
        for (int i = 0; i < n; i++) {
			
			if(Integer.parseInt(str2[i]) < x) {
				System.out.print(str2[i] + " ");
			}
		}
	}

}
