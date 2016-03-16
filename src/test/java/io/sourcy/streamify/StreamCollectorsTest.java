package io.sourcy.streamify;

/*
 * Copyright (c) Sourcy Software & Services GmbH 2016.
 *
 *     _____ ____   __  __ _____ _____ __  __    (_)____
 *    / ___// __ \ / / / // ___// ___// / / /   / // __ \
 *   (__  )/ /_/ // /_/ // /   / /__ / /_/ /_  / // /_/ /
 *  /____/ \____/ \__,_//_/    \___/ \__, /(_)/_/ \____/
 *                                  /____/
 *
 * Created by daniel selinger <d.selinger@sourcy.io> on 2016-02-27.
 */

import com.google.common.collect.*;
import javaslang.Tuple;
import javaslang.Tuple2;
import javaslang.collection.*;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;

import static io.sourcy.streamify.StreamCollectors.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author daniel selinger
 * @author armin walland
 */
public class StreamCollectorsTest {

    // mutable default collectors
    @Test
    public void testMutableToList() {
        final List<Integer> result =
                ImmutableList.of(1, 2, 3).stream().collect(MutableDefaultCollectors.toList());
        assertThat(result, is(ImmutableList.of(1, 2, 3)));
    }

    @Test
    public void testMutableToMap() {
        final Map<Integer, String> result =
                ImmutableList.of(Tuple.of(1, "a"), Tuple.of(2, "b")).stream().collect(MutableDefaultCollectors.toMap());
        assertThat(result, is(ImmutableMap.of(1, "a", 2, "b")));
    }

    @Test
    public void testMutableToSet() {
        final Set<Integer> result =
                ImmutableList.of(1, 2, 2, 3, 3).stream().collect(MutableDefaultCollectors.toSet());
        assertThat(result, is(ImmutableSet.of(1, 2, 3)));
    }

    // immutable default collectors
    @Test
    public void testImmutableToList() {
        final List<Integer> result =
                ImmutableList.of(1, 2, 3).stream().collect(ImmutableDefaultCollectors.toList());
        assertThat(result, is(ImmutableList.of(1, 2, 3)));
    }

    @Test
    public void testImmutableToMap() {
        final Map<Integer, String> result =
                ImmutableList.of(Tuple.of(1, "a"), Tuple.of(2, "b")).stream().collect(ImmutableDefaultCollectors.toMap());
        assertThat(result, is(ImmutableMap.of(1, "a", 2, "b")));
    }

    @Test
    public void testImmutableToSet() {
        final Set<Integer> result =
                ImmutableList.of(1, 2, 2, 3, 3).stream().collect(ImmutableDefaultCollectors.toSet());
        assertThat(result, is(ImmutableSet.of(1, 2, 3)));
    }

    // java collectors
    @Test
    public void testToJavaList() {
        final List<Integer> result =
                ImmutableList.of(1, 2, 3).stream().collect(toJavaList());
        assertThat(result, is(ImmutableList.of(1, 2, 3)));
    }

    @Test
    public void testToJavaMap() {
        final Map<Integer, String> result =
                ImmutableList.of(Tuple.of(1, "a"), Tuple.of(2, "b")).stream().collect(toJavaMap());
        assertThat(result, is(ImmutableMap.of(1, "a", 2, "b")));
    }

    @Test
    public void testToJavaSet() {
        final Set<Integer> result =
                ImmutableList.of(1, 2, 2, 3, 3).stream().collect(toJavaSet());
        assertThat(result, is(ImmutableSet.of(1, 2, 3)));
    }

    // guava collectors
    @Test
    public void testToGuavaImmutableBiMap() {
        final ImmutableBiMap<Integer, String> result =
                ImmutableList.of(Tuple.of(1, "a"), Tuple.of(2, "b")).stream().collect(toGuavaImmutableBiMap());
        assertThat(result, is(ImmutableBiMap.of(1, "a", 2, "b")));
    }

    @Test
    public void testToGuavaImmutableList() {
        final ImmutableList<Integer> result =
                ImmutableList.of(1, 2, 3).stream().collect(toGuavaImmutableList());
        assertThat(result, is(ImmutableList.of(1, 2, 3)));
    }

    @Test
    public void testToGuavaImmutableListMultimap() {
        final ImmutableListMultimap<Integer, String> result =
                ImmutableList.of(Tuple.of(1, "a1"), Tuple.of(2, "b1"), Tuple.of(2, "b2"), Tuple.of(1, "a2")).stream().collect(toGuavaImmutableListMultimap());
        assertThat(result, is(ImmutableListMultimap.of(1, "a1", 1, "a2", 2, "b1", 2, "b2")));
    }

    @Test
    public void testToGuavaImmutableMap() {
        final ImmutableMap<Integer, String> result =
                ImmutableList.of(Tuple.of(1, "a"), Tuple.of(2, "b")).stream().collect(toGuavaImmutableMap());
        assertThat(result, is(ImmutableMap.of(1, "a", 2, "b")));
    }

    @Test
    public void testToGuavaImmutableMultiset() {
        final ImmutableMultiset<Integer> result =
                ImmutableList.of(1, 2, 2, 3, 3).stream().collect(toGuavaImmutableMultiset());
        assertThat(result, is(ImmutableMultiset.of(1, 2, 2, 3, 3)));
    }

    @Test
    public void testToGuavaImmutableRangeMap() {
        final ImmutableRangeMap<Integer, String> result =
                ImmutableList.of(Tuple.of(Range.closed(1, 10), "a"), Tuple.of(Range.greaterThan(10), "b")).stream().collect(toGuavaImmutableRangeMap());
        final ImmutableRangeMap<Integer, String> expected = new ImmutableRangeMap.Builder<Integer, String>()
                .put(Range.closed(1, 10), "a").put(Range.greaterThan(10), "b").build();
        assertThat(result, is(expected));
    }

    @Test
    public void testToGuavaImmutableRangeSet() {
        final ImmutableRangeSet<Integer> result =
                ImmutableList.of(Range.closed(1, 10), Range.greaterThan(10)).stream().collect(toGuavaImmutableRangeSet());
        final ImmutableRangeSet<Integer> expected = new ImmutableRangeSet.Builder<Integer>()
                .add(Range.closed(1, 10)).add(Range.greaterThan(10)).build();
        assertThat(result, is(expected));
    }

    @Test
    public void testToGuavaImmutableSet() {
        final ImmutableSet<Integer> result =
                ImmutableList.of(1, 2, 2, 3, 3).stream().collect(toGuavaImmutableSet());
        assertThat(result, is(ImmutableSet.of(1, 2, 3)));
    }

    @Test
    public void testToGuavaImmutableSetMultimap() {
        final ImmutableSetMultimap<Integer, String> result =
                ImmutableList.of(Tuple.of(1, "a1"), Tuple.of(2, "b1"), Tuple.of(2, "b2"), Tuple.of(1, "a2")).stream().collect(toGuavaImmutableSetMultimap());
        assertThat(result, is(ImmutableSetMultimap.of(1, "a1", 1, "a2", 2, "b1", 2, "b2")));
    }

    @Test
    public void testToGuavaImmutableSortedMap() {
        final ImmutableSortedMap<Integer, String> result =
                ImmutableList.of(Tuple.of(2, "b"), Tuple.of(1, "a"), Tuple.of(4, "d"), Tuple.of(3, "c")).stream().collect(toGuavaImmutableSortedMap(Ordering.natural()));
        assertThat(result, is(ImmutableSortedMap.of(3, "c", 1, "a", 4, "d", 2, "b")));
    }

    @Test
    public void testToGuavaImmutableSortedMultiset() {
        final ImmutableSortedMultiset<Integer> result =
                ImmutableList.of(2, 3, 1, 2, 2, 3, 3).stream().collect(toGuavaImmutableSortedMultiset(Ordering.natural()));
        assertThat(result, is(ImmutableSortedMultiset.of(3, 1, 2, 2, 3, 3, 2)));
    }

    @Test
    public void testToGuavaImmutableSortedSet() {
        final ImmutableSortedSet<Integer> result =
                ImmutableList.of(2, 3, 1).stream().collect(toGuavaImmutableSortedSet(Ordering.natural()));
        assertThat(result, is(ImmutableSortedSet.of(1, 2, 3)));
    }

    @Test
    public void testToGuavaImmutableTable() {
        final ImmutableTable<Integer, Integer, String> result =
                ImmutableList.of(Tuple.of(1, 1, "a"), Tuple.of(2, 2, "b"), Tuple.of(4, 4, "d"), Tuple.of(3, 3, "c")).stream().collect(toGuavaImmutableTable());
        final ImmutableTable<Integer, Integer, String> expected = new ImmutableTable.Builder<Integer, Integer, String>()
                .put(1, 1, "a").put(2, 2, "b").put(4, 4, "d").put(3, 3, "c").build();
        assertThat(result, is(expected));
    }


    // javaslang tests (i sell my soul for coverage!)
    @Test
    public void testToJavaslangList() {
        Collector<Object, ?, javaslang.collection.List<Object>> objectListCollector = toJavaslangList();
    }

    @Test
    public void testToJavaslangStack() {
        Collector<Object, ?, Stack<Object>> objectStackCollector = toJavaslangStack();
    }

    @Test
    public void testToJavaslangStream() {
        Collector<Object, ?, Stream<Object>> objectStreamCollector = toJavaslangStream();
    }

    @Test
    public void testToJavaslangTree() {
        Collector<Object, ?, Tree<Object>> objectTreeCollector = toJavaslangTree();
    }

    @Test
    public void testToJavaslangArray() {
        Collector<Object, ?, Array<Object>> objectArrayCollector = toJavaslangArray();
    }

    @Test
    public void testToJavaslangCharSeq() {
        Collector<Character, ?, CharSeq> characterCharSeqCollector = toJavaslangCharSeq();
    }

    @Test
    public void testToJavaslangHashMap() {
        Collector<Tuple2<Object, Object>, ?, HashMap<Object, Object>> tuple2HashMapCollector = toJavaslangHashMap();
    }

    @Test
    public void testToJavaslangHashSet() {
        Collector<Object, ?, HashSet<Object>> objectHashSetCollector = toJavaslangHashSet();
    }

    @Test
    public void testToJavaslangLinkedHashMap() {
        Collector<Tuple2<Object, Object>, ?, LinkedHashMap<Object, Object>> tuple2LinkedHashMapCollector = toJavaslangLinkedHashMap();
    }

    @Test
    public void testToJavaslangLinkedHashSet() {
        Collector<Object, ?, LinkedHashSet<Object>> objectLinkedHashSetCollector = toJavaslangLinkedHashSet();
    }

    @Test
    public void testToJavaslangQueue() {
        Collector<Object, ?, Queue<Object>> objectQueueCollector = toJavaslangQueue();
    }

    @Test
    public void testToJavaslangTreeMap() {
        Collector<Tuple2<Object, Object>, ?, TreeMap<Object, Object>> tuple2TreeMapCollector = toJavaslangTreeMap();
    }

    @Test
    public void testToJavaslangTreeSet() {
        Collector<Object, ?, TreeSet<Object>> objectTreeSetCollector = toJavaslangTreeSet();
    }

    @Test
    public void testToJavaslangVector() {
        Collector<Object, ?, Vector<Object>> objectVectorCollector = toJavaslangVector();
    }
}
