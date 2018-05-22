package Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Graph {
	private static class Edge {
		Vertex one;
		Vertex two;

		// private static HashMap<String, Edge> edges=new HashMap<>();

		Edge(Vertex one, Vertex two) {
			this.one = one;
			this.two = two;
		}

	}

	private class Vertex {
		String name;
		HashMap<Vertex, Edge> adjacentVertices;

		Vertex(String name) {
			this.name = name;
			this.adjacentVertices = new HashMap<>();
		}

		public boolean isAdjacentTo(Vertex vtx) {
			return this.adjacentVertices.containsKey(vtx);
		}

		public void addEdgeWith(Edge edge) {
			Vertex vtx = edge.one.equals(this) ? edge.two : edge.one;
			if (this.isAdjacentTo(vtx)) {
				return;
			}
			this.adjacentVertices.put(vtx, edge);
		}

		public void removeEdgeWith(Vertex vtx) {
			if (!this.isAdjacentTo(vtx)) {
				return;
			}
			this.adjacentVertices.remove(vtx);
		}

		public ArrayList<Vertex> getAdjacentVertices() {
			ArrayList<Vertex> retval = new ArrayList<>();

			Set<Vertex> allKeys = this.adjacentVertices.keySet();

			for (Vertex val : allKeys) {
				retval.add(val);
			}

			return retval;

		}

		public String toString() {
			String retval = "";

			retval += this.name + " => ";

			Set<Vertex> allKeys = this.adjacentVertices.keySet();

			for (Vertex val : allKeys) {
				retval += val.name + ", ";
			}
			retval += "END\n";
			return retval;
		}

		public int hashCode() {
			return this.name.hashCode();
		}

		public boolean equals(Object other) {
			Vertex otherVrtx = (Vertex) other;
			return this.name.equals(otherVrtx.name);
		}
	}

	private ArrayList<Vertex> vertices;

	public Graph() {
		this.vertices = new ArrayList<>();
	}

	public int numVertices() {
		return this.vertices.size();
	}

	public void AddVertex(String name) {
		if (this.containsVertex(name)) {
			return;
		}
		this.vertices.add(new Vertex(name));
	}

	public void removeVertex(String name) {
		if (!this.containsVertex(name)) {
			return;
		}
		Vertex vtbr = this.getVertex(name);

		ArrayList<Vertex> neighbours = vtbr.getAdjacentVertices();

		for (Vertex vtx : neighbours) {
			vtx.removeEdgeWith(vtbr);
		}

		this.vertices.remove(vtbr);
	}

	public boolean containsVertex(String name) {
		return this.getVertex(name) != null;
	}

	private Vertex getVertex(String name) {
		Vertex retval = null;
		for (Vertex vtx : this.vertices) {
			if (vtx.name.equals(name)) {
				retval = vtx;
				break;
			}
		}
		return retval;
	}

	public int numEdges() {
		int retval = 0;

		for (Vertex val : this.vertices) {
			retval += val.adjacentVertices.size();
		}

		retval /= 2;
		return retval;
	}

	public void addEdge(String v1, String v2) {
		Vertex vertex1 = this.getVertex(v1), vertex2 = this.getVertex(v2);
		if (vertex1 == null || vertex2 == null || vertex1.isAdjacentTo(vertex2)) {
			return;
		}
		Edge edge = new Edge(vertex1, vertex2);
		vertex1.addEdgeWith(edge);
		vertex2.addEdgeWith(edge);
	}

	public void removeEdge(String v1, String v2) {
		Vertex vertex1 = this.getVertex(v1), vertex2 = this.getVertex(v2);
		if (vertex1 == null || vertex2 == null || !vertex1.isAdjacentTo(vertex2)) {
			return;
		}

		vertex1.removeEdgeWith(vertex2);
		vertex2.removeEdgeWith(vertex1);
	}

	public boolean containsEdge(String v1, String v2) {
		Vertex vertex1 = this.getVertex(v1), vertex2 = this.getVertex(v2);
		if (vertex1 == null || vertex2 == null || !vertex1.isAdjacentTo(vertex2)) {
			return false;
		}
		return true;
	}

	public void display() {
		System.out.println(this);
	}

	public String toString() {
		String retval = "";

		for (Vertex vtx : this.vertices) {
			retval += vtx.toString();
		}
		return retval;
	}

	/***
	 * Questions Start from here
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public boolean hasPathFromTo(String v1, String v2) {
		Vertex vrtx1 = this.getVertex(v1), vrtx2 = this.getVertex(v2);
		if (vrtx1 == null || vrtx2 == null) {
			return false;
		}
		HashMap<Vertex, Boolean> visited = new HashMap<>();
		visited.put(vrtx1, true);

		return this.hasPathFromTo_DFS(vrtx1, vrtx2, visited);
	}

	private boolean hasPathFromTo_DFS(Vertex v1, Vertex v2, HashMap<Vertex, Boolean> visited) {
		if (v1.isAdjacentTo(v2)) {
			return true;
		}

		for (Vertex val : v1.getAdjacentVertices()) {
			if (!visited.containsKey(val)) {
				visited.put(val, true);
			}

			if (this.hasPathFromTo_DFS(val, v2, visited)) {
				return true;
			}
		}

		return false;
	}

	private boolean hasPathFromTo_BFS(Vertex v1, Vertex v2) {
		HashMap<Vertex, Boolean> visited = new HashMap<>();
		Queue<Vertex> queue = new LinkedList<>();

		visited.put(v1, true);
		queue.offer(v1);

		while (!queue.isEmpty()) {
			Vertex vtx = queue.poll();

			if (vtx.isAdjacentTo(v2)) {
				return true;
			}

			for (Vertex val : vtx.getAdjacentVertices()) {
				visited.put(val, true);
				queue.offer(val);
			}
		}
		return false;
	}

	public void breadthFirstTraversal() {
		HashMap<Vertex, Boolean> visited = new HashMap<>();
		Queue<Vertex> queue = new LinkedList<>();

		for (Vertex vtx : this.vertices) {
			if (!visited.containsKey(vtx)) {
				visited.put(vtx, true);
				queue.offer(vtx);
			}

			while (!queue.isEmpty()) {
				Vertex vtx1 = queue.poll();

				System.out.print(vtx1.name + ", ");

				for (Vertex val : vtx1.getAdjacentVertices()) {
					if (!visited.containsKey(val)) {
						visited.put(val, true);
						queue.offer(val);
					}
				}
			}
		}
		System.out.println("END");
	}

	public void depthFirstTraversal() {
		HashMap<Vertex, Boolean> visited = new HashMap<>();
		Stack<Vertex> stack = new Stack<>();

		for (Vertex vtx : this.vertices) {
			if (!visited.containsKey(vtx)) {
				visited.put(vtx, true);
				stack.push(vtx);
			}

			while (!stack.isEmpty()) {
				Vertex vtx1 = stack.pop();

				System.out.print(vtx1.name + ", ");

				for (Vertex val : vtx1.getAdjacentVertices()) {
					if (!visited.containsKey(val)) {
						visited.put(val, true);
						stack.push(val);
					}
				}
			}
		}
		System.out.println("END");
	}

	public boolean isConnected() {
		HashMap<Vertex, Boolean> visited = new HashMap<>();
		Queue<Vertex> queue = new LinkedList<>();

		visited.put(this.vertices.get(0), true);
		queue.offer(this.vertices.get(0));

		ArrayList<Vertex> list = new ArrayList<>();
		while (!queue.isEmpty()) {
			Vertex vtx1 = queue.poll();

			list.add(vtx1);

			for (Vertex val : vtx1.getAdjacentVertices()) {
				if (!visited.containsKey(val)) {
					visited.put(val, true);
					queue.offer(val);
				}
			}
		}
		if (list.size() != this.vertices.size()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isAcyclic() {
		HashMap<Vertex, Vertex> visited = new HashMap<>();
		Stack<Vertex> stack = new Stack<>();

		for (Vertex vtx : this.vertices) {
			if (!visited.containsKey(vtx)) {
				visited.put(vtx, null);
				stack.push(vtx);
			}

			while (!stack.isEmpty()) {
				Vertex vtx1 = stack.pop();

				for (Vertex val : vtx1.getAdjacentVertices()) {
					if (!visited.containsKey(val)) {
						visited.put(val, vtx1);
						stack.push(val);
					} else {
						Vertex reasonforVisitingVtx = visited.get(vtx1);
						if (reasonforVisitingVtx != val) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	public boolean isBipartite() {
		HashMap<Vertex, String> visited = new HashMap<>();
		Stack<Vertex> stack = new Stack<>();

		for (Vertex vtx : this.vertices) {
			if (!visited.containsKey(vtx)) {
				visited.put(vtx, "Red");
				stack.push(vtx);
			}

			while (!stack.isEmpty()) {
				Vertex vtx1 = stack.pop();

				for (Vertex val : vtx1.getAdjacentVertices()) {
					String colorToApply = visited.get(vtx1).equals("Red") ? "Green" : "Red";
					if (!visited.containsKey(val)) {
						visited.put(val, colorToApply);
						stack.push(val);
					} else {
						if (!visited.get(val).equals(colorToApply)) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	public boolean isTree() {
		return this.isConnected() && this.isAcyclic();
	}

	public ArrayList<ArrayList<String>> getConnectedComponents() {
		HashMap<Vertex, Boolean> visited = new HashMap<>();
		Queue<Vertex> queue = new LinkedList<>();

		ArrayList<ArrayList<String>> list = new ArrayList<>();
		int i = 0;

		for (Vertex vtx : this.vertices) {
			if (!visited.containsKey(vtx)) {
				visited.put(vtx, true);
				queue.offer(vtx);
			}

			ArrayList<String> list1 = new ArrayList<>();

			while (!queue.isEmpty()) {
				Vertex vtx1 = queue.poll();

				list1.add(vtx1.name);

				for (Vertex val : vtx1.getAdjacentVertices()) {
					if (!visited.containsKey(val)) {
						visited.put(val, true);
						queue.offer(val);
					}
				}
			}
			list.set(i++, list1);
			list1.removeAll(list1);
		}
		return list;
	}
}
