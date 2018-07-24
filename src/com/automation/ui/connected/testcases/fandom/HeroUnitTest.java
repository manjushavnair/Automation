package com.automation.ui.connected.testcases.fandom;

import com.automation.ui.base.common.core.Assertion;
import com.automation.ui.connected.common.templates.fandom.FandomTestTemplate;
import com.automation.ui.connected.elements.fandom.pages.HomePage;
import org.testng.annotations.Test;

@Test(groups = {"Fandom", "Fandom_HeroUnit"})
public class HeroUnitTest extends FandomTestTemplate {

    @Test
    public void anonCanSeeHeroUnit() {
        HomePage homePage = new HomePage().open();

        Assertion.assertTrue(homePage.getHeroBlock().isDisplayed(), "Hero block is not displayed");
    }

    @Test
    public void anonCanSeeFiveHeroUnits() {
        HomePage homePage = new HomePage().open();

        Assertion.assertTrue(homePage.getHeroBlock().hasFiveHeroUnits(), "Hero block doesn't have five units.");
    }


}
