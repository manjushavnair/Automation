package com.automation.ui.base.common.core.api.apicore;

@FunctionalInterface
public interface RestAdapter {

    <T> T execute(Class<T> responseClass);
}
