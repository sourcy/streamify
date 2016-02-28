package io.sourcy.tostream;

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
import javaslang.Tuple2;
import javaslang.control.Either;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author daniel selinger
 * @author armin walland
 */
public final class ToStream {

    private ToStream() {
    }

    public static <T> Stream<T> toStream(final Optional<T> o) {
        return o.isPresent() ? Stream.of(o.get()) : Stream.empty();
    }

    public static <E, T> Stream<T> toStream(final Either<E, T> e) {
        return e.isRight() ? Stream.of(e.get()) : Stream.empty();
    }

    public static <T> Stream<T> toStream(final Collection<T> c) {
        return c.stream();
    }

    public static <T> Stream<T> toStream(final T[] a) {
        return Arrays.stream(a);
    }

    public static <K, V> Stream<Tuple2<K, V>> toStream(final Map<K, V> m) {
        return m.entrySet().stream().map(e -> Tuple.of(e.getKey(), e.getValue()));
    }
}
