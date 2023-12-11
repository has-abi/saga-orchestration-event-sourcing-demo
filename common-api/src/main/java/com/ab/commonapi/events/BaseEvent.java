package com.ab.commonapi.events;

import lombok.Getter;

@Getter
public class BaseEvent<T> {
    private final T id;

    public BaseEvent(T id) {
        this.id = id;
    }
}
