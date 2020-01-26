import tester.*;

//Stop codons are UAG, UAA or UGA

interface ILoString {
  ILoLoString translate();
  boolean stopU();
  boolean stopUA();
  boolean stopUG();
  boolean stopUAG();
  boolean stopUAA();
  boolean stopUGA();
  boolean stopIncomplete();
  ILoString listAcc();
  ILoString allBases();
}
// (A, U, G, G, A, U, G, A, A, U, U, G, EMPTY)
// "AUGGAUGGAAUUG", EMPTY
// AUG, GAU, GAA, UUG, EMPTY
// ^^, ANOTHER, EMPTY
interface ILoLoString {
}
// ("A", "U", "G")
// class represent a List of Strings
class ConsLoString implements ILoString {
  String first;
  ILoString rest;
  ConsLoString c;
  ConsLoString(String first, ILoString rest) {
    this.first = first;
    this.rest = rest;
  }

  public ILoLoString translate(ConsLoString c) {
  }
 
  public ILoString allBases() {
    if(this.equals(new MtLoString())) {
      return "";
    } else {
      return new ConsLoString(this.first + this.rest.allBases(), new MtLoString());
    }
    }

      
      
    /*
    if(this.stopU() || this.stopIncomplete()) {
      return new ConsLoLoString(this.listAcc(c), new MtLoLoString());
      }
    else this.listAcc(c);
    //return new ConsLoLoString(accumulator, MtLoLoString);
    return null;
    }
  // stops if incomplete(i.e. run out of strings) or reach stop codon

  public boolean stopU() {
    if(this.first.equals("U")) {
      return this.rest.stopUA()
          ||  this.rest.stopUG();
    }
    else {
      return false;
    }
  }
  
  public boolean stopUA() {
    if(this.first.equals("A")) {
      return this.rest.stopUAA()
          || this.rest.stopUAG();
    }
    else {
      return false;
    }
  }

  
  public boolean stopUG() {
    if(this.first.equals("G")) {
      return this.rest.stopUGA();
    }
    else {
      return false;
    }
  }

  public boolean stopUAG() {
    if(this.first.equals("G")) {
      return true;
    }
    else {
      return false;
    }
  }

  public boolean stopUAA() {
    if(this.first.equals("A")) {
      return true;
    }
    else {
      return false;
    }
  }

  public boolean stopUGA() {
    if(this.first.equals("A")) {
      return true;
    }
    else {
      return false; 
      }
  }

  public boolean stopIncomplete() {
    return false;
  }

  // ("AUG", "UUG")
  public ConsLoString listAcc(ConsLoString c) {
    if(c.first.length() == 3) {
      return new ConsLoString(this.first, c);
    }
    else {
      return new ConsLoString ((c.first + this.first), c.rest);
    }
  }
 
}
*/
// (("AUG", "UUG"), ("AUG", "UUG")
class MtLoString implements ILoString {
  MtLoString() {}

  public ILoLoString translate() {
    return "";
  }

  @Override
  public boolean stop() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean stopU() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean stopUA() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean stopUG() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean stopUAG() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean stopUAA() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean stopUGA() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean stopIncomplete() {
    // TODO Auto-generated method stub
    return false;
  }
}

// class to represent a List of a List of Strings
class ConsLoLoString implements ILoLoString {
  ILoString first;
  ILoLoString rest;
  ConsLoLoString(ILoString first, ILoLoString rest) {
    this.first = first;
    this.rest = rest;
  }
  
  
}

class MtLoLoString implements ILoLoString {
  MtLoLoString() {}
}