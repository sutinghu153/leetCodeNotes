package leetCode;

/**
 *
 * 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 * 示例 1：
 *
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 *
 * 输入：s = "cbbd"
 * 输出："bb"
 * 示例 3：
 *
 * 输入：s = "a"
 * 输出："a"
 * 示例 4：
 *
 * 输入：s = "ac"
 * 输出："a"
 *
 * 作者：力扣 (leetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvn3ke/
 * 来源：力扣（leetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * @data2021/9/12,10:34
 * @authorsutinghu
 */
public class LongestPalindrome {


    public static void main(String[] args) {
        System.out.println(new LongestPalindrome().longestPalindrome("aaaaaa"));
    }
    /**
     *  先试试暴力破题的思路——两个指针以点向两边扩张，直到两个指针的值不一样时结束
     *  总结：中心化法
     *  步骤 ：
     *      1. 一个字符串数组化
     *      2. 从头开始遍历 并拿出一个数据结构记录它的结构 当形式符合回文时 记录
     *      3. 返回最长的回文子串
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s.length()<2){
            return s;
        }
        int length = s.length();
        String result = new String();
        for (int i = 0 ;i < length; i++){
            String left = getString(i,i,s);
            String right = getString(i,i+1,s);
            if (left.length()>right.length() && result.length()<left.length()){
                result = left;
            }else if (left.length()<right.length() && result.length()<right.length()){
                result = right;
            }
        }
        return result;
    }
    public String getString(int left,int right,String s){
        int length = s.length();
        String result = new String();
        while (left>=0 && right<length && s.charAt(left) == s.charAt(right)){
            if (left>=0 && right<length && s.charAt(left) == s.charAt(right)){
                String medium = s.substring(left,right+1);
                if (medium.length()>result.length()){
                    result = medium;
                }
                right++;
                left--;
            }
        }
        return result;
    }

    /**
     *  动态规划法 解决问题：不建议用该方法解决 太饶了
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }

                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

}
