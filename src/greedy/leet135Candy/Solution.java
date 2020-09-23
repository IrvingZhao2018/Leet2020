package greedy.leet135Candy;

class Solution {
    public int candyOneArray(int[] ratings) {
        int n = ratings.length;
        if (n < 2) return n;
        int[] candies = new int[n];
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1])
                candies[i] = candies[i - 1] + 1;
        }
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                candies[i] = candies[i + 1] + 1;
            }
        }
        int sum = n;
        for (int candy : candies) sum += candy;
        return sum;
    }

    private int count(int n) {
        return (n * (n + 1)) / 2;
    }

    public int candyOnePass(int[] ratings) {
        int n = ratings.length;
        if (n < 2) return n;
        int up = 0;
        int down = 0;
        int candies = 1;
        int oldSlope = 0;
        for (int i = 1; i < n; i++) {
            int newSlope = ratings[i] - ratings[i - 1];
            if (newSlope == 0 || (oldSlope < 0 && newSlope > 0)) {
                candies += count(up) + count(down) + Math.max(up, down);
                up = down = 0;
            }
            if (newSlope > 0) up++;
            else if (newSlope < 0) down++;
            else candies++;
            oldSlope = newSlope;
        }
        candies += count(up) + count(down) + Math.max(up, down);
        return candies;
    }

    public int candyOnePass2(int[] ratings) {
        int n = ratings.length;
        if (n < 2) return n;
        int candies = n;
        int up = 0, down = 0, peak = 0;
        for (int i = 1; i < n; i++) {
            if (ratings[i - 1] < ratings[i]) {
                peak = ++up;
                down = 0;
                candies += up;
            } else if (ratings[i - 1] == ratings[i]) {
                peak = up = down = 0;
            } else {
                up = 0;
                down++;
                candies += down;
                if (peak >= down) candies--;
            }
        }
        return candies;
    }

}
