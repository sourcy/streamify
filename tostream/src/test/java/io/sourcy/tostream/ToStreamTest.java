package io.sourcy.tostream;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import javaslang.control.Either;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.*;

import static io.sourcy.tostream.ToStream.*;
import static io.sourcy.tostream.Collectors.*;


/*
 * Copyright (c) Sourcy Software & Services GmbH $today.year.
 *
 *     _____ ____   __  __ _____ _____ __  __    (_)____
 *    / ___// __ \ / / / // ___// ___// / / /   / // __ \
 *   (__  )/ /_/ // /_/ // /   / /__ / /_/ /_  / // /_/ /
 *  /____/ \____/ \__,_//_/    \___/ \__, /(_)/_/ \____/
 *                                  /____/
 *
 * Created by daniel selinger <d.selinger@sourcy.io> on $today.format('yyyy-MM-dd HH:mm').
 */

public class ToStreamTest implements TestData {

    private Optional<Integer> mapPositiveOptional(Integer i) {
        return (i >= 0) ? Optional.of(i) : Optional.empty();
    }

    private Either<String, Integer> mapPositiveEither(Integer i) {
        return (i >= 0) ? Either.right(i) : Either.left("Negative Number!");
    }

    @Test
    public void testToStreamArray() {
        assertEquals(allValues, toStream(allValues.toArray()).collect(toImmutableList()));
    }
    @Test
    public void testToStreamList() {
        assertEquals(allValues, toStream(allValues).collect(toImmutableList()));
    }

    @Test
    public void testToStreamSet() {
        assertEquals(distinctPositiveValues, toStream(distinctPositiveValues).collect(toImmutableSet()));
    }

    @Test
    public void testToStreamMap() {
        assertEquals(mappedPositiveValues, toStream(mappedPositiveValues).collect(toImmutableMap()));
    }

    @Test
    public void testToStreamOptional() {
        assertEquals(ImmutableList.of(1), toStream(Optional.of(1)).collect(toImmutableList()));
        assertEquals(ImmutableList.of(), toStream(Optional.empty()).collect(toImmutableList()));
    }

    @Test
    public void testToStreamFlatMapOptional() {
        final List<Integer> values = toStream(allValues)
                .flatMap(i -> toStream(mapPositiveOptional(i)))
                .collect(toImmutableList());

        assertEquals(positiveValues, values);
    }

    @Test
    public void testToStreamEither() {
        assertEquals(ImmutableList.of(1), toStream(Either.right(1)).collect(toImmutableList()));
        assertEquals(ImmutableList.of(), toStream(Either.left("error message")).collect(toImmutableList()));
    }

    @Test
    public void testToStreamFlatMapEither() {
        final List<Integer> values = toStream(allValues)
                .flatMap(i -> toStream(mapPositiveEither(i)))
                .collect(toImmutableList());

        assertEquals(positiveValues, values);
    }
}