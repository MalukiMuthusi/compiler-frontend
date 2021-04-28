package inter;

import lexer.*;
import symbols.*;

/**
 * Expression nodes in the syntax tree.
 */
public class Expr extends Node {

   /**
    * the operator in an expression
    */
   public Token op;

   /**
    * the operator type in an expression
    */
   public Type type;

   Expr(Token tok, Type p) {
      op = tok;
      type = p;
   }

   /**
    * returns a term that can fit the right side of a three address instruction
    *    e.g given the expression E = E1 + E2 will return x1 + x2
    * @return
    */
   public Expr gen() {
      return this;
   }

   /**
    * reduces an expression to a single address
    *    it returns a constant, identifier or a temporary name
    * @return
    */
   public Expr reduce() {
      return this;
   }

   public void jumping(int t, int f) {
      emitjumps(toString(), t, f);
   }

   public void emitjumps(String test, int t, int f) {
      if (t != 0 && f != 0) {
         emit("if " + test + " goto L" + t);
         emit("goto L" + f);
      } else if (t != 0)
         emit("if " + test + " goto L" + t);
      else if (f != 0)
         emit("iffalse " + test + " goto L" + f);
      else
         ; // nothing since both t and f fall through
   }

   public String toString() {
      return op.toString();
   }
}
