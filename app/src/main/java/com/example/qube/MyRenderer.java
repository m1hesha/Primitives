package com.example.qube;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyRenderer implements GLSurfaceView.Renderer {
    Context context;
    private Square mSquare;
    private Cube mCube;
    private Sphere mSphere;
    private static float angleCube = 0;
    private static float speedCube = -1.5f;
    private float mTransY = 0f;
    private float mAngle = 0;
    private int currentPage = 0;

    public MyRenderer(Context context) {
        this.context = context;
        mSquare = new Square();
        mCube = new Cube();
        mSphere = new Sphere(10);
    }

    public void nextPage() {
        currentPage = (currentPage + 1) % 3;
    }

    public void prevPage() {
        currentPage = (currentPage - 1 + 3) % 3;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glClearDepthf(1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glDisable(GL10.GL_DITHER);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        if (height == 0) height = 1;
        float aspect = (float) width / height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 45, aspect, 0.1f, 100.f);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        switch (currentPage) {
            case 0:
                gl.glLoadIdentity();
                gl.glTranslatef(0.0f, 0.0f, -15.0f);
                gl.glRotatef(mAngle, 0, 0, -1);
                mSquare.draw(gl);
                break;
            case 1:
                gl.glLoadIdentity();
                gl.glTranslatef(0.0f, 0.0f, -4.0f);
                gl.glScalef(1f, 1f, 1f);
                gl.glRotatef(angleCube, 1.0f, 1.0f, 1.0f);
                gl.glScalef(0.2f, 0.2f, 0.2f);
                mCube.draw(gl);
                break;
            case 2:
                gl.glLoadIdentity();
                gl.glTranslatef(0f, 0f, -4.5f);
                gl.glScalef(1f, 1f, 1f);
                gl.glRotatef(mAngle, 1, -1, 0);
                gl.glScalef(0.05f, 0.05f, 0.05f);
                mSphere.draw(gl);
                break;
        }
        angleCube += speedCube;
        mAngle += 1.8;
    }
}
