import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Fence {

	static int[] array;
	
	public static void main(String[] args) throws IOException {
		InputStream is = System.in;
		BufferedReader bf = new BufferedReader(new InputStreamReader(is));
		
		int testcase = Integer.parseInt(bf.readLine());
		
		for (int u = 0; u < testcase; u++) {
			String str = bf.readLine();
			int length = Integer.parseInt(str);
			array = new int[length];
			str = bf.readLine();
			String[] str2 = str.split(" ");
			for (int i = 0; i < str2.length; i++) {
				array[i] = Integer.parseInt(str2[i]);
			}
			System.out.println(solve(0,length-1));
		}
	}
	
	public static int solve(int left, int right) {
		
		//판자 하나이면 1*판자하나높이 반환
		if(left==right) return array[left];
		
		int mid = (left + right) / 2;
		int ret = Math.max(solve(left, mid), solve(mid+1, right)); 
		int mt = mid;
		int mtn = mid+1;
		int height = Math.min(array[mt], array[mtn]);
		
		//막대 두개만 남았을 떄
		ret = Math.max(ret, height*2);
		
		//두 인덱스는 어레이 범위 안에 있어야 한다.
		//또는 인 이유는, 한쪽으로만 확장이 될 수 있기 때문이다. 가능성을 열어두고 안에서 체크해주어야한다.
		//예를 들어서 1로만 이루어진 판자가 6개가 들어온다면, 앤드라면 한쪽으로만 확장하다가 끝날것이다. 왜냐면 한쪽이 범위를 넘어설것이기 때문에
		//다른 한쪽으로 갈 가능성이 없어지는 것이다.
		while(mtn < right || mt > left) {
			
			//큰 쪽으로 확장한다. 하지만 결과적으로 어레이 전체로 확장가능한지 테스트해야한다.
			if(mtn < right && (mt == left || array[mtn+1] > array[mt-1])) {
				mtn+=1;
				height = Math.min(height, array[mtn]);
			
			}else {
				mt-=1;
				height = Math.min(height, array[mt]);
			}
			
			ret = Math.max(height*(mtn-mt+1), ret);
		}
		
		return ret;
	}
}
