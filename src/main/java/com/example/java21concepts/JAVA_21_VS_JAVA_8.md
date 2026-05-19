# Java 21 Practice With Java 8 Comparison

This package is for practicing important Java 21 concepts while comparing them with a Java 8 style implementation.

## Packages

| Package | Purpose |
| --- | --- |
| `com.example.java21concepts` | Modern Java examples using Java 21 language and library features. |
| `com.example.java8comparison` | Older Java 8 style examples that solve similar problems with more boilerplate. |

## How to Compile

From the project root:

```sh
javac -d build/classes $(find src/main/java -name "*.java")
```

## How to Run

Run Java 21 examples:

```sh
java -cp build/classes com.example.java21concepts.Java21Practice
```

Run Java 8 comparison examples:

```sh
java -cp build/classes com.example.java8comparison.Java8ComparisonPractice
```

## Concepts Covered

| Concept | Java 21 Package | Java 8 Comparison Package | What to Notice |
| --- | --- | --- | --- |
| Data carriers | `record Student` | `Student8` class with fields, constructor, getters, and `toString` | Records remove boilerplate for immutable data. |
| Multi-line strings | Text blocks with `"""` | String concatenation with `+` | Text blocks are easier to read for formatted output. |
| Restricted hierarchies | `sealed interface Payment` | Open `Payment8` interface | Sealed types let the compiler know all permitted implementations. |
| Type checks | Pattern matching `switch` | `instanceof` plus explicit casts | Pattern matching keeps type handling compact and safer. |
| Ordered collections | `SequencedCollection` with `getFirst`, `getLast`, `reversed` | Manual index access and `Collections.reverse` | Java 21 gives common first, last, and reverse operations a shared API. |
| Concurrency | Virtual threads | Fixed thread pool | Virtual threads are lightweight and useful for blocking tasks at high scale. |

## Practice Exercises

1. Add a new payment type called `WalletPayment` in both packages.
2. In the Java 21 package, update the sealed `Payment` interface and the pattern matching `switch`.
3. In the Java 8 package, update the `Payment8` hierarchy and the `instanceof` chain.
4. Add one more field to `Student` and `Student8`, then compare how much code changes.
5. Increase the virtual thread and fixed thread pool task count from `3` to `100`, then observe the code difference.

## Key Takeaway

Java 21 keeps the intent closer to the business idea: model data with records, model known alternatives with sealed types, handle alternatives with pattern matching, use clear text blocks, and reach for virtual threads when many blocking tasks need to run concurrently.
