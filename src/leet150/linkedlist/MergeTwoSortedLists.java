package leet150.linkedlist;

public class MergeTwoSortedLists {
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode fakeHead = new ListNode(0);
        ListNode temp = fakeHead;

        int min = 0;
        int a = 101;
        int b = 101;

        while (list1 != null || list2 != null) {
            if (list1 != null) a = list1.val; // 2
            else a = 101;
            if (list2 != null) b = list2.val; // 1
            else b = 101;
            if (a <= b && list1 != null) {
                min = a;
                list1 = list1.next;
            } else {
                min = b;
                list2 = list2.next;
            }
            temp.next = new ListNode(min);
            temp = temp.next;
        }

        return fakeHead.next;
    }

    public static void main(String[] args) {
        ListNode node = mergeTwoLists(new ListNode(1), new ListNode(2));
        System.out.println(node.val);
    }
}
