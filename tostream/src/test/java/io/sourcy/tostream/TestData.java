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
 * Created by daniel selinger <d.selinger@sourcy.io> on $today.format('yyyy-MM-dd HH:mm').
 */

import com.google.common.collect.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TestData {

    List<Integer> allValues = ImmutableList.of(1, 2, 2, -3);
    List<Integer> positiveValues = ImmutableList.of(1, 2, 2);
    Set<Integer> distinctPositiveValues = ImmutableSet.of(1, 2);
    Map<Integer, Boolean> mappedPositiveValues = ImmutableMap.of(1, true, 2, true, -3, false);

}
