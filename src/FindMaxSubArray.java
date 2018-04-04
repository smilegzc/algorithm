/**
 * 该类为求数组中最大子数组
 * 返回值为int数组，其中int[0]为最大子数组的起始下标，int[1]为最大子数组的终止下标，int[2]为最大子数组的和
 * main方法中为测试代码，与算法无关
 */

public class FindMaxSubArray {

    public static void main(String[] args) {
        int[] temp = {-100,1, 2, -2, 4, 34, -8 ,23,-23,23,23,4,54,6,-2,-54,-12};
        FindMaxSubArray max = new FindMaxSubArray();

        int[] p = max.findMaximumSubArray(temp, 0, temp.length-1);
        for(int i: p) {
            System.out.println(i);
        }
    }

    public int[] findMaximumSubArray(int[] arr, int low, int high) {
        final int MAX_SUM = 2;
        if(low == high) {
            int[] temp = {low, high, arr[low]};
            return temp;
        } else {
            int mid = (low + high)/2;
            int[] leftInfo = findMaximumSubArray(arr, low, mid);
            int[] rightInfo = findMaximumSubArray(arr, mid+1, high);
            int[] crossInfo = findMaxCrossSubArray(arr, low, mid, high);

            if(leftInfo[MAX_SUM] >= rightInfo[MAX_SUM] && leftInfo[MAX_SUM] >= crossInfo[MAX_SUM]) {
                return leftInfo;
            } else if(rightInfo[MAX_SUM] >= leftInfo[MAX_SUM] && rightInfo[MAX_SUM] >= crossInfo[MAX_SUM]) {
                return rightInfo;
            } else {
                return crossInfo;
            }
        }
    }

    private int[] findMaxCrossSubArray(int[] arr, int low, int mid, int high) {
        final int LINDEX = 0, RINDEX = 1, MAX_SUM = 2;
        int[] temp = new int[3];

        int leftSum = arr[mid];
        for(int i = mid, sum = 0; i >= low; i--) {
            sum += arr[i];
            if(sum > leftSum) {
                leftSum = sum;
                temp[LINDEX] = i;
            }
        }

        int rightSum = arr[mid+1];
        for(int i = mid+1, sum  = 0; i <= high; i++) {
            sum += arr[i];
            if(sum > rightSum) {
                rightSum = sum;
                temp[RINDEX] = i;
            }
        }

        temp[MAX_SUM] = leftSum + rightSum;
        return temp;
    }
}