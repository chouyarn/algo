package com.chouyarn.tree_24;

/**
 * Created by chouyarn of BI on 2019/3/2
 */
public class BinarySearchTree {
    private Node tree ;

    public Node find(int data){
        Node p = tree;
        while (p != null){
            if (data < p.data) p = p.left;
            else if(data > p.data) p = p.right;
            else return  p;
        }
        return null;
    }

    public void insert(int data){
        if (tree == null){
            tree = new Node(data);
            return;
        }
        Node p = tree;
        while (p != null){
            if (data <= p.data){
                if (p.left == null){
                    p.left = new Node(data);
                    return;
                }
                p = p.left;
            }else{
                if (p.right == null){
                    p.right = new Node(data);
                    return;
                }
                p = p.right;
            }
        }
    }
    public void delete(int data){
        Node p = tree;
        Node pp = null;
        while (p != null && p.data != data){
            pp = p;
            if (data > p.data) p = p.right;
            else p = p.right;
        }

        if (p == null) return;
        if (p.left != null && p.right != null){
            Node minP = p.right;
            Node minPP = p;
            while (minP.left != null){
                minPP = minP;
                minP = minP.left;
            }
            p.data = minP.data;
            p = minP;
            pp = minPP;
        }
        Node child;
        if(p.left != null) child = p.left;
        else if (p.right != null) child = p.right;
        else child = null;

        if (pp == null) tree = child;
        else if (pp.left == p) pp.left = child;
        else pp.right = child;
    }


    public void delete1(int data){
        Node p = tree;
        Node pp = null;
        while (p != null && p.data != data){
            pp = p;
            if (data < p.data) p = p.left;
            else p = p.right;
        }
        if (p == null) return;
        if (p.left != null && p.right != null){
            Node minP = p.right;
            Node minPP = p;
            while (minP.left != null){
                minPP = minP;
                minP = minP.left;
            }
            p.data = minP.data;
            p = minP;
            pp = minPP;
        }

        Node child;
        if (p.left != null) child = p.left;
        if (p.right != null) child = p.right;
        else child = null;

        if (pp == null) tree = child;
        else if (pp.left == p) pp.left = child;
        else pp.right = child;
    }
    public Node findMin(){
        if (tree == null) return null;
        Node p = tree;
        while (p.left != null){
            p = p.left;
        }
        return p;
    }
    public Node findMax(){
        if (tree == null) return null;
        Node p = tree;
        while (p.right != null){
            p = p.right;
        }
        return p;
    }
    public void inOrder(Node root){
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.data+" ");
        inOrder(root.right);
    }

    public static class Node{
        private int data;
        private Node left;
        private Node right;
        public Node(int data){
            this.data = data;
        }
    }

    public static void main(String[] args){
//        BinarySearchTree b= new BinarySearchTree();
//        b.insert(1);
//        b.insert(2);
//        b.insert(3);
//        Node p = b.tree;
//        b.inOrder(p);
        System.out.print(Runtime.getRuntime().availableProcessors());
    }
}
