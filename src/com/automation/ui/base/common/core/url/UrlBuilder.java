package com.automation.ui.base.common.core.url;

import com.automation.ui.base.common.contentpatterns.URLsContent;
import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.core.configuration.EnvType;
import okhttp3.HttpUrl;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriverException;

import static com.automation.ui.base.common.core.configuration.Configuration.*;

public class UrlBuilder extends BaseUrlBuilder {

    private static Logger logger = Logger.getLogger(UrlBuilder.class);


    private final String siteName;
    private Boolean forceHttps;
    private Boolean forceLanguageInPath;
    private String language;


    private UrlBuilder(String site, String env, Boolean forceHttps, Boolean forceLanguageInPath, String language) {
        super(env);
        this.siteName = site;
        this.forceHttps = forceHttps;
        this.forceLanguageInPath = forceLanguageInPath;
        this.language = language;
    }

    public static UrlBuilder createUrlBuilder() {
        return createUrlBuilderForSiteAndLang(getSiteName(), getSiteLanguage());
    }

    public static UrlBuilder createUrlBuilderForSite(String site) {
        return createUrlBuilderForSiteAndLang(site, getSiteLanguage());
    }

    public static UrlBuilder createUrlBuilderForSiteAndLang(String site, String language) {
        return new UrlBuilder(site, getEnv(), getForceHttps(), getForceLanguageInPath(), language);
    }

    public String normalizePageName(String pageName) {
        if ("".equals(pageName))
            throw new WebDriverException("Page name is missing");
        return pageName.replace(" ", "_");
    }

    public String getUrlForPageWithWWW(String pageName) {
        if ("".equals(pageName)) {
            throw new WebDriverException("Page name is missing");
        }

        //  logger.info("getUrlForPageWithWWW " + getUrl(true) + URLsContent.SITE_DIR + pageName);
        return getUrl(true) + URLsContent.SITE_DIR + pageName;
    }

    public String getUrlForPage(String pageName) {
        if ("".equals(pageName))
            throw new WebDriverException("Page name is missing");

        // String url = getUrl() +  pageName;
        String url = getUrl();

        //  logger.info("getUrlForPage " + getUrl() + " URLsContent.SITE_DIR " + URLsContent.SITE_DIR + "pageName " + pageName + "url"+url);


        if (StringUtils.isNotBlank(Configuration.getQS())) {
            return appendQueryStringToURL(url, Configuration.getQS());
        }
        return url;
    }

    public String getUrlForPath(String sitePath) {
        return addPathToUrl(getUrl(), sitePath);
    }

    public String getUrl() {
        return getUrl(this.language, false);
    }

    public String getUrl(boolean addWWW) {
        return getUrl(language, addWWW, envType);
    }

    public String getUrl(String language, boolean addWWW) {
        return getUrl(language, addWWW, envType);
    }


    public String getUrl(String language, boolean addWWW, EnvType envType) {
        final String siteaName = getSiteGlobalName(siteName);

        //  logger.info("getUrl siteaName :" + siteaName);

        if (language == null || siteaName == null)
            throw new NullPointerException("Site name and language are required");

        HttpUrl.Builder urlBuilder = new HttpUrl.Builder();

        //add the port number to the url
        if (!envType.getPort().equals(""))
            urlBuilder.port(Integer.parseInt(envType.getPort()));


        String www = addWWW ? "www." : "";


        String host = getFormattedSiteHost(www, siteaName, envType, language);

        if (!DEFAULT_LANGUAGE.equals(language) && forceLanguageInPath) {
            urlBuilder.addEncodedPathSegments(language);
        }

        return urlBuilder.scheme(getUrlProtocol()).host(host).build().toString().replaceFirst("/$", "");
    }

    private String getFormattedSiteHost(String www, String siteName, EnvType envType, String language) {

        //  logger.info("getFormattedSiteHost  www: " + www + "siteName " + siteName + " envType " + envType.getSiteDomain() + " language  " + language);

        if (!forceLanguageInPath && !(DEFAULT_LANGUAGE).equals(language)) {
            return getFormattedSiteHost(www, String.join(".", language, siteName), envType);
        }
        return getFormattedSiteHost(www, siteName, envType);
    }


    private String getFormattedSiteHost(String www, String siteName, EnvType envType) {

        String domain = envType.getSiteDomain();
   /* String value="";
    boolean subDomain=false;

    if(subDomain)
    {
      value=String.join(".", www + siteName, domain );
    }
    else
    {
      value=String.join(".", www + siteName );

    }


    switch (envType) {
      case DEV: {
        String devBoxOwner = this.env.split("-")[1];
        logger.info("getFormattedSiteHost  www: "+www + " siteName " +siteName + "envType "+domain + " devBoxOwner  " +devBoxOwner );
       // return String.join(".", www + siteName, devBoxOwner, domain);
        //return String.join(".", www + siteName, domain );
        return value;
      }
      case PROD: {
        return value;
      }
	case TEST: {
		return value;
	}

      case STAGING: {
        return value;
      }
      case SANDBOX: {
        return value;
        //return String.join(".", www + siteName, this.env, domain);
      }
      default:
        throw new WebDriverException("Unknown environment type");
    }
   */

        switch (envType) {
            case DEV: {
                String devBoxOwner = this.env.split("-")[1];
                //  logger.info("getFormattedSiteHost  www: " + www + " siteName " + siteName + "envType " + domain + " devBoxOwner  " + devBoxOwner);

                return String.join(".", www + siteName, devBoxOwner, envType.getSiteDomain());
            }
            case PROD: {
                return String.join(".", www + siteName, envType.getSiteDomain());
            }
            case TEST: {
                return String.join(".", www + siteName);
            }
            case STAGING: {
                return String.join(".", www + siteName, envType.getSiteDomain());
            }
            case SANDBOX: {
                return String.join(".", www + siteName, this.env, envType.getSiteDomain());
            }
            default:
                throw new WebDriverException("Unknown environment type");
        }


    }

    private String getFormattedHostForGlobalUrl(EnvType env) {


        switch (env) {
            case DEV: {
                String devBoxOwner = this.env.split("-")[1];
                return String.join(".", "www", devBoxOwner, envType.getSiteDomain());

            }
            case SANDBOX: {
                return String.join(".", "www", this.env, envType.getSiteDomain());
            }
            case TEST: {
                return String.join(".", "www");
            }
            default:
                return String.join(".", "www", envType.getSiteDomain());
        }

  /* String domain=envType.getSiteDomain();
    String value="";
    String devBoxOwner ="";
    logger.info("getFormattedHostForGlobalUrl  envType "+domain );
    boolean subDomain=false;




    switch (env) {
      case DEV: {
      //  String devBoxOwner = this.env.split("-")[1];
       // return String.join(".", "www", devBoxOwner, domain);
        if(subDomain)
        {
          devBoxOwner = this.env.split("-")[1];
          return String.join(".", "www", devBoxOwner, domain);
        }
        else
        {
          return String.join(".", "www", devBoxOwner);

        }
      }
      case SANDBOX: {
       // return String.join(".", "www", this.env, domain);
        if(subDomain)
        {
          return String.join(".", "www", this.env, domain);
        }
        else
        {
          return String.join(".", "www", this.env);

        }
      }
       case TEST: {
        // return String.join(".", "www", this.env, domain);
         if(subDomain)
         {
           return String.join(".", "www", this.env, domain);
         }
         else
         {
           return String.join(".", "www", this.env);

         }
       }

 default:
        if(subDomain)
        {
          return String.join(".", "www" , domain);
        }
        else
        {
          return String.join(".", "www" );

        }

    }*/

    }

    public String getUrlProtocol() {
        return this.forceHttps ? "https" : "http";
    }

    public String getSiteGlobalURL() {

        HttpUrl.Builder urlBuilder = new HttpUrl.Builder();

        EnvType env = getEnvType(this.env);
        //add the port number to the url
        if (!envType.getPort().equals(""))
            urlBuilder.port(Integer.parseInt(envType.getPort()));


        return urlBuilder
                .scheme(getUrlProtocol())
                .host(getFormattedHostForGlobalUrl(env))
                .build()
                .toString().replaceFirst("/$", "");

    }

    private String getSiteGlobalName(String siteName) {

        // logger.info("getSiteGlobalName siteName : " + siteName + " this.env :" + this.env);
        if (siteName.endsWith(".connected")) {

            //   logger.info("getSiteGlobalName OLD SITE : " + siteName + " this.env :" + this.env);
            if (getEnvType(this.env) == EnvType.DEV) {
                return "global";
            } else {
                //     logger.info("getSiteGlobalName step 2 : " + siteName + " this.env :" + this.env);

                return siteName.replace(".connected", "");
            }
        } else {
            // logger.info("getSiteGlobalName SITE : " + siteName + " this.env :" + this.env);

            return siteName;
        }
    }
}
