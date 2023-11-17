package caterpillar.kvaka.graphql;

import caterpillar.kvaka.entity.InventorsInventions;

public class InventorsInventionsEdge {
    private InventorsInventions node;
    private String cursor;

    public InventorsInventionsEdge(InventorsInventions node, String cursor) {
        this.node = node;
        this.cursor = cursor;
    }

    public InventorsInventions getNode() {
        return node;
    }

    public String getCursor() {
        return cursor;
    }
}

