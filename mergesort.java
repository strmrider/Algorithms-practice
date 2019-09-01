public class MergeSort {

	public void merge(int[] arr, int begin, int middle, int end)
	{
		int subArrSize1 =  (middle-begin)+1;
		int subArrSize2 = end-middle;
		
		int subArr1[] = new int[subArrSize1];
		int subArr2[] = new int[subArrSize2];
		
		 for (int i=0; i<subArrSize1; ++i)
			 subArr1[i] = arr[begin + i];
	     for (int j=0; j<subArrSize2; ++j)
	    	 subArr2[j] = arr[middle +1+ j];
	     
	     int i=0, j=0, k=begin;
	     while(i<subArrSize1 && j<subArrSize2)
	     {
	    	 if(subArr1[i] <= subArr2[j])
	    	 {
	    		 arr[k] = subArr1[i];
	    		 i++;
	    	 }
	    	 else
	    	 {
	    		 arr[k] = subArr2[j];
	    		 j++;
	    	 }
	    	 k++;
	     }
	     
	     while(i < subArrSize1 )
	     {
	    	 arr[k] = subArr1[i];
	    	 i++;
	    	 k++;
	     }
	     
	     while(j < subArrSize2 )
	     {
	    	 arr[k] = subArr2[j];
	    	 j++;
	    	 k++;
	     }
	}
	
	public void sort(int[] arr, int begin, int end)
	{
		if(begin < end)
		{
			int middle = (end+begin)/2;
			sort(arr, begin, middle);
			sort(arr, middle+1, end);
			merge(arr, begin, middle, end);
		}
	}
	
	public void mergeSort(int[] arr)
	{
		sort(arr, 0, arr.length-1);
	}
}
