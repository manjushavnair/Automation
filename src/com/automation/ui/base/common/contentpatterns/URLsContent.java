package com.automation.ui.base.common.contentpatterns;

import com.automation.ui.base.common.core.url.UrlBuilder;

public interface URLsContent {

    // Common url component
    //CONTEXT
    public static final String SITE_DIR = "/";

    //change this for your sites
    public static final String SITE_CONTEXT = "login";


    // Special Urls - links to special pages
    public static final String USER_LOGIN = "/signin";
    public static final String USER_SIGNUP = "/";
    public static final String USER_FORGOT_PASSWORD = "/forgot-password";


    public static final String NOT_A_VALID_COMMUNITY = "Community_Central:Not_a_valid_community";


    // Extra switches - appear after ? in link
    public static final String NOEXTERNALS = "?noexternals=1";


    public static final String API_URL = UrlBuilder.createUrlBuilder().getUrl()
            + "/api.php";


    // External sites
    public static final String FACEBOOK_DOMAIN = "facebook.com";
    public static final String TWITTER_DOMAIN = "twitter.com";
    public static final String GOOGLE_DOMAIN = "accounts.google.com";
    public static final String REDDIT_DOMAIN = "reddit.com";
    public static final String STUMPLEUPON_DOMAIN = "stumbleupon.com";

    // Facebook
    public static final String FACEBOOK_MAINPAGE = "https://www.facebook.com/";
    public static final String FACEBOOK_SETTINGSPAGE = "https://www.facebook.com/settings";
    public static final String FACEBOOK_SETTINGS_APP_TAB =
            "https://www.facebook.com/settings?tab=applications";

    // avatars
    public static final String AVATAR_GENERIC = "Avatar.jpg";


    public static final String COMMUNITY_SITE = "community";

    // External URL
    public static final String EXTERNAL_URL = "http://www.site.com";

    public static final String USER_SIGNOUT = EXTERNAL_URL + "/logout";


}
