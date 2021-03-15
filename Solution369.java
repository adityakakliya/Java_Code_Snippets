/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    int carry = 1;
    public ListNode plusOne(ListNode head) {
        plusOneHelper(head);
        System.out.println(carry);
        if (carry == 1) {
            ListNode newHead = new ListNode(1);
            newHead.next = head;
            head = newHead;
        }
        return head;
    }
    
    public void plusOneHelper (ListNode head) {
        if (head == null) return;
        plusOneHelper(head.next);
        int newVal = head.val + carry;
        carry = newVal/10;
        head.val = newVal%10;
    }
}
