package bucket.leet220ContainsDuplicateIII;

import java.util.HashMap;
import java.util.Map;

class Solution {
    private long getKey(long num, long size){
        return num < 0 ? (num + 1) / size - 1 : num / size;
    }
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(t < 0 || k <= 0 || nums.length < 2) return false;
        Map<Long, Long> buckets = new HashMap<>();
        long size = (long)t + 1l;
        for(int i = 0; i < nums.length; i++){
            long num = nums[i];
            long key = getKey(num, size);
            if(buckets.containsKey(key)) return true;
            if(buckets.containsKey(key - 1) && num - buckets.get(key - 1) < size) return true;
            if(buckets.containsKey(key + 1) && buckets.get(key + 1) - num < size) return true;
            buckets.put(key, num);
            if(i >= k) buckets.remove(getKey(nums[i - k], size));
        }
        return false;
    }
}
