package com.xxy.elasticsearch.leetcode;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author xxy
 */
@Component
public class Solution {
    private static final Logger log = Logger.getLogger(Solution.class);
    /**
     * @param nums 指定的数组去重
     * @return
     */
    public int removeDuplicates(int[] nums) {
        for (int i=nums.length-1;i>0;i--){
            for ( int j=i-1;j>=0;j--){
                if (nums[i]==nums[j]){
                    nums=(int[]) delete(i,nums);
                }
            }
        }
        log.info("====================="+ Arrays.toString(nums));
        return nums.length;
    }

    /**
     * 根据下标删除
     * 复制新的数组
     * @param index
     * @param
     * @return
     */
    public Object delete(int index, int array1[]) {
        int length = array1.length;
        if (index >= 0 && index < length) {
            int [] result= new int[array1.length-1];
            System.arraycopy(array1, 0, result, 0, index);
            if (index < length - 1) {
                System.arraycopy(array1, index + 1, result, index, length - index - 1);
            }
            return result;
        } else {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
        }
    }
}
