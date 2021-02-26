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
    public ListNode partition(ListNode head, int x) {
        ListNode leftPartition = new ListNode();
        ListNode rightPartition = new ListNode();
        ListNode dummyLeft = leftPartition;
        ListNode dummyRight = rightPartition;

        while (head != null) {
            if (head.val < x) {
                leftPartition.next = new ListNode(head.val);
                leftPartition = leftPartition.next;
            } else {
                rightPartition.next = new ListNode(head.val);
                rightPartition = rightPartition.next;
            }
            head = head.next;
        }

        leftPartition.next = dummyRight.next;
        return dummyLeft.next;
    }
}