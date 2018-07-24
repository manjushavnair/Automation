package com.automation.ui.connected.common.remote.discussions;

import com.automation.ui.base.common.core.helpers.User;
import com.automation.ui.base.common.remote.operations.http.PutRemoteOperation;

public class LockPost extends PostPadlock {

    public LockPost(User user) {
        super(new PutRemoteOperation(user));
    }
}
