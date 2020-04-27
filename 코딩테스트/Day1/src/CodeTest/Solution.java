package CodeTest;

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
public static void main(String args[]) throws Exception
{

Scanner sc = new Scanner(System.in);
int T=sc.nextInt();

int[] num = new int[2];
String token="";

for(int test_case = 1; test_case <= T; test_case++){
	for(int i = 0; i<num.length;i++) {
		num[i] = sc.nextInt();
		
		if(num[i] > num[i+1]) {
			token=">";
		}else if(num[i]==num[i+1]) {
			token="=";
		}else if(num[i] < num[i+1]) {
			token="<";
		}
	}
	
//	
//	for(int i = 0; i<num.length;i++) {
//		if(num[i] > num[i+1]) {
//			number=num[i];
//		}else if(num[i]==num[i+1]) {
//			number=num[i];
//		}else if(num[i] < num[i+1]) {
//			number=num[i+1];
//		}
//	
//	}
	
	System.out.println("#"+test_case+" "+token);
	
}
}
}