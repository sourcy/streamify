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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import javaslang.Tuple2;

import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author daniel selinger
 * @author armin walland
 */
public final class StreamCollectors {
    private StreamCollectors() {
    }

    public static <T> Collector<T, ?, ImmutableList<T>> toImmutableList() {
        final Supplier<ImmutableList.Builder<T>> supplier = ImmutableList.Builder::new;
        final BiConsumer<ImmutableList.Builder<T>, T> accumulator = ImmutableList.Builder::add;
        final BinaryOperator<ImmutableList.Builder<T>> combiner = (l, r) -> l.addAll(r.build());
        final Function<ImmutableList.Builder<T>, ImmutableList<T>> finisher = ImmutableList.Builder::build;

        return Collector.of(supplier, accumulator, combiner, finisher);
    }

    public static <T> Collector<T, ?, ImmutableSet<T>> toImmutableSet() {
        final Supplier<ImmutableSet.Builder<T>> supplier = ImmutableSet.Builder::new;
        final BiConsumer<ImmutableSet.Builder<T>, T> accumulator = ImmutableSet.Builder::add;
        final BinaryOperator<ImmutableSet.Builder<T>> combiner = (l, r) -> l.addAll(r.build());
        final Function<ImmutableSet.Builder<T>, ImmutableSet<T>> finisher = ImmutableSet.Builder::build;

        return Collector.of(supplier, accumulator, combiner, finisher);
    }

    public static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(final Function<? super T, ? extends K> keyMapper, final Function<? super T, ? extends V> valueMapper) {
        final Supplier<ImmutableMap.Builder<K, V>> supplier = ImmutableMap.Builder::new;
        final BiConsumer<ImmutableMap.Builder<K, V>, T> accumulator = (b, t) -> b.put(keyMapper.apply(t), valueMapper.apply(t));
        final BinaryOperator<ImmutableMap.Builder<K, V>> combiner = (l, r) -> l.putAll(r.build());
        final Function<ImmutableMap.Builder<K, V>, ImmutableMap<K, V>> finisher = ImmutableMap.Builder::build;

        return Collector.of(supplier, accumulator, combiner, finisher);
    }

    public static <K, V> Collector<Tuple2<K, V>, ?, ImmutableMap<K, V>> toImmutableMap() {
        final Supplier<ImmutableMap.Builder<K, V>> supplier = ImmutableMap.Builder::new;
        final BiConsumer<ImmutableMap.Builder<K, V>, Tuple2<K, V>> accumulator = (b, t) -> b.put(t._1, t._2);
        final BinaryOperator<ImmutableMap.Builder<K, V>> combiner = (l, r) -> l.putAll(r.build());
        final Function<ImmutableMap.Builder<K, V>, ImmutableMap<K, V>> finisher = ImmutableMap.Builder::build;

        return Collector.of(supplier, accumulator, combiner, finisher);
    }
}
