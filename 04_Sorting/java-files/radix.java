public static int[] DoRadixSort(int[] value)
{
	final long INT_MAX = 2147483648L;
	final int RADIX = 4;
	int n = value.length;
	Long[] temp = new Long[n];
	long maxval = 0;
	for(int i=0; i<n; i++){
		temp[i] = (Long.valueOf(value[i])) + INT_MAX;
		if(maxval < temp[i]){
			maxval = temp[i];
		}
	}
	@SuppressWarnings("unchecked")
	List<Long>[] buckets = new ArrayList[RADIX];
	for(int i=0; i<4; i++){
		buckets[i] = new ArrayList<Long>();
	}
	long mod = 1;
	while(mod < maxval){
		for(int i=0; i<n; i++){
			buckets[(int)((temp[i] / mod) % RADIX)].add(temp[i]);
		}
		int cnt = 0;
		for(int i=0; i<RADIX; i++){
			for(Long x : buckets[i]){
				temp[cnt++] = x;
			}
			buckets[i].clear();
		}
		mod *= 4;
	}
	for(int i=0; i<n; i++){
		value[i] = Math.toIntExact(temp[i] - INT_MAX);
	}
	return (value);
}