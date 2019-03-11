package com.chouyarn.skiplist_17;

import java.util.Random;

/**
 * 跳表的一种实现方法。
 * 跳表中存储的是正整数，并且存储的是不重复的。
 * Created by chouyarn of BI on 2019/3/2
 */
public class SkipList {
    private static final int MAX_LEVEL = 16;
    private int levelCount = 1;
    private Node head = new Node();
    private Random r = new Random();
    public void insert(int value){
        int level = randomLevel();
        Node newNode = new Node();
        newNode.data = value;
        newNode.maxLevel = level;
        Node update[] = new Node[level];
        for (int i=0;i<level;++i){
            update[i] = head;
        }
        Node p = head;
        for(int i = level -1;i>=0;--i){
            while (p.forwards[i] != null && p.forwards[i].data < value){
                p = p.forwards[i];
            }
            update[i] = p;
        }

        // in search path node next node become new node forwords(next)
        for (int i = 0; i < level; ++i) {
            newNode.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = newNode;
        }

        // update node hight
        if (levelCount < level) levelCount = level;
    }
    // 随机 level 次，如果是奇数层数 +1，防止伪随机
    private int randomLevel(){
        int level = 1;
        for(int i = 1;i< MAX_LEVEL;++i){
            if (r.nextInt()%2 == 1){
                level++;
            }
        }
        return level;
    }

    public class Node{
        private int data = -1;
        private Node forwards[] = new Node[MAX_LEVEL];
        private int maxLevel = 0;
        @Override
        public String toString(){
            StringBuilder builder = new StringBuilder();
            builder.append("{data: ");
            builder.append(data);
            builder.append(";levels: ");
            builder.append(maxLevel);
            builder.append(" }");
            return builder.toString();
        }
    }

    public static void main(String[] args){
        SkipList skipList = new SkipList();
        System.out.println(skipList.randomLevel());

    }
}
