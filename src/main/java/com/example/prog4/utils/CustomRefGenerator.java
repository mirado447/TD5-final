package com.example.prog4.utils;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

public class CustomRefGenerator implements IdentifierGenerator {
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        String prefix = "ref-";
        long nextValue = sequence.getAndIncrement();
        return prefix + String.format("%05d", nextValue);
    }
}
