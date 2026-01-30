package ds;


public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    private static final class AVLNode<T> extends BinaryNode<T> {
        int height = 1;
        AVLNode(T d) { super(d); }
    }
    public AVLTree(T data){
		super(data);
	}
	
	public AVLTree(){
		super();
	}
    @Override
    protected BinaryNode<T> createNode(T value) {
        return new AVLNode<>(value);
    }

    private AVLNode<T> asAVL(BinaryNode<T> n) {
        return (n == null) ? null : (AVLNode<T>) n;
    }

    private int height(AVLNode<T> n) {
        return (n == null) ? 0 : n.height;
    }

    private int balance(AVLNode<T> n) {
        return height(asAVL(n.left())) - height(asAVL(n.right()));
    }


    @Override
    public boolean insert(T value) {
        if (!super.insert(value)) return false;
        rebalanceUpwards(findNode(value));
        return true;
    }
    

    private BinaryNode<T> deleteNodeForAVL(BinaryNode<T> node) {

        if (node.left() == null || node.right() == null) {
            BinaryNode<T> child = (node.left() != null) ? node.left() : node.right();
            BinaryNode<T> parent = node.parent();

            replaceParentLink(node, child);
            decrementSize();

            return parent;
        }

        BinaryNode<T> succ = node.right();
        while (succ.left() != null) succ = succ.left();

        node.setData(succ.value());

        return deleteNodeForAVL(succ);
    }

    @Override
    public boolean delete(T value) {
        AVLNode<T> node = findNode(value);
        if (node == null) return false;

        AVLNode<T> start = asAVL(deleteNodeForAVL(node)); 
        rebalanceUpwards(start);
        return true;
    }



    private AVLNode<T> findNode(T value) {
        BinaryNode<T> cur = getRootNode();
        while (cur != null) {
            int c = value.compareTo(cur.value());
            if (c == 0) return asAVL(cur);
            cur = (c < 0) ? cur.left() : cur.right();
        }
        return null;
    }


    private AVLNode<T> rotateRight(AVLNode<T> a) {
        AVLNode<T> b = asAVL(a.left());
        AVLNode<T> parent = asAVL(a.parent());

        if (parent == null) {
            setRootNode(b);
            b.setParent(null);
        } else if (a.isLeftChild()) {
            parent.setLeft(b);
        } else {
            parent.setRight(b);
        }

        a.setLeft(b.right());
        b.setRight(a);

        a.height = 1 + Math.max(height(asAVL(a.left())), height(asAVL(a.right())));
        b.height = 1 + Math.max(height(asAVL(b.left())), height(asAVL(b.right())));

        return b;
    }

    private AVLNode<T> rotateLeft(AVLNode<T> a) {
        AVLNode<T> b = asAVL(a.right());
        AVLNode<T> parent = asAVL(a.parent());

        if (parent == null) {
            setRootNode(b);
            b.setParent(null);
        } else if (a.isLeftChild()) {
            parent.setLeft(b);
        } else {
            parent.setRight(b);
        }

        a.setRight(b.left());
        b.setLeft(a);

        a.height = 1 + Math.max(height(asAVL(a.left())), height(asAVL(a.right())));
        b.height = 1 + Math.max(height(asAVL(b.left())), height(asAVL(b.right())));

        return b;
    }


    private void rebalanceUpwards(AVLNode<T> node) {
        while (node != null) {
            node.height = 1 + Math.max(height(asAVL(node.left())), height(asAVL(node.right())));
            int bf = balance(node);

            if (bf > 1) { //left heavy
                AVLNode<T> L = asAVL(node.left());
                if (balance(L) < 0) // lr
                    rotateLeft(L);
                node = rotateRight(node); // ll
            }
            else if (bf < -1) { // right-heavy
                AVLNode<T> R = asAVL(node.right());
                if (balance(R) > 0)      // RL
                    rotateRight(R);
                node = rotateLeft(node); // RR
            }

            node = asAVL(node.parent());
        }
    }
	
    
//    public static void main(String[]args) {
//	AVLTree<Integer> tree = new AVLTree<>();
////	tree.insert(2349);
////	tree.insert(23);
////	tree.delete(234);
////	java.util.Random r = new java.util.Random();
////	int[] x = {17, 32, 75, 116, 131, 322, 369, 418, 427, 509, 512, 546, 565, 666, 710, 729, 737, 759, 847, 913};
//	for(int i=1;i<2001;i++)tree.insert(i);
////	tree.insert("2");
////	tree.insert("0");
////	tree.insert("1");
////	tree.insert("5");
////	tree.insert("4");
////	tree.insert("3");
////	tree.insert("7");
////	tree.insert("6");
////	tree.insert("8");
////	
//////	tree.delete("0");
//	
//	System.out.println("Root node after all insertions: "+tree.getRootNode().value());
//	System.out.println("inorder: "+tree.inorder());
//	System.out.println("preorder: "+tree.preorder());
//	System.out.println("postorder: "+tree.postorder());
//	
//	
//	for(int i=-4;i<10;i++)System.out.println(i+" "+tree.contains(i));
//	System.out.println(tree.height());
//	tree.delete(11);
//	System.out.println(tree.getRootNode());
//	
////	System.out.println(tree.getRootNode());
//}
}

//[[[[]0[]]1[[]2[]]]3[[[]4[]]5[[]6[]]]]7[[[[[]8[]]9[[]10[]]]11[[[]12[]]13[[]14[]]]]15[[[]16[]]17[[[]18[]]19[[]20[]]]]]
//[[[[]0[]]1[[]2[]]]3[[[]4[]]5[[]6[]]]]7[[[[[]8[]]9[[]10[]]]11[[[]12[]]13[[]14[]]]]15[[[]16[]]17[[[]18[]]19[[]20[]]]]]
