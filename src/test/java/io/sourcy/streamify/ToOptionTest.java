package io.sourcy.streamify;

import javaslang.control.Option;
import org.junit.Test;

import static io.sourcy.streamify.ToOption.toOption;
import static io.sourcy.streamify.ToOption.toOptional;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


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

public class ToOptionTest {

    @Test
    public void testToOptionFromJavaOptional() throws Exception {
        assertThat(toOption(java.util.Optional.of(1)), is(Option.some(1)));
        assertThat(toOption(java.util.Optional.empty()), is(Option.none()));
    }

    @Test
    public void testToOptionFromGuavaOptional() throws Exception {
        assertThat(toOption(com.google.common.base.Optional.of(1)), is(Option.some(1)));
        assertThat(toOption(com.google.common.base.Optional.absent()), is(Option.none()));
    }

    @Test
    public void testToOptionFromOption() throws Exception {
        assertThat(toOption(Option.some(1)), is(Option.some(1)));
        assertThat(toOption(Option.none()), is(Option.none()));
    }

    @Test
    public void testToOptionalFromOption() throws Exception {
        assertThat(toOptional(Option.some(1)), is(java.util.Optional.of(1)));
        assertThat(toOptional(Option.none()), is(java.util.Optional.empty()));
    }

    @Test
    public void testToOptionalFromGuavaOptional() throws Exception {
        assertThat(toOptional(com.google.common.base.Optional.of(1)), is(java.util.Optional.of(1)));
        assertThat(toOptional(com.google.common.base.Optional.absent()), is(java.util.Optional.empty()));
    }

    @Test
    public void testToOptionalFromOptional() throws Exception {
        assertThat(toOptional(java.util.Optional.of(1)), is(java.util.Optional.of(1)));
        assertThat(toOptional(java.util.Optional.empty()), is(java.util.Optional.empty()));
    }
}