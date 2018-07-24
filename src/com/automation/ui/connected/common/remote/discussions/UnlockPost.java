package com.automation.ui.connected.common.remote.discussions;

import com.automation.ui.base.common.core.helpers.User;
import com.automation.ui.base.common.remote.operations.http.DeleteRemoteOperation;

public class UnlockPost extends PostPadlock {

    public UnlockPost(User user) {
        super(new DeleteRemoteOperation(user));
    }
}
