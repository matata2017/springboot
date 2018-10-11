package com.xxy.elasticsearch.leetcode;

/**
 * @author xxy
 * 单链表数据结构设计
 * 单链表中的每个结点不仅包含值，还包含链接到下一个结点的引用字段
 * 添加链表
 * 在prev之后添加链表
 * 1 使用给定值初始化当前链表
 * 2.将cur的next结点连接到prev的next结点
 * 3.将prev的next结点连接到cur结点
 * 删除链表结构
 * 1.找到当前结点的next结点
 * 2.从头遍历找到当前结点的prev结点
 * 3将prev结点连接到next结点
 */
public class SinglyListNode {
    int val;
    SinglyListNode nextNode;
    SinglyListNode (int x){
        this.val=x;
    }
    SinglyListNode (SinglyListNode nextNode){
        this.nextNode=nextNode;
    }

    SinglyListNode (int x,SinglyListNode nextNode){
        this.val=x;
        this.nextNode=nextNode;
    }
    /** Initialize your data structure here. */
    public SinglyListNode() {

    }
}
