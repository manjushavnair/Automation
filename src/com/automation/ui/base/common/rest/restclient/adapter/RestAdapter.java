package com.automation.ui.base.common.rest.restclient.adapter;

import io.restassured.path.json.JsonPath;


public interface RestAdapter {

    JsonPath execute();

    <T> T execute(Class<T> responseClass);
}