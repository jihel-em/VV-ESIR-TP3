# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer
1. Les calculs sur les floats ne sont pas exacts. En effet, ici, 3*.4 donne 1.2000000000000002, ce qui est différent de 1,2. Il faut donc utiliser `assertEquals(expected, actual, delta)`, où `delta` est un flottant ; on vérifie alors que `expected` est égal à `actual` à plus ou moins `delta` près.

2.`assertEquals` vérifie que deux objets ont la même valeur, alors que `assertSame` vérifie que deux objets sont les mêmes (même addresse).
```@Test
	void testSameResults() {
		ArrayList<Integer> a1 = new ArrayList<Integer>();
		ArrayList<Integer> a2 = a1;
		for(int i=0; i<10; i++) {
			a1.add(i);
		}
		assertEquals(a1,a2);
		assertSame(a1,a2);
	}
	
	@Test
	void testDifferentResults() {
		ArrayList<Integer> a1 = new ArrayList<Integer>();
		ArrayList<Integer> a2 = new ArrayList<Integer>();
		for(int i=0; i<10; i++) {
			a1.add(i);
			a2.add(i);
		}
		assertEquals(a1,a2);
		assertSame(a1,a2);
	}```
