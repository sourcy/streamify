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
public final class ToStream {

    private ToStream() {
    }

    // tostream for java standard types
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static <T> Stream<T> toStream(final java.util.Optional<T> o) {
        return (o != null && o.isPresent()) ? Stream.of(o.get()) : Stream.empty();
    }

    public static <T> Stream<T> toStream(final Collection<T> c) {
        return c != null ? c.stream() : Stream.empty();
    }

    public static <K, V> Stream<Tuple2<K, V>> toStream(final Map<K, V> m) {
        return m != null ? m.entrySet().stream().map(e -> Tuple.of(e.getKey(), e.getValue())) : Stream.empty();
    }

    public static <T> Stream<T> toStream(final T[] a) {
        return a != null ? Arrays.stream(a) : Stream.empty();
    }

    public static Stream<Integer> toStream(final int[] a) {
        return toStream(a != null ? Arrays.stream(a) : IntStream.empty());
    }

    public static Stream<Double> toStream(final double[] a) {
        return toStream(a != null ? Arrays.stream(a) : DoubleStream.empty());
    }

    public static Stream<Long> toStream(final long[] a) {
        return toStream(a != null ? Arrays.stream(a) : LongStream.empty());
    }

    public static <T> Stream<T> toStream(final Stream<T> s) {
        return s != null ? s : Stream.empty();
    }

    public static Stream<Integer> toStream(final IntStream s) {
        return s != null ? s.mapToObj(Integer::valueOf) : Stream.empty();
    }

    public static Stream<Double> toStream(final DoubleStream s) {
        return s != null ? s.mapToObj(Double::valueOf) : Stream.empty();
    }

    public static Stream<Long> toStream(final LongStream s) {
        return s != null ? s.mapToObj(Long::valueOf) : Stream.empty();
    }

    // tostream for guava types
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static <T> Stream<T> toStream(final com.google.common.base.Optional<T> o) {
        return (o != null && o.isPresent()) ? Stream.of(o.get()) : Stream.empty();
    }

    public static <R, C, V> Stream<Tuple3<R, C, V>> toStream(final ImmutableTable<R, C, V> t) {
        return t != null ? t.cellSet().stream().map(c -> Tuple.of(c.getRowKey(), c.getColumnKey(), c.getValue())) : Stream.empty();
    }

    // tostream for javaslang types
    public static <T extends Value<U>, U> Stream<U> toStream(final T v) {
        return v != null ? v.toJavaStream() : Stream.empty();
    }
}
