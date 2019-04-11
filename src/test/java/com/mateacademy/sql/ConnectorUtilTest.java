package com.mateacademy.sql;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class ConnectorUtilTest {
    private ConnectorUtil connectorUtil;

    @Before
    public void init() {
        connectorUtil = new ConnectorUtil();
    }

    @Test
    public void testConnectionMethod() {
        try {
            connectorUtil.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertNotNull(connectorUtil);
    }
}
