package com.automation.ui.connected.pageobjectsfactory.pageobject.special.galleryboxes;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import lombok.Getter;

public final class SpecialUncategorizedFilesPage extends SiteBasePageObject {

    private static final String SPECIAL_UNCATEGORIZED_FILES_PATH = "Special:UncategorizedFiles";

    @Getter(lazy = true)
    private final GalleryGrid galleryGrid = new GalleryGrid();

    public SpecialUncategorizedFilesPage open() {
        getUrl(urlBuilder.getUrlForPage(SPECIAL_UNCATEGORIZED_FILES_PATH));
        Log.log("Special Uncategorized Files Page",
                SPECIAL_UNCATEGORIZED_FILES_PATH + " opened", true);

        return this;
    }
}
