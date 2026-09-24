class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int n = matrix[0].length;
        int[] heights = new int[n];
        int ans = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1')
                    heights[j]++;
                else
                    heights[j] = 0;
            }
            ans = Math.max(ans, largestRectangle(heights));
        }
        return ans;
    }
    public int largestRectangle(int[] heights) {
        Stack<Integer> st = new Stack<>();
        int max = 0;
        for (int i = 0; i <= heights.length; i++) {
            int curr = (i == heights.length) ? 0 : heights[i];
            while (!st.isEmpty() && heights[st.peek()] > curr) {
                int h = heights[st.pop()];
                int width;
                if (st.isEmpty())
                    width = i;
                else
                    width = i - st.peek() - 1;
                max = Math.max(max, h * width);
            }
            st.push(i);
        }

        return max;
    }
}