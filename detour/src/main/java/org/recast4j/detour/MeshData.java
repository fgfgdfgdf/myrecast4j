package org.recast4j.detour;

public class MeshData {

    /** The tile header. */
    public MeshHeader header;
    /** The tile vertices. [Size: MeshHeader::vertCount] */
    public float[] verts;
    /** The tile polygons. [Size: MeshHeader::polyCount] */
    public Poly[] polys;
    /** The tile's detail sub-meshes. [Size: MeshHeader::detailMeshCount] */
    public PolyDetail[] detailMeshes;
    /** The detail mesh's unique vertices. [(x, y, z) * MeshHeader::detailVertCount] */
    public float[] detailVerts;
    /**
     * The detail mesh's triangles. [(vertA, vertB, vertC) * MeshHeader::detailTriCount] See DetailTriEdgeFlags and
     * NavMesh::getDetailTriEdgeFlags.
     */
    public int[] detailTris;
    /**
     * The tile bounding volume nodes. [Size: MeshHeader::bvNodeCount] (Will be null if bounding volumes are disabled.)
     */
    public BVNode[] bvTree;
    /** The tile off-mesh connections. [Size: MeshHeader::offMeshConCount] */
    public OffMeshConnection[] offMeshCons;

}