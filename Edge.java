import java.util.ArrayList;
/*
 * Edge class to represent edges in a directed weighted graph for the max flow min cut algorithm
 * @Author: Bradley Pirie
*/

public class Edge {
   
   private int capacity;
   private int currFlow;
   private Node from;
   private Node to;
   
   public Edge() {
      this.capacity = 0;
      this.currFlow = 0;
      this.from = null;
      this.to = null;
   }
   
   public Edge(Node start, Node end, int capacity, int flow) {
      this.from = start;
      this.to = end;
      this.capacity = capacity;
      this.currFlow = flow;
   }
   
   public int getCapacity() {
      return this.capacity;
   }
   
   public void setCapacity(int capacityInput) {
      this.capacity = capacityInput;
   }
   
   public int getFlow() {
      return this.currFlow;
   }
   
   public void setFlow(int flowInput) {
      this.currFlow = flowInput;
   }
   
   public Node fromNode() {
      return this.from;
   }
   
   public void fromNode(Node nodeInput) {
      this.from = nodeInput;
   }
   
   public Node toNode() {
      return this.to;
   }
   
   public void toNode(Node nodeInput) {
      this.to = nodeInput;
   }
   
   public boolean equalsTo(Edge e) {
      if (this.from.equalsTo(e.from) & this.to.equalsTo(e.to) & this.capacity == e.capacity) {
         return true;
      }
      else {
         return false;
      }
   }
   
   
}
