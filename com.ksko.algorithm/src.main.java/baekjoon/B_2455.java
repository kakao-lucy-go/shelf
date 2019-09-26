import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class B_2455 {

    public static void main(String[] args) throws IOException {
        InputStream is = System.in;
        BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));

        int[][] array = new int[4][2];
        int[] result = new int[4];
        int sum = 0;
        //setting
        //[i][0] = 내린사람
        //[i][1] = 탄사람
        for (int i = 0; i < 4; i++) {
            String str = bf.readLine();
            String[] strs = str.split(" ");
            array[i][0] = Integer.parseInt(strs[0]);
            array[i][1] = Integer.parseInt(strs[1]);

            if(i==0) {
                result[0] = array[0][1];
                sum = result[0];
            }else {
                result[i] = result[i-1] - array[i][0] + array[i][1];
                if(result[i] > sum) {
                    sum = result[i];
                }
            }
        }
        System.out.println(sum);


    }
}
