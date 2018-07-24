package com.automation.ui.connected.testcases.globalnavigationbar;


import com.automation.ui.base.common.contentpatterns.URLsContent;
import com.automation.ui.base.common.core.Assertion;
import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.core.configuration.EnvType;
import com.automation.ui.base.common.core.url.UrlBuilder;
import com.automation.ui.connected.common.templates.NewTestTemplate;
import com.automation.ui.connected.pageobjectsfactory.pageobject.HomePage;
import org.testng.annotations.Test;

@Test(groups = {"globalnavigationbar", "globalnavigationbarNavigating"})
public class Navigating extends NewTestTemplate {

    @Test(groups = {"fandomLogoClickOnEnCommunityOpensFandomWikia"})
    public void logoClickOnEnglishCommunityOpensFandom() {
        new HomePage()
                .getGlobalNavigation()
                .clickFandomLogo();

        Assertion.assertEquals(driver.getCurrentUrl(), fandomUrlBuilder.getFandomUrl(EnvType.PROD));
    }

    @Test(groups = {"gamesHubLinkClickOnEnCommunityOpensGamesHub"})
    public void testGamesHubLink() {
        new HomePage()
                .getGlobalNavigation()
                .clickGamesHubLink();

        Assertion.assertEquals(driver.getCurrentUrl(), fandomUrlBuilder.getFandomUrl(EnvType.PROD) + "topics/games");
    }

    @Test(groups = {"moviesHubLinkClickOnEnCommunityOpensMoviesHub"})
    public void testMoviesHubLink() {
        new HomePage()
                .getGlobalNavigation()
                .clickMoviesHubLink();

        Assertion.assertEquals(driver.getCurrentUrl(), fandomUrlBuilder.getFandomUrl(EnvType.PROD) + "topics/movies");
    }

    @Test(groups = {"tvHubLinkClickOnEnCommunityOpensTvHub"})
    public void testTVHubLink() {
        new HomePage()
                .getGlobalNavigation()
                .clickTVHubLink();

        Assertion.assertEquals(driver.getCurrentUrl(), fandomUrlBuilder.getFandomUrl(EnvType.PROD) + "topics/tv");
    }


    @Test(groups = {"exploreWikisLinkClickOnEnCommunityOpensExplorePage"})
    public void testExploreWikisLink() {
        new HomePage()
                .getGlobalNavigation()
                .openWikisMenu()
                .clickExploreWikisLink();

        Assertion.assertEquals(driver.getCurrentUrl(), fandomUrlBuilder.getFandomUrl(EnvType.PROD) + "explore");
    }

    @Test(groups = {"communityCentralLinkClickOnEnCommunityOpensEnCommunityCentral"})
    public void testCommunityCentralLink() {
        new HomePage()
                .getGlobalNavigation()
                .openWikisMenu()
                .clickCommunityCentralLink();

        Assertion.assertEquals(driver.getCurrentUrl(),
                UrlBuilder.createUrlBuilderForSiteAndLang(URLsContent.COMMUNITY_WIKI, Configuration.DEFAULT_LANGUAGE).getUrlForPage(
                        URLsContent.COMMUNITY_CENTRAL));
    }
}
