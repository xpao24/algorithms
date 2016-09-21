package leecode;

import java.util.*;


/**
 * 序列化时,采用先序遍历(广度). 反序列化时,采用双端队列LinkedList存储节点.同样是先序遍历.
 */
public class Codec {
    private static final String spliter = ",";
    private static final String NN = "NULL";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        buildString(root,builder);
        return builder.toString();
    }
    private StringBuilder buildString(TreeNode node,StringBuilder builder){
        if(node == null){
            builder.append(NN).append(spliter);
        }else{
            builder.append(node.val).append(spliter);
            buildString(node.left,builder);
            buildString(node.right,builder);
        }
        return builder;
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data){
        Queue<String> nodes = new LinkedList();
        String[] strs = data.split(",");
        if(strs!=null && strs.length>0){
            nodes.addAll(Arrays.asList(strs));
            return buildTree(nodes);
        }
        return null;
    }
    private TreeNode buildTree(Queue<String> nodes){
        TreeNode node=null;
        String str = nodes.remove();
        if(NN.equals(str)) return null;
        else{
            node = new TreeNode(Integer.valueOf(str));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
        }
        return node;
    }
}
// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

/**
 * Definition for a binary tree node.
 */
final class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
