// Hung Auduong && Evan Ko
// 5/30/16
// CSE 373
// Professor: Linda Shapiro
// Assignment #5
//
// develope a graph representation and implement Dijkstra's algorithm
// to find the shortest path

import java.util.*;

public class MyGraph implements Graph {
   
   
   private HashMap<Vertex, LinkedList<Vertex>> vertexMap;           //keeps track of the vertex
   private List<Edge> e;                                            //keeps a list of all edges
   private List<Vertex> v;                                          //keeps a list of all vertex
   
   // post: Constructor that initialize a collection of vertecies with the adjacent vertices.
   //		and initialize a collectoin of all the vertecies and edges.
   public MyGraph(Collection<Vertex> v, Collection<Edge> e) {
      this.vertexMap = new HashMap<Vertex, LinkedList<Vertex>>();
      this.e = new ArrayList<Edge>(e);
      this.v = new ArrayList<Vertex>(v);
      for (Vertex temp : v) {
         vertexMap.put(temp, new LinkedList<Vertex>());
         for (Edge temp2 : e) {
            if (temp.equals(temp2.getSource())) {
               vertexMap.get(temp).add(temp2.getDestination());
            }
         }
      }
   }
   
   // post: return a collection of vertices
   public Collection<Vertex> vertices() {
      return v;
   }
   
   // post: return a collection of edges;
   public Collection<Edge> edges() {
      return e;
   }
   
   // pre: vertexMap.get(v) != null (otherwise throw new IllegalArgumentException())
   // post: return all the adjacent vertices with a vertex that is passed in the parameter
   public Collection<Vertex> adjacentVertices(Vertex v) {
      if (vertexMap.get(v) == null) {
         throw new IllegalArgumentException("vertex does not exist");
      }
      return vertexMap.get(v);
      
   }
   
   // pre: vertex a && b is not null in vertex map (throw IllegalArgumentExceptoin if not)
   // post: find the edge in the collection of edges with starting vertex a, and
   //		ending vertex b. and return the cost of the edge. if it's not in the collection
   //	    of edges, then it would return -1
   public int edgeCost(Vertex a, Vertex b) {
      if (vertexMap.get(a) == null || vertexMap.get(b) == null) {
         throw new IllegalArgumentException("a vertex does not exist");
      }
      for (Edge temp : e) {
         if (a.equals(temp.getSource()) && b.equals(temp.getDestination())) {
            return temp.getWeight();
         }
         
      }
      return -1;
   }
   
   // pre: a and b are not null (throw new IllegalArgumentException otherwise)
   // post: find the shortest path starting from vertex a to vertex b. this method
   //		will use Dijkstra's Algorithm. to traverse through the graph to
   //		find the shortest path.
   public Path shortestPath(Vertex a, Vertex b) {
      if (a == null || b == null) {
         throw new IllegalArgumentException("the vertex does not exist");
      }
      
      Set<Vertex> unknownVertex = new HashSet<Vertex>();
      Set<Vertex> knownVertex = new HashSet<Vertex>();
      HashMap<Vertex, Integer> distance = new HashMap<Vertex, Integer>();
      HashMap<Vertex, Vertex> link = new HashMap<Vertex, Vertex>();
      
      for (Vertex v : v) {
         distance.put(v, Integer.MAX_VALUE);
         unknownVertex.add(v);
      }
      unknownVertex.remove(a);
      distance.put(a, 0);
      
      while (!unknownVertex.isEmpty()) {
         for (Vertex adj : vertexMap.get(a)) {
            if (unknownVertex.contains(adj)) {
               int c1 = distance.get(a) + edgeCost(a, adj);
               int c2 = distance.get(adj);
               if (c1 < c2) {
                  distance.put(adj, c1);
                  link.put(adj, a);
               }
               if (!knownVertex.contains(adj)) {
                  knownVertex.add(adj);
               }
            }
         }
         int min = Integer.MAX_VALUE;
         for (Vertex v : knownVertex) {
            int n = distance.get(v);
            if (n < min) {
               min = n;
               a = v;
            }
         }
         if (a.equals(b)) {
            unknownVertex.clear();
         }
         knownVertex.remove(a);
         unknownVertex.remove(a);
      }
      
      if (a.equals(b)) {
         List<Vertex> finalList = new ArrayList<Vertex>();
         if (link.get(b) == null) {
            return null;
         } else {
            finalList.add(b);
            Vertex temp = b;
            while (link.get(temp) != null) {
               temp = link.get(temp);
               finalList.add(temp);
            }
            Collections.reverse(finalList);
         }
         Path p = new Path(finalList, distance.get(b));
         return p;
      } else {
         return null;
      }
   }
}
