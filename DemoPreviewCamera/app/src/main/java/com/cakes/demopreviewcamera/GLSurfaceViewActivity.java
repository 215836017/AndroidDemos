package com.cakes.demopreviewcamera;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.opengl.GLES11Ext;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLSurfaceView.RENDERMODE_WHEN_DIRTY;

public class GLSurfaceViewActivity extends AppCompatActivity {

    private Camera camera;
    private GLSurfaceView gLSurfaceView;
    int mTextureID = -1;
    SurfaceTexture mSurface;
    DirectDrawer mDirectDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glsurface_view);

        camera = Camera.open(1);
        gLSurfaceView = findViewById(R.id.gLSurfaceView);
        gLSurfaceView.setEGLContextClientVersion(2);
        gLSurfaceView.setRenderer(renderer);
        gLSurfaceView.setRenderMode(RENDERMODE_WHEN_DIRTY);
    }

    @Override
    protected void onStop() {
        super.onStop();
        gLSurfaceView.onPause();
        if (camera != null) {
            camera.stopPreview();
            camera.release();
        }
    }

    private GLSurfaceView.Renderer renderer = new GLSurfaceView.Renderer() {
        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            mTextureID = createTextureID();
            mSurface = new SurfaceTexture(mTextureID);
            mSurface.setOnFrameAvailableListener(new SurfaceTexture.OnFrameAvailableListener() {
                @Override
                public void onFrameAvailable(SurfaceTexture surfaceTexture) {
                    gLSurfaceView.requestRender();
                }
            });
            mDirectDrawer = new DirectDrawer(mTextureID);
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            GLES20.glViewport(0, 0, width, height);
            try {
                camera.setPreviewTexture(mSurface);
                camera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            Log.d("GLSurfaceViewActivity", "onDrawFrame() -- 11111");
            GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
            mSurface.updateTexImage();
            float[] mtx = new float[16];
            mSurface.getTransformMatrix(mtx);
            mDirectDrawer.draw(mtx);
        }
    };

    private int createTextureID() {
        int[] texture = new int[1];

        GLES20.glGenTextures(1, texture, 0);
        GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, texture[0]);
        GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        GLES20.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
                GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);

        return texture[0];
    }
}
