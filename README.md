# Compiler

Team Members

- MALUKI MUTHUSI P15/81741/2017
- AMBANI PAULSTERN MADEGWA P15/1721/2016
- NDUNGU DANIEL GICHURU P15/133036/2018
- AHMED MOHAMED P15/102276/2017

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

`java main.Main < block.t`

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

##### input file expr1.t

```java
{
	int i; float x; bool b;
	i = 0;
	i = 365;
	x = 0.0;
	x = 3.14159;
	b = true;
	b = false;
	i = x;
	x = i;
}
```

##### output file expr1.i

```txt
L1:	i = 0
L3:	i = 365
L4:	x = 0.0
L5:	x = 3.1415896
L6:	b = true
L7:	b = false
L8:	i = x
L9:	x = i
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
