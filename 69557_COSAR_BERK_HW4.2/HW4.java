import java.util.Arrays;
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
		
		System.out.println(hw4.totalTransitTime(graph));
		System.out.println(hw4.cheapestTransitTime(graph));
	    System.out.println(hw4.timeIncrease(graph));
	
	}
	
	// You can add any methods you need, both to this file and Graph.java file

	// The method for task 1 
	int totalTransitTime(Graph graph) {
		
		        int total=0;
				int[][] adjacency= graph.asArray(true);
				
				for(int i=0; i<adjacency.length;i++) {
					total=total+dij(adjacency,i);
				}
		return total;
	}
	
	int dij(int matrix[][], int src) {
		
		int total=0;
		int[] latency= new int[matrix.length];
		boolean spt[]= new boolean[matrix.length];
		
		for(int i=0; i<matrix.length;i++) {
			latency[i]=Integer.MAX_VALUE;
			spt[i]=false;
		}
		
		latency[src]=0;
		
		for(int count=0; count<matrix.length-1;count++) {
			int u= minDistance(latency,spt);
			
			spt[u]=true;
			
			for(int v=0; v<matrix.length;v++) {
				if(!spt[v] && matrix[u][v] !=0 && latency[u] != Integer.MAX_VALUE
						&&latency[u]+matrix[u][v]<latency[v]) {
					
					latency[v]=latency[u]+matrix[u][v];
				}
			}
		}
		
		for(int t=0; t<latency.length;t++) {
			total=total+latency[t];
		}
		
		return total;
	}
	
	private int minDistance(int[] dist, boolean[] spt) {
		
		int min=Integer.MAX_VALUE;
		int index=-1;
		
		for(int v=0; v<dist.length;v++) {
			if(spt[v]==false && dist[v]<=min) {
				min=dist[v];
				index=v;
			}
		}
		
		return index;
	}

	// The method for task 2 
	int cheapestTransitTime(Graph graph) {
		
		
		return prim(graph);
	}

	
	int prim(Graph graph) {
		int[][] adjacency= graph.asArray(false);
		Graph tree= new Graph();
		
		boolean[] mst= new boolean[adjacency.length];
		int parent[]= new int[adjacency.length];
		int[] key= new int[adjacency.length];
		
		for(int i=0; i<adjacency.length;i++) {
			key[i]=Integer.MAX_VALUE;
			
		}
		key[0]=0;
		parent[0]=-1;
		
		for(int i=0; i<adjacency.length;i++) {
			
			//find min vertex
			int minKey=Integer.MAX_VALUE;
			int vertex=-1;
			for(int p=0; p<adjacency.length;p++) {
				if(mst[p]==false && minKey>key[p]) {
					minKey=key[p];
					vertex=p;
				}
			}
			mst[vertex]=true;
		for(int j=0; j<adjacency.length; j++) {
			if(adjacency[vertex][j]>0) {
				if(mst[j]==false && adjacency[vertex][j]<key[j]) {
					key[j]=adjacency[vertex][j];
					parent[j]=vertex;
				}
			}
		}
		}
		
		
		
		for(Edge edge: graph.getEdges())
		for(int i=1; i<parent.length;i++) {
			
	if(edge.getSrc().equals(graph.getVertices().get(i)) 
	   && edge.getDst().equals(graph.getVertices().get(parent[i]))
	   || edge.getSrc().equals(graph.getVertices().get(parent[i]))
	   && edge.getDst().equals(graph.getVertices().get(i)))
	   {
		
		tree.addEdge(edge);
	}
	
			
		}
		
		int total=0;
		int[][] matrix= tree.asArray(true);
		
		

		for(int i=0; i<matrix.length;i++) {
			total=total+dij(matrix,i);
		}
		
		return total;
	}
	// The method for task 3 
	int timeIncrease(Graph graph) {
		
		
		
		return cheapestTransitTime(graph)-totalTransitTime(graph);
	}
	
}
