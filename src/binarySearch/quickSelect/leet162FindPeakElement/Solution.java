package binarySearch.quickSelect.leet162FindPeakElement;


class Solution {
    // nums[i] != nums[j]
    public int findPeakElement(int[] nums) {
        int left = 0;
        int n = nums.length;
        int right = n - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
