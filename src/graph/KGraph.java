package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class KGraph {
	ArrayList<KEdge> edgeList =new ArrayList<KEdge>();
	HashMap<Integer, KNode> nodeList =new HashMap<Integer, KNode>();
    int vertexNum;
    int edgeNum;
    
    public KGraph(){
    		vertexNum = 0;
    		edgeNum = 0;
    }
    
    public void addEdge(int a, int b) {
    		if (!nodeList.containsKey(a)) {
    			KNode tmp = new KNode(a);
    			nodeList.put(a, tmp);
    			vertexNum++;
    		}
    		if (!nodeList.containsKey(b)) {
    			KNode tmp = new KNode(b);
    			nodeList.put(b, tmp);
    			vertexNum++;
    		}
    		KEdge newEdge = new KEdge(nodeList.get(a), nodeList.get(b));
    		edgeList.add(newEdge);
    		edgeNum++;
    		nodeList.get(a).addNeighbor(b);
    		nodeList.get(b).addNeighbor(a);
    }
    
    public int getDistance(int a, int b, int maxStep) {
    		Queue<Integer> q = new LinkedList<Integer>();
    		HashSet<Integer> visited= new HashSet<Integer>();
    		q.add(a);
    		visited.add(a);
    		int step = 0;
    		while(!q.isEmpty()) {
    			int size = q.size();
    			for(int i = 0; i<size; i++) {
    				KNode curr = nodeList.get(q.poll());
    				if(curr.getIndex() == b) return step;
    				while(curr != null) {
    					if(!visited.contains(curr.getIndex())) {
    						visited.add(curr.getIndex());
    						q.add(curr.getIndex());
    					}
    					curr = curr.getNext();
    				}
    			}
    			if(++step > maxStep) return -1;
    		}
    		return -1;
    }
    public void kSpanner(KGraph g, int k) {
    		HashMap<Integer, KNode> gnodeList = g.getNodeList();
    		for(Integer i: gnodeList.keySet()) nodeList.put(i, new KNode(i));
    		for(KEdge e: g.getEdgeList()) {
			if (this.getDistance(e.getFirstNode().getIndex(), e.getSecondNode().getIndex(), 2*k-1) == -1)
				this.addEdge(e.getFirstNode().getIndex(), e.getSecondNode().getIndex());
		}
    		
    }
    public int getVertexNum(){
    		return vertexNum;
    }
    
    public int getEdgeNum(){
		return edgeNum;
    }
    public HashMap<Integer, KNode> getNodeList(){
    		return nodeList;
    }
    public ArrayList<KEdge> getEdgeList(){
		return edgeList;
    }
}
