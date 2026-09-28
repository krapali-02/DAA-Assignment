/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int maxi = Integer.MIN_VALUE;
    
    int solve(TreeNode root){

    if(root == null) return 0;
        
        int l = solve(root.left);
        int r = solve(root.right);

        l = Math.max(0,l);
        r = Math.max(0,r);
        
        int subTree = root.val + l + r ;
        int oneOfPath = root.val + Math.max(l,r);
        int onlyRoot = root.val;
        
        maxi = Math.max(maxi ,
               Math.max(subTree,
               Math.max(oneOfPath,onlyRoot)));
        
        return Math.max(oneOfPath , onlyRoot);
    }

    public int maxPathSum(TreeNode root) {
    solve(root);
    return maxi;
    
    }
        
    
}