import java.util.ArrayList;
/*
 * Label class to represent vertex labels in a directed weighted graph for the max flow min cut algorithm
 * @Author: Bradley Pirie
*/

public class Label {
   
   private Node currNode;
   private Node fromNode;
   private Node toNode;
   private int flow;
   
   public Label() {
      this.currNode = null;
      this.fromNode = null;
      this.toNode = null;
      this.flow = 0;
   }
   
   public Label(Node nodeInput, int flowInput, Node fromInput) {
      this.currNode = nodeInput;
      this.flow = flowInput;
      this.fromNode = fromInput;
   }
   
   public Label(Node nodeInput, Node toInput, int flowInput) {
      this.currNode = nodeInput;
      this.flow = flowInput;
      this.toNode = toInput;
   }
   
   public Node getNode() {
      return this.currNode;
   }
   
   public void setNode(Node nodeInput) {
      this.currNode = nodeInput;
   }
   
   public Node prevNode() {
      return this.fromNode;
   }
   
   public void prevNode(Node nodeInput) {
      this.fromNode = nodeInput;
   }
   
   public Node nextNode() {
      return this.toNode;
   }
   
   public void nextNode(Node nodeInput) {
      this.toNode = nodeInput;
   }
   
   public int getFlow() {
      return this.flow;
   }
   
   public void setFlow(int flowInput) {
      this.flow = flowInput;
   }
   
   public String getLabelInfo() {
      String curr = this.currNode.getName();
      String from = this.fromNode.getName();
      //String to = this.toNode.getName();
      int i = this.flow;
      String output = "(" + curr + ", " + from + ", " + i + ")";
      return output;
   }
}