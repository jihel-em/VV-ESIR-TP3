# Balanced strings

A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed symbol `)]}` and the substrings before, after and between each pair of symbols is also balanced. The empty string is considered as balanced.

For example: `{[][]}({})` is balanced, while `][`, `([)]`, `{`, `{(}{}` are not.

Implement the following method:

```java
public static boolean isBalanced(String str) {
    ...
}
```

`isBalanced` returns `true` if `str` is balanced according to the rules explained above. Otherwise, it returns `false`.

Use the coverage criteria studied in classes as follows:

1. Use input space partitioning to design an initial set of inputs. Explain below the characteristics and partition blocks you identified.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written so far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Write below the actions you took on each step and the results you obtained.
Use the project in [tp3-balanced-strings](../code/tp3-balanced-strings) to complete this exercise.

## Answer
```Java
public static boolean isBalanced(String str) {
    Stack<Character> s = new Stack<Character>();
    for(int i=0; i<str.length(); i++) {
        char currentChar = str.charAt(i);

        if(currentChar=='(' || currentChar=='[' || currentChar=='{') {
            s.add(currentChar);
        }
        else {

            if(currentChar==')') {
                if(s.isEmpty()) return false;
                char previousSymbol = s.pop();
                if(previousSymbol!='(') {
                    return false;
                }
            }

            if(currentChar==']') {
                if(s.isEmpty()) return false;
                char previousSymbol = s.pop();
                if(previousSymbol!='[') {
                    return false;
                }
            }

            if(currentChar=='}') {
                if(s.isEmpty()) return false;
                char previousSymbol = s.pop();
                if(previousSymbol!='{') {
                    return false;
                }
            }
        }
    }
    if(s.size()==0) return true;
    return false;
}
```

1. Dans les tests suivants, on a testé les chaînes alphanumériques :
- sans symboles parenthèse, bracket ou accolade ;
- qui commencent par un symbole fermant ;
- qui ne contiennent qu'un des trois types de symbole, dans le cas simple où il n'y a qu'une paire ;
- qui ne contiennent qu'un des trois types de symbole, où il est plusieurs fois et entremêlé correctement ;
- qui contiennent un entremêlement correct ou non des trois symboles, avec ou sans autres caractères.

```Java
@Test
void testIsBalancedNoSymbol() {
    String s1 = "";
    String s2 = "lucas";
    assertTrue(Main.isBalanced(s1));
    assertTrue(Main.isBalanced(s2));
}

@Test
void testIsBalancedStartsWithClosingSymbol() {
    String s1 = "][";
    String s2 = ")(";
    String s3 = "}{";
    assertFalse(Main.isBalanced(s1));
    assertFalse(Main.isBalanced(s2));
    assertFalse(Main.isBalanced(s3));
}

@Test
void testIsBalancedOnlyOneSymbolSimple() {
    String s1 = "[]";
    String s2 = "()";
    String s3 = "{}";
    assertTrue(Main.isBalanced(s1));
    assertTrue(Main.isBalanced(s2));
    assertTrue(Main.isBalanced(s3));
}

@Test
void testIsBalancedOnlyOneSymbol() {
    String s1 = "[[][][[]]]";
    String s2 = "()(()(()))";
    String s3 = "{{}{}{{}}}";
    assertTrue(Main.isBalanced(s1));
    assertTrue(Main.isBalanced(s2));
    assertTrue(Main.isBalanced(s3));
}

@Test
void testIsBalancedCorrectMultipleSymbols() {
    String s = "([]{{}()})";
    assertTrue(Main.isBalanced(s));
}

@Test
void testIsBalancedIncorrectMultipleSymbols() {
    String s = "({[}])";
    assertFalse(Main.isBalanced(s));
}

@Test
void testIsBalancedCorrectWithCharacters() {
    String s = "(zdqzf[gqzg]jfkjS{KIUBf{qkzyf}Huqyfy(mpfuq iqohfb) oiseufb! }qf?);;";
    assertTrue(Main.isBalanced(s));
}

@Test
void testIsBalancedIncorrectWithCharacters() {
    String s = "zhjqgb (fqekje %%{filn!![oigiubn%}klfh!]qzflmn;)zqlkufb;";
    assertFalse(Main.isBalanced(s));
}
```

2.
