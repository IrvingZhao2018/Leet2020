function MinWindowSubstring(strArr) {
    const s = strArr[0];
    const t = strArr[1];
    const counter = {};
    for(let c of t) {
        if(!counter[c]) counter[c] = 0;
        counter[c]++;
    }
    let len = t.length;
    let start = 0;
    let resStart = 0;
    let min = s.length + 1;
    for(let i = 0; i < s.length; i++){
        if(counter[s[i]]-- > 0) len--;
        while(len == 0){
            if(i - start + 1 < min){
                min = i - start + 1;
                resStart = start;
            }
            if(counter[s[start++]]++ == 0) len++;
        }
    }
    return s.substring(resStart, resStart + min);
}
const test = ["aaabaaddae", "aed"];
// keep this function call here
// console.log(MinWindowSubstring(readline()));
console.log(MinWindowSubstring(test));