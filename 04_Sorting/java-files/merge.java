public static int[] DoMergeSort(int[] value)
{
	int n = value.length;
	if(n > 1){
		int[] arr1 = DoMergeSort(Arrays.copyOfRange(value, 0, n/2));
		int[] arr2 = DoMergeSort(Arrays.copyOfRange(value, n/2, n));
		int idx1 = 0, idx2 = 0, cnt = 0;
		while(true){
			if(idx1 == arr1.length){
				for(int i=idx2; i<arr2.length; i++){
					value[cnt++] = arr2[i];
				}
				break;
			}
			if(idx2 == arr2.length){
				for(int i=idx1; i<arr1.length; i++){
					value[cnt++] = arr1[i];
				}
				break;
			}
			if(arr1[idx1] > arr2[idx2]){
				value[cnt++] = arr2[idx2];
				idx2 += 1;
			}
			else{
				value[cnt++] = arr1[idx1];
				idx1 += 1;
			}
		}
	}
	return (value);
}