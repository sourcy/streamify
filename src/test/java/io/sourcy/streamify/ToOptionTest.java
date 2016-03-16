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
 * Created by daniel selinger <d.selinger@sourcy.io> on 2016-03-15.
 */

import org.junit.Test;

import static io.sourcy.streamify.ToOption.toOption;
import static io.sourcy.streamify.ToOption.toOptional;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author daniel selinger
 * @author armin walland
 */
public class ToOptionTest {

    @Test
    public void testToOptionFromJavaOptional() {
        final javaslang.control.Option<Integer> someResult = toOption(java.util.Optional.of(1));
        assertThat(someResult, is(javaslang.control.Option.some(1)));

        final javaslang.control.Option<Integer> noneResult = toOption(java.util.Optional.empty());
        assertThat(noneResult, is(javaslang.control.Option.none()));

        final javaslang.control.Option<Integer> nullResult = toOption((java.util.Optional<Integer>) null);
        assertThat(nullResult, is(javaslang.control.Option.none()));
    }

    @Test
    public void testToOptionFromGuavaOptional() {
        final javaslang.control.Option<Integer> someResult = toOption(com.google.common.base.Optional.of(1));
        assertThat(someResult, is(javaslang.control.Option.some(1)));

        final javaslang.control.Option<Integer> noneResult = toOption(com.google.common.base.Optional.absent());
        assertThat(noneResult, is(javaslang.control.Option.none()));

        final javaslang.control.Option<Integer> nullResult = toOption((com.google.common.base.Optional<Integer>) null);
        assertThat(nullResult, is(javaslang.control.Option.none()));
    }

    @Test
    public void testToOptionFromOption() {
        final javaslang.control.Option<Integer> someResult = toOption(javaslang.control.Option.some(1));
        assertThat(someResult, is(javaslang.control.Option.some(1)));

        final javaslang.control.Option<Integer> noneResult = toOption(javaslang.control.Option.none());
        assertThat(noneResult, is(javaslang.control.Option.none()));

        final javaslang.control.Option<Integer> nullResult = toOption((javaslang.control.Option<Integer>) null);
        assertThat(nullResult, is(javaslang.control.Option.none()));
    }

    @Test
    public void testToOptionFromAnything() {
        final javaslang.control.Option<Integer> someResult = toOption(1);
        assertThat(someResult, is(javaslang.control.Option.some(1)));

        final javaslang.control.Option<Integer> nullResult = toOption((Integer) null);
        assertThat(nullResult, is(javaslang.control.Option.none()));
    }

    @Test
    public void testToOptionalFromOption() {
        final java.util.Optional<Integer> someResult = toOptional(javaslang.control.Option.some(1));
        assertThat(someResult, is(java.util.Optional.of(1)));

        final java.util.Optional<Integer> noneResult = toOptional(javaslang.control.Option.none());
        assertThat(noneResult, is(java.util.Optional.empty()));

        final java.util.Optional<Integer> nullResult = toOptional((javaslang.control.Option<Integer>) null);
        assertThat(nullResult, is(java.util.Optional.empty()));
    }

    @Test
    public void testToOptionalFromGuavaOptional() {
        final java.util.Optional<Integer> someResult = toOptional(com.google.common.base.Optional.of(1));
        assertThat(someResult, is(java.util.Optional.of(1)));

        final java.util.Optional<Integer> noneResult = toOptional(com.google.common.base.Optional.absent());
        assertThat(noneResult, is(java.util.Optional.empty()));

        final java.util.Optional<Integer> nullResult = toOptional((com.google.common.base.Optional<Integer>) null);
        assertThat(nullResult, is(java.util.Optional.empty()));
    }

    @Test
    public void testToOptionalFromOptional() {
        final java.util.Optional<Integer> someResult = toOptional(java.util.Optional.of(1));
        assertThat(someResult, is(java.util.Optional.of(1)));

        final java.util.Optional<Integer> noneResult = toOptional(java.util.Optional.empty());
        assertThat(noneResult, is(java.util.Optional.empty()));

        final java.util.Optional<Integer> nullResult = toOptional((java.util.Optional<Integer>) null);
        assertThat(nullResult, is(java.util.Optional.empty()));
    }

    @Test
    public void testToOptionalFromAnything() {
        final java.util.Optional<Integer> someResult = toOptional(1);
        assertThat(someResult, is(java.util.Optional.of(1)));

        final java.util.Optional<Integer> nullResult = toOptional((Integer) null);
        assertThat(nullResult, is(java.util.Optional.empty()));
    }
}