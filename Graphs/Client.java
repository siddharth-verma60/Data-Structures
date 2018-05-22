package Graphs;

public class Client {

	public static void main(String[] args) {
		Graph graph=new Graph();
		
		graph.AddVertex("A");
		graph.AddVertex("B");
		graph.AddVertex("C");
		graph.AddVertex("D");
		graph.AddVertex("E");
		graph.AddVertex("F");
		graph.AddVertex("G");
		
		graph.addEdge("A", "B");
		graph.addEdge("A", "D");
		graph.addEdge("D", "C");
		graph.addEdge("C", "B");
		graph.addEdge("D", "E");
		graph.addEdge("E", "F");
		graph.addEdge("E", "G");
		graph.addEdge("G", "F");
		
		graph.display();
//		System.out.println(graph.numEdges());
//		System.out.println(graph.numVertices());
//		
		graph.removeVertex("E");
//		graph.display();
//		System.out.println(graph.numEdges());
//		System.out.println(graph.numVertices());
		
//		System.out.println(graph.hasPathFromTo("D", "G"));

	}

}
