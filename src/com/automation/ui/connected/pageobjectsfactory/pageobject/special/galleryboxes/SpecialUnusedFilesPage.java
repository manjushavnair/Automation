package com.automation.ui.connected.pageobjectsfactory.pageobject.special.galleryboxes;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import lombok.Getter;

public final class SpecialUnusedFilesPage extends SiteBasePageObject {

    private static final String SPECIAL_UNUSED_FILES_PATH = "Special:UnusedFiles";

    @Getter(lazy = true)
    private final GalleryGrid galleryGrid = new GalleryGrid();

    public SpecialUnusedFilesPage open() {
        getUrl(urlBuilder.getUrlForPage(SPECIAL_UNUSED_FILES_PATH));
        Log.log("Special Unused Files Page", SPECIAL_UNUSED_FILES_PATH + " opened", true);

        return this;
    }
}
