package com.automation.ui.connected.elements.mercury.old.curatedcontent.curatededitorform;

import com.automation.ui.connected.elements.mercury.old.curatedcontent.CuratedEditorFormPageObject;

/**
 * This class represents Section editor Form That object differs from Category and Item Form: Done
 * button redirects to section items view It has one text field to fill instead of two It doesn't
 * allow adding namespace item, it creates a section
 */
public class SectionFormPageObject extends CuratedEditorFormPageObject {

    public SectionItemListPageObject clickDone() {
        //NEEDTOCHECK
        wait.forElementVisibleW(doneButton);
        doneButton.click();

        return new SectionItemListPageObject();
    }
}
