package leetCode;

import java.util.HashMap;

/**
 *
 * 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 示例 4:
 *
 * 输入: s = ""
 * 输出: 0
 *
 * 作者：力扣 (leetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xv2kgi/
 * 来源：力扣（leetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @data2021/9/11,13:22
 * @authorsutinghu
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        int value = new LengthOfLongestSubstring().lengthOfLongestSubstring(" ");
        System.out.println(value);
    }

    /**
     *  双指针 算法
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring4(String s) {
        int[] chars = new int[128];
        int sLen = s.length();
        char[] sc = s.toCharArray();
        // 子串于子序列有所不同！
        if(sLen <= 1) {
            return sLen;
        }
        int max_len = 0;
        int r = 0, l = 0;   // 实现点3 保留r l位置 扩大作用域
        int posR = 0;       // 实现点2 记录 那个导致我们右指针停下来的字符
        for(; r < sLen; r++){
            ++chars[ sc[r] ];
            if(chars[ sc[r] ] == 2){
                max_len = Math.max(max_len, r-l );
                posR = sc[r];
                while( l<r && chars[posR]!=1 ){ // 实现点2
                    --chars[ sc[l] ];
                    // 实现点 1 直接的方法自然是找过去的记录 通过O(n)的hash表 但是太麻烦了
                    // 毕竟效率当然没有数组高 hash表示数组+链表+红黑二叉树实现的大概
                    // 数组实现思路 主要是把之前的记录销毁
                    // 所以直接让l再走一遍 这才是滑动窗口的另一个精髓所在！
                    ++l;
                }
            }
        }
        // 实现点3 最后一个 因为边界 找不到下一个重复的字符了 于是最后一次也算上
        // 也可以for循环里边判断 我觉得耗性能 算了
        // 有些题目可以采用哨兵模式 我封装一个数据结构 使得超过len也有相应的值
        // 但是这里不需要 因为只需要记录位置 返回长度
        return Math.max(max_len, r-l );
    }

    /**
     *  算法：滑动窗口
     *         核心就是
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring3(String s) {

        int length = s.length();

        if (length<2) {
            return 0;
        }

        HashMap<Character, Integer> map = new HashMap<>();

        int max = 0;

        int left = 0;

        for(int i = 0; i < length; i ++){

            if(map.containsKey(s.charAt(i))){

                left = Math.max(left,map.get(s.charAt(i)) + 1);

            }

            map.put(s.charAt(i),i);

            max = Math.max(max,i-left+1);

        }

        return max;

    }

    /**
     *  暴力破解 2
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        char rouce[] = s.toCharArray();
        int length = s.length();
        if (length < 2){
            return length ;
        }
        HashMap<Object, Object> map = new HashMap<>();
        int result = 0;
        for (int j = 0;j<length;j++){
            int i = j;
            while (i<length){
                if (map.get(rouce[i]) == null){
                    map.put(rouce[i],rouce[i]);
                    i++;
                }else {
                    int size = map.size();
                    if (result < size){
                        result = size;
                    }
                    map.clear();
                    i = length;
                }
            }
        }
        return result;
    }

    /**
     * 执行用时：66 ms, 在所有 Java 提交中击败了13.41%的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了6.25%的用户
     * 暴力破题法
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        char rouce[] = s.toCharArray();
        int length = s.length();
        if (length < 2){
            return length ;
        }
        HashMap<Object, Object> map = new HashMap<>();
        int result = 0;
        for (int j = 0;j<length;j++){

            for (int i = j;i<length;i++){
                if (map.get(rouce[i]) == null){
                    map.put(rouce[i],rouce[i]);
                }else {
                    int size = map.size();
                    if (result < size){
                        result = size;
                    }
                    map.clear();
                    break;
                }
            }
        }
        return result;
    }
}
