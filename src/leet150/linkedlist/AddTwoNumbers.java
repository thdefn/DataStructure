package leet150.linkedlist;

public class AddTwoNumbers {
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

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();

        int carry = 0;
        int digit = 0;
        int vall1 = 0;
        int vall2 = 0;

        ListNode cur = head;
        ListNode curl1 = l1;
        ListNode curl2 = l2;
        vall1 = curl1.val;
        vall2 = curl2.val;
        digit = (vall1 + vall2 + carry) % 10;
        carry = (vall1 + vall2 + carry) / 10;
        cur.val = digit;

        curl1 = curl1.next;
        curl2 = curl2.next;

        while (curl1 != null || curl2 != null) {
            vall1 = (curl1 == null) ? 0 : curl1.val;
            vall2 = (curl2 == null) ? 0 : curl2.val;
            digit = (vall1 + vall2 + carry) % 10;
            carry = (vall1 + vall2 + carry) / 10;

            ListNode temp = new ListNode(digit);
            cur.next = temp;
            cur = temp;
            curl1 = curl1 == null ? null : curl1.next;
            curl2 = curl2 == null ? null : curl2.next;
        }
        if (carry > 0) {
            ListNode temp = new ListNode(carry);
            cur.next = temp;
        } else {
            cur.next = null;
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode node = addTwoNumbers(new ListNode(1, new ListNode(1, null)), new ListNode(2, null));
        System.out.println(node.next.val);
    }

}
