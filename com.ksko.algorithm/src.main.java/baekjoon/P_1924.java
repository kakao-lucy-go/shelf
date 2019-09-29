
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//참고로 2007년에는 1, 3, 5, 7, 8, 10, 12월은 31일까지, 4, 6, 9, 11월은 30일까지, 2월은 28일까지 있다.
public class P_1924 {

	public static void main(String[] args) throws IOException {
        InputStream is = System.in;
        BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        
        int[] days = {0,31,28,31,30,31,30,31,31,30,31,30,31};
        String[] dayOfWeek = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        String str = bf.readLine();
        bf.close();
        String[] str2 = str.split(" ");
        int m = Integer.parseInt(str2[0]);
        int d = Integer.parseInt(str2[1]);
        int total = 0;
        for (int i = 0; i < m; i++) {
			total += days[i];
			
		}
        total += d;
        System.out.println(dayOfWeek[total % 7]); 
        
        
	}

}
