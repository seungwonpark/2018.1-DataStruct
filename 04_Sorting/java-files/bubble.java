// Referred to https://en.wikipedia.org/wiki/Bubble_sort#Implementation
public static int[] DoBubbleSort(int[] value)
{
	int n = value.length;
	boolean swapped = true;
	while(swapped){
		swapped = false;
		for(int i=0; i<n-1; i++){
			if(value[i] > value[i+1]){
				swap(value, i, i+1);
				swapped = true;
			}
		}
	}
	return (value);
}