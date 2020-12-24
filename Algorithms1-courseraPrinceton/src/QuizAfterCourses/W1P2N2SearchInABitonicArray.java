/**
 * Coursera - Algorithms Part I
 * Week 1 - Interview Questions Part.2
 *
 * Question 2: Search in a bitonic array
 * An array is bitonic if it is comprised of an increasing sequence of integers
 * followed immediately by a decreasing sequence of integers.
 * Write a program that, given a bitonic array of n distinct integer values,
 * determines whether a given integer is in the array.
 *
 * Standard version: Use ∼3lgn compares in the worst case.
 * Signing bonus: Use ∼2lgn compares in the worst case
 * (and prove that no algorithm can guarantee to perform fewer than ∼2lgn compares in the worst case)
 * You may assume that you can sort the n integers in time proportional to n^2 or better.
 *
 * Solution :
 * 1. We can use binary search to find the max value in the array and then search in both sides. O(3lgn)
 *
 * 2. We can also find the target by binary search without knowing the max value in ßthe array.
 *    Suppose the following situations: (low,mid,high,the max value, the target value)
 *    For example:
 *    [-1, 0, 2, 3, 4, 6, mid:8, 10, 13, max:15, 11, 7, 5, 1] the max value is in the right.
 *    [-1, 4, 8, 10, max:14, 12, mid:11, 9, 7, 6, 5, 3, 2, 0] the max value is in the left.
 *
 *    1. nums[mid] > target: the target can be in the right or left, so we should search in both sides.
 *                           But the good news is that we can simply do ascending binary search in the left side
 *                           and descending binary search in the right to find the target.
 *                           If the max is in the right, than the left is ascending.
 *                           We can find that the elements from nums[mid] and nums[max] are all larger than the target
 *                           so we can do descending binary search.
 *                           If the max is in the left, the situation is similar.
 *    2. nums[mid] < target: If the max is in the right, than we can only do bitonic search in the right
 *                           because all elements in the left part is small than the target.
 *                           If the max is in the left, than we can only do bitonic search in the left with similar reason.
 *    3. nums[mid] == target: We find the target!!
 *
 * Another way of writing binary search: !!!
 * https://github.com/eschwabe/interview-practice/blob/master/coursera/algorithms-part1/analysis-of-algorithms/BitonicArray.java
 */
public class W1P2N2SearchInABitonicArray {
/* Standard version:
    public int BitonicArray(int[] nums, int target) {
        if (nums == null || nums.length < 1) return -1;
        int low = 0;
        int high = nums.length -1;
        int mid = 1;
        int maxIndex = -1;

        while (low <= high && mid < nums.length - 1 && mid > 0) {
            mid  = (low + high) / 2;
            //if (nums[mid] == target) return mid;
            if (nums[mid] < nums[mid + 1] && nums[mid] > nums[mid - 1]) low = mid + 1;
            if (nums[mid] > nums[mid + 1] && nums[mid] < nums[mid - 1]) high = mid -1;
            if (nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1]) {
                maxIndex = mid;
                break;
            }
        }

        if (maxIndex == -1) {
            System.out.println("The array is not bitonic.");
            return -1;
        }

        System.out.printf("The max value in the array is the %d-th element.", maxIndex);

        low = 0;
        high = maxIndex;
        while (low <= high) {
            mid = (low + high) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) low = mid + 1;
            if (nums[mid] > target) high = mid - 1;
        }

        low = maxIndex;
        high = nums.length;
        while (low <= high) {
            mid = (low + high) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) low = mid + 1;
            if (nums[mid] < target) high = mid - 1;
        }
        return -1;
    }

 */
   public int bitonicSearch(int low, int high, int target, int[] nums) {
       if (low > high) return -1;
       int mid = (low + high) / 2;

       if (nums[mid] == target) return mid;
       else if (nums[mid] > target) {
           int left = binarySearch(low, mid - 1, target, nums, true);
           int right = binarySearch(mid + 1, high,target, nums, false);
           if (left != -1) return left;
           else if (right != -1) return right;
       }else {
           if (nums[mid] < nums[mid + 1] && nums[mid] > nums[mid - 1]) return bitonicSearch(mid +1, high, target, nums);
           if (nums[mid] > nums[mid + 1] && nums[mid] < nums[mid - 1]) return bitonicSearch(low, mid - 1, target, nums);
           // if nums[mid] happens to be the max value in the array and target is bigger than it,
           // it means that there is no target in the array. It will return -1 anyway, so we don't list such condition here.
       }
       return -1;
   }

   public int binarySearch(int low, int high, int target, int[] nums, boolean ascending) {
       if (low > high) return -1;
       int mid = (low + high) / 2;

       if (nums[mid] == target) return mid;
       else if (nums[mid] > target) {
           if (ascending) return binarySearch(low, mid -1, target, nums, true);
           return binarySearch(mid + 1, high, target, nums, false);
       } else {
           if (ascending) return binarySearch(mid + 1, high, target, nums, true);
           return binarySearch(low, mid -1, target, nums, false);
       }
   }

    public static void main (String[]args){
        int[] nums = {-1};
        int target = -1;
        W1P2N2SearchInABitonicArray s = new W1P2N2SearchInABitonicArray();
        int result = s.bitonicSearch(0, nums.length, target, nums);
        if (result == -1) System.out.println("There is no such element");
        else System.out.printf(" The target element is in the array. The position is %d.", result);
    }
}
