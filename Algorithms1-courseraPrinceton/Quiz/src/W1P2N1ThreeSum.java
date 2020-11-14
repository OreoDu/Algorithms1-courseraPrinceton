import java.util.*;

/**
 * Coursera - Algorithms Part I
 * Week 1 - Interview Questions Part.2
 *
 * Question 1: 3-SUM in quadratic time.
 * Design an algorithm for the 3-SUM problem that takes time proportional to n^2 in the worst case.
 * You may assume that you can sort the n integers in time proportional to n^2 or better.
 *
 * Solution :
 *  We don't need set to deduplicate. We can do pruning based the sort.
 *  It doesn't matter if the nums[j](exist) that is saved in the map but not put into the result list is duplicated.
 *  because we only get the value from it. But We have to deduplicate the last elements.
 *
 */

public class W1P2N1ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new LinkedList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            // If the first element is bigger than 0, it means all the elements will bigger than 0.
            // Then there is no elements meet the condition (a+b+c = 0)
            if (nums[i] > 0) return result;
            // We have to jump over the same nums[i], because they will get the same result.
            if (i > 0 && nums[i] == nums[i-1]) continue;
            Map<Integer, Integer> hashMap = new HashMap<>(nums.length - i);
            for (int j = i + 1; j < nums.length; j++) {
                int v = -nums[i] - nums[j];
                Integer exist = hashMap.get(v);
                if (exist != null) {
                    result.add(Arrays.asList(nums[i], exist, nums[j]));
                    // We have to finish the matching first, then we can jump over those are the same with the nums[j].
                    // Because this kind of situation has already been put into the result.
                    while (j + 1 < nums.length && nums[j] == nums[j+1]) j++;
                } else {
                    // When there is no element matching with it, it has to wait in the map for other elements to match it;
                    hashMap.put(nums[j], nums[j]);
                }
            }
        }
        return result;
    }

    public static void main (String[]args){
        int[] nums = {-3, -1, -1, 0, 1, 3, 4};
        W1P2N1ThreeSum s = new W1P2N1ThreeSum();
        List<List<Integer>> result = s.threeSum(nums);
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(result.get(i).get(j) + " ");
            }
            System.out.println(" ");
        }
    }
}
