package com.ab.commonapi.commands;

import java.nio.ByteBuffer;
import java.util.UUID;

public class Test {
    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();

        // Convert UUID to a long
        long numericValue = uuidToLong(uuid);

        // Print the results
        System.out.println("UUID: " + uuid);
        System.out.println("Numeric Value: " + numericValue);
    }

    private static long uuidToLong(UUID uuid) {
        // Extract the most significant bits of the UUID
        long mostSignificantBits = uuid.getMostSignificantBits();

        // Extract the least significant bits of the UUID
        long leastSignificantBits = uuid.getLeastSignificantBits();

        // Concatenate the most and least significant bits
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES * 2);
        buffer.putLong(mostSignificantBits);
        buffer.putLong(leastSignificantBits);
        buffer.flip();

        // Return the result as a long
        return buffer.getLong();
    }
}
