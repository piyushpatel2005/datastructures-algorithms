package leetcode.lessons.tree;

import java.util.ArrayList;

public class TreeNode {
    private String data;
    ArrayList<TreeNode> children;

    public TreeNode(String data) {
        this.data = data;
        this.children = new ArrayList<TreeNode>();
    }

    public void addChild(TreeNode node) {
        this.children.add(node);
    }

    private String repeat(String str, int times) {
        while (times > 0) {
            str = str + str;
        }
        return str;
    }

    public String print(int level) {
        String result;
        result = this.repeat("  ", level) + data + "\n";
        for (TreeNode node: this.children) {
            result += node.print(level + 1);
        }
        return result;
    }
}
