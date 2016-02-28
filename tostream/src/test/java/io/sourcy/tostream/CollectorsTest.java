package io.sourcy.tostream;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

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

public class CollectorsTest implements TestData {

    @Test
    public void testToImmutableList() {
        assertEquals(allValues, toStream(allValues).collect(toImmutableList()));

    }

    @Test
    public void testToImmutableSet() {
        assertEquals(distinctPositiveValues, toStream(distinctPositiveValues).collect(toImmutableSet()));
    }

    @Test
    public void testToImmutableMap() {
        assertEquals(mappedPositiveValues, toStream(mappedPositiveValues).collect(toImmutableMap()));
    }
}