package com.automation.ui.connected.pageobjectsfactory.pageobject.serverdetails.filter;

public interface FilterDetailsCONSTANTS {


    public String ALLTAG = "//label[@for='s1']//span";
    public String LISTOFTAG = "//label[@for='s2']//span";
    public String ASSETTREETAG = "//label[@for='s3']//span";

    public String FILTER_NEXT = "//button[@class='primary next ng-binding']";
    public String CONNECTION_BACK = "//button[@class='link back ng-binding']";


    /*

<button class="primaryLink " ng-init="isHower = false">
                                    Edit Tags
                                    <ul ch-menu="" class="ch-dropMenu ui-menu ui-widget ui-widget-content" ng-mouseover="isHower = true" ng-mouseleave="isHower = false" id="ui-id-9" role="menu" tabindex="0">
                                        <li class="ui-menu-item">
                                            <a class="icon menu ui-menu-item-wrapper" aria-haspopup="true" id="ui-id-10" tabindex="-1" role="menuitem"><span class="ui-menu-icon ui-icon ui-icon-caret-1-e"></span>&nbsp;</a>
                                            <ul class="ch-dropMenuInner ui-menu ui-widget ui-widget-content ui-front ch-menuClose" ng-class="{'ch-menuOpen': isHower === true, 'ch-menuClose': isHower === false }" role="menu" aria-hidden="true" aria-expanded="false" style="display: none;">
                                                <li class="exportCodeJQ ui-menu-item">
                                                    <a class="icon download icon-blue ui-menu-item-wrapper" ng-click="steps.ExportConfigFile()" id="ui-id-11" tabindex="-1" role="menuitem">Export Config</a>
                                                </li>
                                                <li class="importCodeJQ ui-menu-item">
                                                    <a class="icon upload icon-blue ui-menu-item-wrapper" id="ui-id-12" tabindex="-1" role="menuitem">Import Config</a>
                                                </li>
                                            </ul>
                                        </li>
                                    </ul>
                                </button>




           * */
}
