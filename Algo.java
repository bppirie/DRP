import java.util.ArrayList;
import java.util.Scanner;
/*
 * Driver to import a directed weighted graph for the max flow min cut algorithm using a matrix txt file
 * @Author: Bradley Pirie and Nathan Uricchio
*/
public class Algo {
   public static void main (String[] args) {
      Scanner scnr = new Scanner(System.in); //Scanner to accept input from the user
      String graphName; //Graph matrix file name
      String flowYN; //Either y or n depending on if the user would like to initialize flow
      String flowMatrix; //Flow graph matrix file name
      
      System.out.print("Select graph below:\nGraph.txt\nGraph1.txt\nGraph5.txt\nType name: "); //Display graphs to pick from
      graphName = scnr.nextLine(); //Save users selection
      System.out.print("\nWould you like to initialize a flow?\nType y/n: "); //Ask user if they would like to initialize flow
      flowYN = scnr.nextLine(); //Save the input
      if (flowYN.compareToIgnoreCase("y") == 0 ) { //If the user says yes, check which graph they selected
         if (graphName.compareTo("Graph.txt") == 0) { //Is it Graph.txt
            System.out.print("There is no initial flow for Graph.txt"); //Graph.txt doesnt have a flow matrix yet
         }
         else if (graphName.compareTo("Graph1.txt") == 0) { //Is it Graph1.txt
            System.out.print("There is no initial flow for Graph1.txt"); //Graph1.txt doesnt have a flow matrix yet
         }
         else if (graphName.compareTo("Graph5.txt") == 0) { //Is it Graph5.txt
            System.out.print("\nSelect flow below:\nFlow5.txt\nType name: "); //Display flows for Graph5.txt to pick from
            flowMatrix = scnr.nextLine(); //Save users selection
            ArrayList<int[]> graph = Driver.importGraph(graphName); //Import the graph from the txt file
            int n = graph.size(); //n will be the number of nodes in the graph
            ArrayList<int[]> flowGraph = Driver.buildFlow(flowMatrix); //Create the flow matrix from its txt file
            ArrayList<Node> nodeList = Driver.createNodes(graph); //Create nodes and node list
            ArrayList<Edge> edgeList = Driver.createEdges(graph, nodeList, flowGraph); //Create edges and edge list
            Node source = nodeList.get(0); //Source = The first node
            Node sink = nodeList.get(n-1); //Sink = The last node
            Driver.labelSource(source); //Label the source
            Driver.scanNodes(source, sink, nodeList, edgeList); //Scan the graph
         }
         else {
            System.out.print("That graph does not exist"); //Graph doesnt exist
         }
      }
      else {
         ArrayList<int[]> graph = Driver.importGraph(graphName); //Import the graph from the txt file
         int n = graph.size(); //n is the graph size
         ArrayList<Node> nodeList = Driver.createNodes(graph); //Create the nodes and the node list
         ArrayList<Edge> edgeList = Driver.createEdges(graph, nodeList); //Create the edges and the edge list
         Node source = nodeList.get(0); //Source = The first node
         Node sink = nodeList.get(n-1); //Sink = The last node
         Driver.labelSource(source); //Label source
         Driver.scanNodes(source, sink, nodeList, edgeList); //Scan the graph
      }
   }
}