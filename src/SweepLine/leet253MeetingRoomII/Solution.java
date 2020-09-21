package SweepLine.leet253MeetingRoomII;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if(intervals == null || intervals.length == 0){
            return 0;
        }
        List<int[]> list = new ArrayList<>();
        for(int[] interval : intervals){
            list.add(new int[] {interval[0], 1});
            list.add(new int[] {interval[1], -1});
        }
        Collections.sort(list, (i1, i2) -> {
            if(i1[0] == i2[0]) return i1[1] - i2[1]; // pop before push new interval
            return i1[0] - i2[0];
        });
        int count = 0;
        int res = 0;
        for(int[] i : list){
            if(i[1] > 0) count++;
            else count--;
            res = Math.max(res, count);
        }
        return res;
    }
}
