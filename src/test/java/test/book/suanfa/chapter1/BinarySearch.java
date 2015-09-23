package test.book.suanfa.chapter1;

import java.util.Arrays;

import com.algs4.stdlib.In;
import com.algs4.stdlib.StdIn;
import com.algs4.stdlib.StdOut;

public class BinarySearch {
	
	public static int rank(int key,int []a)
	{
		int lo=0;
		int hi=a.length-1;
		while(lo<=hi)
		{
			int mid=lo+(hi-lo)/2;
			if(key<a[mid])
			{
				hi=mid-1;
			}
			else if(key>a[mid])
			{
				lo=mid+1;
			}
			else return mid;
		}
		return -1;
	}
	
	
	public static void main(String[] args) {
		int []whitelist=In.readInts(args[0]);
		Arrays.sort(whitelist);
		while(!StdIn.isEmpty())
		{
			int key =StdIn.readInt();
			if(rank(key,whitelist)==-1)
			{
				StdOut.println(key);
			}
		}
	}

}
