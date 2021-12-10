package leetCode;

import java.util.*;

/**
 * 算法题：给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 *
 * 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：[[1,0,1],[0,0,0],[1,0,1]]
 *
 * 输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * 输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 *
 * 进阶：
 *
 * 一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个仅使用常量空间的解决方案吗？
 *
 * 作者：力扣 (leetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvmy42/
 * 来源：力扣（leetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * @data2021/9/6,12:38
 * @authorsutinghu
 */
public class MatrixZeroing {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,1,1},{1,0,1},{1,1,1}};
        System.out.println(Arrays.deepToString(matrix));
        new MatrixZeroing().setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    /**
     * 原地算法：也叫就地算法，就是用限定的空间额度来实现数据的处理
     *         简单的讲就是将数据通过某种策略实现数据内部的处理
     *
     *  暴力破解：额外空间为实际的0总数
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        /**
         * 首先思考：
         * 本题的核心步骤主要有两个
         *      1. 找到matrix[i][j]中的元素为零的位置，并记录i和j
         *      2. 遍历整个矩阵 将i行和j列的所有数字改成0
         *  注意的点：
         *      在矩阵遍历的过程中，如果修改了行列值，那会对后面的矩阵的值的判断产生影响
         *      因此，需要先将所有值为0 的行列号记录下来
         *      再统一修改记录的值——在这个统一记录的过程中，就需要提供额外的资源来操作，这个额外的资源就是就地资源
         *      就地算法就是在这个额外的空间里完成本次的步骤
         *
         *      记录值的时候，根据矩阵的特性，记录它的行和列，即i和j 【i和j存在一定的关系】
         */
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> row = new ArrayList<>();
        List<Integer> col = new ArrayList<>();
        /**
         * 步骤一，记录
         */
        for (int i = 0;i<m;i++){
            for (int j = 0;j<n;j++){
                if (matrix[i][j] == 0){
                    /**
                     *  记录值
                     */
                    if (!row.contains(i)){
                        row.add(i);
                    }
                    if (!col.contains(j)){
                        col.add(j);
                    }
                }
            }
        }
        /**
         * 步骤二，清零
         */

        int lengthRow = row.size();
        for (int i = 0;i<lengthRow;i++){
           for (int k = 0;k<n;k++){
               matrix[row.get(i)][k] = 0;
           }
        }

        int lengthCol = col.size();
        for (int i = 0;i<lengthCol;i++){
            for (int k = 0;k<m;k++){
                matrix[k][col.get(i)] = 0;
            }
        }
    }

    /**
     * 暴力破解简化版——标记思想
     * @param matrix
     */
    public void setZeroes2(int[][] matrix) {
        Set<Integer> row_zero = new HashSet<>();
        Set<Integer> col_zero = new HashSet<>();
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    row_zero.add(i);
                    col_zero.add(j);
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (row_zero.contains(i) || col_zero.contains(j)){
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 同位标记法——当矩阵的某个位置有零时，对应行的第一列和对应列的第一行设置为零
     * @param matrix
     */
    public void setZeroes3(int[][] matrix){
        boolean col0_flag = false;
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            if (matrix[i][0] == 0) {
                col0_flag = true;
            }
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (col0_flag) {
                matrix[i][0] = 0;
            }
        }
    }
}
