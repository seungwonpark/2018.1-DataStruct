public static int[] DoInsertionSort(int[] value)
{
	int n = value.length;
	for(int i=1; i<n; i++){
		int temp = value[i];
		int j = i-1;
		for(; j>=0 && value[j] > temp; j--){
			value[j+1] = value[j];
		}
		value[j+1] = temp;
	}
	return (value);
}