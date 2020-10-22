package com.wzl.j8new.leetcode;

/**
 * @author wangzhilong
 * @date 2020/7/30 13:30
 * @Description: 删除链表倒数第n个元素
 */

/*给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
        示例：
        给定一个链表: 1->2->3->4->5, 和 n = 2.
        当删除了倒数第二个节点后，链表变为 1->2->3->5.
        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list*/
public class RemoveNthFromEnd {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            this.val = x;
        }

        public static ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode first = dummy;
            ListNode second = dummy;
            // Advances first pointer so that the gap between first and second is n nodes apart
            for (int i = 1; i <= n + 1; i++) {
                first = first.next;
            }
            // Move first to the end, maintaining the gap
            while (first != null) {
                first = first.next;
                second = second.next;
            }
            second.next = second.next.next;
            return dummy.next;
        }

        public static void main(String[] args) {
            ListNode node1 = new ListNode(1);
            ListNode node2 = new ListNode(2);
            ListNode node3 = new ListNode(3);
            ListNode node4 = new ListNode(4);
            ListNode node5 = new ListNode(5);
            node1.next = node2;
            node2.next = node3;
            node3.next = node4;
            node4.next = node5;

            ListNode listNode = removeNthFromEnd(node1, 2);
            while(listNode != null) {
                System.out.print(listNode.val + " ");
                listNode = listNode.next;
            }
        }
    }
}
