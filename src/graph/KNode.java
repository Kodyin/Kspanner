package graph;

public class KNode {
	int index;
	KNode next;
	int degree;
	public KNode() {
		degree = 0;
	}
	public KNode(int n) {
		index = n;
		degree = 0;
		next = null;
	}
	public void addNeighbor(int n) {
		KNode newnext = new KNode(n);
		newnext.next = this.next;
		this.next = newnext;
		degree++;
	}
	public int getIndex() {
		return index;
	}
	public KNode getNext() {
		return next;
	}
}
