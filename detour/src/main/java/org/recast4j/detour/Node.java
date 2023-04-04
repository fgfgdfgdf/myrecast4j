/*
Copyright (c) 2009-2010 Mikko Mononen memon@inside.org
recast4j copyright (c) 2015-2019 Piotr Piastucki piotr@jtilia.org

This software is provided 'as-is', without any express or implied
warranty.  In no event will the authors be held liable for any damages
arising from the use of this software.
Permission is granted to anyone to use this software for any purpose,
including commercial applications, and to alter it and redistribute it
freely, subject to the following restrictions:
1. The origin of this software must not be misrepresented; you must not
 claim that you wrote the original software. If you use this software
 in a product, an acknowledgment in the product documentation would be
 appreciated but is not required.
2. Altered source versions must be plainly marked as such, and must not be
 misrepresented as being the original software.
3. This notice may not be removed or altered from any source distribution.
*/
package org.recast4j.detour;

import java.util.List;

public class Node {

    static int DT_NODE_OPEN = 0x01;
    static int DT_NODE_CLOSED = 0x02;
    /**
     * 节点的父节点不相临, 使用光线投射查找
     */
    static int DT_NODE_PARENT_DETACHED = 0x04;

    public final int index;

    /** Position of the node. */
    public float[] pos = new float[3];
    /** Cost of reaching the given node. */
    public float cost;
    /** Total cost of reaching the goal via the given node including heuristics. */
    float total;
    /** Index to parent node. */
    public int pidx;
    /**
     * extra state information. A polyRef can have multiple nodes with different extra info. see DT_MAX_STATES_PER_NODE
     */
    int state;
    /** Node flags. A combination of dtNodeFlags. */
    int flags;
    /** Polygon ref the node corresponds to. */
    long id;
    /** Shortcut found by raycast. */
    List<Long> shortcut;

    public Node(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Node [id=" + id + "]";
    }

}
