package com.automation.ui.connected.common.remote.discussions.context;

import com.automation.ui.base.common.remote.context.RemoteContext;
import com.automation.ui.connected.elements.mercury.components.discussions.common.PostEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class ThreadContext extends RemoteContext {

    private final String threadId;

    @Builder
    public ThreadContext(String siteId, String threadId) {
        super(siteId);
        this.threadId = threadId;
    }

    public static ThreadContext defaultContextUsing(final String siteId, final PostEntity.Data data) {
        Objects.requireNonNull(data.getId());

        return ThreadContext.builder()
                .siteId(siteId)
                .threadId(data.getId())
                .build();
    }
}