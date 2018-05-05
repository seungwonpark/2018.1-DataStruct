import java.io.*;
import java.util.*;

public class SortingTest
{
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try
		{
			boolean isRandom = false;	// 입력받은 배열이 난수인가 아닌가?
			int[] value;	// 입력 받을 숫자들의 배열
			String nums = br.readLine();	// 첫 줄을 입력 받음
			if (nums.charAt(0) == 'r')
			{
				// 난수일 경우
				isRandom = true;	// 난수임을 표시

				String[] nums_arg = nums.split(" ");

				int numsize = Integer.parseInt(nums_arg[1]);	// 총 갯수
				int rminimum = Integer.parseInt(nums_arg[2]);	// 최소값
				int rmaximum = Integer.parseInt(nums_arg[3]);	// 최대값

				Random rand = new Random();	// 난수 인스턴스를 생성한다.

				value = new int[numsize];	// 배열을 생성한다.
				for (int i = 0; i < value.length; i++)	// 각각의 배열에 난수를 생성하여 대입
					value[i] = rand.nextInt(rmaximum - rminimum + 1) + rminimum;
			}
			else
			{
				// 난수가 아닐 경우
				int numsize = Integer.parseInt(nums);

				value = new int[numsize];	// 배열을 생성한다.
				for (int i = 0; i < value.length; i++)	// 한줄씩 입력받아 배열원소로 대입
					value[i] = Integer.parseInt(br.readLine());
			}

			// 숫자 입력을 다 받았으므로 정렬 방법을 받아 그에 맞는 정렬을 수행한다.
			while (true)
			{
				int[] newvalue = (int[])value.clone();	// 원래 값의 보호를 위해 복사본을 생성한다.

				String command = br.readLine();

				long t = System.currentTimeMillis();
				switch (command.charAt(0))
				{
					case 'B':	// Bubble Sort
						newvalue = DoBubbleSort(newvalue);
						break;
					case 'I':	// Insertion Sort
						newvalue = DoInsertionSort(newvalue);
						break;
					case 'H':	// Heap Sort
						newvalue = DoHeapSort(newvalue);
						break;
					case 'M':	// Merge Sort
						newvalue = DoMergeSort(newvalue);
						break;
					case 'Q':	// Quick Sort
						newvalue = DoQuickSort(newvalue, 0, newvalue.length-1);
						break;
					case 'R':	// Radix Sort
						newvalue = DoRadixSort(newvalue);
						break;
					case 'X':
						return;	// 프로그램을 종료한다.
					default:
						throw new IOException("잘못된 정렬 방법을 입력했습니다.");
				}
				if (isRandom)
				{
					// 난수일 경우 수행시간을 출력한다.
					System.out.println((System.currentTimeMillis() - t) + " ms");
				}
				else
				{
					// 난수가 아닐 경우 정렬된 결과값을 출력한다.
					for (int i = 0; i < newvalue.length; i++)
					{
						System.out.println(newvalue[i]);
					}
				}

			}
		}
		catch (IOException e)
		{
			System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
		}
	}

	private static void swap(int[] arr, int x, int y){
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoBubbleSort(int[] value)
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

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoInsertionSort(int[] value)
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

	////////////////////////////////////////////////////////////////////////////////////////////////////
	// heap sort: 0 is the root index.
	// 0
	// 1 2
	// 3 4 5 6
	// 7 8 9 10 11 12 13 14
	// left/right child of x is (2x+1), (2x+2), respectively.
	private static void percolateDown(int[] arr, int i, int n){
		int child = 2*i+1;
		int rightChild = 2*i+2;
		if(child < n){
			if((rightChild < n) && (arr[child] < arr[rightChild])){
				child = rightChild;
			}
			if(arr[i] < arr[child]){
				swap(arr, i, child);
				percolateDown(arr, child, n);
			}
		}
	}
	private static int[] DoHeapSort(int[] value)
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
		return value;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoMergeSort(int[] value)
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

	////////////////////////////////////////////////////////////////////////////////////////////////////
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
	private static int[] DoQuickSort(int[] value, int lo, int hi)
	{
		if(lo < hi){
			int p = partition(value, lo, hi);
			DoQuickSort(value, lo, p-1);
			DoQuickSort(value, p+1, hi);
		}
		return (value);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoRadixSort(int[] value)
	{
		// TODO : Radix Sort 를 구현하라.
		return (value);
	}
}
