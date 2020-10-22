package com.wzl.j8new.datastructure.linkedlist;

/**
 * @author wangzhilong
 * @date 2020/8/5 13:35
 * @Description: 环形链表，约瑟夫(Josephus)问题
 */
/*N个人围成一圈，从第K个开始报数，报到第M个将被杀掉，最后剩下一个，其余人都将被杀掉
例N=5,K=1,M=2时的顺序为2->4->1->5->3*/
public class CircleLinkedList {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.add(5);
        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.countBoy(1,2,5);//2->4->1->5->3
    }

    //创建一个环形单向链表
    static class CircleSingleLinkedList{
        private Boy first = null;
        //nums代表加入多少个节点
        public void add(int nums){
            //校验nums,至少得有一个节点
            if (nums < 1){
                System.out.println("nums数值不正确");
                return;
            }
            //辅助指针，帮助构建环形链表
            Boy curBoy = null;
            for (int i = 1; i <= nums; i++) {
                //构建小孩节点
                Boy boy = new Boy(i);
                if (i == 1) {
                    //让初始化节点指向第一个节点
                    first = boy;
                    //让第一个节点的next域指向自身，构成环形链表
                    first.setNext(first);
                    curBoy = first;
                } else {
                    curBoy.setNext(boy);//添加一个新节点
                    boy.setNext(first);//构成环
                    curBoy = boy;
                }
            }
        }

        //遍历
        public void showBoy() {
            if (first == null) {
                System.out.println("没有任何小孩");
                return;
            }
            //first不能动，构建一个辅助节点来遍历
            Boy curBoy = first;
            while (true) {
                System.out.printf("当前小孩编号为：%d\n",curBoy.getNo());
                if (curBoy.getNext() == first) {//已遍历完
                    break;
                }
                curBoy = curBoy.next;//没有遍历完，curBoy后移一位
            }
        }

        /**
         *
         * @param startNo 从第几个小孩开始数
         * @param countNo 数到第几个小孩(出圈)
         * @param nums    总共有多少个小孩
         */
        public void countBoy(int startNo,int countNo,int nums){
            if (first == null || startNo < 1 || startNo > nums) {
                System.out.println("输入的数据有误，请重新输入");
                return;
            }
            //创建一个辅助节点，指向当前链表的最后一个节点
            Boy helper = first;
            while (true) {
                if (helper.getNext() == first) {//已经到达最后一个节点
                    break;
                }
                helper = helper.getNext();
            }
            //小孩报数前先让helper和first向前移动startNo - 1次
            for (int i = 0; i < startNo - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //当小孩报数时，让helper和first同时移动countNo - 1次，并让first指向的节点(即小孩)出圈
            while (true) {
                if (helper == first) {//当圈中还剩最后一个小孩，结束循环
                    break;
                }
                for (int i = 0; i < countNo - 1; i++) {
                    first = first.getNext();
                    helper = helper.getNext();
                }
                //这时first指向的小孩就是要出圈的小孩
                System.out.printf("当前小孩%d出圈\n",first.getNo());
                //first节点出圈
                first = first.getNext();
                //重新连接成环形链表
                helper.setNext(first);
            }
            System.out.printf("最后圈中剩余小孩%d\n",first.getNo());
        }
    }
    static class Boy {
        private int no;
        private Boy next;
        public Boy(int num) {
            this.no = num;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public Boy getNext() {
            return next;
        }

        public void setNext(Boy next) {
            this.next = next;
        }
    }
}
