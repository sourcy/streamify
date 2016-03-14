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

import com.google.common.collect.Multiset;
import javaslang.Tuple;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static io.sourcy.streamify.StreamCollectors.*;
import static io.sourcy.streamify.ToStream.toStream;
import static java.util.function.Function.identity;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author daniel selinger
 * @author armin walland
 */
public class StreamCollectorsTest {
    private final TestData testData = new TestData();

    @Test
    public void testToGuavaImmutableList() {
        final List<Integer> result = toStream(testData.getAllValues()).collect(toGuavaImmutableList());
        assertThat(result, is(testData.getAllValues()));
    }

    @Test
    public void testToGuavaImmutableSet() {
        final Set<Integer> result = toStream(testData.getDistinctPositiveValues()).collect(toGuavaImmutableSet());
        assertThat(result, is(testData.getDistinctPositiveValues()));
    }

    @Test
    public void testToGuavaSortedImmutableSet() {
        final Set<Integer> expected = testData.getAllValues().stream().distinct().collect(Collectors.toSet());
        final Set<Integer> result = toStream(testData.getAllValues()).collect(toGuavaImmutableSortedSet(Integer::compareTo));
        assertThat(result, is(expected));
    }

    @Test
    public void testToGuavaImmutableMapWithTuples() {
        final Map<Integer, Boolean> result = toStream(testData.getMappedPositiveValues()).collect(toGuavaImmutableMap());
        assertThat(result, is(testData.getMappedPositiveValues()));
    }

    @Test
    public void testToGuavaImmutableSortedMap() {
        final Map<Integer, Integer> result = toStream(testData.getAllValues())
                .distinct()
                .map(a -> Tuple.of(a, a))
                .collect(toGuavaImmutableSortedMap(Integer::compareTo));
        final Map<Integer, Integer> expected = testData.getAllValues().stream()
                .sorted()
                .distinct()
                .collect(Collectors.toMap(identity(), identity()));
        assertThat(result, is(expected));
    }

    @Test
    public void testToGuavaImmutableSortedMultiset() {
        // this will break if the hashing algorithm changes
        final Iterator<Multiset.Entry<Integer>> result = toStream(testData.getAllValues())
                .collect(toGuavaImmutableSortedMultiset(Integer::compareTo))
                .entrySet()
                .stream()
                .iterator();
        assertThat(result.next().getCount(), is(1));
        assertThat(result.next().getCount(), is(1));
        assertThat(result.next().getCount(), is(2));
    }

    @Test
    public void testToGuavaImmutableMultiset() {
        // this will break if the hashing algorithm changes
        final Iterator<Multiset.Entry<Integer>> iterator = toStream(testData.getAllValues())
                .collect(toGuavaImmutableMultiset())
                .entrySet()
                .iterator();
        assertThat(iterator.next().getCount(), is(1));
        assertThat(iterator.next().getCount(), is(2));
        assertThat(iterator.next().getCount(), is(1));
    }
}
