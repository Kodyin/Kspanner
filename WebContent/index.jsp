<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.*"%>
<%@ page import="graph.*"%>
<%@ page import="util.*"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My K-Spanner</title>
<style>
.link {
  stroke: #000;
}

.node {
  stroke: #fff;
}
</style>
</head>

<body>
<div>
<%
	KGraph g1 = new KGraph();

	String realPath=getServletContext().getRealPath("/");
	GraphLoader.loadGraph(g1,realPath + "data.csv");
	
	KGraph g2 = new KGraph();
	g2.kSpanner(g1, 2);

	GraphLoader.exportCsv(new File(realPath+"export.csv"), g2.getEdgeList());
	HashMap<Integer, KNode> nodeList = g1.getNodeList();
	KNode cur = nodeList.get(3);
	while(cur != null) {
		out.println(cur.getIndex());
		cur = cur.getNext();
	}  
	/* 
	HashMap<Integer, KNode> nodeList = g2.getNodeList();
	KNode cur = nodeList.get(8);
	while(cur != null) {
		out.println(cur.getIndex());
		cur = cur.getNext();
	} */
	//out.println("222222222222222");
	/* KGraph g2 = new KGraph();
	g2.kSpanner(g1, 2);
	HashMap<Integer, KNode> nodeList = g2.getNodeList();
	KNode cur = nodeList.get(8);
	while(cur != null) {
		out.println(cur.getIndex());
		cur = cur.getNext();
	} */
%>
</div>
<script src="//d3js.org/d3.v3.min.js"></script>
<script>

var width = 960,
    height = 500;

var svg = d3.select("body").append("svg")
    .attr("width", width)
    .attr("height", height);

var force = d3.layout.force()
    .size([width, height]);

d3.csv("data.csv", function(error, links) {
	  if (error) throw error;
	
	  var nodesByName = {};
	
	  // Create nodes for each unique source and target.
	  links.forEach(function(link) {
	    link.source = nodeByName(link.source);
	    link.target = nodeByName(link.target);
	  });
	
	  // Extract the array of nodes from the map by name.
	  var nodes = d3.values(nodesByName);
	
	  // Create the link lines.
	  var link = svg.selectAll(".link")
	      .data(links)
	    .enter().append("line")
	      .attr("class", "link");
	
	  // Create the node circles.
	  var node = svg.selectAll(".node")
	      .data(nodes)
	    .enter().append("circle")
	      .attr("class", "node")
	      .attr("r", 4.5)
	      .call(force.drag);
	
	  // Start the force layout.
	  force
	      .nodes(nodes)
	      .links(links)
	      .on("tick", tick)
	      .start();
	
	  function tick() {
	    link.attr("x1", function(d) { return d.source.x; })
	        .attr("y1", function(d) { return d.source.y; })
	        .attr("x2", function(d) { return d.target.x; })
	        .attr("y2", function(d) { return d.target.y; });
	
	    node.attr("cx", function(d) { return d.x; })
	        .attr("cy", function(d) { return d.y; });
	  }
	
	  function nodeByName(name) {
	    return nodesByName[name] || (nodesByName[name] = {name: name});
	  }
	});

</script>

</body>
</html>