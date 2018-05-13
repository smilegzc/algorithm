import java.util.Random;

/**
 * 该类为各种排序算法的Java代码实现
 * main方法中为测试代码，与算法无关
 */

public class Sort {
    public static void main(String[] args) {
        int[] arrTest = new int[30];
        for(int i = 0; i < arrTest.length; i++) {
            arrTest[i] = i + 1;
        }
        Sort s = new Sort();

        //在这里测试排序方法
        s.randomSort(arrTest);
        for(int i: arrTest) {
            System.out.print(i + ", ");
        }
        
        s.quickSort(arrTest, 0, arrTest.length-1);
        System.out.print("\n");
        for(int i: arrTest) {
            System.out.print(i + ", ");
        }
    }
    
    public void quickSort(int[] arr, int start, int end) {
        if(start < end) {
            int q = partition(arr, start, end);
            quickSort(arr, start, q-1);
            quickSort(arr, q+1, end);
        }
    }
    
    private int partition(int[] arr, int start, int end) {
        int element = arr[end];
        int index = start - 1;
        for(int i = start; i < end; i++) {
            if(arr[i] <= element) {
                index++;
                int temp = arr[index];
                arr[index] = arr[i];
                arr[i] = temp;
            }
        }
        index++;
        int temp = arr[index];
        arr[index] = arr[end];
        arr[end] = temp;
        
        return index;
    }
    
    //堆排序算法
    public void heapSort(int[] arr) {
        int heapSize = arr.length;
        buildMaxHeap(arr, heapSize);
        for(int i = heapSize; i > 1; i--) {
            int temp = arr[0];
            arr[0] = arr[i-1];
            arr[i-1] = temp;
            maxHeapify(arr, i-1, 1);
        }
    }
    //对无序数组建堆
    private void buildMaxHeap(int arr[], int heapSize) {
        for(int i = heapSize/2; i > 0; i--) {
            maxHeapify(arr, heapSize, i);
        }
    }
    //维护最大堆性质
    private void maxHeapify(int[] arr, int heapSize, int i) {
        int largest;
        int l = 2 * i;
        int r = 2 * i + 1;
        if(l <= heapSize && arr[l-1] > arr[i-1]) {
            largest = l;
        } else {
            largest = i;
        }
        
        if(r <= heapSize && arr[r-1] > arr[largest-1]) {
            largest = r;
        }
        
        if(largest != i) {
            int temp = arr[i-1];
            arr[i-1] = arr[largest-1];
            arr[largest-1] = temp;
            maxHeapify(arr, heapSize, largest);
        }
    }

    //对原数组进行重新随机化排序
    public void randomSort(int[] arr) {
        Random rand = new Random();
        int length = arr.length;
        for(int i = 0; i < length; i++) {
            int randIndex = rand.nextInt(length - i) + i;
            int temp = arr[i];
            arr[i] = arr[randIndex];
            arr[randIndex] = temp;
        }
    }

    /**
     * 按照数组key对数组arr进行重新排序
     * 以下为数组key的实现方法实例，key数组为完全随机序列
     * int n = arr.length;
     * int temp = n*n*n;
     * int[] key = new int[n];
     * Random rand = new Random();
     * for(int i = 0; i < n; i++) {
     *     key[i] = rand.nextInt(temp);
     * }
     * 
     */
    public void permuteRandomSort(int[] arr, int[] key) {
        //此处采用插入排序算法，可改用其他更加高效的算法
        for(int i = 1; i < arr.length; i++) {
            int keyTemp = key[i];
            int arrTemp = arr[i];
            int j = i-1;
            while(j >= 0 && keyTemp < key[j]) {
                key[j+1] = key[j];
                arr[j+1] = arr[j];
                j--;
            }
            key[j+1] = keyTemp;
            arr[j+1] = arrTemp;
        }
    }

    //冒泡排序
    public void bubbleSort(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = arr.length-1; j > i; j--) {
                if(arr[j] < arr[j-1]) {
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }
    }

    //归并排序
    public void mergeSort(int[] arr, int p, int r) {
        if(p < r) {
            int temp = (p + r)/2;
            mergeSort(arr, p, temp);
            mergeSort(arr, temp+1, r);
            merge(arr, p, temp, r);
        }
    }
    private void merge(int[] arr, int p, int q, int n) {
        int[] l = new int[q - p + 1];
        int[] r = new int[n - q];
        for(int i = 0; i < l.length; i ++) {
            l[i] = arr[p + i];
        }
        for(int i = 0; i < r.length; i ++) {
            r[i] = arr[q + i + 1];
        }

        int lIndex = 0;
        int rIndex = 0;
        int arrIndex = p;
        while(lIndex < l.length && rIndex < r.length) {
            if(l[lIndex] < r[rIndex]) {
                arr[arrIndex ++] = l[lIndex ++];
            } else {
                arr[arrIndex ++] = r[rIndex ++];
            }
        }

        while(lIndex < l.length) {
            arr[arrIndex ++] = l[lIndex ++];
        }

        while(rIndex < r.length) {
            arr[arrIndex ++] = r[rIndex ++];
        }
    }

    //插入排序
    public void insertSort(int[] arr) {
        int length = arr.length;
        for(int i = 1; i < length; i++) {
            int j = i-1;
            int temp = arr[i];
            while(j >= 0 && temp < arr[j]) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = temp;
        }
    }
}