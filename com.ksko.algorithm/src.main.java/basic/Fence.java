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
		
		//���� �ϳ��̸� 1*�����ϳ����� ��ȯ
		if(left==right) return array[left];
		
		int mid = (left + right) / 2;
		int ret = Math.max(solve(left, mid), solve(mid+1, right)); 
		int mt = mid;
		int mtn = mid+1;
		int height = Math.min(array[mt], array[mtn]);
		
		//���� �ΰ��� ������ ��
		ret = Math.max(ret, height*2);
		
		//�� �ε����� ��� ���� �ȿ� �־�� �Ѵ�.
		//�Ǵ� �� ������, �������θ� Ȯ���� �� �� �ֱ� �����̴�. ���ɼ��� ����ΰ� �ȿ��� üũ���־���Ѵ�.
		//���� �� 1�θ� �̷���� ���ڰ� 6���� ���´ٸ�, �ص��� �������θ� Ȯ���ϴٰ� �������̴�. �ֳĸ� ������ ������ �Ѿ���̱� ������
		//�ٸ� �������� �� ���ɼ��� �������� ���̴�.
		while(mtn < right || mt > left) {
			
			//ū ������ Ȯ���Ѵ�. ������ ��������� ��� ��ü�� Ȯ�尡������ �׽�Ʈ�ؾ��Ѵ�.
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
