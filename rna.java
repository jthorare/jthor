import tester.*;

//Stop codons are UAG, UAA or UGA

interface ILoString {
  ILoLoString translate();

  String listToString();

  ILoLoString listProteins(ILoString p);

  ILoString reverse();

  ILoString reverseHelp(ILoString p);
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

    return codons.listProteins(new MtLoString());
  }

  // UAG, UAA or UGA
  public ILoLoString listProteins(ILoString p) {
    if (this.first.equals("UAG") || this.first.equals("UAA") || this.first.equals("UGA")) {
      return new ConsLoLoString(p.reverse(), this.rest.listProteins(new MtLoString()));
    }
    else {
      return this.rest.listProteins(new ConsLoString(this.first, p));
    }
  }

  public ILoString reverse() {
    return this.rest.reverseHelp(new ConsLoString(this.first, new MtLoString()));
  }

  public ILoString reverseHelp(ILoString p) {
    return this.rest.reverseHelp(new ConsLoString(this.first, p));
  }

  public String listToString() {
    return this.first + this.rest.listToString();
  }

  public ILoString stringToList3(String str) {
    if (str.length() % 3 == 0 && str.length() != 0) {
      return new ConsLoString(str.substring(0, 3), this.stringToList3(str.substring(3)));
    }
    else if (str.length() % 3 != 0) {
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
  MtLoString() {
  }

  public ILoLoString translate() {
    return new MtLoLoString();
  }

  public String listToString() {
    return "";
  }

  public ILoLoString listProteins(ILoString p) {
    return new ConsLoLoString(p.reverse(), new MtLoLoString());
  }

  public ILoString reverse() {
    return new MtLoString();
  }

  public ILoString reverseHelp(ILoString p) {
    return p;
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
  MtLoLoString() {
  }
}

class ExamplesProteins {
  ExamplesProteins() {
  }

  ILoString emptyLo = new MtLoString();
  ILoLoString emptyLoLo = new MtLoLoString();
  ILoString example1 = new ConsLoString("A",
      new ConsLoString("C", new ConsLoString("A", new ConsLoString("A", new ConsLoString("A",
          new ConsLoString("G", new ConsLoString("U", new ConsLoString("A", new ConsLoString("G",
              new ConsLoString("U", new ConsLoString("U", new ConsLoString("G", emptyLo))))))))))));
  ILoString example1Codons = new ConsLoString("ACA",
      new ConsLoString("AAG", new ConsLoString("UAG", new ConsLoString("UUG", emptyLo))));
  ILoString exampleReverse = new ConsLoString("AAG", new ConsLoString("ACA", emptyLo));
  ILoString example2 = new ConsLoString("A",
      new ConsLoString("C", new ConsLoString("A", new ConsLoString("A", new ConsLoString("A",
          new ConsLoString("G", new ConsLoString("U", new ConsLoString("A", new ConsLoString("G",
              new ConsLoString("U", new ConsLoString("A", new ConsLoString("G",new ConsLoString("U",
                  new ConsLoString("U", new ConsLoString("G", emptyLo)))))))))))))));
  // (ACA, AAG) and (UUG)
   boolean testTranslate(Tester t) {
   return t.checkExpect(example1.translate(), new ConsLoLoString(new
   ConsLoString("ACA", new ConsLoString("AAG", emptyLo)),
   new ConsLoLoString(new ConsLoString("UUG", emptyLo), emptyLoLo)))
       && t.checkExpect(example2.translate(), new ConsLoLoString(new
   ConsLoString("ACA", new ConsLoString("AAG", emptyLo)),
   new ConsLoLoString(emptyLo, new ConsLoLoString(new ConsLoString("UUG", emptyLo), emptyLoLo))));
   }

  // tests the ILoString method listToString()
  boolean testListToString(Tester t) {
    return t.checkExpect(example1.listToString(), "ACAAAGUAGUUG");
  }

  // tests the ILoString method listProteins()
  boolean testListProteins(Tester t) {
    return t.checkExpect(example1Codons.listProteins(new MtLoString()),
        new ConsLoLoString(new ConsLoString("ACA", new ConsLoString("AAG", emptyLo)),
            new ConsLoLoString(new ConsLoString("UUG", emptyLo), emptyLoLo)));
  }

  // tests the ILoString method reverse()
  boolean testReverse(Tester t) {
    return t.checkExpect(exampleReverse.reverse(),
        new ConsLoString("ACA", new ConsLoString("AAG", emptyLo)));
  }

}
