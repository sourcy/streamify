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

import com.google.common.collect.*;

import javaslang.Tuple2;
import javaslang.Tuple3;
import javaslang.collection.*;

import java.util.Comparator;
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

    // java standard collectors (sane alternatives for eg toMap)
    // TODO

    // guava collectors (excluding special purpose collections like class, type and range collections)

    public static <K, V> Collector<Tuple2<K, V>, ?, ImmutableBiMap<K, V>> toGuavaImmutableBiMap() {
        final Supplier<ImmutableBiMap.Builder<K, V>> supplier = ImmutableBiMap.Builder::new;
        final BiConsumer<ImmutableBiMap.Builder<K, V>, Tuple2<K, V>> accumulator = (b, t) -> b.put(t._1, t._2);
        final BinaryOperator<ImmutableBiMap.Builder<K, V>> combiner = (l, r) -> l.putAll(r.build());
        final Function<ImmutableBiMap.Builder<K, V>, ImmutableBiMap<K, V>> finisher = ImmutableBiMap.Builder::build;

        return Collector.of(supplier, accumulator, combiner, finisher);
    }

    public static <T> Collector<T, ?, ImmutableList<T>> toGuavaImmutableList() {
        final Supplier<ImmutableList.Builder<T>> supplier = ImmutableList.Builder::new;
        final BiConsumer<ImmutableList.Builder<T>, T> accumulator = ImmutableList.Builder::add;
        final BinaryOperator<ImmutableList.Builder<T>> combiner = (l, r) -> l.addAll(r.build());
        final Function<ImmutableList.Builder<T>, ImmutableList<T>> finisher = ImmutableList.Builder::build;

        return Collector.of(supplier, accumulator, combiner, finisher);
    }

    public static <K, V> Collector<Tuple2<K, V>, ?, ImmutableListMultimap<K, V>> toGuavaImmutableListMultimap() {
        final Supplier<ImmutableListMultimap.Builder<K, V>> supplier = ImmutableListMultimap.Builder::new;
        final BiConsumer<ImmutableListMultimap.Builder<K, V>, Tuple2<K, V>> accumulator = (b, t) -> b.put(t._1, t._2);
        final BinaryOperator<ImmutableListMultimap.Builder<K, V>> combiner = (l, r) -> l.putAll(r.build());
        final Function<ImmutableListMultimap.Builder<K, V>, ImmutableListMultimap<K, V>> finisher = ImmutableListMultimap.Builder::build;

        return Collector.of(supplier, accumulator, combiner, finisher);
    }

    public static <K, V> Collector<Tuple2<K, V>, ?, ImmutableMap<K, V>> toGuavaImmutableMap() {
        final Supplier<ImmutableMap.Builder<K, V>> supplier = ImmutableMap.Builder::new;
        final BiConsumer<ImmutableMap.Builder<K, V>, Tuple2<K, V>> accumulator = (b, t) -> b.put(t._1, t._2);
        final BinaryOperator<ImmutableMap.Builder<K, V>> combiner = (l, r) -> l.putAll(r.build());
        final Function<ImmutableMap.Builder<K, V>, ImmutableMap<K, V>> finisher = ImmutableMap.Builder::build;

        return Collector.of(supplier, accumulator, combiner, finisher);
    }

    public static <K, V> Collector<Tuple2<K, V>, ?, ImmutableMultimap<K, V>> toGuavaImmutableMultimap() {
        final Supplier<ImmutableMultimap.Builder<K, V>> supplier = ImmutableMultimap.Builder::new;
        final BiConsumer<ImmutableMultimap.Builder<K, V>, Tuple2<K, V>> accumulator = (b, t) -> b.put(t._1, t._2);
        final BinaryOperator<ImmutableMultimap.Builder<K, V>> combiner = (l, r) -> l.putAll(r.build());
        final Function<ImmutableMultimap.Builder<K, V>, ImmutableMultimap<K, V>> finisher = ImmutableMultimap.Builder::build;

        return Collector.of(supplier, accumulator, combiner, finisher);
    }

    public static <T> Collector<T, ?, ImmutableMultiset<T>> toGuavaImmutableMultiset() {
        final Supplier<ImmutableMultiset.Builder<T>> supplier = ImmutableMultiset.Builder::new;
        final BiConsumer<ImmutableMultiset.Builder<T>, T> accumulator = ImmutableMultiset.Builder::add;
        final BinaryOperator<ImmutableMultiset.Builder<T>> combiner = (l, r) -> l.addAll(r.build());
        final Function<ImmutableMultiset.Builder<T>, ImmutableMultiset<T>> finisher = ImmutableMultiset.Builder::build;

        return Collector.of(supplier, accumulator, combiner, finisher);
    }

    public static <T> Collector<T, ?, ImmutableSet<T>> toGuavaImmutableSet() {
        final Supplier<ImmutableSet.Builder<T>> supplier = ImmutableSet.Builder::new;
        final BiConsumer<ImmutableSet.Builder<T>, T> accumulator = ImmutableSet.Builder::add;
        final BinaryOperator<ImmutableSet.Builder<T>> combiner = (l, r) -> l.addAll(r.build());
        final Function<ImmutableSet.Builder<T>, ImmutableSet<T>> finisher = ImmutableSet.Builder::build;

        return Collector.of(supplier, accumulator, combiner, finisher);
    }

    public static <K, V> Collector<Tuple2<K, V>, ?, ImmutableSetMultimap<K, V>> toGuavaImmutableSetMultimap() {
        final Supplier<ImmutableSetMultimap.Builder<K, V>> supplier = ImmutableSetMultimap.Builder::new;
        final BiConsumer<ImmutableSetMultimap.Builder<K, V>, Tuple2<K, V>> accumulator = (b, t) -> b.put(t._1, t._2);
        final BinaryOperator<ImmutableSetMultimap.Builder<K, V>> combiner = (l, r) -> l.putAll(r.build());
        final Function<ImmutableSetMultimap.Builder<K, V>, ImmutableSetMultimap<K, V>> finisher = ImmutableSetMultimap.Builder::build;

        return Collector.of(supplier, accumulator, combiner, finisher);
    }

    public static <K, V> Collector<Tuple2<K, V>, ?, ImmutableSortedMap<K, V>> toGuavaImmutableSortedMap(final Comparator<? super K> comparator) {
        final Supplier<ImmutableSortedMap.Builder<K, V>> supplier = () -> new ImmutableSortedMap.Builder<>(comparator);
        final BiConsumer<ImmutableSortedMap.Builder<K, V>, Tuple2<K, V>> accumulator = (b, t) -> b.put(t._1, t._2);
        final BinaryOperator<ImmutableSortedMap.Builder<K, V>> combiner = (l, r) -> l.putAll(r.build());
        final Function<ImmutableSortedMap.Builder<K, V>, ImmutableSortedMap<K, V>> finisher = ImmutableSortedMap.Builder::build;

        return Collector.of(supplier, accumulator, combiner, finisher);
    }

    public static <T> Collector<T, ?, ImmutableSortedMultiset<T>> toGuavaImmutableSortedMultiset(final Comparator<? super T> comparator) {
        final Supplier<ImmutableSortedMultiset.Builder<T>> supplier = () -> new ImmutableSortedMultiset.Builder<>(comparator);
        final BiConsumer<ImmutableSortedMultiset.Builder<T>, T> accumulator = ImmutableSortedMultiset.Builder::add;
        final BinaryOperator<ImmutableSortedMultiset.Builder<T>> combiner = (l, r) -> l.addAll(r.build());
        final Function<ImmutableSortedMultiset.Builder<T>, ImmutableSortedMultiset<T>> finisher = ImmutableSortedMultiset.Builder::build;

        return Collector.of(supplier, accumulator, combiner, finisher);
    }

    public static <T> Collector<T, ?, ImmutableSortedSet<T>> toGuavaImmutableSortedSet(final Comparator<? super T> comparator) {
        final Supplier<ImmutableSortedSet.Builder<T>> supplier = () -> new ImmutableSortedSet.Builder<>(comparator);
        final BiConsumer<ImmutableSortedSet.Builder<T>, T> accumulator = ImmutableSortedSet.Builder::add;
        final BinaryOperator<ImmutableSortedSet.Builder<T>> combiner = (l, r) -> l.addAll(r.build());
        final Function<ImmutableSortedSet.Builder<T>, ImmutableSortedSet<T>> finisher = ImmutableSortedSet.Builder::build;

        return Collector.of(supplier, accumulator, combiner, finisher);
    }

    public static <R, C, V> Collector<Tuple3<R, C, V>, ?, ImmutableTable<R, C, V>> toGuavaImmutableTable() {
        final Supplier<ImmutableTable.Builder<R, C, V>> supplier = ImmutableTable.Builder::new;
        final BiConsumer<ImmutableTable.Builder<R, C, V>, Tuple3<R, C, V>> accumulator = (b, t) -> b.put(t._1, t._2, t._3);
        final BinaryOperator<ImmutableTable.Builder<R, C, V>> combiner = (l, r) -> l.putAll(r.build());
        final Function<ImmutableTable.Builder<R, C, V>, ImmutableTable<R, C, V>> finisher = ImmutableTable.Builder::build;

        return Collector.of(supplier, accumulator, combiner, finisher);
    }

    // javaslang collectors

    public static <T> Collector<T, ?, Array<T>> toJavaslangArray() {
        return Array.collector();
    }

    static Collector<Character, ?, CharSeq> toJavaslangCharSeq() {
        return CharSeq.collector();
    }

    static <K,V> Collector<Tuple2<K,V>, ?, HashMap<K,V>> toJavaslangHashMap() {
        return HashMap.collector();
    }

    static <T> Collector<T, ?, HashSet<T>> toJavaslangHashSet() {
        return HashSet.collector();
    }

    static <K,V> Collector<Tuple2<K,V>, ?, LinkedHashMap<K,V>> toJavaslangLinkedHashMap() {
        return LinkedHashMap.collector();
    }

    static <T> Collector<T, ?, LinkedHashSet<T>> toJavaslangLinkedHashSet() {
        return LinkedHashSet.collector();
    }

    static <T> Collector<T, ?, List<T>> toJavaslangList() {
        return List.collector();
    }

    static <T> Collector<T, ?, Queue<T>> toJavaslangQueue() {
        return Queue.collector();
    }

    static <T> Collector<T, ?, Stack<T>> toJavaslangStack() {
        return Stack.collector();
    }

    static <T> Collector<T, ?, Stream<T>> toJavaslangStream() {
        return Stream.collector();
    }

    static <T> Collector<T, ?, Tree<T>> toJavaslangTree() {
        return Tree.collector();
    }

    static <K,V> Collector<Tuple2<K,V>, ?, TreeMap<K,V>> toJavaslangTreeMap() {
        return TreeMap.collector();
    }

    static <T> Collector<T, ?, TreeSet<T>> toJavaslangTreeSet() {
        return TreeSet.collector();
    }

    static <T> Collector<T, ?, Vector<T>> toJavaslangVector() {
        return Vector.collector();
    }
}
