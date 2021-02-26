/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution430 {
    public Node flatten(Node head) {
        if (head == null) return head;
        Node headRef = head;
        while (headRef != null) {
            Node nextHead = headRef.next;
            if (headRef.child != null) {
                Node childListHead = flatten(headRef.child);
                Node childListTail = getTail(childListHead);
                headRef.child = null;
                headRef.next = childListHead;
                childListHead.prev = headRef;
                childListTail.next = nextHead;
                if (nextHead != null) nextHead.prev = childListTail;
            }
            headRef = nextHead;
        }
        return head;
    }
    private Node getTail (Node childListHead) {
        Node ref = childListHead;
        while (ref.next != null) {
            ref = ref.next;
        }
        return ref;
    }
}