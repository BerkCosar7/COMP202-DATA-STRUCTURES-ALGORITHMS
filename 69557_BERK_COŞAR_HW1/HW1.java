import java.util.Random;
import java.util.Arrays;

public class HW1 {
	 public static int result=0;
	 public static int row=0;
	
	// This is the method that returns the count of 'a' chars in the matrix
	// Feel free to add a helper method for the recursive part of the algorithm
	public static int acount(char[][] mat) {
		
		
		// Your code goes here 
		if(mat.length==0) {
			return 0;
			
			
		}else {
			return firstBFounder(row,mat);}
		
		}
	
	public static int firstBFounder(int row, char[][] mat) { 
		int lo=0;
		int hi=mat.length-1;
		int mid=(lo+hi)/2;
		while(lo<=hi) {
			
			if(mat[row][mid]=='a') {
				lo=mid;
				mid=(hi+lo)/2;
				if(mid==lo) {
					mid++;
				}
			}//move if a
			
			else if(mat[row][mid]=='b' && mid!=0) {
				if(mat[row][mid-1]=='a') {
					row++;
					if(row!=mat.length) {
					return mid+firstBFounder(row,mat);} else {return mid;}
				}else {
					hi=mid;
					mid=(hi+lo)/2;
				}
			}// move b
			
			else { row++; if(row!=mat.length) {return 0+firstBFounder(row,mat);} else {return 0;}}
			
		}//binary search
		
		if(hi==0 && lo==0) {
			row++;
			if(row!=mat.length) {
				return 0+firstBFounder(row,mat);} else {return 0;}
			
		} else 
			row++;
			if(row!=mat.length) {
				return mat.length+firstBFounder(row,mat);} else {return mat.length;}
			
	}
	
		
		


	public static void main(String[] args) {
		// This method creates a test case for you
		
		int n = 5;
		Random rand = new Random();
		char[][] input = new char[n][n];
		for (int i = 0; i < n; i++) {
			int a_num = rand.nextInt(n);
			for (int j = 0; j < a_num; j++) {
				input[i][j] = 'a';
			}
			for (int j = a_num; j < n; j++) {
				input[i][j] = 'b';
			}
		}
		// Here you can see the matrix row by row 
		System.out.println(Arrays.deepToString(input));
		// Here you can see the result of your function
		
		System.out.println(acount(input));
		
	
	
	}
	
	

}
