import tester.*;
import javalib.worldimages.*;   // images, like RectangleImage or OverlayImages
import javalib.funworld.*;      // the abstract World class and the big-bang library
import java.awt.Color; 
 
interface ITree { /* see methods below */ 
  WorldImage draw(); // OverlayOffsetAlign = pinhole to pinhole
}
 
class Leaf implements ITree {
  int size; // represents the radius of the leaf
  Color color; // the color to draw it
  
  Leaf(int size, Color color) {
    this.size = size;
    this.color = color;
  }
  
  // leaves are RED Color.RED
  public WorldImage draw() {
    return new CircleImage(this.size, OutlineMode.SOLID, this.color);
  }
}
 
class Stem implements ITree {
  // How long this stick is
  int length;
  // The angle (in degrees) of this stem, relative to the +x axis
  double theta;
  // The rest of the tree
  ITree tree;
  
  Stem(int length, double theta, ITree tree) {
    this.length = length;
    this.theta = theta;
    this.tree = tree;
  }
  
  public WorldImage draw() {
    return new LineImage(new Posn((int)Math.round((this.length * Math.cos(this.theta))), (int)Math.round((this.length * Math.sin(this.theta)))), Color.BLACK);
  }
}
 
class Branch implements ITree {
  // How long the left and right branches are
  int leftLength;
  int rightLength;
  // The angle (in degrees) of the two branches, relative to the +x axis,
  double leftTheta;
  double rightTheta;
  // The remaining parts of the tree
  ITree left;
  ITree right;
  
  Branch(int leftLength, int rightLength, double leftTheta, double rightTheta, ITree left, ITree right) {
    this.leftLength = leftLength;
     this.rightLength = rightLength;
     this.leftTheta = leftTheta;
     this.rightTheta = rightTheta;
     this.left = left;
     this.right = right;
  }
  
  public WorldImage draw() {
    return new LineImage
  }
}

class ExamplesTree {
  ExamplesTree() {}
  ITree tree1 = new Branch(30, 30, 135, 40, new Leaf(10, Color.RED), new Leaf(15, Color.BLUE));
  ITree tree2 = new Branch(30, 30, 115, 65, new Leaf(15, Color.GREEN), new Leaf(8, Color.ORANGE));

  ITree tree1Stem = new Stem(40, 90, tree1);
  ITree tree2Stem = new Stem(50, 90, tree2);

  boolean testDraw(Tester t) {
    return t.checkExpect(this.tree1.draw(this.tree2), new Branch(40, 50, 150, 30, tree1, tree2));
  }
}