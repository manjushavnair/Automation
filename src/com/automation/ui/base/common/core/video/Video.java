package com.automation.ui.base.common.core.video;

public interface Video {

    /**
     * When adding a video to site, backend is replacing some special characters, make sure you
     * return a title in the same form that is presented on front-end
     */
    public String getTitle();

    /**
     * Returns URL to video
     */
    public String getUrl();

    /**
     * Returns video ID
     */
    public String getID();

    /**
     * Get a video title in a site file link format. e. g. for a video titled 'Evolution -
     * Poznańska {site}-1424144130' a proper filename should be, 'Evolution_-_Poznańska_Wiki-1424144130',
     * so a url to video on site should look like: (siteName).site.com/wiki/File:WikiEvolution_-_Poznańska_Wiki-1424144130
     */
    public String getFileName();
}
