package io.sourcy.streamslang;

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
import javaslang.control.Either;
import javaslang.control.Try;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static io.sourcy.streamslang.StreamCollectors.*;
import static io.sourcy.streamslang.ToStream.toStream;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author daniel selinger
 * @author armin walland
 */
public class ToStreamTest {
    private final TestData testData = new TestData();

    private Optional<Integer> mapPositiveOptional(final Integer i) {
        return (i >= 0) ? Optional.of(i) : Optional.empty();
    }

    private Either<String, Integer> mapPositiveEither(final Integer i) {
        return (i >= 0) ? Either.right(i) : Either.left("Negative Number!");
    }

    @Test
    public void testToStreamArray() {
        final Integer[] a = new Integer[testData.getAllValues().size()];
        final List<Integer> result = toStream(testData.getAllValues().toArray(a)).collect(toGuavaImmutableList());
        assertThat(result, is(testData.getAllValues()));
    }

    @Test
    public void testToStreamList() {
        final List<Integer> result = toStream(testData.getAllValues()).collect(toGuavaImmutableList());
        assertThat(result, is(testData.getAllValues()));
    }

    @Test
    public void testToStreamSet() {
        final Set<Integer> result = toStream(testData.getDistinctPositiveValues()).collect(toGuavaImmutableSet());
        assertThat(result, is(testData.getDistinctPositiveValues()));
    }

    @Test
    public void testToStreamMap() {
        final Map<Integer, Boolean> result = toStream(testData.getMappedPositiveValues()).collect(toGuavaImmutableMap());
        assertThat(result, is(testData.getMappedPositiveValues()));
    }

    @Test
    public void testToStreamOptional() {
        final List<Integer> result1 = toStream(Optional.of(1)).collect(toGuavaImmutableList());
        assertThat(result1, is(ImmutableList.of(1)));

        final List<Integer> result2 = toStream(Optional.<Integer>empty()).collect(toGuavaImmutableList());
        assertThat(result2, is(ImmutableList.of()));
    }

    @Test
    public void testToStreamFlatMapOptional() {
        final List<Integer> result = toStream(testData.getAllValues())
                .flatMap(i -> toStream(mapPositiveOptional(i)))
                .collect(toGuavaImmutableList());

        assertThat(result, is(testData.getPositiveValues()));
    }

    @Test
    public void testToStreamEither() {
        final List<Integer> result1 = toStream(Either.<String, Integer>right(1)).collect(toGuavaImmutableList());
        assertThat(result1, is(ImmutableList.of(1)));

        final List<Integer> result2 = toStream(Either.<String, Integer>left("error message")).collect(toGuavaImmutableList());
        assertThat(result2, is(ImmutableList.of()));
    }

    @Test
    public void testToStreamFlatMapEither() {
        final List<Integer> result = toStream(testData.getAllValues())
                .flatMap(i -> toStream(mapPositiveEither(i)))
                .collect(toGuavaImmutableList());

        assertThat(result, is(testData.getPositiveValues()));
    }

    @Test
    public void testToStreamTryFailure() {
        final List<Integer> result = toStream(Try.<Integer>failure(new Throwable("nothing"))).collect(toGuavaImmutableList());
        assertThat(result, is(ImmutableList.<Integer>of()));
    }

    @Test
    public void testToStreamTrySuccess() {
        final List<Integer> result = toStream(Try.success(9)).collect(toGuavaImmutableList());
        assertThat(result, is(ImmutableList.of(9)));
    }
}
