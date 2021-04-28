package inter;

/**
 * send control out of an enclosing loop or switch statement
 */
public class Break extends Stmt {

   Stmt stmt;

   public Break() {
      if (Stmt.Enclosing == Stmt.Null)
         error("unenclosed break");
      stmt = Stmt.Enclosing;
   }

   public void gen(int b, int a) {
      emit("goto L" + stmt.after);
   }
}
