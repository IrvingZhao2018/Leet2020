package Lark;

import java.util.ArrayList;
import java.util.List;

public class ZeroPlusOne {
    public List<Integer> partition(int[] nums){
        int count = 0;
        int[] dp = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 1)
                count++;
            dp[i] = count;
        }
        int max = 0;
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            int cur = i + 1 - dp[i] + count - dp[i];
            if(cur > max){
                res = new ArrayList<>();
                res.add(i);
                max = cur;
            } else if(cur == max){
                res.add(i);
            }
        }
        res.add(0);
        res.add(max);
        return res;
    }

    public static void main(String[] args) {
        ZeroPlusOne test = new ZeroPlusOne();
        int[] num = {0,0,0,1,1,1};
                //{0,0,1,0,0,1,0,1};
        System.out.println(test.partition(num));

    }
}

