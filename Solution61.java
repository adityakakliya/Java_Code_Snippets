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
    public ListNode rotateRight(ListNode head, int k) {
        int length = 0;
        ListNode tempHead = head;

        while (tempHead != null) {
            length++;
            tempHead = tempHead.next;
        }
        if (length == 0 || k == 0 || k % length == 0) {
            return head;
        }

        k = k % length;
        ListNode nodeBeforeNewHead = head;
        tempHead = head;
        while (k-- > 0) {
            tempHead = tempHead.next;
        }

        while (tempHead.next != null) {
            tempHead = tempHead.next;
            nodeBeforeNewHead = nodeBeforeNewHead.next;
        }

        ListNode newHead = nodeBeforeNewHead.next;
        tempHead.next = head;
        nodeBeforeNewHead.next = null;
        return newHead;
    }
}

/*


1 2 3 4 5
!   t
n
4 5 1 2 3

k = 6
5 1 2 3 4


*/