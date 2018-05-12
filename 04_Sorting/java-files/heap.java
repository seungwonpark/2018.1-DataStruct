private static void percolateDown(int[] arr, int i, int n){
	int child = 2*i+1;
	int rightChild = 2*i+2;
	if(child < n){
		if((rightChild < n) && (arr[child] < arr[rightChild])){
			// find max index of arr[child], arr[rightChild]
			child = rightChild;
		}
		if(arr[i] < arr[child]){
			swap(arr, i, child);
			percolateDown(arr, child, n);
		}
	}
}
public static int[] DoHeapSort(int[] value)
{
	int n = value.length;
	// Step 1. Build heap.
	for(int i=n/2-1; i>=0; i--){
		percolateDown(value, i, n);
	}
	// Step 2. Sorting.
	for(int i=n-1; i>=1; i--){
		swap(value, 0, i);
		percolateDown(value, 0, i);
	}
	return (value);
}