package aps2.shortestpath;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ShortestPath {
	/**
	 * In this task you need to find a shortest path on a given directed graph
	 * with arbitrary edge lengths (including negative weights!) from a single
	 * source node to all other nodes in the graph.
	 *
	 * Your algorithm should also detect, if there is a negative cycle in the
	 * graph and return null in this case.
	 */

	/**
	 * Adds a new node named s to the graph.
	 *
	 * @param s Name of the node
	 * @return True, if a node is unique and successfully added; False otherwise.
	 */
	Vector<String> nodes = new Vector<>();
	Vector<String> edges = new Vector<>();
	int distances[][];
	Vector<String>[][] pathToThis;
	byte isCycle;

	public boolean addNode(String s) {
		for (String node : nodes)
			if(node.compareTo(s) == 0)
				return false;
		nodes.add(s);
		return true;
	}

	/**
	 * Returns names of all nodes in the graph.
	 *
	 * @return Names of all nodes in the graph.
	 */
	public Vector<String> getNodes() {
		return nodes;
	}

	/**
	 * Adds an edge from node a to node b.
	 *
	 * @param a Source node.
	 * @param b Target node.
	 * @param w Edge weight. Warning: The weight can also be negative!
	 */
	public void addEdge(String a, String b, int w) {
		String edge1 = a+":"+b + ":"+ w;
		for (String edge : edges)
		{
			String[] temp = edge.split(":");
			if(temp[0].compareTo(a) == 0 && temp[1].compareTo(b) == 0) {
				edges.remove(edge);
				break;
			}
		}
		edges.add(edge1);
	}

	/**
	 * Computes and locally stores shortest paths from the given origin node
	 * to all other nodes in the graph.
	 *
	 * @param start Origin node.
	 */
	public void computeShortestPaths(String start) {
		if(distances == null)
			this.distances = new int[nodes.size()][nodes.size()];
		if(this.pathToThis == null)
			this.pathToThis = new Vector[nodes.size()][nodes.size()];


		for (int i = 0; i < nodes.size(); i++)
			for (int j = 0; j < nodes.size(); j++) {
				this.distances[i][j] = Integer.MAX_VALUE;
				this.pathToThis[i][j] = new Vector<>();
			}



		this.distances[nodes.indexOf(start)][nodes.indexOf(start)] = 0;

		for(int i = 0; i < nodes.size(); i++) {
			for(String edge: edges)
			{
				String[] temp = edge.split(":");
				int from = nodes.indexOf(temp[0]);
				int to = nodes.indexOf(temp[1]);
				int w = Integer.parseInt(temp[2]);
				if(this.distances[nodes.indexOf(start)][from] != Integer.MAX_VALUE && this.distances[nodes.indexOf(start)][from] + w < this.distances[nodes.indexOf(start)][to]){
					this.distances[nodes.indexOf(start)][to] = this.distances[nodes.indexOf(start)][from] + w;
					this.pathToThis[nodes.indexOf(start)][to] =  new Vector<>(this.pathToThis[nodes.indexOf(start)][from]);
					this.pathToThis[nodes.indexOf(start)][to].add(this.nodes.get(from));
				}
			}
		}


		isCycle = 0;
		for(int i = 0; i < nodes.size(); i++) {
			for(String edge: edges)
			{
				String[] temp = edge.split(":");
				int from = nodes.indexOf(temp[0]);
				int to = nodes.indexOf(temp[1]);
				int w = Integer.parseInt(temp[2]);
				if(this.distances[nodes.indexOf(start)][from] != Integer.MAX_VALUE && this.distances[nodes.indexOf(start)][from] + w < this.distances[nodes.indexOf(start)][to]){
					isCycle = 1;
					break;
				}
			}
		}
	}

	/**
	 * Returns a list of nodes on the shortest path from the given origin to
	 * destination node. Returns null, if a path does not exist or there is a
	 * negative cycle in the graph.
	 *
	 * @param start Origin node
	 * @param dest Destination node
	 * @return List of nodes on the shortest path from start to dest or null, if the nodes are not connected or there is a negative cycle in the graph.
	 */
	public Vector<String> getShortestPath(String start, String dest) {
		if(isCycle == 1)
			return null;
		//this.pathToThis[nodes.indexOf(start)][nodes.indexOf(dest)].add(dest);
		Vector<String> temp = new Vector<>(this.pathToThis[nodes.indexOf(start)][nodes.indexOf(dest)]);
		temp.add(dest);
		return temp;
	}

	/**
	 * Returns the distance of the shortest path from the given origin to
	 * destination node. Returns Integer.MIN_VALUE, if a path does not exist
	 * or there is a negative cycle in the graph.
	 *
	 * @param start Origin node
	 * @param dest Destination node
	 * @return Distance of the shortest path from start to dest, Integer.MIN_VALUE, if there is a negative cycle in the graph, or Integer.MAX_VALUE, if the nodes are not connected.
	 */
	public int getShortestDist(String start, String dest) {
		if(isCycle == 1)
			return Integer.MIN_VALUE;
		return this.distances[nodes.indexOf(start)][nodes.indexOf(dest)];
	}
}
