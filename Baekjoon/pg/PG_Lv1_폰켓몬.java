package pg;

import java.util.HashSet;

class PG_Lv1_폰켓몬 {
    public int solution(int[] nums) {
        HashSet keep = new HashSet();
        for (int i = 0; i < nums.length; i++) {
            if (!keep.contains(nums[i])) {
                keep.add(nums[i]);
            }
        }
        if (keep.size() >= nums.length / 2) {
            return nums.length / 2;
        } else {
            return keep.size();
        }
    }
}

