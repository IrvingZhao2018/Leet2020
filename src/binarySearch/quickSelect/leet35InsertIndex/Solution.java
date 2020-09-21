package binarySearch.quickSelect.leet35InsertIndex;

import java.util.Arrays;

public class Solution {
    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return 0;
        int left = 0;
        int right = n; // not reached
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    public static void main(String[] args) {
        int[] test = {1,3,5};
        System.out.println(Arrays.binarySearch(test, 4));
    }
}
