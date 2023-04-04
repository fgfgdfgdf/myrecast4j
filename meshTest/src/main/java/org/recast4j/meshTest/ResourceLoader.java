package org.recast4j.meshTest;


import org.recast4j.detour.NavMesh;
import org.recast4j.detour.io.MeshSetReader;
import org.recast4j.recast.geom.ConvexVolumeProvider;
import org.recast4j.recast.geom.InputGeomProvider;
import org.recast4j.recast.geom.SimpleInputGeomProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ResourceLoader {

    private static final String OBJ_FILE = "obj/";
    private static final String BIN_FILE = "bin/";

    public static InputGeomProvider getObjResource(String name) {
        InputStream inputStream = ResourceLoader.class.getClassLoader().getResourceAsStream(OBJ_FILE + name);
        return load(inputStream);
    }

    public static NavMesh getBinResource(String name) {
        InputStream inputStream = ResourceLoader.class.getClassLoader().getResourceAsStream(BIN_FILE + name);
        MeshSetReader meshSetReader = new MeshSetReader();
        NavMesh navMesh = null;
        try {
            navMesh = meshSetReader.read(inputStream, 6);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return navMesh;
    }


    private static class ObjImporterContext {
        List<Float> vertexPositions = new ArrayList<>();
        List<Integer> meshFaces = new ArrayList<>();
    }

    public static InputGeomProvider load(InputStream is) {
        ObjImporterContext context = new ObjImporterContext();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                readLine(line, context);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
        }
        return new SimpleInputGeomProvider(context.vertexPositions, context.meshFaces);
    }

    private static void readLine(String line, ObjImporterContext context) {
        if (line.startsWith("v")) {
            readVertex(line, context);
        } else if (line.startsWith("f")) {
            readFace(line, context);
        }
    }

    private static void readVertex(String line, ObjImporterContext context) {
        if (line.startsWith("v ")) {
            float[] vert = readVector3f(line);
            for (float vp : vert) {
                context.vertexPositions.add(vp);
            }
        }
    }

    private static float[] readVector3f(String line) {
        String[] v = line.split("\\s+");
        if (v.length < 4) {
            throw new RuntimeException("Invalid vector, expected 3 coordinates, found " + (v.length - 1));
        }
        return new float[]{Float.parseFloat(v[1]), Float.parseFloat(v[2]), Float.parseFloat(v[3])};
    }

    private static void readFace(String line, ObjImporterContext context) {
        String[] v = line.split("\\s+");
        if (v.length < 4) {
            throw new RuntimeException("Invalid number of face vertices: 3 coordinates expected, found " + v.length);
        }
        for (int j = 0; j < v.length - 3; j++) {
            context.meshFaces.add(readFaceVertex(v[1], context));
            for (int i = 0; i < 2; i++) {
                context.meshFaces.add(readFaceVertex(v[2 + j + i], context));
            }
        }
    }

    private static int readFaceVertex(String face, ObjImporterContext context) {
        String[] v = face.split("/");
        return getIndex(Integer.parseInt(v[0]), context.vertexPositions.size());
    }

    private static int getIndex(int posi, int size) {
        if (posi > 0) {
            posi--;
        } else if (posi < 0) {
            posi = size + posi;
        } else {
            throw new RuntimeException("0 vertex index");
        }
        return posi;
    }

}
