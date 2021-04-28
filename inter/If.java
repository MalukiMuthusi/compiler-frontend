package inter;

import symbols.*;

public class If extends Stmt {

   Expr expr;
   Stmt stmt;

   /**
    * build a node for a statement if(E)S
    * 
    * @param x
    * @param s
    */
   public If(Expr x, Stmt s) {
      expr = x;
      stmt = s;
      if (expr.type != Type.Bool)
         expr.error("boolean required in if");
   }

   public void gen(int b, int a) {
      int label = newlabel(); // label for the code for stmt
      expr.jumping(0, a); // fall through on true, goto a on false
      emitlabel(label);
      stmt.gen(label, a);
   }
}
