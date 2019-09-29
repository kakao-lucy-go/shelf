
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class P_9498 {

	public static void main(String[] args) throws IOException {
        InputStream is = System.in;
        BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));

        int score = Integer.parseInt(bf.readLine());
        
        if(score >= 90 && score <= 100) {
        	System.out.print("A");
        }else if(score >= 80 && score < 90) {
        	System.out.println("B");
        }else if(score >= 70 && score < 80) {
        	System.out.println("C");
        }else if(score >= 60 && score < 70) {
        	System.out.println("D");
        }else {
        	System.out.println("F");
        }
	}

}
