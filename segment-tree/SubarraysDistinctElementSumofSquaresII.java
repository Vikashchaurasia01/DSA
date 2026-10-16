// package segment-tree;

/*
    You are given a 0-indexed integer array nums.

    The distinct count of a subarray of nums is defined as:

    Let nums[i..j] be a subarray of nums consisting of all the indices from i to j such that 0 <= i <= j < nums.length. Then the number of distinct values in nums[i..j] is called the distinct count of nums[i..j].
    Return the sum of the squares of distinct counts of all subarrays of nums.

    Since the answer may be very large, return it modulo 109 + 7.

    A subarray is a contiguous non-empty sequence of elements within an array.

    

    Example 1:

    Input: nums = [1,2,1]
    Output: 15
    Explanation: Six possible subarrays are:
    [1]: 1 distinct value
    [2]: 1 distinct value
    [1]: 1 distinct value
    [1,2]: 2 distinct values
    [2,1]: 2 distinct values
    [1,2,1]: 2 distinct values
    The sum of the squares of the distinct counts in all subarrays is equal to 12 + 12 + 12 + 22 + 22 + 22 = 15.
    Example 2:

    Input: nums = [2,2]
    Output: 3
    Explanation: Three possible subarrays are:
    [2]: 1 distinct value
    [2]: 1 distinct value
    [2,2]: 1 distinct value
    The sum of the squares of the distinct counts in all subarrays is equal to 12 + 12 + 12 = 3.
    

    Constraints:

    1 <= nums.length <= 10^5
    1 <= nums[i] <= 10^5
*/

import java.util.HashSet;
import java.util.Set;

class SubarraysDistinctElementSumofSquaresII {

    
    int M = 1_000_000_007;
    // brute force, gives TLE
    class Solution {
        public int sumCounts(int[] nums) {
            int sum = 0;
            
            for(int i=0; i<nums.length; i++) {
                Set<Integer> set = new HashSet<>();
                for(int j=i; j<nums.length; j++) {
                    set.add(nums[j]);
                    
                    sum = (sum + (set.size() * set.size()) % M) % M;
                }
            }
            
            return sum;
        }
    }


    // optimal solution using segment-tree

    int n;

    long[] sqSumSegTree;
    long[] sumSegTree;
    long[] lazyTree;

    void lazyAddition(int i, int l, int r, long val) {
        long len = r - l + 1;

        sqSumSegTree[i] += 2 * val * sumSegTree[i] + len * val * val;
        sumSegTree[i] += len * val;
    }

    void updateRange(int start, int end, int i, int l, int r, long val) {
        if (lazyTree[i] != 0) {
            lazyAddition(i, l, r, lazyTree[i]);

            if (l != r) {
                lazyTree[2 * i + 1] += lazyTree[i];
                lazyTree[2 * i + 2] += lazyTree[i];
            }
            lazyTree[i] = 0;
        }

        // out of range
        if (l > end || r < start || l > r) {
            return;
        }

        // completely within range
        if (l >= start && r <= end) {
            lazyAddition(i, l, r, val);

            if (l != r) {
                lazyTree[2 * i + 1] += val;
                lazyTree[2 * i + 2] += val;
            }
            return;
        }

        int mid = l + (r - l) / 2;
        updateRange(start, end, 2 * i + 1, l, mid, val);
        updateRange(start, end, 2 * i + 2, mid + 1, r, val);

        sumSegTree[i] = sumSegTree[2 * i + 1] + sumSegTree[2 * i + 2];
        sqSumSegTree[i] = sqSumSegTree[2 * i + 1] + sqSumSegTree[2 * i + 2];
    }

    public int sumCounts(int[] nums) {
        n = nums.length;

        sqSumSegTree = new long[4 * n];
        sumSegTree = new long[4 * n];
        lazyTree = new long[4 * n];

        int[] lastSeenIdx = new int[100001];
        java.util.Arrays.fill(lastSeenIdx, -1);
        long result = 0;

        for (int i = 0; i < n; i++) {
            int start = lastSeenIdx[nums[i]] + 1;
            int end = i;

            updateRange(start, end, 0, 0, n - 1, 1);

            result = (result + sqSumSegTree[0]) % M;

            lastSeenIdx[nums[i]] = i;
        }

        return (int) result;
    }
}