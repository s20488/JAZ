package com.westeros.tools.safeinvoker;

/**
 * interfejs który posłuży jako lambda, w której możę wystąpić wyjątek
 */
@FunctionalInterface
public interface NotSafeAction {
    void execute() throws Exception;
}
