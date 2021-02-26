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

/*
pq - custom comparator

recursively merge lists




*/
class Solution {
    public ListNode mergeKLists(ListNode[] listNodes) {
        if (listNodes.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return Integer.compare(o1.val, o2.val);
            }
        });

        ListNode result = new ListNode(-1);
        ListNode resultHead = result;
        for (ListNode listNode : listNodes) {
            if (listNode != null) {
                minHeap.add(listNode);
            }
        }

        while (!minHeap.isEmpty()) {
            ListNode nextNode = minHeap.poll();
            if (nextNode.next != null) {
                minHeap.add(nextNode.next);
            }
            result.next = nextNode;
            result = result.next;
        }
        // while (resultHead != null) {
        //     System.out.print(resultHead.val + " ");
        //     resultHead = resultHead.next;
        // }
        return resultHead.next;
    }
}