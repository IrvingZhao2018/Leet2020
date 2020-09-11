package pramp.DecryptMessage;


/*
Convert every letter to its ASCII value. Add 1 to the first letter,
and then for every letter from the second one to the last one,
add the value of the previous letter.
Subtract 26 from every letter until it is in the range of lowercase letters a-z in ASCII.
Convert the values back to letters.

For instance, to encrypt the word “crime”

Decrypted message:	c	r	i	m	e
Step 1:	99	114	105	109	101
Step 2:	100	214	319	428	529
Step 3:	100	110	111	116	113
Encrypted message:	d	n	o	t	q
*/
public class Solution {

    static String decrypt(String word) {
        // your code goes here
        char[] arr = word.toCharArray();
        char[] res = new char[arr.length];
        res[0] = arr[0] == 'a' ? 'z' : (char) (arr[0] - 1);
        int sum = arr[0];
        for(int i = 1; i < arr.length; i++){
            int cur = arr[i] - sum;
            int offset = (i - 1) * 'a';
            offset -= offset % 26;
            cur += offset;
            while(cur < (int)'a') cur += 26;
            res[i] = (char) cur;
            sum += res[i];
        }
        return new String(res);
    }

    public static void main(String[] args) {
        String test = "dnotq";
        String test2 = "flgxswdliefy";
        //System.out.println(decrypt(test));
        System.out.println(decrypt(test2));
    }

}
