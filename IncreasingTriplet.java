package leetCode;

import java.util.LinkedList;

/**
 *  递增的三元子序列
 * 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
 *
 * 如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,4,5]
 * 输出：true
 * 解释：任何 i < j < k 的三元组都满足题意
 * 示例 2：
 *
 * 输入：nums = [5,4,3,2,1]
 * 输出：false
 * 解释：不存在满足题意的三元组
 * 示例 3：
 *
 * 输入：nums = [2,1,5,0,4,6]
 * 输出：true
 * 解释：三元组 (3, 4, 5) 满足题意，因为 nums[3] == 0 < nums[4] == 4 < nums[5] == 6
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 *
 * 作者：力扣 (leetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvvuqg/
 * 来源：力扣（leetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * @data2021/9/12,12:53
 * @authorsutinghu
 */
public class IncreasingTriplet {

    public static void main(String[] args) {
        int[] nums = new int[]{3,5,1,2,9,10};
        System.out.println(new IncreasingTriplet().increasingTriplet2(nums));
    }

    /**
     *  过滤的思想 :
     *      1. 当元素小于栈顶元素时——入栈
     *      2. 当栈中元素大于三个时，结束
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {
        if (nums.length<3){
            return false;
        }
        int length = nums.length;
        LinkedList<Integer> stack = new LinkedList<>();
        stack.addFirst(nums[0]);
        for (int i = 0;i<length;i++){
            if (stack.size()>2){
                return true;
            }
            if (nums[i] <= stack.getFirst()){
                stack.clear();
                stack.addFirst(nums[i]);
            }else if (nums[i] > stack.getFirst()){
                stack.addFirst(nums[i]);
            }
        }
        return stack.size()>2;
    }

    /**
     *  过滤器思想——双层双重过滤思想
     *
     *  这是什么牛逼的解法 真的是发现三元数的真相了
     *          思想：有两个数，按大小排好序了 ，如果存在第三个数字 比较大的大 就成立
     *          所以需要过滤：通过顺序的的比较，先比较两个数字中最小的，再比较较大的
     *          这个过滤器只能放两个数，如果这个数字装不下第三个数时，过滤结束
     *
     *          因为第二个过滤器已经过滤了第一个过滤器，因此 如果有比第二个数大的数来，那第二个数字相当于替前第一个数同时进行了过滤
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet2(int[] nums) {
        if (nums.length<3){
            return false;
        }
        int small = Integer.MAX_VALUE, mid = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= small) {
                small = num;
            } else if (num <= mid) {
                mid = num;
            } else if (num > mid) {
                return true;
            }
        }
        return false;
    }
}
