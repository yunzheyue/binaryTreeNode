package com.example.app11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        foo1();
        foo2();
    }

    //构建查找二叉树，就是将数据构建二分法的二叉树
    private void foo2() {
        TreeNode1 rootNode = putSearchTree(53, null);
        putSearchTree(27, rootNode);
        putSearchTree(20, rootNode);
        putSearchTree(90, rootNode);
        putSearchTree(77, rootNode);
        putSearchTree(67, rootNode);
        putSearchTree(80, rootNode);
        putSearchTree(30, rootNode);
        putSearchTree(12, rootNode);
        putSearchTree(25, rootNode);
        preBinaryNode1(rootNode);
    }

    private TreeNode1 putSearchTree(int data, TreeNode1 parentNode) {

        if (parentNode == null) {
            parentNode = new TreeNode1(data);
            return parentNode;
        }
        TreeNode1 rootNode = parentNode;
        //放在左子树
        if (data < parentNode.data) {
            parentNode.leftChild = putSearchTree(data, parentNode.leftChild);
            //右子树
        } else if (data > parentNode.data) {
            parentNode.rightChild = putSearchTree(data, parentNode.rightChild);
            //如果数值相等
        } else {
            return rootNode;
        }
        return rootNode;
    }

    private void preBinaryNode1(TreeNode1 node) {
        if (node != null) {
            Log.e("TAG", "先序遍历==" + node.data);
            preBinaryNode1(node.leftChild);
            preBinaryNode1(node.rightChild);
        }
    }

    //将先序遍历转化成二叉树
    private void foo1() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("A");
        strings.add("B");
        strings.add("D");
        strings.add("#");
        strings.add("#");
        strings.add("E");
        strings.add("#");
        strings.add("#");
        strings.add("C");
        strings.add("F");
        strings.add("#");
        strings.add("#");
        strings.add("G");
        strings.add("#");
        strings.add("H");
        strings.add("#");
        strings.add("#");

        TreeNode binaryNode = createBinaryNode(0, strings);
        preBinaryNode(binaryNode);

    }

    //    将先序遍历转化成二叉树
    private TreeNode createBinaryNode(int index, ArrayList<String> strings) {
        if (strings.size() <= 0) {
            return null;
        }
        TreeNode treeNode;
        if ("#".equals(strings.get(0))) {
            //遇见终止符要进行删除，否则就会一直在这判断
            strings.remove(0);
            return null;
        }
        //其他的都要进行创建节点
        treeNode = new TreeNode(strings.get(0));
        strings.remove(0);
        Log.e("TAG", "index===" + index);
        treeNode.leftChild = createBinaryNode(++index, strings);
        treeNode.rightChild = createBinaryNode(++index, strings);
        return treeNode;
    }

    private void preBinaryNode(TreeNode node) {
        if (node != null) {
            Log.e("TAG", "先序遍历==" + node.data);
            preBinaryNode(node.leftChild);
            preBinaryNode(node.rightChild);
        }
    }

    class TreeNode1 {
        public int data;
        public TreeNode1 leftChild = null;
        public TreeNode1 rightChild = null;

        public TreeNode1(int data) {
            this.data = data;
        }
    }

    class TreeNode {
        public String data;
        public TreeNode leftChild;
        public TreeNode rightChild;

        public TreeNode(String data) {
            this.data = data;
        }
    }


}
