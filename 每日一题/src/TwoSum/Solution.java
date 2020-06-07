package TwoSum;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/two-sum/
 * 两数之和
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * 暴力法: 时间复杂度 O(n^2), 空间复杂度 O(1)
 * 哈希法: 时间复杂度 O(n), 空间复杂度 O(1)
 */
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            map.put(nums[i], i);
            int component = target - nums[i];
            if(map.get(component)!=null){
                return new int[]{i, map.get(component)};
            }
        }
        throw new IllegalArgumentException("no two sum solution");
    }
}
