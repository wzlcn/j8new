package com.wzl.j8new.datastructure.linkedlist;

import java.util.Stack;

/**
 * @author wangzhilong
 * @date 2020/7/30 16:49
 * @Description: 单链表实现增删改查等操作
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        //加入
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);
        System.out.println("原来的链表如下~");
        singleLinkedList.list();
        System.out.println("逆序打印链表结果如下~");
        reversePrint(singleLinkedList.head);
        /*System.out.println("反转后链表如下~");
        reverseList(singleLinkedList.head);
        singleLinkedList.list();*/
        /*//按顺序加入
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero2);
        //显示链表
        singleLinkedList.list();
        //修改
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        singleLinkedList.update(newHeroNode);
        System.out.println("修改后内容");
        singleLinkedList.list();
        //删除
        singleLinkedList.delete(1);
        System.out.println("删除后内容");
        singleLinkedList.list();*/
    }

    //合并两个有序链表1->5->6,1->2->3->7新链表1->1->2->3->5->6->7
    public static HeroNode mergeTwoLists(HeroNode l1, HeroNode l2) {
        HeroNode prehead = new HeroNode(0,"","");
        HeroNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.no <= l2.no) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;
        return prehead.next;
    }
    //逆序打印链表，各大厂高频面试题
    //逆序思想一般都要用到栈，栈这种数据结构是LIFO(LAST IN FIRST OUT)后进先出的，与链表FIFO先进先出相反
    //栈的入栈出栈过程可以参考枪的弹夹上子弹的过程，上子弹就是入栈(弹夹可看做栈)，开枪就是出栈
    public static void reversePrint(HeroNode head) {
        //判断链表是否为空
        if (head.next == null) {
            return;
        }
        //初始化栈，将各个节点压入栈中
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while (cur != null) {
            stack.add(cur);//入栈
            cur = cur.next;//节点后移
        }
        //栈不为空开始打印
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
    //反转链表，各大厂必考面试题
    public static void reverseList(HeroNode head) {
        //如果当前节点为空或者只有一个节点，不必反转
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义一个辅助指针，帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;//指向当前[cur]节点的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在reverseHead的最前端
        while (cur != null) {
            next = cur.next;//先保存当前节点的下一个节点，因为后面需要使用
            cur.next = reverseHead.next;//重新组装cur,将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur;//将cur连接到新的链表上
            cur = next;//让cur后移
        }
        //将head.next指向reverseHead.next，实现链表的反转
        head.next = reverseHead.next;
    }
    static class SingleLinkedList{
        //定义头节点，不存放具体的数据，指向第一个有数据的节点
        private HeroNode head = new HeroNode(0,"","");

        //将节点加入到链表
        //找到当前链表的左后一个节点，将这个节点的next指向新的节点
        public void add(HeroNode heroNode){
            //因为头节点不能动，所以在定义一个辅助指针
            HeroNode temp = head;
            while (true) {
                //如果找到最后一个节点，结束while循环
                if (temp.next == null)
                    break;
                //没有找到最后一个，就将指针后移
                temp = temp.next;
            }
            //找到后将next指向新节点
            temp.next = heroNode;
        }

        //按照编号顺序添加英雄
        public void addByOrder(HeroNode heroNode) {
            //辅助指针
            HeroNode temp = head;
            //节点标识，默认false，在链表中不存在该元素
            boolean flag = false;
            while (true) {
                //判断链表是否为空
                if (temp.next == null) {
                    break;//已经到达链表尾部，或者要插入的节点就在链表尾，结束循环
                } else if (temp.next.no > heroNode.no) {
                    break;//找到要插入的位置，在temp和temp.next之间，结束循环
                } else if (temp.next.no == heroNode.no){
                    flag = true;//链表中已有该元素，改变flag并结束循环
                    break;
                }
                temp = temp.next;//若不满足上述条件，遍历链表，继续找要插入的位置
            }
            if (flag) {
                System.out.printf("要插入的英雄%d已经存在，插入失败\n" ,heroNode.no);
            } else {
                //下面两步为什么不能交换位置？
                heroNode.next = temp.next;//新节点的next域指向temp原来指向的节点
                temp.next = heroNode;//temp的next域指向新节点
            }
        }
        //根据传入节点的编号修改节点内容
        public void update(HeroNode newHeroNode){
            //判断链表是否为空
            if (head.next == null) {
                System.out.println("链表为空");
                return;
            }
            //定义辅助节点，指向有数据的节点
            HeroNode temp = head.next;
            //定义是否找到该节点的标识
            boolean flag = false;
            while (true) {
                if (temp.next == null) {
                    break;//已经遍历完节点
                } else if (temp.no == newHeroNode.no) {
                    flag = true;//找到该节点后结束循环
                    break;
                }
                temp = temp.next;//未找到就遍历继续找
            }
            if (flag) {//如果存在修改的节点，替换成传入的内容
                temp.name = newHeroNode.name;
                temp.nickName = newHeroNode.nickName;
            } else {
                System.out.printf("没有找到要修改的元素%d",newHeroNode.no);
            }
        }
        //根据传入的no删除节点
        public void delete(int no) {
            HeroNode temp = head;
            boolean flag = false;//是否找到标识
            while (true) {
                if (temp.next == null) {
                    break;
                } else if (temp.next.no == no) {
                    flag = true;//找到，要删除节点的前一个节点，不是它本身，否则无法删除
                    break;
                }
                temp = temp.next;
            }
            if (flag) {//找到，将要删除元素的前一个节点的next域指向它next(要删除节点)的next(要删除的后一个)节点
                temp.next = temp.next.next;
            } else {
                System.out.printf("没有找到要删除的元素%d",no);
            }
        }
        //显示链表数据[遍历]
        public void list(){
            //判断链表是否为空
            if (head.next == null){
                System.out.println("链表为空");
                return;
            }
            //因为头节点不能动，我们还是需要一个辅助指针来遍历
            HeroNode temp = head.next;
            while (true) {
                //判断链表是否为空
                if (temp == null) {
                    break;
                }
                //不为空，输出节点信息
                System.out.println(temp);
                //节点后移
                temp = temp.next;
            }
        }
    }
    /*定义节点，包括data数据域和next指针域*/
    static class HeroNode {
        int no;
        String name;
        String nickName;
        HeroNode next;

        HeroNode(int no, String name, String nickName) {
            this.no = no;
            this.name = name;
            this.nickName = nickName;
        }

        @Override
        public String toString() {
            return "HeroNode{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", nickName='" + nickName + '\'' +
                    '}';
        }
    }
}
