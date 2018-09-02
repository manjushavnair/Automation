package com.automation.ui.base.common.api.adapter;

import io.restassured.path.json.JsonPath;


public interface RestAdapter {

    JsonPath execute();

    <T> T execute(Class<T> responseClass);
}