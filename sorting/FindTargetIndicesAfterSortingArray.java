// package sorting;

/*
    You are given a 0-indexed integer array nums and a target element target.

    A target index is an index i such that nums[i] == target.

    Return a list of the target indices of nums after sorting nums in non-decreasing order. If there are no target indices, return an empty list. The returned list must be sorted in increasing order.

    

    Example 1:

    Input: nums = [1,2,5,2,3], target = 2
    Output: [1,2]
    Explanation: After sorting, nums is [1,2,2,3,5].
    The indices where nums[i] == 2 are 1 and 2.
    Example 2:

    Input: nums = [1,2,5,2,3], target = 3
    Output: [3]
    Explanation: After sorting, nums is [1,2,2,3,5].
    The index where nums[i] == 3 is 3.
    Example 3:

    Input: nums = [1,2,5,2,3], target = 5
    Output: [4]
    Explanation: After sorting, nums is [1,2,2,3,5].
    The index where nums[i] == 5 is 4.
    

    Constraints:

    1 <= nums.length <= 100
    1 <= nums[i], target <= 100
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FindTargetIndicesAfterSortingArray {
    class Solution {
        public List<Integer> targetIndices(int[] nums, int target) {
            List<Integer> li = new ArrayList<>();

            // Arrays.sort(nums);
            int targetCount = 0;
            int lessThanTarget = 0;

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target)
                    targetCount++;
                else if (nums[i] < target)
                    lessThanTarget++;
            }
            for (int i = lessThanTarget; i < lessThanTarget + targetCount; i++)
                li.add(i);

            return li;

        }
    }

    // brut force
    public List<Integer> targetIndices(int[] nums, int target) {
        List<Integer> li = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target)
                li.add(i);
        }
        return li;

    }
}