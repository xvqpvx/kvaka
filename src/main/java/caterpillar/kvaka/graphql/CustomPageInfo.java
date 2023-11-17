package caterpillar.kvaka.graphql;

import graphql.relay.ConnectionCursor;
import graphql.relay.DefaultConnectionCursor;
import graphql.relay.PageInfo;

public class CustomPageInfo implements PageInfo {

    private final String startCursor;
    private final String endCursor;
    private final boolean hasNextPage;
    private final String previousPageCursor;

    public CustomPageInfo(String startCursor, String endCursor, boolean hasNextPage, String previousPageCursor) {
        this.startCursor = startCursor;
        this.endCursor = endCursor;
        this.hasNextPage = hasNextPage;
        this.previousPageCursor = previousPageCursor;
    }

    @Override
    public ConnectionCursor getStartCursor() {
        return new DefaultConnectionCursor(this.startCursor);
    }

    @Override
    public ConnectionCursor getEndCursor() {
        return new DefaultConnectionCursor(this.endCursor);
    }

    @Override
    public boolean isHasPreviousPage() {
        return this.previousPageCursor != null;
    }

    @Override
    public boolean isHasNextPage() {
        return this.hasNextPage;
    }
}