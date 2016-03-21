[![Build Status](https://travis-ci.org/sourcy/streamify.svg?branch=master)](https://travis-ci.org/sourcy/streamify)
[![codecov.io](https://codecov.io/github/sourcy/streamify/coverage.svg?branch=master)](https://codecov.io/github/sourcy/streamify?branch=master)

# Streamify

Streamify is a small utility library that combines the power of google's 
Guava and Javaslang with the standard Java 8 streaming API.

Java 8 did a good job laying the ground work for a much more fluent 
style of coding but left a few things to be desired.

Guava's collections are extremely helpful (immutable and implementing default collection interfaces)
and Javaslang provides a much better version of `Optional` (`Option`) plus
additional monadic types known from functional programming (`Try`, `Either`, `Validation`)
and a myriad of other wonderful things like `Tuple`, `Future`, pattern matching and a whole new collection library.

This library aims to make using and mixing them easier in standard Java 8.

## Maven

```
<dependency>
    <groupId>io.sourcy</groupId>
    <artifactId>streamify</artifactId>
    <version>streamifyVersion</version>
</dependency>
<dependency>
    <groupId>com.google.guava</groupId>
    <artifactId>guava</artifactId>
    <version>guavaVersion</version>
</dependency>
<dependency>
    <groupId>com.javaslang</groupId>
    <artifactId>javaslang</artifactId>
    <version>javaslangVersionTwoRC3OrGreater</version>
</dependency>
```

## What can i do with it?

Streamify contains

* `Collector` implementations for most immutable Guava, all Javaslang, and Java default collection types, brought together in one consistent naming schema
* `stream(data)` conversion functions to make a (Java) `Stream` out of those collections and other monadic types

This basically boils down to the following usage pattern:

* Use `stream(data)` instead of the Java and Javaslang default methods. This gives you one consistent way to create a (Java) `Stream` from pretty much anything. All those are Null-safe and return empty Streams instead of exploding into your face.
* Use the Java streaming API and Javaslang monads to transform the stream
* You want your data back? Just use one of the provided collectors to produce a collection of your liking. No more `copyOf`, `asList`, `ofAll` and the other 10 different variants you can't remember when you need them.


## Examples

### Collecting into an immutable List

#### Plain Java
```java
final List<Integer> result = ImmutableList.copyOf(Arrays.stream(new Integer[]{1, 2, 2, -3})
  .collect(Collectors.toList()));
```

#### Streamify

As you can see there is a `stream()` function that directly supports Arrays and the Stream is collected directly into a Guava `ImmutableList`.

```java
final List<Integer> result = stream(new Integer[]{1, 2, 2, -3})
  .collect(toGuavaImmutableList()); // this can be shortened to toList() by another static import, read further down for details
```

### Working with Maps

Let's switch the key and value of a Map.

#### Plain Java
```java
final Map<Integer, String> values = ImmutableMap.of(1, "asdf", 2, "bsdf", -3, "csdf");
final Map<String, Integer> result = values
  .entrySet()
  .stream()
  .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
```

#### Streamify

Note that the `stream()` functions for Collections with more than one type parameter, first and foremost `Map` return a `Stream<Tuple>` instead of using the tedious java default `Map.Entry` interface.

```java
final Map<Integer, String> values = ImmutableMap.of(1, "asdf", 2, "bsdf", -3, "csdf");
final Map<String, Integer> result = stream(values)
  .map(t -> Tuple.of(t._2, t._1))
  .collect(toGuavaImmutableMap());
```
You can leverage the full power of Javaslang Tuples within the streaming API.

### Flatmapping Optional/Option into a Stream
```java
Optional<Person> getPerson(final Integer id) {
  // in reality this would maybe do some lookup somewhere
  return Optional.of(new Person(id));
}
```

#### Plain Java
```java
final List<Person> existingPersons = ImmutableList.copyOf(IntStream.iterate(1, i -> i + 1)
  .mapToObj(Integer::valueOf)
  .limit(3) 
  .map(this::getPerson)
  .filter(Optional::isPresent)
  .map(Optional::get)
  .collect(Collectors.toList()));
```

#### Streamify

Note that `stream(IntStream)` also creates a `Stream<Integer>` from the `IntStream`. This also works for `LongStream`, `DoubleStream`, `int[]`, `long[]` and `double[]`.

```java
final List<Person> existingPersons = stream(IntStream.iterate(1, i -> i + 1))
  .limit(3)
  .flatMap(i -> stream(getPerson(i)))
  .collect(toGuavaImmutableList());
```
Flatmapping the other Javaslang monadic types like `Either` and `Try` works exactly the same.

## I want this! What do i need to do?

### Stream conversion

Just import

```java
import static io.sourcy.streamify.Streamify.stream;
```

and you are ready to go.


### Collectors

All collectors can be statically imported without the pain of name conflicts.

A simple
```java
import static io.sourcy.streamify.StreamifyCollectors.*;
```
and you will have all
```java
toJava...()
toGuava...()
toJavaslang...()
```
collectors at your disposal.

If you like it short, there are 3 additional Classes you can statically import (one of them) if you want.
```java
import static io.sourcy.streamify.StreamifyCollectors.GuavaDefaultCollectors.*;
```
This will give you immutable guava versions of ```toList()```, ```toMap()``` and ```toSet()```.

If you prefer the mutable counterparts you can import
```java
import static io.sourcy.streamify.StreamifyCollectors.JavaDefaultCollectors.*;
```
This will mostly give you the Java 8 default mutable collectors but with a version of `toMap()` that works with Javaslang Tuples.

If you primarly use the Javaslang collections you will prefer to import
```java
import static io.sourcy.streamify.StreamifyCollectors.JavaslangDefaultCollectors.*;
```
This will give you collectors for the Javaslang variants of `List`, `Map` and `Set`.
Note: If you don't yet know Javaslang, those are NOT compatible with the java standard collections with the same name!
