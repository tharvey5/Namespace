package com.jtaodyssey.namespace.components;

/**
 * This class will represent the different types of statuses that occur
 * as either errors or other information as a result of notification passing
 */
public interface JTAStatus {
    String getStatus();
    String getTime();
    String getStatusType();
}
