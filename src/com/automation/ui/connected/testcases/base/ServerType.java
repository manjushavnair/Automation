package com.automation.ui.connected.testcases.base;

public enum ServerType {


    ODBC_COLLECTOR("ODBC Collector"),
    OPCUA_SERVER("OPC Server"),
    OPCUA_UA_SERVER("OPC UA Server"),
    PHD_SERVER("PHD Server"),
    SDX_COLLECTOR("SDX Collector");

    private String serverType;

    ServerType(String type) {
        this.serverType = type;
    }

    public String getServerType() {
        return this.serverType;
    }

    @Override
    public String toString() {
        return this.serverType;
    }
}