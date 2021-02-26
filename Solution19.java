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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first = head;
        ListNode second = head;
        int l = 0;
        for (int i = 0; i < n; i++, l++, second = second.next) {
        }
        if (second == null) {
            return l == n ? first.next : null;
        }
        while (second.next != null) {
            second = second.next;
            first = first.next;
        }

        first.next = first.next.next;

        return head;
    }
}