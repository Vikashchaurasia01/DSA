import java.util.TreeMap;

/*
    The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle values.

    For examples, if arr = [2,3,4], the median is 3.
    For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
    You are given an integer array nums and an integer k. There is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

    Return the median array for each window in the original array. Answers within 10^-5 of the actual value will be accepted.
    Example 1:

    Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
    Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
    Explanation: 
    Window position                Median
    ---------------                -----
    [1  3  -1] -3  5  3  6  7        1
    1 [3  -1  -3] 5  3  6  7       -1
    1  3 [-1  -3  5] 3  6  7       -1
    1  3  -1 [-3  5  3] 6  7        3
    1  3  -1  -3 [5  3  6] 7        5
    1  3  -1  -3  5 [3  6  7]       6

    Example 2:
    Input: nums = [1,2,3,4,2,3,1,4,2], k = 3
    Output: [2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]
    

    Constraints:
    1 <= k <= nums.length <= 10^5
    -2^31 <= nums[i] <= 2^31 - 1
*/

public class SlidingWindowMedian {
    class Solution {
    class Operations {
        private void removeOne(TreeMap<Integer, Integer> map, int val) {
            int freq = map.get(val);

            if (freq == 1) {
                map.remove(val);
            } else {
                map.put(val, freq - 1);
            }
        }

        private void addOne(TreeMap<Integer, Integer> map, int val) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }

        private void rightToleftSwap(TreeMap<Integer, Integer> rightTreeMap, TreeMap<Integer, Integer> leftTreeMap,
                int val) {
            removeOne(rightTreeMap, val);
            addOne(leftTreeMap, val);
        }

        private void leftToRightSwap(TreeMap<Integer, Integer> rightTreeMap, TreeMap<Integer, Integer> leftTreeMap,
                int val) {
            removeOne(leftTreeMap, val);
            addOne(rightTreeMap, val);
        }
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;

        Operations opr = new Operations();

        double[] res = new double[n - k + 1];

        int i = 0;
        int j = 0;
        int count = 0;

        TreeMap<Integer, Integer> leftTreeMap = new TreeMap<>();
        TreeMap<Integer, Integer> rightTreeMap = new TreeMap<>();

        int leftSize = 0;
        int rightSize = 0;

        while (j < n) {

            int num = nums[j];

            if (leftTreeMap.isEmpty() || num <= leftTreeMap.lastKey()) {
                opr.addOne(leftTreeMap, num);
                leftSize++;
            } else {
                opr.addOne(rightTreeMap, num);
                rightSize++;
            }

            // Rebalance after insertion
            if (leftSize > rightSize + 1) {
                int leftVal = leftTreeMap.lastKey();
                opr.leftToRightSwap(rightTreeMap, leftTreeMap, leftVal);
                leftSize--;
                rightSize++;

            } else if (rightSize > leftSize) {
                int rightVal = rightTreeMap.firstKey();
                opr.rightToleftSwap(rightTreeMap, leftTreeMap, rightVal);
                rightSize--;
                leftSize++;
            }

            if (j - i + 1 == k) {
                long leftMax = leftTreeMap.lastKey();

                if (k % 2 == 1) {
                    res[count++] = leftMax;
                } else {
                    long rightMin = rightTreeMap.firstKey();
                    double median = (leftMax + rightMin) / 2.0;
                    res[count++] = median;
                }

                // Remove outgoing element
                int val = nums[i];

                if (leftTreeMap.containsKey(val)) {
                    opr.removeOne(leftTreeMap, val);
                    leftSize--;
                } else {
                    opr.removeOne(rightTreeMap, val);
                    rightSize--;
                }

                i++;

                // Rebalance again after removal
                if (leftSize > rightSize + 1) {
                    int leftVal = leftTreeMap.lastKey();
                    opr.leftToRightSwap(rightTreeMap, leftTreeMap, leftVal);
                    leftSize--;
                    rightSize++;

                } else if (rightSize > leftSize) {
                    int rightVal = rightTreeMap.firstKey();

                    opr.rightToleftSwap(rightTreeMap, leftTreeMap, rightVal);
                    rightSize--;
                    leftSize++;
                }
            }

            j++;
        }

        return res;
    }
}
}