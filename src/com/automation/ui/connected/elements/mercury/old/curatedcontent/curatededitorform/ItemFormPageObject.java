package com.automation.ui.connected.elements.mercury.old.curatedcontent.curatededitorform;

import com.automation.ui.connected.elements.mercury.old.curatedcontent.CuratedEditorFormPageObject;
import com.automation.ui.connected.elements.mercury.old.curatedcontent.EditorHomePageObject;

/**
 * This class represents item editor Form That object differs from Section Form: Done button
 * redirects you to main editor home It has two text fields to fill instead of one That object
 * differs from Category Form: It allows more namespaces than category
 */
public class ItemFormPageObject extends CuratedEditorFormPageObject {

    public EditorHomePageObject clickDone() {
        //NEEDTOCHECK
        wait.forElementVisibleW(doneButton);
        doneButton.click();

        return new EditorHomePageObject();
    }
}
