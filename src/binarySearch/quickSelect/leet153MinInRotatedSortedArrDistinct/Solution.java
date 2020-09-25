package binarySearch.quickSelect.leet153MinInRotatedSortedArrDistinct;

class Solution {
    public int findMin(int[] nums) {
        int n = nums.length;
        // if(n == 0) return 0; // invalid input
        if(nums[0] < nums[n - 1]) return nums[0];
        int left = 0;
        int right = n - 1;
        while(left < right){
            int mid = (left + right) >>> 1;
            if(nums[mid] > nums[right]){
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[right];
    }
}