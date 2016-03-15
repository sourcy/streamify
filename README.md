# Streamify

Streamify is a small utility library that combines the power of google's 
Guava and javaslang with the standard Java 8 streaming API.

Java 8 did a good job laying the ground work for a much more fluent 
style of coding but left a few things to be desired.

Since Guava's collections are extremely helpful (you know, immutability) and 
javaslang provides a much better version of `Optional` (`Option`) plus 
additional monadic types known from functional programming (`Try`, `Either`, `Validation`)
this library aims to make using them easier in standard Java 8.


## What can i do with it?

Streamify contains 

* collectors for all immutable Guava, all javaslang, and java default collections
* toStream() methods for many data types
* toOption() and toOptional() methods for easy conversion between different optional types

## Examples

### Collecting into an immutable List

#### Plain Java
```
final List<Integer> result = ImmutableList.copyOf(Arrays.stream(new Integer[]{1, 2, 2, -3})
  .collect(Collectors.toList()));
```

#### Streamify
```
final List<Integer> result = toStream(new Integer[]{1, 2, 2, -3})
  .collect(toGuavaImmutableList());
```

### Exchanging K and V in a map

#### Plain Java
```
final Map<Integer, String> values = ImmutableMap.of(1, "asdf", 2, "bsdf", -3, "csdf");
final Map<String, Integer> result = values
  .entrySet()
  .stream()
  .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
```

#### Streamify
```
final Map<Integer, String> values = ImmutableMap.of(1, "asdf", 2, "bsdf", -3, "csdf");
final Map<String, Integer> result = toStream(values)
  .map(t -> Tuple.of(t._2, t._1))
  .collect(toGuavaImmutableMap());
```

### Flatmapping Optional into a Stream
```
Optional<Person> getPerson(final Integer id) {
  // in reality this would maybe do some lookup somewhere
  return Optional.of(new Person(id));
}
```

### Plain Java
```
final List<Person> existingPersons = ImmutableList.copyOf(IntStream.iterate(1, i -> i + 1)
  .mapToObj(Integer::valueOf)
  .map(this::getPerson)
  .filter(Optional::isPresent)
  .map(Optional::get)
  .collect(Collectors.toList()));
```

### Streamify
```
final List<Person> existingPersons = toStream(IntStream.iterate(1, i -> i + 1))
  .flatMap(i -> toStream(getPerson(i)))
  .collect(toGuavaImmutableList());
```
