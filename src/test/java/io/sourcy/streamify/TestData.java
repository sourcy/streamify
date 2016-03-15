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

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author daniel selinger
 * @author armin walland
 */
public final class TestData {
    protected TestData() {
    }

    // TODO should we just kill this and inline testdata within each test? doesn't seem worth it
    // tests are probably more readable when we inline this definitions

    private final List<Integer> allValues = ImmutableList.of(1, 2, 2, -3);
    private final List<Integer> positiveValues = ImmutableList.of(1, 2, 2);
    private final Set<Integer> distinctPositiveValues = ImmutableSet.of(1, 2);
    private final Map<Integer, Boolean> mappedPositiveValues = ImmutableMap.of(1, true, 2, true, -3, false);

    public List<Integer> getAllValues() {
        return allValues;
    }

    public List<Integer> getPositiveValues() {
        return positiveValues;
    }

    public Set<Integer> getDistinctPositiveValues() {
        return distinctPositiveValues;
    }

    public Map<Integer, Boolean> getMappedPositiveValues() {
        return mappedPositiveValues;
    }
}
