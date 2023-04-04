package org.recast4j.detour;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines a navigation mesh tile.
 */
public class MeshTile {

    final int index;

    /** Counter describing modifications to the tile */
    int salt;
    /** The tile data */
    public MeshData data;

    public int[] polyLinks;
    /** The tile links. */
    public final List<Link> links = new ArrayList<>();
    /** Index to the next free link. */
    int linksFreeList = NavMesh.DT_NULL_LINK;

    /** Tile flags. (See: #dtTileFlags) */
    int flags;

    public MeshTile(int index) {
        this.index = index;
    }

}
