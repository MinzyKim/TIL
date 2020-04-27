package CodeTest;
import java.util.Scanner;
import java.io.FileInputStream;

class Solution1
{
public static void main(String args[]) throws Exception
{

Scanner sc = new Scanner(System.in);
int T=sc.nextInt();

int[] num = new int[10];

for(int test_case = 1; test_case <= T; test_case++)
{
	for(int i=0; i < num.length; i++) {
		num[i] = sc.nextInt();
	}
	int sum = 0;
	for(int i=0; i < num.length; i++) {
		if(num[i]%2 == 1) {
			sum += num[i];
		}
	}
	System.out.println("#" + test_case+" "+ sum);
}
}
}