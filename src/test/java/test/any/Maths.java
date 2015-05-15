package test.any;

public class Maths {
	
	/**
	 * 扑克牌排序原理
	 * 
	 * 插入排序
	 * @param a
	 */
	public static void insertionSort(int a[]){
		int temp=-1;
		for(int i=1;i<a.length;i++){
			temp=a[i];
			int j=i-1;
			while(j>=0&&a[j]>temp){
				a[j+1]=a[j];
				j=j-1;
			}
			a[j+1]=temp;
		}
		for(int m=0;m<a.length;m++){
			System.out.print(a[m]+",");
		}
		System.out.println();
		
	}
	
	
	/**
	 * 选中第i个元素，然后一次跟后面的元素比较，如果a[j]<a[i] 就交换位置
	 * 选择排序
	 * @param a
	 */
	public static void selectSort(int a[]){
		int temp=-1;
		for(int i=0;i<a.length-1;i++){
			for(int j=i+1;j<a.length;j++){
				if(a[j]<a[i]){
					temp=a[i];
					a[i]=a[j];
					a[j]=temp;
				}
			}
		}
		
		for(int t=0;t<a.length;t++){
			System.out.print(a[t]+",");
		}
		System.out.println();
	}
	
	/**
	 * 冒泡排序
	 * @param a
	 */
	public static void maopaoSort(int a[]){
		int temp=-1;
		for(int i=0;i<a.length-1;i++){
			for(int j=i;j<a.length;j++){
				if(a[i]>a[j]){
					temp=a[i];
					a[i]=a[j];
					a[j]=temp;
				}
			}
		}
		for(int m=0;m<a.length;m++){
			System.out.print(a[m]+",");
		}
		System.out.println();
	}
	
	/**
	 * 
	 * 两个牌面向上的扑克牌总是取最小的直至一个为空
	 * 
	 * @param a 带排序数组
	 * @param p 起始索引（包含）
	 * @param q 分割索引（属于p）
	 * @param r 结束索引（包含）
	 */
	public static void mergeSort(int a[],int p,int q,int r){
		
		//创建两个新数组 L(p->q) R(q+1->r)
		int n1=q-p+1;
		int n2=r-q;
		int L[]=new int[n1];
		int R[]=new int[n2];
		for(int i=p;i<=r;i++){
			if(i>=p&&i<=q){
				L[i-p]=a[i];
			}else{
				R[i-q-1]=a[i];
			}
		}
		
		int m=0,n=0;
		for(int j=p;j<=r;j++){
			if(m<L.length &&(n>=R.length||L[m]<=R[n])){
				a[j]=L[m];
				m=m+1;
			}else if(n<R.length&&(m>=L.length||L[m]>R[n])){
				a[j]=R[n];
				n=n+1;
			}
		}
		
		
		for(int t=0;t<a.length;t++){
			System.out.print(a[t]+",");
		}
		System.out.println();
	}
	
	/**
	 * 调用上边的方法
	 * @param $arr
	 * @param $start
	 * @param $end
	 */
	public static void merge_sort(int $arr[],int $start,int $end){
		if($start<$end){
	        int $mid=($start+$end)/2;
	        merge_sort($arr,$start,$mid);
	        merge_sort($arr,$mid+1,$end);
	        mergeSort($arr,$start,$mid,$end);
	    }
		
		for(int t=0;t<$arr.length;t++){
			System.out.print($arr[t]+",");
		}
		System.out.println();
	}
	
	public static void quickSort(int t){
		t=1000;
		int i=10;
		int j=i;
		i=30;
	}
	

	public static void main(String[] args) {
		int a[]={5,2,3,6,8,4,1,9};
		int b[]={4,6,92,1,2,3,100,101,44,23};
//		maopaoSort(a);
//		insertionSort(a);
//		selectSort(a);
//		merge_sort(b,0,b.length-1);
		int i=100;
		quickSort(i);
	}

}
