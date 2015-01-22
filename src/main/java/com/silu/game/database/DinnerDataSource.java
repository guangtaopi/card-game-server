package com.silu.game.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class DinnerDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataBaseContextHolder.getDataSource();
    }
}
