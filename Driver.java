import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
/*
 * Driver to import a directed weighted graph for the max flow min cut algorithm using a matrix txt file
 * @Author: Bradley Pirie and Nathan Uricchio
*/
public class Driver {
   
   public static ArrayList<int[]> importGraph (String filePath) {
      try {
         BufferedReader lineReader = new BufferedReader(new FileReader(filePath));
         String curLine = lineReader.readLine();
         //System.out.println(curLine);
         ArrayList<int[]> graph = new ArrayList<int[]>();
         while (curLine != null) {
            String[] curStringLine = curLine.split(",");
            //System.out.print(curStringLine);
            int n = curStringLine.length;
            int[] curIntLine = new int[n];
            for (int i = 0; i < n; i++) {
               curIntLine[i] = Integer.parseInt(curStringLine[i]);
            }
            //System.out.println(curIntLine);
            graph.add(curIntLine);
            curLine = lineReader.readLine();
         }
         return graph;
      }
      catch (IOException ioe) {
         System.out.println("Input Error");
         return null;
      }
   }
   
   public static ArrayList<int[]> buildFlow(String filePath) {
      try {
         BufferedReader lineReader = new BufferedReader(new FileReader(filePath));
         String curLine = lineReader.readLine();
         //System.out.println(curLine);
         ArrayList<int[]> graph = new ArrayList<int[]>();
         while (curLine != null) {
            String[] curStringLine = curLine.split(",");
            //System.out.print(curStringLine);
            int n = curStringLine.length;
            int[] curIntLine = new int[n];
            for (int i = 0; i < n; i++) {
               curIntLine[i] = Integer.parseInt(curStringLine[i]);
            }
            //System.out.println(curIntLine);
            graph.add(curIntLine);
            curLine = lineReader.readLine();
         }
         return graph;
      }
      catch (IOException ioe) {
         System.out.println("Input Error");
         return null;
      }
   }
   
   public static ArrayList<Node> createNodes(ArrayList<int[]> graph) {
      ArrayList<Node> nodeList = new ArrayList<Node>();
      for (int i = 0; i < graph.size(); i++) {
         String name = "" + i;
         Node n = new Node(name);
         nodeList.add(n);
      }
      return nodeList;
   }
   
   public static ArrayList<Edge> createEdges(ArrayList<int[]> graph, ArrayList<Node> nodeList) {
      ArrayList<Edge> edgeList = new ArrayList<Edge>();
      for (int i = 0; i < graph.size(); i++) {
         int[] row = graph.get(i);
         for (int j = 0; j < graph.size(); j++) {
            if (row[j] != 0) {
               Node from = nodeList.get(i);
               Node to = nodeList.get(j);
               int c = row[j];
               Edge e = new Edge(from, to, c, 0);
               edgeList.add(e);
            }
         }
      }
      return edgeList;
   }
   
   public static ArrayList<Edge> createEdges(ArrayList<int[]> graph, ArrayList<Node> nodeList, ArrayList<int[]> flowGraph) {
      ArrayList<Edge> edgeList = new ArrayList<Edge>();
      for (int i = 0; i < graph.size(); i++) {
         int[] row = graph.get(i);
         int[] flowRow = flowGraph.get(i);
         for (int j = 0; j < graph.size(); j++) {
            if (row[j] != 0) {
               Node from = nodeList.get(i);
               Node to = nodeList.get(j);
               int c = row[j];
               int flow = flowRow[j];
               Edge e = new Edge(from, to, c, flow);
               edgeList.add(e);
            }
         }
      }
      return edgeList;
   }

   
   public static void labelSource(Node x) {
      Label l = new Label();
      l.setNode(x);
      l.prevNode(null);
      l.nextNode(null);
      l.setFlow(10000);
      x.setLabel(l);
   }
   
   public static ArrayList<Node> scanNode(Node x, ArrayList<Edge> edgeList) { //Scans a labeled node
      ArrayList<Node> in = checkIncoming(x, edgeList); //Checks the nodes incoming edges
      ArrayList<Node> out = checkOutgoing(x, edgeList); //Checks the nodes outgoing edges
      ArrayList<Node> labeled = in;
      for (Node i : out) {
         in.add(i);
      }
      return labeled;
   }
   
   public static ArrayList<Node> checkIncoming(Node x, ArrayList<Edge> edgeList) { //Checks a nodes incoming edges
      ArrayList<Node> labeled = new ArrayList<Node>(); //Create an array list to store the labeled nodes
      for (Edge e : edgeList) { //Goes through the edges
         Node next = e.toNode(); //Sets 'next' equal to where the edge is going
         if (next.equalsTo(x)) { //Checks if the edge is going to node x
            Node curr = e.fromNode(); //Sets curr equal to the node the edge is coming from
            Label currLabel = curr.getLabel(); //Gets the label from curr
            int f = e.getFlow(); //Gets the flow in the edge
            if (currLabel == null & f > 0) { //Checks that curr has no label and there is flow in the edge
               Label xLabel = x.getLabel(); //Gets label for the scanned node
               int xLabelFlow = xLabel.getFlow(); //Flow available from label on scanned node
               int newFlow = Math.min(f, xLabelFlow); //Sets newFlow equal to min between available flow and flow currently being sent
               Label l = new Label(curr, x, newFlow); //Create label that says we can stop sending newFlow to node x from curr node
               curr.setLabel(l); //Set label of curr node to the new label
               labeled.add(curr); //Add the node to the labeled list
            }
         }
      }
      return labeled;
   }
   
   public static ArrayList<Node> checkOutgoing(Node x, ArrayList<Edge> edgeList) { //Checks a nodes outgoing edges
      ArrayList<Node> labeled = new ArrayList<Node>(); //Create an array list to hold the labeled nodes
      for (Edge e : edgeList) { //Goes through edges
         Node prev = e.fromNode(); //Sets prev equal to the node the edge is coming from
         if (prev.equalsTo(x)) { //Checks if the edge is coming from node x
            Node curr = e.toNode(); //Sets curr to the node the edge is going to
            Label currLabel = curr.getLabel(); //Gets the label for curr
            int slack = (e.getCapacity() - e.getFlow()); //Sets slack equal to the edges capacity minus its current flow
            if (currLabel == null & slack > 0) { //Checks if there is slack in the edge and the node is unlabled
               Label xLabel = x.getLabel(); //Gets label from node x
               int xLabelFlow = xLabel.getFlow(); //Gets the available flow in node x's label
               int newFlow = Math.min(slack, xLabelFlow); //Sets new flow equal to min between extra available flow and slack in the edge
               Label l = new Label(curr, newFlow, x); //Creates new label saying newFlow can be sent to the curr node from node x
               curr.setLabel(l); //Set label of curr equal to the new label
               labeled.add(curr); //Add the node to the labeled list
               //System.out.print(l.getLabelInfo());
            }
         }
      }
      return labeled;
   }
   
   public static void scanNodes(Node source, Node sink, ArrayList<Node> nodeList, ArrayList<Edge> edgeList) {
      Queue<Node> labels = new LinkedList<Node>();
      ArrayList<Node> partition = new ArrayList<Node>();
      labels.add(source);
      partition.add(source);
      while (labels.isEmpty() == false) {
         Node y = labels.peek();
         if (y.equalsTo(sink)) {
            Label lbl = y.getLabel();
            int flow = lbl.getFlow();
            System.out.print("Augmenting Path:\n");
            augmentPath(y, edgeList, flow);
            break;
         }
         else {
            Node x = labels.remove();
            ArrayList<Node> labeled = scanNode(x, edgeList);
            for (Node i : labeled) {
               labels.add(i);
               partition.add(i);
            }
         }
      }
      Node z = labels.peek();
      if (z == null){
         returnPart(partition);
         int maxFlow = 0;
         for (Edge e : edgeList) {
            if (e.toNode().equalsTo(sink)) {
               maxFlow += e.getFlow();
            }
         }
         System.out.println("\nMaximum flow = " + maxFlow);
      }
      else if (z.equalsTo(sink)) {
         resetNodeLabels(nodeList, source);
         scanNodes(source, sink, nodeList, edgeList);
      }
   }
   
   public static void returnPart(ArrayList<Node> partition) {
      System.out.print("Minimum Cut Partition: ");
      for (Node i : partition) {
         i.getInfo();
         System.out.print(" ");
      }
   }
   
   
   public static void resetNodeLabels(ArrayList<Node> nodeList, Node source) {
      for (Node i : nodeList) {
         if (i.equalsTo(source) == false) {
            i.setLabel(null);
         }
      }
   }
   
   public static void augmentPath(Node n, ArrayList<Edge> edgeList, int flow) {
      Label lbl = n.getLabel();
      if (lbl.nextNode() == null & lbl.prevNode() == null) {
         lbl.setFlow(10000);
         System.out.print("\n");
      }
      else {
         if (lbl.nextNode() == null) {
            Node x = lbl.prevNode();
            for (Edge e : edgeList) {
               if (e.toNode().equalsTo(n) & e.fromNode().equalsTo(x)) {
                  int currFlow = e.getFlow();
                  e.setFlow(currFlow + flow);
                  System.out.println("Send " + flow + " flow from Node: " + e.fromNode().displayInfo() + " to Node: " + e.toNode().displayInfo());
                  augmentPath(e.fromNode(), edgeList, flow);
                  break;
               }
            }
         }
         else {
            Node x = lbl.nextNode();
            for (Edge e : edgeList) {
               if (e.fromNode().equalsTo(n) & e.toNode().equalsTo(x)) {
                  int currFlow = e.getFlow();
                  e.setFlow(currFlow - flow);
                  System.out.println("Stop sending " + flow + " flow from Node: " + e.fromNode().displayInfo() + " to Node: " + e.toNode().displayInfo());
                  augmentPath(e.toNode(), edgeList, flow);
                  break;
               }
            }
         }
      }
   }
   
   
}