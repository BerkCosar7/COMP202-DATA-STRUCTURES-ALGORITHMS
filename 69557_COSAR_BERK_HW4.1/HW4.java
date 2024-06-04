
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;





public class HW4 {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Graph graph = new Graph();
				
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			String[] parts = line.split(" ");
			if (parts[0].equals("end")) break;
			String src = parts[0];
			String dst = parts[1];
			int cost = Integer.parseInt(parts[2]);
			int latency = Integer.parseInt(parts[3]);
			
			graph.addVertex(src);
			graph.addVertex(dst);
			Edge edge = new Edge(src, dst, cost, latency);
			graph.addEdge(edge);
		}
		
		//System.out.println(Arrays.deepToString(graph.asArray(false)));
		//System.out.println(Arrays.deepToString(graph.asArray(true)));
		
		HW4 hw4 = new HW4();
		scan.close();
		
		System.out.println(hw4.totalLinkCost(graph));
		System.out.println(hw4.cheapestNetwork(graph.asArray(false)));
	    System.out.println(hw4.savedAmount(graph));
	     
	}
	
	// You can add any methods you need, both to this file and Graph.java file

	// The method for task 1 
	int totalLinkCost(Graph graph) {
		// TODO Auto-generated method stub
		int totalCost=0;
		for( Edge e: graph.getEdges()) {
			totalCost=totalCost+ e.getCost();
		}
		
		return totalCost;
	}

	// The method for task 2 
	int cheapestNetwork(int[][] array_graph) {
		
		int cheapestNetwork=0;
		boolean[] mst= new boolean[array_graph.length];
		
		int[] key= new int[array_graph.length];
		
		for(int i=0; i<array_graph.length;i++) {
			key[i]=Integer.MAX_VALUE;
			
		}
		
		
		key[0]=0;
		
		
		for(int i=0; i<array_graph.length;i++) {
			
			//find min vertex
			int minKey=Integer.MAX_VALUE;
			int vertex=-1;
			for(int p=0; p<array_graph.length;p++) {
				if(mst[p]==false && minKey>key[p]) {
					minKey=key[p];
					vertex=p;
				}
			}
			mst[vertex]=true;
		for(int j=0; j<array_graph.length; j++) {
			if(array_graph[vertex][j]>0) {
				if(mst[j]==false && array_graph[vertex][j]<key[j]) {
					key[j]=array_graph[vertex][j];
				}
			}
		}
		}
		for(int k=0; k<array_graph.length; k++) {
			
			cheapestNetwork= cheapestNetwork+key[k];
		}
		return cheapestNetwork;
		
	}
	
	// The method for task 3 
	int savedAmount(Graph graph) {
		// TODO Auto-generated method stub
		return totalLinkCost(graph)-cheapestNetwork(graph.asArray(false));
	}
	
	
	
		
		
		
	
}
