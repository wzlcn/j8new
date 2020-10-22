package com.wzl.j8new.datastructure.linkedlist;

/**
 * @author wangzhilong
 * @date 2020/8/4 15:54
 * @Description: 双向链表实现增删改查
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表测试开始~~");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        System.out.println("双向链表展示~~");
        doubleLinkedList.list();

        System.out.println("修改后的链表~~");
        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        doubleLinkedList.list();

        System.out.println("删除后的链表如下~~");
        doubleLinkedList.delete(4);
        doubleLinkedList.list();
    }
    static class DoubleLinkedList{
        //定义头节点，不存放具体的数据，指向第一个有数据的节点
        private HeroNode2 head = new HeroNode2(0,"","");

        //将节点加入到链表尾部，默认
        public void add(HeroNode2 heroNode){
            //因为头节点不能动，所以在定义一个辅助指针
            HeroNode2 temp = head;
            while (true) {
                //如果找到最后一个节点，结束while循环
                if (temp.next == null)
                    break;
                //没有找到最后一个，就将指针后移
                temp = temp.next;
            }
            //找到后将next指向新节点,新节点的pre指向最后一个节点
            temp.next = heroNode;
            heroNode.pre = temp;
        }

        //根据传入节点的编号修改节点内容
        public void update(HeroNode2 newHeroNode){
            //判断链表是否为空
            if (head.next == null) {
                System.out.println("链表为空");
                return;
            }
            //定义辅助节点，指向有数据的节点
            HeroNode2 temp = head.next;
            //定义是否找到该节点的标识
            boolean flag = false;
            while (true) {
                if (temp == null) {
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
        //单向链表只能找到待删除节点的其哪一个节点进行删除，双向链表可以找到删除节点实现自我删除
        public void delete(int no) {
            HeroNode2 temp = head.next;
            boolean flag = false;//是否找到标识
            while (true) {
                if (temp == null) {
                    break;
                } else if (temp.no == no) {
                    flag = true;//找到，要删除节点的前一个节点，不是它本身，否则无法删除
                    break;
                }
                temp = temp.next;
            }
            if (flag) {//找到
                //将要删除节点的前一个节点的next域指向删除节点的后一个节点
                temp.pre.next = temp.next;
                //将要删除节点的下一个节点的pre域指向删除节点的前一个节点
                //如果删除的是最后一个节点，这行代码不必执行
                //∵temp.next = null，会报空指针异常
                if (temp.next != null) {
                    temp.next.pre = temp.pre;
                }
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
            HeroNode2 temp = head.next;
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
    /*定义节点，包括data数据域和next指针域以及pre指针域*/
    static class HeroNode2 {
        int no;
        String name;
        String nickName;
        HeroNode2 next;//后指针
        HeroNode2 pre;//前指针

        HeroNode2(int no, String name, String nickName) {
            this.no = no;
            this.name = name;
            this.nickName = nickName;
        }

        @Override
        public String toString() {
            return "HeroNode2{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", nickName='" + nickName + '\'' +
                    '}';
        }
    }
}
