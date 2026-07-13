import java.util.*;

class Solution {
private int postIndex;
private Map<Integer, Integer> inorderMap = new HashMap<>();

public TreeNode buildTree(int[] inorder, int[] postorder) {
    for (int i = 0; i < inorder.length; i++) {
        inorderMap.put(inorder[i], i);
    }

    postIndex = postorder.length - 1;
    return build(postorder, 0, inorder.length - 1);
}

private TreeNode build(int[] postorder, int left, int right) {
    if (left > right) {
        return null;
    }

    int rootVal = postorder[postIndex--];
    TreeNode root = new TreeNode(rootVal);

    int mid = inorderMap.get(rootVal);

    root.right = build(postorder, mid + 1, right);
    root.left = build(postorder, left, mid - 1);

    return root;
}

}