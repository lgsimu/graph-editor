package com.lgsim.engine.graphEditor.util.function;

@FunctionalInterface
@SuppressWarnings("unused")
public interface ThrowableFunction<T, R, E extends Exception> {
  R apply(T t) throws E;
}
