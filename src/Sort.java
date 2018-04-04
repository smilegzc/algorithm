import java.util.Random;

/**
 * 该类为各种排序算法的Java代码实现
 * main方法中为测试代码，与算法无关
 */

public class Sort {
    public static void main(String[] args) {
        int[] arrTest = new int[15];
        for(int i = 0; i < arrTest.length; i++) {
            arrTest[i] = i + 1;
        }
        Sort s = new Sort();

        //在这里测试排序方法
        s.randomSort(arrTest);
        for(int i: arrTest) {
            System.out.print(i + ", ");
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