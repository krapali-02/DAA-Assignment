/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null){
            return "#";
        }
        //BFS k liyee queue
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        //Stringbuilder mai tree ka serialized form bnayenge 
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()){
            TreeNode node = q.poll();

            //Agar Node null hai toh "#" store krenge 
            if(node ==null){
                sb.append("#,");
                continue;
            }
            //current node ki value store kro
            sb.append(node.val).append(",");
            //left child ko queue mai daalo
            //null bhi queue mai dalna hai ,
            //kyuki structure preserve krna hai 
            q.offer(node.left);
            q.offer(node.right);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        //agr data empty hai ya "#" , toh tree empty hai 
        if(data == null || data.equals("#")){
            return null;
        }
        //String ko comma-separated value mai tod do
        String[] nodes = data.split(",");

        //firse value root hogi 
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));

        //BFS queue
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        //nodes[] mai currently kis position par hai
        int i=1;
        while(!q.isEmpty() && i<nodes.length){

            //Parent node nikalo
            TreeNode parent = q.poll();
            //-----------------LEFT CHILD-----------------------
            //Agar "#" nhi hai toh actual node create kro
            if(!nodes[i].equals("#")){
                TreeNode left = new TreeNode(Integer.parseInt(nodes[i]));
                parent.left = left;

                //created node ko queue mai daalo
                q.offer(left);
            }
            i++;

            //-----------------RIGHT CHILD-----------------------

            //Check kra data abhi khatam to nhi hua 
            if(i<nodes.length && !nodes[i].equals("#")){
                TreeNode right = new TreeNode(Integer.parseInt(nodes[i]));
                parent.right=right;

                //created node ko queue mai daalo
                q.offer(right);
            }
            i++;
        }
        return root;
        
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;