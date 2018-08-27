package com.automation.ui.base.common.remote;

import com.automation.ui.base.common.core.XMLReader;
import com.automation.ui.base.common.core.configuration.Configuration;

import java.io.File;
import java.util.Objects;

public final class Utils {


    private Utils() {
        throw new AssertionError();
    }


    public static String buildServicesUrl() {
        File configurationFile = new File(Configuration.getCredentialsFilePath());
        final String environment = Configuration.getEnvType().getKey();
        final String url = XMLReader.getValue(configurationFile, "services." + environment);
        Objects.requireNonNull(url,
                "Please check if your configuration file contains url for service " + environment);
        return url;
    }
}
