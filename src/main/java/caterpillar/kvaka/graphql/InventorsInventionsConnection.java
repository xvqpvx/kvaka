package caterpillar.kvaka.graphql;

import graphql.relay.PageInfo;

import java.util.List;

public class InventorsInventionsConnection {
    private List<InventorsInventionsEdge> edges;
    private PageInfo pageInfo;

    public InventorsInventionsConnection(List<InventorsInventionsEdge> edges, PageInfo pageInfo) {
        this.edges = edges;
        this.pageInfo = pageInfo;
    }

    public List<InventorsInventionsEdge> getEdges() {
        return edges;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }
}

