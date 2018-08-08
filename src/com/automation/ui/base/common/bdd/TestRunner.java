package com.automation.ui.base.common.bdd;

import cucumber.api.CucumberOptions;
//import cucumber.api.junit.Cucumber;


//@RunWith(Cucumber.class)
@CucumberOptions(
        features = "Feature"
        , glue = {"com.automation.ui.base.common.bdd"}
        , monochrome = false
)

public class TestRunner {

}


