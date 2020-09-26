const canPartition = function (nums) {
    const sum = nums.reduce((acc, val) => acc + val, 0);
    if (sum % 2) // odd
        return -1;
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
    const res2 = [];
    //console.log(dp);
    //console.log(counter);
    //console.log(res);
    // update counter to generate result
    for(let num of res){
        counter.set(num, counter.get(num) - 1);
    }
    for(let pair of counter){
        while(pair[1] > 0){
            res2.push(pair[0]);
            pair[1]--;
        }
        // console.log(res);
    }
    if(res[0] < res2[0]) return [...res, ...res2].toString();
    return [...res2, ...res].toString();
};

const test = [1, 5, 11, 5];
console.log(canPartition(test));