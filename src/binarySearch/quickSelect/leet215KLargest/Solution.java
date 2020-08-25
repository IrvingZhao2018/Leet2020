package binarySearch.quickSelect.leet215KLargest;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        k = n - k; // 0-indexed

        //Quick Select Kth Smallest
        int left = 0;
        int right = n - 1;
        while(left < right){
            int mid = (left + right) >>> 1;
            int pivot = nums[mid];
            swap(nums, right, mid);
            int index = left;
            for(int j = left; j < right; j++){
                if(nums[j] < pivot)
                    swap(nums, index++, j);
            }
            swap(nums, right, index);

            // nums[left] .. nums[index - 1] < nums[index] = pivot
            if(index == k) return pivot;
            else if(index < k) left = index + 1;
            else right = index - 1;

        }
        return nums[left];
    }

    private void swap(int[] nums, int a, int b){
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public static void main(String[] args) {
        /*
            Example 1:
            Input: [3,2,1,5,6,4] and k = 2
            Output: 5

            Example 2:
            Input: [3,2,3,1,2,4,5,5,6] and k = 4
            Output: 4
        */
        Solution solution = new Solution();
        int[] test1 = new int[] {3,2,1,5,6,4};
        System.out.println(solution.findKthLargest(test1, 2));
        int[] test2 = new int[] {3,2,3,1,2,4,5,5,6};
        System.out.println(solution.findKthLargest(test2, 4));

    }
}
