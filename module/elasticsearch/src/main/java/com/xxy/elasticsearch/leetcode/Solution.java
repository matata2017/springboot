package com.xxy.elasticsearch.leetcode;
import com.sun.org.apache.bcel.internal.generic.SWAP;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author xxy
 */
@Component
public class Solution {
    private static final Logger log = Logger.getLogger(Solution.class);
    /**
     * @param nums 指定的数组去重
     * 思路：双重循环 将数组中取出一个数据和之后的数据对比找到数组中第几个，根据下标从数组中
     *   删除，因为删除数组数组后前移所以采用的i--
     * @return
     */
    public int removeDuplicates(int[] nums) {
        for (int i=nums.length-1;i>0;i--){
            for ( int j=i-1;j>=0;j--){
                if (nums[i]==nums[j]){
                    int length = nums.length;
                    if (i >= 0 && i < length) {
                        int [] result= new int[nums.length-1];
                        System.arraycopy(nums, 0, result, 0, i);
                        if (i < length - 1) {
                            System.arraycopy(nums, i + 1, result, i, length - i - 1);
                        }
                        nums=result;
                    } else {
                        throw new IndexOutOfBoundsException("Index: " + i + ", Length: " + length);
                    }
                }
            }
        }
        return nums.length;
    }

    /**
     * 买卖股票的最佳时机 II
     * 思路：有钱赚就卖掉获得的利润相加
     */
    public int maxProfit(int[] prices) {
        int profit=0;
        if (prices.length<=0){
            return 0;
        }
        for (int i=1;i<prices.length;i++){
            if (prices[i]-prices[i-1]>0){
                profit+=prices[i]-prices[i-1];
            }
        }
        return profit;
    }

    /**
     * 旋转数组
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     * 思路：数组中两两交换位置
     * 117 ms 性能堪忧。。。
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int n=0;
        //对偏移的长度取余数
        k = k % nums.length;
        while (n<k){
            int end=nums[nums.length-1];
            for (int i=nums.length-2;i>=0;i--){
                nums[i+1]=nums[i];
            }
            nums[0]=end;
            n++;
        }
    }

    /**
     * 旋转数组
     * 看看别人的思路
     * 借助O(n)的空间解法。再利用(i + k) % n = 旋转后的位置
     *      3元素在数组中的2位置上，(2+3) % 7 = 5
     *      5元素在数组中的4位置上，(4+3) % 7 = 0
     *      6元素在数组中的5位置上，(5+3) % 7 = 1
     *      7元素在数组中的6位置上，(6+3) % 7 = 2
     * @param nums
     * @param k
     */
    public void rotate1(int[] nums, int k) {
             k = k % nums.length;
            int[] result = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                result[(i + k) % nums.length] = nums[i];
            }
            nums=result;
            log.info("==========="+Arrays.toString(nums));
    }

    /**
     * 旋转数组
     * 看看别人的思路
     * @param nums
     * @param k
     * 1ms
     */
    public void rotate2(int[] nums, int k) {
        k = k % nums.length;
        for(int i = 0,j=nums.length-k-1;i < j; i++,j--) {
            int tem = nums[i];
            nums[i] = nums[j];
            nums[j] = tem;
        }
        for(int i = nums.length-k,j=nums.length-1;i<j;i++,j--) {
            int tem = nums[i];
            nums[i] = nums[j];
            nums[j] = tem;
        }
        for(int i = 0,j=nums.length-1;i<j;i++,j--) {
            int tem = nums[i];
            nums[i] = nums[j];
            nums[j] = tem;
        }
    }


    /**
     * 存在重复
     * 直接比较
     *  性能堪忧。。。。
     * @param nums
     * @return
     */
    public boolean containsDuplicate2(int[] nums) {
        for (int i=nums.length-1;i>0;i--){
            for ( int j=i-1;j>=0;j--){
                if (nums[i]==nums[j]){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 存在重复
     * 先排序，比较俩个是否相等
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i=0;i<nums.length-1;i++){
            if (nums[i]==nums[i+1]){
                return true;
            }
        }
        return false;
    }

    /**
     *给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * 思路： 采用异或
     * 异或二进制 相同位取0 不同取1
     * a^0=a  a^a=0
     * 1^2^2 =1
     * 所以把数组中所有是数采用异或得到的最后的数就是不同的数
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int result=0;
        for (int i=0;i<nums.length;i++){
             result =  nums[i]^result;
        }
        return result;
    }


    /**
     * 数组交集
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {

        return null;
    }

    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        List<Integer> index = new ArrayList();
        for (int i=0;i<nums.length;i++){
            if (nums[i]==0){

            }
        }

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
