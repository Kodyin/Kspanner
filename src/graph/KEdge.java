package graph;

public class KEdge {
	KNode firstNode, secondNode;
	public KEdge(KNode a, KNode b){
		firstNode = a;
		secondNode = b;
	}
	public KNode getFirstNode() {
		return firstNode;
	}
	public KNode getSecondNode() {
		return secondNode;
	}
}
