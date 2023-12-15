package com.ab.saga.orderservice.query.service;

import lombok.RequiredArgsConstructor;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class EventSourcingService {

    private final EventStore eventStore;

    public Stream<?> getOrderEvents(String orderId) {
        return eventStore.readEvents(orderId).asStream();
    }
}
