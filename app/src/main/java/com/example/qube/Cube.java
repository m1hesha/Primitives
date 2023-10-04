package com.example.qube;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Cube {
    private FloatBuffer vertexBuffer;
    private int numFaces = 6;

    private float[][] colors = {  // Цвета для 6 граней куба
            {1f, 0f, 0f, 1f},  // 1. красный
            {1f, 0f, 0f, 1f},  // 2. оранжевый
            {1f, 1f, 0f, 1f},  // 3. желтый
            {0f, 1f, 0f, 1f},  // 4. зеленый
            {0f, 0f, 1f, 1f},  // 5. синий
            {1f, 0f, 1f, 1f},  // 6. фиолетовый
    };

    private float[] vertices = {  // Вершины для  граней куба
            // Передняя грань
            -1.0f, -1.0f,  1.0f,    // 0. лево-низ-перед
            1.0f, -1.0f,  1.0f,     // 1. право-низ-перед
            -1.0f,  1.0f,  1.0f,    // 2. лево-верх-перед
            1.0f,  1.0f,  1.0f,     // 3. право-верх-перед
            // Задняя грань
            1.0f, -1.0f, -1.0f,     // 6. право-низ-зад
            -1.0f, -1.0f, -1.0f,    // 4. лево-низ-зад
            1.0f,  1.0f, -1.0f,     // 7. право-верх-зад
            -1.0f,  1.0f, -1.0f,    // 5. лево-верх-зад
            // Левая грань
            -1.0f, -1.0f, -1.0f,    // 4. лево-низ-зад
            -1.0f, -1.0f,  1.0f,    // 0. лево-низ-перед
            -1.0f,  1.0f, -1.0f,    // 5. лево-верх-зад
            -1.0f,  1.0f,  1.0f,    // 2. лево-верх-перед
            // Правая грань
            1.0f, -1.0f,  1.0f,     // 1. право-низ-перед
            1.0f, -1.0f, -1.0f,     // 6. право-низ-зад
            1.0f,  1.0f,  1.0f,     // 3. право-верх-перед
            1.0f,  1.0f, -1.0f,     // 7. право-верх-зад
            // Верхняя грань
            -1.0f,  1.0f,  1.0f,    // 2. лево-верх-перед
            1.0f,  1.0f,  1.0f,     // 3. право-верх-перед
            -1.0f,  1.0f, -1.0f,    // 5. лево-верх-зад
            1.0f,  1.0f, -1.0f,     // 7. право-верх-зад
            // Нижняя грань
            -1.0f, -1.0f, -1.0f,    // 4. лево-низ-зад
            1.0f, -1.0f, -1.0f,     // 6. право-низ-зад
            -1.0f, -1.0f,  1.0f,    // 0. лево-низ-перед
            1.0f, -1.0f,  1.0f      // 1. право-низ-перед
    };

    public Cube() {
        ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        vertexBuffer = byteBuf.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);
    }

    public void draw(GL10 gl) {
        gl.glFrontFace(GL10.GL_CCW);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glCullFace(GL10.GL_BACK);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

        for (int face = 0; face < numFaces; face++) {
            gl.glColor4f(colors[face][0], colors[face][1], colors[face][2], colors[face][3]);
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, face * 4, 4);
        }

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisable(GL10.GL_CULL_FACE);
    }
}
