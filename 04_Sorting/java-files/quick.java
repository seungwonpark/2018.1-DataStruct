private static int partition(int[] value, int lo, int hi){
	int pivot = value[hi];
	int i = lo-1;
	for(int j=lo; j<hi; j++){
		if(value[j] < pivot){
			i += 1;
			swap(value, i, j);
		}
	}
	swap(value, i+1, hi);
	return i+1;
}
public static int[] DoQuickSort(int[] value){
	RecursiveQuickSort(value, 0, value.length-1);
	return (value);
}
public static int[] RecursiveQuickSort(int[] value, int lo, int hi)
{
	if(lo < hi){
		int p = partition(value, lo, hi);
		RecursiveQuickSort(value, lo, p-1);
		RecursiveQuickSort(value, p+1, hi);
	}
	return (value);
}