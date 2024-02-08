package com.westeros.tools.schedulers.abstractions;

import java.time.LocalDateTime;

public interface IProvideNextExecutionTime {
    LocalDateTime provideTime();
}
