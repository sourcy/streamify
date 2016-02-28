package io.sourcy.tostream;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.sourcy.tostream.StreamCollectors.*;
import static io.sourcy.tostream.ToStream.toStream;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


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

/**
 * @author daniel selinger
 * @author armin walland
 */
public class StreamCollectorsTest {
    private final TestData testData = new TestData();

    @Test
    public void testToImmutableList() {
        final List<Integer> result = toStream(testData.getAllValues()).collect(toImmutableList());
        assertThat(result, is(testData.getAllValues()));
    }

    @Test
    public void testToImmutableSet() {
        final Set<Integer> result = toStream(testData.getDistinctPositiveValues()).collect(toImmutableSet());
        assertThat(result, is(testData.getDistinctPositiveValues()));
    }

    @Test
    public void testToImmutableMapWithTuples() {
        final Map<Integer, Boolean> result = toStream(testData.getMappedPositiveValues()).collect(toImmutableMap());
        assertThat(result, is(testData.getMappedPositiveValues()));
    }

    @Test
    public void testToImmutableMapWithMappers() {
        final Map<Integer, Boolean> result = toStream(testData.getAllValues()).distinct().collect(toImmutableMap(i -> i, i -> i >= 0));
        assertThat(result, is(testData.getMappedPositiveValues()));
    }
}
