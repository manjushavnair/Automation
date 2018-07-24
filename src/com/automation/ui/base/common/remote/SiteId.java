package com.automation.ui.base.common.remote;


import com.automation.ui.base.common.contentpatterns.URLsContent;
import com.automation.ui.base.common.remote.operations.http.NoAuthOperation;
import lombok.Getter;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SiteId {

    @Getter
    private String siteId;
    private String siteUrl;
    public SiteId(String siteUrl) {
        this.siteUrl = siteUrl;
        this.extractSiteIdFromSpecialVersion();
    }

    private void extractSiteIdFromSpecialVersion() {
        NoAuthOperation request = new NoAuthOperation();
        String response = request
                .execute(this.siteUrl + URLsContent.SITE_DIR + URLsContent.SPECIAL_VERSION,
                        new JSONObject());
        Pattern p = Pattern.compile(".*city_id: (\\d+).*", Pattern.DOTALL);
        Matcher m = p.matcher(response);
        if (m.find()) {
            this.siteId = m.group(1);
        } else {
            this.siteId = "";
        }
    }
}
