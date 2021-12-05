import java.util.HashMap;
import java.util.Map;

class TreeNode {
    private String value;
    private Map<Integer, TreeNode> children =new HashMap<Integer, TreeNode>();

    public TreeNode(String value) {
        this.value = value;
    }

    public boolean addNode(int key, String value) {
        if (!children.containsKey(key)) {
            children.put(key, new TreeNode(value));
            return true;
        }
        return false;
    }

    public String getValue() {
        return this.value;
    }

    public TreeNode getChild(int key) {
        return this.children.get(key);
    }
}
