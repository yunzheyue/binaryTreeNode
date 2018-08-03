package com.example.app11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        foo1();

    }

    private void foo1() {

        //构建查找二叉树，就是将数据构建二分法的二叉树
        TreeNode rootNode = putSearchTree(53, null, null);
        TreeNode parent = putSearchTree(27, rootNode, rootNode);
        parent = putSearchTree(20, rootNode, parent);
        parent = putSearchTree(90, rootNode, parent);
        parent = putSearchTree(77, rootNode, parent);
        parent = putSearchTree(67, rootNode, parent);
        parent = putSearchTree(80, rootNode, parent);
        parent = putSearchTree(30, rootNode, parent);
        parent = putSearchTree(12, rootNode, parent);
        parent = putSearchTree(25, rootNode, parent);
        parent = putSearchTree(78, rootNode, parent);
        parent = putSearchTree(11, rootNode, parent);
        parent = putSearchTree(26, rootNode, parent);
        parent = putSearchTree(28, rootNode, parent);
        parent = putSearchTree(29, rootNode, parent);
        parent = putSearchTree(88, rootNode, parent);
        parent = putSearchTree(79, rootNode, parent);
        parent = putSearchTree(85, rootNode, parent);
        parent = putSearchTree(86, rootNode, parent);
        parent = putSearchTree(83, rootNode, parent);
        parent = putSearchTree(84, rootNode, parent);
        preBinaryNode(rootNode);

        TreeNode treeNode = searchTreeNode(80, rootNode);
        Log.e("TAG", "查到节点---------------------treeNode==" + treeNode.data + "   " + treeNode);
        deleteTreeNode(treeNode);
        preBinaryNode(rootNode);
    }

    private boolean deleteTreeNode(TreeNode rootNode) {
        //进行判断
        if (rootNode == null) {
            return false;
        }
        Log.e("TAG", "rootNode===" + rootNode.data + "  " + rootNode + "    parent==" + rootNode.parent.data + "   " + rootNode.parent);
        //如果当前为叶子节点
        if (rootNode.leftChild == null && rootNode.rightChild == null) {
            //直接置空是不行的，这里其实还是重新在栈中创建了变量，
            //虽然指向的地址相同，但是在这就是将当前变量的栈中地址置空，其他变量还是指向堆中的数据
            //这里仅仅是改变了当前变量的赋值,这时候将当前变量置空是不能修改数据的，需要父节点重新进行指向
//            rootNode = null;
            //这里可能是左节点 也有可能是右节点
            //左节点
            if (rootNode.data < rootNode.parent.data) {
                rootNode.parent.leftChild = null;
            } else {//右节点
                rootNode.parent.rightChild = null;
            }
            return true;
        }

        //如果当前只有左节点，这里有两种情况
        if (rootNode.leftChild != null && rootNode.rightChild == null) {
            //当前节点是父节点的左孩子
            if (rootNode.parent.data > rootNode.data) {
                rootNode.parent.leftChild = rootNode.leftChild;
            } else {//当前节点是父节点的右孩子
                rootNode.parent.rightChild = rootNode.leftChild;
            }
            return true;
        }
        //如果当前只有右节点
        if (rootNode.leftChild == null && rootNode.rightChild != null) {
            //当前节点是父节点的左孩子
            if (rootNode.parent.data > rootNode.data) {
                rootNode.parent.leftChild=rootNode.rightChild;
            }else{//当前节点是父节点的右孩子
                rootNode.parent.rightChild=rootNode.rightChild;
            }
            return true;
        }
        //如果当前有左右节点,这时候需要节点的移动，可以用左子树的最大值进行代替，或是用右子树的最小值进行代替
        //当前用左子树的最小值进行代替,这样如果这个节点有右节点，则成为此节点的左孩子
        if (rootNode.leftChild != null && rootNode.rightChild != null) {
            TreeNode tempTreeNode = rootNode.rightChild;
            while (tempTreeNode != null) {
                if (tempTreeNode.leftChild != null) {
                    tempTreeNode = tempTreeNode.leftChild;
                } else {
                    //找到右子树中最左边的节点了，就跳出循环
                    break;
                }
            }
            rootNode.data = tempTreeNode.data;
            //如果rootNode和tempTreeNode是相邻的节点
            if(rootNode.rightChild==tempTreeNode){
                rootNode.rightChild=null;
            }else{
                //这时候可能有右子树，那么将右子树的第一个节点占据当前的节点位置
                tempTreeNode.parent.leftChild = tempTreeNode.rightChild;
            }

            return true;
        }
        return true;
    }


    private TreeNode searchTreeNode(int data, TreeNode rootNode) {
        if (rootNode == null) {
            return null;
        }
        while (rootNode != null) {
            if (data < rootNode.data) {
                rootNode = rootNode.leftChild;
            } else if (data > rootNode.data) {
                rootNode = rootNode.rightChild;
            } else {
                //找到这个节点
                return rootNode;
            }
        }

        return null;
    }


    private TreeNode putSearchTree(int data, TreeNode rootNode, TreeNode parent) {

        if (rootNode == null) {
            rootNode = new TreeNode(data, parent);
            return rootNode;
        }
        TreeNode temRootNode = rootNode;
        //放在左子树
        if (data < rootNode.data) {
            rootNode.leftChild = putSearchTree(data, rootNode.leftChild, rootNode);
            //右子树
        } else if (data > rootNode.data) {
            rootNode.rightChild = putSearchTree(data, rootNode.rightChild, rootNode);
            //如果数值相等
        } else {
            return temRootNode;
        }
        return temRootNode;
    }

    private void preBinaryNode(TreeNode node) {
        if (node != null) {
            Log.e("TAG", "先序遍历==" + node.data + "    " + node);
            preBinaryNode(node.leftChild);
            preBinaryNode(node.rightChild);
        }
    }

    class TreeNode {
        TreeNode parent;
        TreeNode leftChild;
        TreeNode rightChild;
        int data;

        public TreeNode(int data, TreeNode parent) {
            this.data = data;
            this.parent = parent;
        }
    }
}
