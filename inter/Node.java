package inter;
import lexer.*;

public class Node {

   int lexline = 0;

   Node() { lexline = Lexer.line; }

   void error(String s) { throw new Error("near line "+lexline+": "+s); }

   static int labels = 0;

   public int newlabel() { return ++labels; }

   public void emitlabel(int i) { /* do nothing */ }

   public void emit(String s) { /* do nothing */ }
}
