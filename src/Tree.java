public class Tree {
    private Object root;
    private Tree[] subtrees;

    public Tree(Object newRoot, Tree[] newSubtrees) {
        setRoot(newRoot);
        if (this.subtrees == null) {
            setSubtrees(new Tree[0]);
        } else {
            setSubtrees(newSubtrees);
        }
    }

    public Object getRoot() {
        return this.root;
    }
    public void setRoot(Object newRoot) {
        this.root = newRoot;
    }
    public Tree[] getSubtrees() {
        return this.subtrees;
    }
    public void setSubtrees(Tree[] newSubtree) {
        this.subtrees = newSubtree;
    }
    public boolean is_empty() {
        return this.root == null;
    }
    public int len() {
        if (this.is_empty()) {
            return 0;
        } else {
            int size = 1;
            for (Tree sub : this.getSubtrees()) {
                size += sub.len();
            }
            return size;
        }
    }
    public int count(Object item) {
        if (this.is_empty()) {
            return 0;
        } else {
            int num = 0;
            if (this.root == item) {
                num += 1;
            }
            for (Tree sub : this.getSubtrees()) {
                num += sub.count(item);
            }
            return num;
        }
    }
}

class MultiSet {

    public static boolean add(Object item) {
        return false;
    }

    public static void remove(Object item) {

    }

    public static boolean contains(Object item) {
        return false;
    }

    public static boolean is_empty() {
        return false;
    }

    public static int count(Object item) {
        return 0;
    }

    public static int size(Object item) {
        return 0;
    }

}


class TreeMultiSet extends MultiSet {
    private Tree tree;
    public TreeMultiSet() {
        super();
        setTree(new Tree(null, new Tree[0]));
    }
    public Tree getTree() {
        return tree;
    }
    public void setTree(Tree newTree) {
        this.tree = newTree;
    }
}