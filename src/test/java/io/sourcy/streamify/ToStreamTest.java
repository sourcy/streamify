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

import static io.sourcy.streamify.ToStream.toStream;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author daniel selinger
 * @author armin walland
 */
public class ToStreamTest {

    // java standard types
    @Test
    public void testToStreamJavaOptional() {
        final List<Integer> someResult = toStream(java.util.Optional.of(1)).collect(toList());
        assertThat(someResult, is(Stream.of(1).collect(toList())));

        final List<Integer> noneResult = toStream(java.util.Optional.<Integer>empty()).collect(toList());
        assertThat(noneResult, is(Stream.empty().collect(toList())));

        final List<Integer> nullResult = toStream((java.util.Optional<Integer>) null).collect(toList());
        assertThat(nullResult, is(Stream.empty().collect(toList())));
    }

    @Test
    public void testToStreamList() {
        final ImmutableList<Integer> l = ImmutableList.of(1, 2, 3);
        final List<Integer> someResult = toStream(l).collect(toList());
        assertThat(someResult, is(l));

        final List<Integer> nullResult = toStream((List<Integer>) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }

    @Test
    public void testToStreamMap() {
        final List<Tuple2<Integer, String>> someResult = toStream(ImmutableMap.of(1, "a", 2, "b")).collect(toList());
        assertThat(someResult, is(ImmutableList.of(Tuple.of(1, "a"), Tuple.of(2, "b"))));

        final List<Tuple2<Integer, String>> nullResult = toStream((Map<Integer, String>) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }

    @Test
    public void testToStreamSet() {
        final List<Integer> someResult = toStream(ImmutableSet.of(1, 2, 2, 3, 3)).collect(toList());
        assertThat(someResult, is(ImmutableList.of(1, 2, 3)));

        final List<Integer> nullResult = toStream((Set<Integer>) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }

    @Test
    public void testToStreamArray() {
        final Integer[] a = {1, 2, 3};
        final List<Integer> someResult = toStream(a).collect(toList());
        assertThat(someResult, is(ImmutableList.of(1, 2, 3)));

        final List<Integer> nullResult = toStream((Integer[]) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }

    @Test
    public void testToStreamIntArray() {
        final int[] a = {1, 2, 3};
        final List<Integer> someResult = toStream(a).collect(toList());
        assertThat(someResult, is(ImmutableList.of(1, 2, 3)));

        final List<Integer> nullResult = toStream((int[]) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }

    @Test
    public void testToStreamDoubleArray() {
        final double[] a = {1d, 2d, 3d};
        final List<Double> someResult = toStream(a).collect(toList());
        assertThat(someResult, is(ImmutableList.of(1d, 2d, 3d)));

        final List<Double> nullResult = toStream((double[]) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }

    @Test
    public void testToStreamLongArray() {
        final long[] a = {1L, 2L, 3L};
        final List<Long> someResult = toStream(a).collect(toList());
        assertThat(someResult, is(ImmutableList.of(1L, 2L, 3L)));

        final List<Long> nullResult = toStream((long[]) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }

    @Test
    public void testToStreamStream() throws Exception {
        final List<Integer> someResult = toStream(Stream.of(1, 2, 3)).collect(toList());
        assertThat(someResult, is(ImmutableList.of(1, 2, 3)));

        final List<Integer> nullResult = toStream((Stream<Integer>) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }

    @Test
    public void testToStreamIntStream() throws Exception {
        final List<Integer> someResult = toStream(IntStream.iterate(1, i -> i + 1)).limit(3).collect(toList());
        assertThat(someResult, is(ImmutableList.of(1, 2, 3)));

        final List<Integer> nullResult = toStream((IntStream) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }

    @Test
    public void testToStreamLongStream() throws Exception {
        final List<Long> someResult = toStream(LongStream.iterate(1, i -> i + 1)).limit(3).collect(toList());
        assertThat(someResult, is(ImmutableList.of(1L, 2L, 3L)));

        final List<Long> nullResult = toStream((LongStream) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }

    @Test
    public void testToStreamDoubleStream() throws Exception {
        final List<Double> someResult = toStream(DoubleStream.iterate(1, i -> i + 1)).limit(3).collect(toList());
        assertThat(someResult, is(ImmutableList.of(1d, 2d, 3d)));

        final List<Double> nullResult = toStream((DoubleStream) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }

    // guauva types (most of them are handled by Map, List, Set)
    @Test
    public void testToStreamGuavaOptional() {
        final List<Integer> someResult = toStream(com.google.common.base.Optional.of(1)).collect(toList());
        assertThat(someResult, is(Stream.of(1).collect(toList())));

        final List<Integer> noneResult = toStream(com.google.common.base.Optional.<Integer>absent()).collect(toList());
        assertThat(noneResult, is(Stream.empty().collect(toList())));

        final List<Integer> nullResult = toStream((com.google.common.base.Optional<Integer>) null).collect(toList());
        assertThat(nullResult, is(Stream.empty().collect(toList())));
    }

    @Test
    public void testToStreamGuavaImmutableTable() {
        final ImmutableTable<Integer, Integer, String> t = new ImmutableTable.Builder<Integer, Integer, String>()
                .put(1, 1, "a").put(2, 2, "b").put(4, 4, "d").put(3, 3, "c").build();
        final List<Tuple3<Integer, Integer, String>> someResult = toStream(t).collect(toList());
        assertThat(someResult, is(ImmutableList.of(Tuple.of(1, 1, "a"), Tuple.of(2, 2, "b"), Tuple.of(4, 4, "d"), Tuple.of(3, 3, "c"))));

        final List<Tuple3<Integer, Integer, String>> nullResult = toStream((ImmutableTable<Integer, Integer, String>) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }

    // javaslang types (toStream can handle all subtypes of @Value, just testing some)
    @Test
    public void testToStreamJavaslangTry() {
        final List<Integer> failureResult = toStream(Try.<Integer>failure(new Throwable("nothing"))).collect(toList());
        assertThat(failureResult, is(ImmutableList.<Integer>of()));

        final List<Integer> successResult = toStream(Try.success(9)).collect(toList());
        assertThat(successResult, is(ImmutableList.of(9)));

        final List<Integer> nullResult = toStream((Try<Integer>) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }

    @Test
    public void testToStreamJavaslangEither() {
        final List<Integer> rightResult = toStream(Either.<String, Integer>right(1)).collect(toList());
        assertThat(rightResult, is(ImmutableList.of(1)));

        final List<Integer> leftResult = toStream(Either.<String, Integer>left("error message")).collect(toList());
        assertThat(leftResult, is(ImmutableList.of()));

        final List<Integer> nullResult = toStream((Either<String, Integer>) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }

    @Test
    public void testToStreamJavaslangList() {
        final List<Integer> someResult = toStream(javaslang.collection.List.of(1, 2, 3)).collect(toList());
        assertThat(someResult, is(ImmutableList.of(1, 2, 3)));

        final List<Integer> nullResult = toStream((javaslang.collection.List<Integer>) null).collect(toList());
        assertThat(nullResult, is(ImmutableList.of()));
    }
  }
