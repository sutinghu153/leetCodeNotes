package leetCode;

import java.util.*;

/**
 * leetcode 算法题
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：
 *
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：[]
 *
 * 作者：力扣 (leetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvpj16/
 * 来源：力扣（leetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * @data2021/9/3,12:14
 * @authorsutinghu
 */
public class SumOfThree {

    public static void main(String[] args) {
        int[] x = new int[6];
        x[0] = -1;
        x[1] = 0;
        x[2] = 1;
        x[3] = 0;
        List<List<Integer>> t = threeSum1(x);
        System.out.println(t.toString());
    }

    /**
     *执行用时：21 ms, 在所有 Java 提交中击败了78.55%的用户
     * 内存消耗：42 MB, 在所有 Java 提交中击败了87.92%的用户
     * 通过测试用例：318 / 318
     *  排序 双指针法 直接用指针实现去重的思想
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        int len = nums.length;
        if(nums == null || len < 3) {
            return ans;
        }
        Arrays.sort(nums); // 排序
        for (int i = 0; i < len ; i++) {
            if(nums[i] > 0) {
                break; // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            }
            if(i > 0 && nums[i] == nums[i-1]) {
                continue; // 去重
            }
            int L = i+1;
            int R = len-1;
            while(L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0){
                    ans.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    while (L<R && nums[L] == nums[L+1]) {
                        L++; // 去重
                    }
                    while (L<R && nums[R] == nums[R-1]) {
                        R--; // 去重
                    }
                    L++;
                    R--;
                }
                else if (sum < 0){
                    L++;
                }
                else if (sum > 0){
                    R--;
                }
            }
        }
        return ans;
    }

    /**
     *  排序 查找算法
     *      执行用时：1677 ms, 在所有 Java 提交中击败了5.00%的用户
     *      内存消耗：43.2 MB, 在所有 Java 提交中击败了6.72%的用户
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // 排除元素个数小于三和等于三的特殊情况
        if (nums.length<3){
            return new ArrayList<>();
        }
        // 先对所有的数字进行排序
        Arrays.sort(nums);
        int min = nums[0];
        int max = nums[nums.length-1];
        // 首先思考：什么情况下三个数字和为零？至少一个负整数 && 至少一个正整数 || 全为0
        // 将有序的数组组成两个数字的组合 组合不能出行重复的组合 因此循环时如下设计
        Map<Integer,Integer> mapi = new HashMap<>();
        for (int i = 0;i<nums.length-2;i++){
            // 因为策略是只向后查找 因此 当三个组合中的第一个数字大于0时 该组合肯定大于0
            if (nums[i]>0){
                break;
            }
            // 去重 目的如果数组第一位已经出现过 就规避
            if (mapi.get(nums[i])!=null){
                continue;
            }
            mapi.put(nums[i],nums[i]);
            int count = 0;
            Map<Integer,Integer> mapj = new HashMap<>();
            for (int j = i+1;j<nums.length-1;j++){
                // 去重 目的如果数组第二位已经出现过 就规避
                if (mapj.get(nums[j])!=null){
                    continue;
                }
                mapj.put(nums[j],nums[j]);
                // 如果第一个和第二个数字一样只能够遍历一次
                if (nums[i] == nums[j] && count>0 ){
                    break;
                }
                count++;
                Integer c = -nums[i] - nums[j];
                // 预判 ：如果三数之和的补数 的范围太小 或太大就放弃这个组合
                if (c<min||c>max){
                    continue;
                }else {
                    // 查找原来的数组中是否存在补数 只向后查找
                    for (int k = j+1;k<nums.length;k++){
                        if (nums[k] == c){
                            List<Integer> list = new ArrayList<>();
                            list.add(nums[i]);
                            list.add(nums[j]);
                            list.add(c);
                            result.add(list);
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

}
