package com.example.qube;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.WindowManager;

public class MainActivity extends Activity {
    private GLSurfaceView g;
    private MyRenderer myRenderer;
    // private static final float MIN_SWIPE_DISTANCE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
        myRenderer = new MyRenderer(this);

        g = new GLSurfaceView(this);
        g.setEGLConfigChooser(8, 8, 8, 8, 16, 1);
        g.setRenderer(myRenderer);
        g.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        setContentView(g);
    }

    @Override
    protected void onPause() {
        super.onPause();
        g.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        g.onResume();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                myRenderer.nextPage();
                break;
        }
        return super.onTouchEvent(event);
    }
}
