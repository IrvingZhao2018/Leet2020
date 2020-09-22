package SweepLine.leet1589MaximumSumObtainedOfAnyPermutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 8, 5, 5, 2};
        int[][] requests = {{4, 4}, {3, 4}, {4, 4}, {2, 4}, {0, 0}};
        System.out.println(solution.maxSumRangeQuery(nums, requests));
    }

    public int maxSumRangeQueryLee215(int[] A, int[][] req) {
        int res = 0, mod = (int) 1e9 + 7, n = A.length;
        int[] count = new int[n];
        for (int[] r : req) {
            count[r[0]] += 1;
            if (r[1] + 1 < n)
                count[r[1] + 1] -= 1;
        }
        for (int i = 1; i < n; ++i)
            count[i] += count[i - 1];
        Arrays.sort(A);
        Arrays.sort(count);
        for (int i = 0; i < n; ++i)
            res = (res + A[i] * count[i]) % mod;
        return res;
    }

    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        Arrays.sort(nums);
        List<int[]> list = new ArrayList<>();
        for (int[] request : requests) {
            list.add(new int[]{request[0], 1});
            list.add(new int[]{request[1], -1});
        }
        Collections.sort(list, (r1, r2) -> {
            if (r1[0] == r2[0]) return r2[1] - r1[1];
            return r1[0] - r2[0];
        });
        int cur = 0;
        int pre = list.get(0)[0];
        long[] count = new long[nums.length];
        for (int[] r : list) {
            if (r[1] > 0) {
                for (int i = pre; i < r[0]; i++)
                    count[i] += cur;
                cur++;
                pre = r[0];
            } else {
                for (int i = pre; i <= r[0]; i++)
                    count[i] += cur;
                cur--;
                pre = r[0] + 1;
            }
        }
        Arrays.sort(count);
        long mod = (int) 1e9 + 7;
        long sum = 0;
        for (int i = count.length - 1; i >= 0 && count[i] > 0; i--) {
            sum += count[i] * nums[i];
            sum %= mod;
        }
        return (int) sum;
    }

}
