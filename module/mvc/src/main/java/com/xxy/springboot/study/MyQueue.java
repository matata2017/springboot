package com.xxy.springboot.study;


/**
 * @author xxy
 * 环形队列学习
 */
public class MyQueue {

    /**
     * 队列的总长度
     */
    private Integer MAX_QUEUE=10;

    /**
     * 队列头
     */
    int front=0;
    /**
     * 队列尾
     */
    int rear=0;
    /**
     * 队列数组
     */
    int queueList[]= new int[MAX_QUEUE];;


    /**
     * 创建队列
     */
    public  void  createQueue(){
    }

    /**
     * 销毁队列
     */
    public void  destroyQueue(){

    }

    /**
     * 队列判断空
     */
    public boolean  isEmtyQueue(){
        if(front==rear){
            return true;
        }
        return false;
    }

    /**
     * 队列长度判断
     */
    public void  lenthQueue(){

    }

    /**
     * 判断队列是否是满的
     * @return
     */
    public boolean isFull(){
        if(((rear+1)%MAX_QUEUE)==front){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * 入列
     */
    public void enQueue(){

    }

    /**
     * 出列
     */
    public void deQueue(){

    }


    /**
     * 遍历队列
     */
    public void traverseQueue(){

    }

}
