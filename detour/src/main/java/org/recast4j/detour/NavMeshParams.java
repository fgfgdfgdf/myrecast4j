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

/**
 * NavMesh配置参数,初始化时分配
 * @see NavMesh
 */
public class NavMeshParams {

    /** 原点 */
    public final float[] orig = new float[3];
    /** The width of each tile. (Along the x-axis.) */
    public float tileWidth;
    /** The height of each tile. (Along the z-axis.) */
    public float tileHeight;

    public int maxTiles;
    /** The maximum number of polygons each tile can contain. */
    public int maxPolys;
}
