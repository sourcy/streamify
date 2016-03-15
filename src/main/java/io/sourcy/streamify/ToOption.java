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

/**
 * @author daniel selinger
 * @author armin walland
 */
public final class ToOption {

    private ToOption() {
    }

    public static <T> javaslang.control.Option<T> toOption(final java.util.Optional<T> o) {
        return (o != null && o.isPresent()) ? javaslang.control.Option.of(o.get()) : javaslang.control.Option.none();
    }

    public static <T> javaslang.control.Option<T> toOption(final com.google.common.base.Optional<T> o) {
        return (o != null && o.isPresent()) ? javaslang.control.Option.some(o.get()) : javaslang.control.Option.none();
    }

    public static <T> javaslang.control.Option<T> toOption(final javaslang.control.Option<T> o) {
        return o != null ? o : javaslang.control.Option.none();
    }

    public static <T> javaslang.control.Option<T> toOption(final T o) {
        return javaslang.control.Option.of(o);
    }

    public static <T> java.util.Optional<T> toOptional(final javaslang.control.Option<T> o) {
        return (o != null && o.isDefined()) ? java.util.Optional.of(o.get()) : java.util.Optional.empty();
    }

    public static <T> java.util.Optional<T> toOptional(final com.google.common.base.Optional<T> o) {
        return (o != null && o.isPresent()) ? java.util.Optional.of(o.get()) : java.util.Optional.empty();
    }

    public static <T> java.util.Optional<T> toOptional(final java.util.Optional<T> o) {
        return o != null ? o : java.util.Optional.empty();
    }

    public static <T> java.util.Optional<T> toOptional(final T o) {
        return java.util.Optional.ofNullable(o);
    }
}
