package io.sourcy.streamslang;

/*
 * Copyright (c) Sourcy Software & Services GmbH $today.year.
 *
 *     _____ ____   __  __ _____ _____ __  __    (_)____
 *    / ___// __ \ / / / // ___// ___// / / /   / // __ \
 *   (__  )/ /_/ // /_/ // /   / /__ / /_/ /_  / // /_/ /
 *  /____/ \____/ \__,_//_/    \___/ \__, /(_)/_/ \____/
 *                                  /____/
 *
 * Created by daniel selinger <d.selinger@sourcy.io> on 2016-02-27.
 */

import javaslang.Tuple;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static io.sourcy.streamslang.StreamCollectors.*;
import static io.sourcy.streamslang.ToStream.toStream;
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
}
