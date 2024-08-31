package com.emazon.stockservice.domain.utils;

public class SortOrder {

    public enum Direction {
        ASC, DESC
    }

    private final String sortBy;
    private final Direction direction;

    public SortOrder(String sortBy, Direction direction) {
        this.sortBy = sortBy;
        this.direction = direction;
    }

    public String getSortBy() {
        return sortBy;
    }

    public Direction getDirection() {
        return direction;
    }
}
