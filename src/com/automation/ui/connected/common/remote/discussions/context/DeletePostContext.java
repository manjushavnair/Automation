package com.automation.ui.connected.common.remote.discussions.context;

import com.automation.ui.base.common.remote.context.RemoteContext;
import com.automation.ui.connected.elements.mercury.components.discussions.common.PostEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class DeletePostContext extends RemoteContext {

    private final String postId;

    @Builder
    public DeletePostContext(String siteId, String postId) {
        super(siteId);
        this.postId = postId;
    }

    public static DeletePostContext defaultContextUsing(final String siteId, final PostEntity.Data data) {
        Objects.requireNonNull(data.getId());

        return DeletePostContext.builder()
                .siteId(siteId)
                .postId(data.getId())
                .build();
    }
}
