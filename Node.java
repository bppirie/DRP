import java.util.ArrayList;
/*
 * Node class to represent nodes in a directed weighted graph for the max flow min cut algorithm
 * @Author: Bradley Pirie
*/

public class Node {
   
   private String name;
   private Label label;
   
   public Node() {
      this.name = null;
      this.label = null;
   }
   
   public Node(String nameInput) {
      this.name = nameInput;
      this.label = null;
   }
   
   public Node(String nameInput, Label labelInput) {
      this.name = nameInput;
      this.label = labelInput;
   }
   
   public String getName() {
      return this.name;
   }
   
   public void setName(String nameInput) {
      this.name = nameInput;
   }
   
   public Label getLabel() {
      return this.label;
   }
   
   public void setLabel(Label labelInput) {
      this.label = labelInput;
   }
   
   public boolean equalsTo(Node x) {
      String thisName = this.name;
      String checkName = x.name;
      if (thisName.equals(checkName)) {
         return true;
      }
      else {
         return false;
      }
   }
   
   public void getInfo() {
      System.out.print(name);
   }
   
   public String displayInfo() {
      String s = this.name;
      return s;
   }
}