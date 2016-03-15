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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multiset;
import javaslang.Tuple;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static io.sourcy.streamify.StreamCollectors.*;
import static io.sourcy.streamify.ToStream.toStream;
import static java.util.function.Function.identity;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author daniel selinger
 * @author armin walland
 */
public class StreamCollectorsTest {

    // java collectors
    @Test
    public void testToJavaList() {
        final List<Integer> result = ImmutableList.of(1, 2, 3).stream().collect(toJavaList());
        assertThat(result, instanceOf(ArrayList.class));
        assertThat(result, is(ImmutableList.of(1, 2, 3)));
    }

    @Test
    public void testToJavaMap() {
        final Map<Integer, String> result = ImmutableList.of(Tuple.of(1, "a"), Tuple.of(2, "b")).stream().collect(toJavaMap());
        assertThat(result, instanceOf(HashMap.class));
        assertThat(result, is(ImmutableMap.of(1, "a", 2, "b")));
    }

    @Test(expected = IllegalStateException.class)
    public void testToJavaMapDuplicates() {
        final Map<Integer, String> result = ImmutableList.of(Tuple.of(1, "a"), Tuple.of(1, "a")).stream().collect(toJavaMap());
    }

    @Test
    public void testToJavaSet() {
        final Set<Integer> result = ImmutableList.of(1, 2, 3).stream().collect(toJavaSet());
        assertThat(result, instanceOf(HashSet.class));
        assertThat(result, is(ImmutableSet.of(1, 2, 3)));
    }

    @Test
    public void testToJavaSetDuplicates() {
        final Set<Integer> result = ImmutableList.of(1, 2, 2, 3).stream().collect(toJavaSet());
        assertThat(result, instanceOf(HashSet.class));
        assertThat(result, is(ImmutableSet.of(1, 2, 3)));
    }

    @Test
    public void testToGuavaImmutableBiMap() {

    }

    @Test
    public void testToGuavaImmutableClassToInstanceMap() {

    }

    @Test
    public void testToGuavaImmutableList() {

    }

    @Test
    public void testToGuavaImmutableListMultimap() {

    }

    @Test
    public void testToGuavaImmutableMap() {

    }

    @Test
    public void testToGuavaImmutableMultimap() {

    }

    @Test
    public void testToGuavaImmutableMultiset() {

    }

    @Test
    public void testToGuavaImmutableRangeMap() {

    }

    @Test
    public void testToGuavaImmutableRangeSet() {

    }

    @Test
    public void testToGuavaImmutableSet() {

    }

    @Test
    public void testToGuavaImmutableSetMultimap() {

    }

    @Test
    public void testToGuavaImmutableSortedMap() {

    }

    @Test
    public void testToGuavaImmutableSortedMultiset() {

    }

    @Test
    public void testToGuavaImmutableSortedSet() {

    }

    @Test
    public void testToGuavaImmutableTable() {

    }

    @Test
    public void testToJavaslangList() {

    }

    @Test
    public void testToJavaslangStack() {

    }

    @Test
    public void testToJavaslangStream() {

    }

    @Test
    public void testToJavaslangTree() {

    }

    @Test
    public void testToJavaslangArray() {

    }

    @Test
    public void testToJavaslangCharSeq() {

    }

    @Test
    public void testToJavaslangHashMap() {

    }

    @Test
    public void testToJavaslangHashSet() {

    }

    @Test
    public void testToJavaslangLinkedHashMap() {

    }

    @Test
    public void testToJavaslangLinkedHashSet() {

    }

    @Test
    public void testToJavaslangQueue() {

    }

    @Test
    public void testToJavaslangTreeMap() {

    }

    @Test
    public void testToJavaslangTreeSet() {

    }

    @Test
    public void testToJavaslangVector() {

    }
}