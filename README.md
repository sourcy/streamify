# Streamify

Streamify is a small utility library that combines the power of google's 
Guava and javaslang with the standard Java 8 streaming API.

Java 8 did a good job laying the ground work for a much more fluent 
style of coding but left a few things to be desired.

Since Guava's collections are extremely helpful (you know, immutability) and 
javaslang provides a much better version of `Optional` (`Option`) plus 
additional monadic types known from functional programming (`Try`, `Either`, `Validation`)
plus a myriad more wonderful things.

This library aims to make using them easier in standard Java 8.

## What can i do with it?

Streamify contains

* `Collector` implementations for most immutable Guava, all javaslang, and java default collection types, brought together in one consistent naming schema
* `toStream()` conversion functions to make a (Java) `Stream` out of those collection types
* `toOption()` and `toOptional()` methods for easy conversion between different optional types

This basically boils down to the following usage pattern:

* Use `toStream(data)` instead of the Java and Javaslang default methods. This gives you one consistent way to create a (Java) `Stream` from pretty much everything. All those are Null-safe and return empty Streams instead of exploding into your face.
* Use (Javaslang) `toOption(data)` or (Java) `toOptional(data)` (depending on which you prefer) to convert from the other Optional types and raw data e.g. `toOption(someIntegerVariable)` to the type you prefer to work with. Those are also Null-safe and give you an empty Optional / None if need to.
* You want your data back? Just use one of the provided collectors to produce a collection of your liking. No more `copyOf`, `asList`, `ofAll` and the other 10 different variants you can't remember when you need them.


## Examples

### Collecting into an immutable List

#### Plain Java
```java
final List<Integer> result = ImmutableList.copyOf(Arrays.stream(new Integer[]{1, 2, 2, -3})
  .collect(Collectors.toList()));
```

#### Streamify

As you can see there is a `toStream()` function that directly supports Arrays and the Stream gets directly collected into an Guava `ImmutableList`.

```java
final List<Integer> result = toStream(new Integer[]{1, 2, 2, -3})
  .collect(toGuavaImmutableList());
```

### Working with Maps

Lets exchange the key and value of a Map.

#### Plain Java
```java
final Map<Integer, String> values = ImmutableMap.of(1, "asdf", 2, "bsdf", -3, "csdf");
final Map<String, Integer> result = values
  .entrySet()
  .stream()
  .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
```

#### Streamify

Note that the `toStream()` functions for Collections with more than one type parameter, first and foremost `Map` return a `Stream<Tuple>` instead of using the tedious java default `Map.Entry` interface.

```java
final Map<Integer, String> values = ImmutableMap.of(1, "asdf", 2, "bsdf", -3, "csdf");
final Map<String, Integer> result = toStream(values)
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
  .map(this::getPerson)
  .filter(Optional::isPresent)
  .map(Optional::get)
  .collect(Collectors.toList()));
```

#### Streamify
```java
final List<Person> existingPersons = toStream(IntStream.iterate(1, i -> i + 1))
  .flatMap(i -> toStream(getPerson(i)))
  .collect(toGuavaImmutableList());
```
Flatmapping the other Javaslang value types like `Either` and `Try` works exactly the same.

### Creating and converting between Option types

TODO

## I want this! What do i need to do?

### Collectors

All collectors can be statically included without the pain of name conflicts.

A simple

```java
import static io.sourcy.streamify.StreamCollectors.*;
```

and you will have all

```java
toJava...()
toGuava...()
toJavaslang...()
```

collectors at your disposal.

If you like it short, there are 2 additional Classes you can import if you want.

```java
import static io.sourcy.streamify.StreamCollectors.ImmutableDefaultCollectors.*;
```
This will give you immutable versions of ```toList()```, ```toMap()``` and ```toSet()```.

If you prefer the mutable counterparts you can import
```java
import static io.sourcy.streamify.StreamCollectors.MutableDefaultCollectors.*;
```
This will mostly give you the Java 8 default mutable collectors but with a version of `toMap()` that works with Tuples.