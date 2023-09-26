import java.util.*;

public class Tree {
    private Object root;
    private ArrayList<Tree> subtrees;

    public Tree(Object newRoot, ArrayList<Tree> newSubtrees) {
        setRoot(newRoot);
        if (this.subtrees == null) {
            setSubtrees(new ArrayList<>());
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
    public List<Tree> getSubtrees() {
        return this.subtrees;
    }
    public void setSubtrees(ArrayList<Tree> newSubtree) {
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
    private int[] averageHelper() {
        if (this.is_empty()) {
            return new int[2];
        } else {
            int total = (int) this.root;
            int size = 1;
            for (Tree subtree : this.getSubtrees()) {
                int[] avg = subtree.averageHelper();
                total += avg[0];
                size += avg[1];
            }
            int[] result = new int[2];
            result[0] = total;
            result[1] = size;
            return result;
        }
    }
    public float average() {
        if (this.is_empty()) {
            return (float) 0;
        } else {
            int[] result = this.averageHelper();
            return (float) result[0] /result[1];
        }
    }
    @Override
    public boolean equals(Object obj) {
        Tree other = (Tree) obj;
        if (this.is_empty() && other.is_empty()) {
            return true;
        } else if (this.is_empty() || other.is_empty()) {
            return false;
        } else {
            if (this.root != other.getRoot()) {
                return false;
            }
            if (this.len() != other.len()) {
                return false;
            }
            return this.subtrees == other.getSubtrees();
        }
    }
    public boolean contains(Object item) {
        if (this.is_empty()) {
            return false;
        } else if (this.root == item) {
            return true;
        } else {
            for (Tree subtree : this.getSubtrees()) {
                if (subtree.contains(item)) {
                    return true;
                }
            }
            return false;
        }
    }
    public List<Object> leaves() {
        if (this.is_empty()) {
            return new ArrayList<>();
        } else if (Objects.equals(this.subtrees, new ArrayList<Tree>())) {
            return new ArrayList<>(Collections.singletonList(this.root));
        } else {
            List<Object> leaves = new ArrayList<>();
            for (Tree subtree : this.getSubtrees()) {
                leaves.addAll(leaves.size(), subtree.leaves());
            }
            return leaves;
        }
    }
    private void delete_root() {
        if (Objects.equals(this.subtrees, new ArrayList<Tree>())) {
            this.root = null;
        } else {
            Tree chosenSubtree = this.subtrees.get((this.subtrees.size() - 1));
            this.subtrees.remove(this.subtrees.size() - 1);
            this.root = chosenSubtree.getRoot();
            this.subtrees.addAll(chosenSubtree.getSubtrees());
        }
    }
    public boolean delete_item(Object item) {
        if (this.is_empty()) {
            return false;
        } else if (this.root == item) {
            this.delete_root();
            return true;
        } else {
            for (Tree subtree : this.getSubtrees()) {
                boolean deleted = subtree.delete_item(item);
                if (deleted && subtree.is_empty()) {
                    this.subtrees.remove(subtree);
                    return true;
                } else if (deleted) {
                    return true;
                }
            }
            return false;
        }
    }
    private Object extractLeaf() {
        if (this.subtrees.isEmpty()) {
            Object oldRoot = this.root;
            this.root = null;
            return oldRoot;
        } else {
            Object leaf = this.subtrees.get(0).extractLeaf();
            if (this.subtrees.get(0).is_empty()) {
                this.subtrees.remove(0);
            }
            return leaf;
        }
    }
    public void insert(Object item) {
        if (this.is_empty()) {
            this.root = item;
        } else if (this.subtrees.isEmpty()) {
            this.subtrees = new ArrayList<>();
            this.subtrees.add(new Tree(item, new ArrayList<>()));
        } else {
            Random rand = new Random();
            if (rand.nextInt(1, 3) == 3) {
                this.subtrees.add(new Tree(item, new ArrayList<>()));
            } else {
                int subtreeIndex = rand.nextInt(0, this.subtrees.size()-1);
                this.subtrees.get(subtreeIndex).insert(item);
            }
        }
    }
    public boolean insertChild(Object item, Object parent) {
        if (this.is_empty()) {
            return false;
        } else if (this.root == parent) {
            this.subtrees.add(new Tree(item, new ArrayList<>()));
            return true;
        } else {
            for (Tree subtree : this.getSubtrees()) {
                if (subtree.insertChild(item, parent)) {
                    return true;
                }
            }
            return false;
        }
    }
}

class MultiSet {

    public static boolean add(Object item) {
        return false;
    }

    public static void remove(Object item) {    }

    public static boolean contains(Object item) {
        return false;
    }

    public static boolean is_empty() {
        return false;
    }

    public static int count(Object item) { return 0; }

    public static int size(Object item) { return 0; }

}


class TreeMultiSet extends MultiSet {
    private Tree tree;
    public TreeMultiSet() {
        super();
        setTree(new Tree(null, new ArrayList<>()));
    }
    public Tree getTree() {
        return tree;
    }
    public void setTree(Tree newTree) {
        this.tree = newTree;
    }
}