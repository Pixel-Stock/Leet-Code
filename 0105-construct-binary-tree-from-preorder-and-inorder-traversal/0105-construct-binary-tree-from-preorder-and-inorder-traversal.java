import java.util.*;

class Solution {
private int preorderIndex = 0;
private Map<Integer, Integer> inorderMap = new HashMap<>();

public TreeNode buildTree(int[] preorder, int[] inorder) {
    for (int i = 0; i < inorder.length; i++) {
        inorderMap.put(inorder[i], i);
    }

    return build(preorder, 0, inorder.length - 1);
}

private TreeNode build(int[] preorder, int left, int right) {
    if (left > right) {
        return null;
    }

    int rootVal = preorder[preorderIndex++];
    TreeNode root = new TreeNode(rootVal);

    int mid = inorderMap.get(rootVal);

    root.left = build(preorder, left, mid - 1);
    root.right = build(preorder, mid + 1, right);

    return root;
}

}