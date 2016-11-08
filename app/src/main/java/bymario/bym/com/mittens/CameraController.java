package bymario.bym.com.mittens;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CameraController {

    private Context context;

    private boolean hasCamera;

    private Camera camera;
    private int cameraId;

    public CameraController(Context c) {
        context = c.getApplicationContext();

        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            cameraId = getFrontCameraId();

            if (cameraId != -1) {
                hasCamera = true;
            } else {
                hasCamera = false;
            }
        } else {
            hasCamera = false;
        }
    }

    public boolean hasCamera() {
        return hasCamera;
    }

    public void getCameraInstance() {
        camera = null;

        if (hasCamera) {
            try {
                camera = Camera.open(cameraId);
                prepareCamera();
            } catch (Exception e) {
                hasCamera = false;
            }
        }
    }

    public void takePicture() {
        if (hasCamera) {
            camera.takePicture(null, null, mPicture);
        }
    }

    public void releaseCamera() {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

    private int getFrontCameraId() {
        int camId = -1;
        int numberOfCameras = Camera.getNumberOfCameras();
        Camera.CameraInfo ci = new Camera.CameraInfo();

        for (int i = 0; i < numberOfCameras; i++) {
            Camera.getCameraInfo(i, ci);
            if (ci.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                camId = i;
            }
        }

        return camId;
    }

    private void prepareCamera() {
        SurfaceView view = new SurfaceView(context);

        try {
            camera.setPreviewDisplay(view.getHolder());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        camera.startPreview();

        Camera.Parameters params = camera.getParameters();
        params.setJpegQuality(100);

        camera.setParameters(params);
    }

    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File pictureFile = getOutputMediaFile();

            if (pictureFile == null) {
                return;
            }

            try {

                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
                MediaScannerConnection.scanFile(context,
                        new String[]{
                                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + "/MyCameraApp/" + pictureFile.getName()
                        },
                        null,
                        new MediaScannerConnection.OnScanCompletedListener() {
                            @Override
                            public void onScanCompleted(String path, Uri uri) {
                                Log.d("MITTENS", "Scanned: " + path);
                                Log.d("MITTENS", "uri=" + uri);
                            }
                        }

                );


                releaseCamera();
            } catch (FileNotFoundException e) {
                Log.d("MITTENS", "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.d("MITTENS", "Error accessing file: " + e.getMessage());
            }
        }
    };

    private File getOutputMediaFile() {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.


        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyCameraApp");

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + "_mittens.jpg");
        return mediaFile;
    }
}