package com.automation.ui.base.common.core.url;

import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.core.configuration.EnvType;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import static com.automation.ui.base.common.core.configuration.Configuration.getEnvType;

public class BaseUrlBuilder {

    protected String env;
    protected EnvType envType;

    public BaseUrlBuilder(String env) {
        this.env = env;
        this.envType = getEnvType(this.env);
    }

    /**
     * It actually adds string to url, so the path might consist a query
     *
     * @param url  site's url.
     * @param path path to be added to the url
     **/
    protected String addPathToUrl(String url, String path) {

        String outputUrl = (!path.startsWith("/")) ? String.format("%s/%s", url, path) : String.format("%s%s", url, path);

        String qs = Configuration.getQS();
        if (StringUtils.isNotBlank(qs)) {
            outputUrl = appendQueryStringToURL(outputUrl, qs);
        }
        return outputUrl;
    }

    public String appendQueryStringToURL(String url, String qs) {
        String separator = url.contains("?") ? "&" : "?";

        String[] filteredUrl = url.split("#");
        if (filteredUrl.length > 1) {
            return filteredUrl[0] + separator + qs + "#" + filteredUrl[1];
        } else {
            return url + separator + qs;
        }
    }


    public String getCacheBusterQuery(String pageName) {
        return pageName.equals("") || pageName.equals("/") ? "" : "?cb=" + DateTime.now().getMillis();
    }

    public String getQueryParams(String pageName, String[] queryParams) {
        String query = this.getCacheBusterQuery(pageName);

        if (!query.equals("")) {
            for (String queryParam : queryParams) {
                query = query + "&" + queryParam;
            }
        }

        return query;
    }
}
