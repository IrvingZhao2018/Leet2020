const canPartition = function (nums) {
    const sum = nums.reduce((acc, val) => acc + val, 0);
    if (sum % 2) // odd
        return false;
    const target = sum / 2;
    const dp = new Array(target + 1);
    dp[0] = [0]; // base case
    const counter = new Map();
    for (let num of nums) {
        if (!counter.has(num)) {
            counter.set(num, 0);
        }
        counter.set(num, counter.get(num) + 1);
        for (let i = target; i >= num; i--) {
            if (!dp[i] && dp[i - num]) {
                dp[i] = [...dp[i - num], num];
            }
        }
    }
    if (!dp[target]) return -1; // no solution
    const res = dp[target].splice(1, dp[target].length); // remove 0
    //console.log(dp);
    //console.log(counter);
    //console.log(res);
    // update counter to generate result
    for(let num of res){
        counter.set(num, counter.get(num) - 1);
    }
    for(let pair of counter){
        while(pair[1] > 0){
            res.push(pair[0]);
            pair[1]--;
        }
        // console.log(res);
    }
    return res;
};

const test = [1, 5, 11, 5];
console.log(canPartition(test));