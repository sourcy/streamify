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
import javaslang.control.Either;
import javaslang.control.Option;
import javaslang.control.Try;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static io.sourcy.streamify.StreamCollectors.ImmutableDefaultCollectors.*;
import static io.sourcy.streamify.ToStream.toStream;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author daniel selinger
 * @author armin walland
 */
public class ToStreamTest {

    private Optional<Integer> mapPositiveOptional(final Integer i) {
        return (i >= 0) ? Optional.of(i) : Optional.empty();
    }

    private Option<Integer> mapPositiveOption(final Integer i) {
        return (i >= 0) ? Option.some(i) : Option.none();
    }

    private Either<String, Integer> mapPositiveEither(final Integer i) {
        return (i >= 0) ? Either.right(i) : Either.left("Negative Number!");
    }

    @Test
    public void testToStreamArray() {
        final Integer[] a = new Integer[ImmutableList.of(1, 2, 2, -3).size()];
        final List<Integer> result = toStream(ImmutableList.of(1, 2, 2, -3).toArray(a)).collect(toList());
        assertThat(result, is(ImmutableList.of(1, 2, 2, -3)));
    }

    @Test
    public void testToStreamList() {
        final List<Integer> result = toStream(ImmutableList.of(1, 2, 2, -3)).collect(toList());
        assertThat(result, is(ImmutableList.of(1, 2, 2, -3)));
    }

    @Test
    public void testToStreamSet() {
        final Set<Integer> result = toStream(ImmutableSet.of(1, 2)).collect(toSet());
        assertThat(result, is(ImmutableSet.of(1, 2)));
    }

    @Test
    public void testToStreamMap() {
        final Map<Integer, Boolean> result = toStream(ImmutableMap.of(1, true, 2, true, -3, false)).collect(toMap());
        assertThat(result, is(ImmutableMap.of(1, true, 2, true, -3, false)));
    }

    @Test
    public void testToStreamOptional() {
        final List<Integer> result1 = toStream(Optional.of(1)).collect(toList());
        assertThat(result1, is(ImmutableList.of(1)));

        final List<Integer> result2 = toStream(Optional.<Integer>empty()).collect(toList());
        assertThat(result2, is(ImmutableList.of()));
    }

    @Test
    public void testToStreamFlatMapOptional() {
        final List<Integer> result = toStream(ImmutableList.of(1, 2, 2, -3))
                .flatMap(i -> toStream(mapPositiveOptional(i)))
                .collect(toList());

        assertThat(result, is(ImmutableList.of(1, 2, 2)));
    }

    @Test
    public void testToStreamOption() {
        final List<Integer> result1 = toStream(Option.of(1)).collect(toList());
        assertThat(result1, is(ImmutableList.of(1)));

        final List<Integer> result2 = toStream(Option.<Integer>none()).collect(toList());
        assertThat(result2, is(ImmutableList.of()));
    }

    @Test
    public void testToStreamFlatMapOption() {
        final List<Integer> result = toStream(ImmutableList.of(1, 2, 2, -3))
                .flatMap(i -> toStream(mapPositiveOption(i)))
                .collect(toList());

        assertThat(result, is(ImmutableList.of(1, 2, 2)));
    }

    @Test
    public void testToStreamEither() {
        final List<Integer> result1 = toStream(Either.<String, Integer>right(1)).collect(toList());
        assertThat(result1, is(ImmutableList.of(1)));

        final List<Integer> result2 = toStream(Either.<String, Integer>left("error message")).collect(toList());
        assertThat(result2, is(ImmutableList.of()));
    }

    @Test
    public void testToStreamFlatMapEither() {
        final List<Integer> result = toStream(ImmutableList.of(1, 2, 2, -3))
                .flatMap(i -> toStream(mapPositiveEither(i)))
                .collect(toList());

        assertThat(result, is(ImmutableList.of(1, 2, 2)));
    }

    @Test
    public void testToStreamTryFailure() {
        final List<Integer> result = toStream(Try.<Integer>failure(new Throwable("nothing"))).collect(toList());
        assertThat(result, is(ImmutableList.<Integer>of()));
    }

    @Test
    public void testToStreamTrySuccess() {
        final List<Integer> result = toStream(Try.success(9)).collect(toList());
        assertThat(result, is(ImmutableList.of(9)));
    }
}
