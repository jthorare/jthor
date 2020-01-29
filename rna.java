import tester.*;

//Stop codons are UAG, UAA or UGA

interface ILoString {
  ILoLoString translate();
  String listToString();
  ILoLoString listProteins();
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

  public ILoLoString translate() {
   ILoString codons = this.stringToList3(listToString());
   
   return codons.listProteins();
   }
 // UAG, UAA or UGA
   public ILoLoString listProteins(ILoLoString p) {
    return new ConsLoLoString(this.first.protein, this.rest()); 
   }
     
  ILoString protein() {
     if(this.first.equals("UAG") || this.first.equals("UAA") || this.first.equals("UGA")) {
      return p && this.rest.listProteins(); 
     }
     else {
       return new ConsLoLoString(this.first, this.rest.protein());
   }
   
  public String listToString() {
    return this.first + this.rest.listToString();
  }
  
  public ILoString stringToList3(String str) {
    if(str.length() % 3 == 0 && str.length() != 0) {
     return new ConsLoString(str.substring(0, 3), this.stringToList3(str.substring(3)));
    }
    else if(str.length() % 3 != 0) {
      int n = str.length() % 3;
      return this.stringToList3(str.substring(0, (str.length() - n)));
    }
    else {
      return new MtLoString();
    }
  }
}

  
// (("AUG", "UUG"), ("AUG", "UUG")
class MtLoString implements ILoString {
  MtLoString() {}

  public ILoLoString translate() {
    ;
  }
  
  public String listToString() {
    return "";
  }

  public ILoLoString listProteins() {
    return new MtLoLoString();
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
