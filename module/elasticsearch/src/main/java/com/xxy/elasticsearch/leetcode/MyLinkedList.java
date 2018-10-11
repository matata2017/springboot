package com.xxy.elasticsearch.leetcode;

/**
 * @author xxy
 * 單鏈表
 */
public class MyLinkedList {
    /**
     * 头结点
     */
    private SinglyListNode head;
    /**
     * 链表的长度
     */
    private int size = 0;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
        head = new SinglyListNode();
    }

//    public MyLinkedList(int val) {
//        size++;
//        head = new SinglyListNode(val);
//    }
//        public void setHead(int val) {
//        size++;
//        head.val = val;
//    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index < 0 || index > size - 1) {
            return -1;
        }
        SinglyListNode node = getNodeByIndex(index);
        if (null == node) {
            return -1;
        }
        return node.val;
    }

    public SinglyListNode getNodeByIndex(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        int p = 0;
        SinglyListNode temp = new SinglyListNode(head.nextNode);
        while (p <= index) {
            temp = temp.nextNode;
            p++;
        }
        return temp;
    }


    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
//        if (size == 0) {
//            head.val = val;
//        }
        //取原来头结点的下一个结点
        SinglyListNode first = head.nextNode;
        //将头结点的下个结点设置成新的结点，新结点的下个结点是原来的第一个结点
        head.nextNode = new SinglyListNode(val, first);
        //链表长度+1
        size++;
    }


    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
//        SinglyListNode newTail=new SinglyListNode(val);
//        SinglyListNode tail= getNodeByIndex(size);
//        tail.nextNode=newTail;
//        size++;
        size++;
        SinglyListNode temp = new SinglyListNode(head.nextNode);
        while (temp.nextNode != null) {
            temp = temp.nextNode;
        }
        temp.nextNode = new SinglyListNode(val, null);

    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        size++;
        if (index == 0) {
            head.nextNode = new SinglyListNode(val, null);
        } else {
            SinglyListNode prev = getNodeByIndex(index-1);
            if (null == prev) {
                return;
            }
            SinglyListNode next = prev.nextNode;
            SinglyListNode newNode = new SinglyListNode(val, next);
            prev.nextNode = newNode;
        }
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        SinglyListNode deleteNode=null;
        if (index==0){
            if (null==head.nextNode){
                return;
            }
            head.nextNode=head.nextNode.nextNode;
            size--;
        }
        if (index > 0 && index <= size - 1) {
            SinglyListNode  prev=getNodeByIndex(index-1);
            deleteNode = getNodeByIndex(index);
            prev.nextNode=deleteNode.nextNode;
            size--;
        }
    }
}
