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

import com.google.common.collect.ImmutableTable;
import javaslang.Tuple;
import javaslang.Tuple2;
import javaslang.Tuple3;
import javaslang.Value;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author daniel selinger
 * @author armin walland
 */
public final class Streamify {

    private Streamify() {
    }

    // streams for java standard types
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static <T> Stream<T> stream(final java.util.Optional<T> o) {
        return (o != null && o.isPresent()) ? Stream.of(o.get()) : Stream.empty();
    }

    public static <T> Stream<T> stream(final Collection<T> c) {
        return c != null ? c.stream() : Stream.empty();
    }

    public static <K, V> Stream<Tuple2<K, V>> stream(final Map<K, V> m) {
        return m != null ? m.entrySet().stream().map(e -> Tuple.of(e.getKey(), e.getValue())) : Stream.empty();
    }

    public static <T> Stream<T> stream(final T[] a) {
        return a != null ? Arrays.stream(a) : Stream.empty();
    }

    public static Stream<Integer> stream(final int[] a) {
        return stream(a != null ? Arrays.stream(a) : IntStream.empty());
    }

    public static Stream<Double> stream(final double[] a) {
        return stream(a != null ? Arrays.stream(a) : DoubleStream.empty());
    }

    public static Stream<Long> stream(final long[] a) {
        return stream(a != null ? Arrays.stream(a) : LongStream.empty());
    }

    public static <T> Stream<T> stream(final Stream<T> s) {
        return s != null ? s : Stream.empty();
    }

    public static Stream<Integer> stream(final IntStream s) {
        return s != null ? s.mapToObj(Integer::valueOf) : Stream.empty();
    }

    public static Stream<Double> stream(final DoubleStream s) {
        return s != null ? s.mapToObj(Double::valueOf) : Stream.empty();
    }

    public static Stream<Long> stream(final LongStream s) {
        return s != null ? s.mapToObj(Long::valueOf) : Stream.empty();
    }

    // streams for guava types
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static <T> Stream<T> stream(final com.google.common.base.Optional<T> o) {
        return (o != null && o.isPresent()) ? Stream.of(o.get()) : Stream.empty();
    }

    public static <R, C, V> Stream<Tuple3<R, C, V>> stream(final ImmutableTable<R, C, V> t) {
        return t != null ? t.cellSet().stream().map(c -> Tuple.of(c.getRowKey(), c.getColumnKey(), c.getValue())) : Stream.empty();
    }

    // streams for javaslang types
    public static <T extends Value<U>, U> Stream<U> stream(final T v) {
        return v != null ? v.toJavaStream() : Stream.empty();
    }
}
