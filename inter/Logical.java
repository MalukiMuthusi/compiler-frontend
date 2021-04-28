package inter;

import lexer.*;
import symbols.*;

/**
 * Logical operator node in a syntax tree
 * 
 * @subclass Or, @subclass And, @subclass Not
 */
public class Logical extends Expr {

   public Expr expr1, expr2;

   /**
    * build a syntax node
    * 
    * @param tok
    * @param x1
    * @param x2
    */
   Logical(Token tok, Expr x1, Expr x2) {
      super(tok, null); // null type to start
      expr1 = x1;
      expr2 = x2;
      // ensure both are boolean types
      type = check(expr1.type, expr2.type);
      if (type == null)
         error("type error");
   }

   public Type check(Type p1, Type p2) {
      if (p1 == Type.Bool && p2 == Type.Bool)
         return Type.Bool;
      else
         return null;
   }

   public Expr gen() {
      int f = newlabel();
      int a = newlabel();
      Temp temp = new Temp(type);
      this.jumping(0, f);
      emit(temp.toString() + " = true");
      emit("goto L" + a);
      emitlabel(f);
      emit(temp.toString() + " = false");
      emitlabel(a);
      return temp;
   }

   public String toString() {
      return expr1.toString() + " " + op.toString() + " " + expr2.toString();
   }
}
