package com.automation.ui.connected.pageobjectsfactory.pageobject.special.galleryboxes;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import lombok.Getter;

public final class SpecialUnusedVideosPage extends SiteBasePageObject {

    private static final String SPECIAL_UNUSED_VIDEOS_PATH = "Special:UnusedVideos";

    @Getter(lazy = true)
    private final GalleryGrid galleryGrid = new GalleryGrid();

    public SpecialUnusedVideosPage open() {
        getUrl(urlBuilder.getUrlForPage(SPECIAL_UNUSED_VIDEOS_PATH));
        Log.log("Special Unused Videos Page", SPECIAL_UNUSED_VIDEOS_PATH + " opened",
                true);

        return this;
    }
}
