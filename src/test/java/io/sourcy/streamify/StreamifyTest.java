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
import com.google.common.collect.ImmutableTable;
import javaslang.Tuple;
import javaslang.Tuple2;
import javaslang.Tuple3;
import javaslang.control.Either;
import javaslang.control.Try;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.*;

import static io.sourcy.streamify.Streamify.stream;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author daniel selinger
 * @author armin walland
 */
public class StreamifyTest {

    // java standard types
    @Test
    public void testStreamJavaOptional() {
        final List<Integer> someResult = stream(java.util.Optional.of(1)).collect(toList());
        assertThat(someResult, is(Stream.of(1).collect(toList())));

        final List<Integer> noneResult = stream(java.util.Optional.<Integer>empty()).collect(toList());
        assertThat(noneResult, is(Stream.empty().collect(toList())));

        final List<Integer> nullResult = stream((java.util.Optional<Integer>) null).collect(toList());
        assertThat(nullResult, is(Stream.empty().collect(toList())));
    }

    @Test
    public void testStreamList() {
        final ImmutableList<Integer> l = ImmutableList.of(1, 2, 3);
        final List<Integer> someResult = stream(l).collect(toList());
        assertThat(someResult, is(l));

        final List<Integer> nullResult = stream((List<Integer>) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }

    @Test
    public void testStreamMap() {
        final List<Tuple2<Integer, String>> someResult = stream(ImmutableMap.of(1, "a", 2, "b")).collect(toList());
        assertThat(someResult, is(ImmutableList.of(Tuple.of(1, "a"), Tuple.of(2, "b"))));

        final List<Tuple2<Integer, String>> nullResult = stream((Map<Integer, String>) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }

    @Test
    public void testStreamSet() {
        final List<Integer> someResult = stream(ImmutableSet.of(1, 2, 2, 3, 3)).collect(toList());
        assertThat(someResult, is(ImmutableList.of(1, 2, 3)));

        final List<Integer> nullResult = stream((Set<Integer>) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }

    @Test
    public void testStreamArray() {
        final Integer[] a = {1, 2, 3};
        final List<Integer> someResult = stream(a).collect(toList());
        assertThat(someResult, is(ImmutableList.of(1, 2, 3)));

        final List<Integer> nullResult = stream((Integer[]) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }

    @Test
    public void testStreamIntArray() {
        final int[] a = {1, 2, 3};
        final List<Integer> someResult = stream(a).collect(toList());
        assertThat(someResult, is(ImmutableList.of(1, 2, 3)));

        final List<Integer> nullResult = stream((int[]) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }

    @Test
    public void testStreamDoubleArray() {
        final double[] a = {1d, 2d, 3d};
        final List<Double> someResult = stream(a).collect(toList());
        assertThat(someResult, is(ImmutableList.of(1d, 2d, 3d)));

        final List<Double> nullResult = stream((double[]) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }

    @Test
    public void testStreamLongArray() {
        final long[] a = {1L, 2L, 3L};
        final List<Long> someResult = stream(a).collect(toList());
        assertThat(someResult, is(ImmutableList.of(1L, 2L, 3L)));

        final List<Long> nullResult = stream((long[]) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }

    @Test
    public void testStreamStream() throws Exception {
        final List<Integer> someResult = stream(Stream.of(1, 2, 3)).collect(toList());
        assertThat(someResult, is(ImmutableList.of(1, 2, 3)));

        final List<Integer> nullResult = stream((Stream<Integer>) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }

    @Test
    public void testStreamIntStream() throws Exception {
        final List<Integer> someResult = stream(IntStream.iterate(1, i -> i + 1)).limit(3).collect(toList());
        assertThat(someResult, is(ImmutableList.of(1, 2, 3)));

        final List<Integer> nullResult = stream((IntStream) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }

    @Test
    public void testStreamLongStream() throws Exception {
        final List<Long> someResult = stream(LongStream.iterate(1, i -> i + 1)).limit(3).collect(toList());
        assertThat(someResult, is(ImmutableList.of(1L, 2L, 3L)));

        final List<Long> nullResult = stream((LongStream) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }

    @Test
    public void testStreamDoubleStream() throws Exception {
        final List<Double> someResult = stream(DoubleStream.iterate(1, i -> i + 1)).limit(3).collect(toList());
        assertThat(someResult, is(ImmutableList.of(1d, 2d, 3d)));

        final List<Double> nullResult = stream((DoubleStream) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }

    // guauva types (most of them are handled by Map, List, Set)
    @Test
    public void testStreamGuavaOptional() {
        final List<Integer> someResult = stream(com.google.common.base.Optional.of(1)).collect(toList());
        assertThat(someResult, is(Stream.of(1).collect(toList())));

        final List<Integer> noneResult = stream(com.google.common.base.Optional.<Integer>absent()).collect(toList());
        assertThat(noneResult, is(Stream.empty().collect(toList())));

        final List<Integer> nullResult = stream((com.google.common.base.Optional<Integer>) null).collect(toList());
        assertThat(nullResult, is(Stream.empty().collect(toList())));
    }

    @Test
    public void testStreamGuavaImmutableTable() {
        final ImmutableTable<Integer, Integer, String> t = new ImmutableTable.Builder<Integer, Integer, String>()
                .put(1, 1, "a").put(2, 2, "b").put(4, 4, "d").put(3, 3, "c").build();
        final List<Tuple3<Integer, Integer, String>> someResult = stream(t).collect(toList());
        assertThat(someResult, is(ImmutableList.of(Tuple.of(1, 1, "a"), Tuple.of(2, 2, "b"), Tuple.of(4, 4, "d"), Tuple.of(3, 3, "c"))));

        final List<Tuple3<Integer, Integer, String>> nullResult = stream((ImmutableTable<Integer, Integer, String>) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }

    // javaslang types (stream can handle all subtypes of @Value, just testing some)
    @Test
    public void testStreamJavaslangTry() {
        final List<Integer> failureResult = stream(Try.<Integer>failure(new Throwable("nothing"))).collect(toList());
        assertThat(failureResult, is(ImmutableList.<Integer>of()));

        final List<Integer> successResult = stream(Try.success(9)).collect(toList());
        assertThat(successResult, is(ImmutableList.of(9)));

        final List<Integer> nullResult = stream((Try<Integer>) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }

    @Test
    public void testStreamJavaslangEither() {
        final List<Integer> rightResult = stream(Either.<String, Integer>right(1)).collect(toList());
        assertThat(rightResult, is(ImmutableList.of(1)));

        final List<Integer> leftResult = stream(Either.<String, Integer>left("error message")).collect(toList());
        assertThat(leftResult, is(ImmutableList.of()));

        final List<Integer> nullResult = stream((Either<String, Integer>) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }

    @Test
    public void testStreamJavaslangList() {
        final List<Integer> someResult = stream(javaslang.collection.List.of(1, 2, 3)).collect(toList());
        assertThat(someResult, is(ImmutableList.of(1, 2, 3)));

        final List<Integer> nullResult = stream((javaslang.collection.List<Integer>) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }
  }
