package leet150.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * Definition for singly-linked list.
 * class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
public class LinkedListCycle {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static boolean hasCycle(ListNode head) {
        ListNode cur = head;
        Set<ListNode> nextNodes = new HashSet<>();
        while (cur != null){
            cur = cur.next;
            if (nextNodes.contains(cur)) break;
            nextNodes.add(cur);
        }
        if(cur == null) return false;
        return true;
    }

    public static void main(String[] args) {

    }
}
