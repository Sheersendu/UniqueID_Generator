package com.UniqueIDGenerator.utilities;

import java.time.Instant;

import static com.UniqueIDGenerator.utilities.Constants.*;
import static java.lang.System.currentTimeMillis;

public class SnowflakeIDGenerator {
    private static final long EPOCH = Instant.parse("2024-01-01T00:00:00Z").toEpochMilli();
    private int datacenterId, machineId;
    private long lastTimestamp = -1;
    private int sequence = 0;
    private final int maximumSequence = (int) Math.pow(2, MAX_SEQUENCE_NUMBER_BITS);

    public SnowflakeIDGenerator() {
        datacenterId = (System.getenv("Datacenter_ID") != null) ? Integer.parseInt(System.getenv("Datacenter_ID")) : 1;
        machineId = (System.getenv("Machine_ID") != null) ? Integer.parseInt(System.getenv("Machine_ID")) : 1;
        int maximumDatacenters = (int) Math.pow(2, MAX_DATACENTER_ID_BITS);
        if (datacenterId >= maximumDatacenters) throw new RuntimeException("Datacenter doesn't exists");
        int maximumMachines = (int) Math.pow(2, MAX_MACHINE_ID_BITS);
        if (machineId >= maximumMachines) throw  new RuntimeException("Machine doesn't exists in datacenter");
    }

    public synchronized long nextId() {
        long currentTimestamp = currentTimeMillis();
        if (currentTimestamp < lastTimestamp) throw new RuntimeException("System clock has been reset");
        if (currentTimestamp == lastTimestamp) {
            if ((sequence+1) >= maximumSequence)
                currentTimestamp = getNextMillisecond(lastTimestamp);
            else sequence += 1;
        }
        else sequence = 0;

        lastTimestamp = currentTimestamp;
        String binaryTimestamp = generateID(currentTimestamp - EPOCH);
        String binaryDatacenterId = String.format("%"+MAX_DATACENTER_ID_BITS+"s", Integer.toBinaryString(datacenterId)).replace(' ', '0');
        String binaryMachineId = String.format("%"+MAX_MACHINE_ID_BITS+"s", Integer.toBinaryString(machineId)).replace(' ', '0');
        String binarySequence = String.format("%"+MAX_SEQUENCE_NUMBER_BITS+"s", Integer.toBinaryString(sequence)).replace(' ', '0');
        String id = "0" + binaryTimestamp + binaryDatacenterId + binaryMachineId + binarySequence;
        return Long.parseLong(id, 2);
    }

    private long getNextMillisecond(long currentTimeStamp){
        long timestamp = currentTimeStamp;
        while(timestamp <= lastTimestamp){
            timestamp = currentTimeMillis();
        }
        return timestamp;
    }

    private String generateID(long timestamp){
        int leadingZeroes = Long.numberOfLeadingZeros(timestamp);
        int shiftBits = Math.min(leadingZeroes - (Long.SIZE -41), 0);
        long extractedBits = timestamp << shiftBits;
        String binaryString = Long.toBinaryString(extractedBits);
        binaryString = String.format("%41s", binaryString).replace(' ', '0');
        return binaryString;
    }

    public static void main(String[] args) {
        SnowflakeIDGenerator generator = new SnowflakeIDGenerator();
        long id = generator.nextId();
        System.out.println(id);
    }
}