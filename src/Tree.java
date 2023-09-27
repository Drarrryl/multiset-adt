import java.util.*;

public class Tree {
    private Object root;
    private ArrayList<Tree> subtrees;

    public Tree(Object newRoot, ArrayList<Tree> newSubtrees) {
        setRoot(newRoot);
        if (newSubtrees == null) {
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
    private String strIndented(int depth) {
        if (this.is_empty()) {
            return "";
        } else {
            String indent = "";
            for (int i = 0; i < depth; i++) {
                indent = indent.concat("   ");
            }
            String str = (indent + this.root.toString() + "\n");
            for (Tree subtree : this.subtrees) {
                str = str.concat(subtree.strIndented(depth + 1));
            }
            return str;
        }
    }
    @Override
    public String toString() {
        return strIndented(0);
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
            if (this.subtrees.isEmpty()) {
                return this.root == other.root;
            } else {
                for (int i = 0; i < this.subtrees.size(); i++) {
                    if (!(this.subtrees.get(i).equals(other.subtrees.get(i)))) {
                        return false;
                    }
                }
                return true;
            }
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
    public ArrayList<Object> leaves() {
        if (this.is_empty()) {
            return new ArrayList<>();
        } else if (Objects.equals(this.subtrees, new ArrayList<Tree>())) {
            return new ArrayList<>(Collections.singletonList(this.root));
        } else {
            ArrayList<Object> leaves = new ArrayList<>();
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

    public boolean add(Object item) {
        return false;
    }

    public void remove(Object item) {    }

    public boolean contains(Object item) {
        return false;
    }

    public boolean is_empty() {
        return false;
    }

    public int count(Object item) { return 0; }

    public int size(Object item) { return 0; }

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
    @Override
    public boolean add(Object item) {
        this.tree.insert(item);
        return true;
    }
    @Override
    public void remove(Object item) {
        this.tree.delete_item(item);
    }
    @Override
    public boolean contains(Object item) {
        return this.tree.contains(item);
    }
    @Override
    public boolean is_empty() {
        return this.tree.is_empty();
    }
    @Override
    public int count(Object item) {
        return this.tree.count(item);
    }
    @Override
    public int size(Object item) {
        return this.tree.len();
    }
}

class ArrayListMultiSet extends MultiSet {
    private ArrayList<Object> list;
    public ArrayListMultiSet() {
        super();
        setArrayList(new ArrayList<>());
    }
    public ArrayList<Object> getArrayList() {
        return list;
    }
    public void setArrayList(ArrayList<Object> newList) {
        this.list = newList;
    }
    @Override
    public boolean add(Object item) {
        this.list.add(item);
        return true;
    }
    @Override
    public void remove(Object item) {
        this.list.remove(item);
    }
    @Override
    public boolean contains(Object item) {
        return this.list.contains(item);
    }
    @Override
    public boolean is_empty() {
        return this.list.isEmpty();
    }
    @Override
    public int count(Object item) {
        int count = 0;
        while (this.list.contains(item)) {
            count += 1;
            list.remove(item);
        }
        return count;
    }
    @Override
    public int size(Object item) {
        return this.list.size();
    }
}

class Node {
    public Object item;
    private Node next;
    public Node(Object item) {
        this.item = item;
        setNext(null);
    }
    public Node getNext() {
        return this.next;
    }
    public void setNext(Node next) {
        this.next = next;
    }
}

class LinkedListMultiSet extends MultiSet {
    private Node front;
    private int size;
    public LinkedListMultiSet() {
        this.setFront(null);
        this.setSize(0);
    }
    public void setFront(Node front) {
        this.front = front;
    }
    public Node getFront() {
        return front;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public int getSize() {
        return size;
    }
    @Override
    public boolean add(Object item) {
        Node newNode = new Node(item);
        newNode.setNext(this.front);
        this.setFront(newNode);
        this.size += 1;
        return true;
    }
    @Override
    public void remove(Object item) {
        Node curr = this.front;
        Node prev = null;
        while (curr != null) {
            if (curr.item == item) {
                this.size -= 1;
                if (prev != null) {
                    prev.setNext(curr.getNext());
                } else {
                    this.front = curr.getNext();
                }
                return;
            }
            prev = curr;
            curr = curr.getNext();
        }
    }
    @Override
    public boolean contains(Object item) {
        Node curr = this.front;
        while (curr != null) {
            if (curr.item == item) {
                return true;
            }
            curr = curr.getNext();
        }
        return false;
    }
    @Override
    public boolean is_empty() {
        return this.front == null;
    }
    @Override
    public int count(Object item) {
        int count = 0;
        Node curr = this.front;
        while (curr != null) {
            if (curr.item == item) {
                count += 1;
            }
            curr = curr.getNext();
        }
        return count;
    }
    @Override
    public int size(Object item) {
        return this.getSize();
    }
}