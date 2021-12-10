package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 字母异位词分组
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母都恰好只用一次。
 *
 * 示例 1:
 *
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 示例 2:
 *
 * 输入: strs = [""]
 * 输出: [[""]]
 * 示例 3:
 *
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 *
 * 作者：力扣 (leetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvaszc/
 * 来源：力扣（leetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * @data2021/9/7,13:20
 * @authorsutinghu
 */
public class LetterectopicGroup {

    /**
     *  步骤：
     *      1. 读取字符串数组中的值
     *      2. 判断是否有一样的值
     *
     *  哈希表的特性：key值唯一性
     *  字符的特性：字符ascall码值唯一性
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<String, List<String>> hashMap = new HashMap<>();
        for (String k:strs){
            // 取出每个数组中的值 并转换成字符
            char[] v = k.toCharArray();
            // 对这个字符进行排序 排序是根据它自己的ASCALL码值进行的
            Arrays.sort(v);
            // 我们将排序后的字符存入一个hashmap中
            String key =  new String(v);
            // 如果原来就有这个值 就直接添加 否则新建
            if (hashMap.containsKey(key)){
                hashMap.get(key).add(k);
            }else {
                List<String> list = new ArrayList<>();
                list.add(k);
                hashMap.put(new String(v),list);
            }
        }
        // 最后把hash中的value取出来 放入一个list中
        return hashMap.values().stream().collect(Collectors.toList());
    }
}
