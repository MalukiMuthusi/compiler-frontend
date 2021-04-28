# Compiler

## The Language Grammar

```bnf
program -> block
block -> {decls stmts}
decls -> decls decl | e
decl -> type id;
type -> type [num] | basic
basic -> int | float
stmts -> stmts stmt | e
stmt -> loc = bool; |
       if(bool) stmt |
       if(bool) stmt else stmt |
       while(bool) stmt |
       do stmt while(bool); |
       break; |
       block |

loc -> loc[bool] | id

bool -> bool || join | join
join -> join && equality | equality
equality -> equality == rel |
            equality != rel |
            rel

rel -> expr < expr | expr <= expr | expr >= expr |
       expr > expr | expr

expr -> expr + term | expr - term | term
term -> term * unary | term / unary | unary
unary -> ! unary | - unary | factor
factor -> (bool) | loc | num | real | true | false
```

### Sample Program

#### Example 1

##### input file block.t

```java
{
	int a; int b; a = 0; b = 0;
	{
		int b; b = 1;
		{
			int a; a = 2;
		}
		{
			int b; b = 3;
		}
		a = a + 1; b = b + 1;
	}
	a = a + 1; b = b + 1;
}

```

##### output file block.i

```txt
L1:	a = 0
L3:	b = 0
L4:	b = 1
L6:	a = 2
L7:	b = 3
L8:	a = a + 1
L9:	b = b + 1
L5:	a = a + 1
L10:	b = b + 1
L2:

```

#### Example 2

##### input file prog2.t

```java
{
	int i; int j; float[10][10] a;
	i = 0;
	while ( i < 10 ) {
		j = 0;
		while ( j < 10 ) {
			a[i][j] = 0;
			j = j+1;
		}
		i = i+1;
	}
	i = 0;
	while ( i < 10 ) {
		a[i][i] = 1;
		i = i+1;
	}
}
```

##### output file prog2.i

```txt
L1:	prod = 0
L3:	i = 1
L4:	t1 = i * 8
	t2 = a [ t1 ]
	t3 = i * 8
	t4 = b [ t3 ]
	t5 = t2 * t4
	prod = prod + t5
L6:	i = i + 1
L5:	if i <= 20 goto L4
L2:
```

### To build
Run `make build`

### Package lexer

class Tag. Tags distinguish tokens.
class Token with subclasses Num, Real, and Word
class Lexer, with procedure scan

### Package symbols

class Type. Put types here.
class Id. Could have put Id's with expressions; in fact Id extends Expr
class Env. Linked symbol tables.

### Package inter for intermediate code

For simplicity, the front end builds syntax trees. Three-address code is
emitted during a subsequent pass.
