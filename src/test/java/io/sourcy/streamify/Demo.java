/*
 * Copyright (c) Sourcy Software & Services GmbH 2016.
 *
 *     _____ ____   __  __ _____ _____ __  __    (_)____
 *    / ___// __ \ / / / // ___// ___// / / /   / // __ \
 *   (__  )/ /_/ // /_/ // /   / /__ / /_/ /_  / // /_/ /
 *  /____/ \____/ \__,_//_/    \___/ \__, /(_)/_/ \____/
 *                                  /____/
 *
 * Created by armin walland <a.walland@sourcy.io> on 2016-03-19.
 */

package io.sourcy.streamify;

import javaslang.control.Option;
import org.junit.Test;

import static javaslang.API.For;

public class Demo {

    @Test
    public void twoOptionalsWithJavaslang() throws Exception {
        For(
                getInteger(),
                getString()
        )
                .yield((i, s) -> s + " number " + i)
                .forEach(System.err::println);
    }

    @Test
    public void twoOptionalsWithJava() throws Exception {
        final Option<Integer> maybeI = getInteger();
        final Option<String> maybeS = getString();
        if (maybeI.isDefined()) {
            if (maybeS.isDefined()) {
                System.err.println(maybeS.get() + " " + maybeI.get());
            }
        }
    }

    private Option<Integer> getInteger() {
        return Option.of(1);
    }

    private Option<String> getString() {
        return Option.of("Hello");
    }
}
