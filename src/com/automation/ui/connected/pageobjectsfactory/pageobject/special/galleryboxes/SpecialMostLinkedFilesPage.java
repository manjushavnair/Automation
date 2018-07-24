package com.automation.ui.connected.pageobjectsfactory.pageobject.special.galleryboxes;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import lombok.Getter;

public final class SpecialMostLinkedFilesPage extends SiteBasePageObject {

    private static final String SPECIAL_MOST_LINKED_FILES_PATH = "Special:MostLinkedFiles";

    @Getter(lazy = true)
    private final GalleryGrid galleryGrid = new GalleryGrid();

    public SpecialMostLinkedFilesPage open() {
        getUrl(urlBuilder.getUrlForPage(SPECIAL_MOST_LINKED_FILES_PATH));
        Log.log("Special Most Linked Files Page",
                SPECIAL_MOST_LINKED_FILES_PATH + " opened", true);

        return this;
    }
}
