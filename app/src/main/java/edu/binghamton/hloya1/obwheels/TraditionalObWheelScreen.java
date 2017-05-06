package edu.binghamton.hloya1.obwheels;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by hloya on 3/29/2017.
 */

public class TraditionalObWheelScreen extends Fragment
{
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    ImageView outer_move_zoom_rotate;
    ImageView inner_move_zoom_rotate;
    float scalediffX1;
    float scalediffY1;
    float scalediffX2;
    float scalediffY2;
    private int mode = NONE;
    private float oldDist = 1f;
    private float d = 0f;
    private float newRot = 0f;
    private Matrix matrix = new Matrix();
    private RelativeLayout relativeLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.traditionalobwheel_screen, container, false);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.inner_trial_circular_pic);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.outer_orange_cropped);

        //CustomImageView customImageView = (CustomImageView) view.findViewById(R.id.viewForImage);
        //customImageView.initializeData(getContext(), getResizedBitmap(bitmap, 0.25f, 0.25f), getResizedBitmap(bitmap2, 0.25f, 0.25f));

        relativeLayout = (RelativeLayout) view.findViewById(R.id.eddSettingDetailsScreen);

        View customImageView = new CustomImageView(getContext(), getResizedBitmap(bitmap, 0.25f, 0.25f), getResizedBitmap(bitmap2, 0.25f, 0.25f));

        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(width, height);
        lp.topMargin = 130;
        lp.bottomMargin = 70;
        lp.leftMargin = 16;
        lp.rightMargin = 16;

        relativeLayout.addView(customImageView, lp);

        /*
        inner_move_zoom_rotate = (ImageView) view.findViewById(R.id.obWheel_inner);
        outer_move_zoom_rotate = (ImageView) view.findViewById(R.id.obWheel_outer);


        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, height);
        inner_move_zoom_rotate.setLayoutParams(layoutParams);
        outer_move_zoom_rotate.setLayoutParams(layoutParams);

        inner_move_zoom_rotate.setOnTouchListener(rotateOnTouch);
        //outer_move_zoom_rotate.setOnTouchListener(rotateOnTouch);

        // Working on this - Not Complete
        outer_move_zoom_rotate.setOnTouchListener(panAndZoomOnTouch);

        */
        return view;
    }


    public Bitmap getResizedBitmap(Bitmap bm, float scaleWidth, float scaleHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();

        //Using matrix for image manipulation
        Matrix matrix = new Matrix();

        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // Creating the new scaled bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }

/*
    View.OnTouchListener panAndZoomOnTouch = new View.OnTouchListener()
    {
        RelativeLayout.LayoutParams parms1;
        int startwidth1;
        int startheight1;
        float dx1 = 0, dy1 = 0, x1 = 0, y1 = 0;
        float angle1 = 0;

        RelativeLayout.LayoutParams parms2;
        int startwidth2;
        int startheight2;
        float dx2 = 0, dy2 = 0, x2 = 0, y2 = 0;
        float angle2 = 0;

        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            final ImageView view1 = (ImageView) v;
            final ImageView view2 = (ImageView) inner_move_zoom_rotate;

            ((BitmapDrawable) view1.getDrawable()).setAntiAlias(true);
            ((BitmapDrawable) view2.getDrawable()).setAntiAlias(true);

            switch (event.getAction() & MotionEvent.ACTION_MASK)
            {
                case MotionEvent.ACTION_DOWN:
                    parms1 = (RelativeLayout.LayoutParams) view1.getLayoutParams();
                    startwidth1 = parms1.width;
                    startheight1 = parms1.height;
                    dx1 = event.getRawX() - parms1.leftMargin;
                    dy1 = event.getRawY() - parms1.topMargin;

                    parms2 = (RelativeLayout.LayoutParams) view2.getLayoutParams();
                    startwidth2 = parms2.width;
                    startheight2 = parms2.height;
                    dx2 = event.getRawX() - parms2.leftMargin;
                    dy2 = event.getRawY() - parms2.topMargin;

                    mode = DRAG;
                    break;

                case MotionEvent.ACTION_POINTER_DOWN:
                    oldDist = spacing(event);
                    if (oldDist > 10f) {
                        mode = ZOOM;
                    }

                    break;

                case MotionEvent.ACTION_MOVE:
                    if (mode == DRAG)
                    {
                        x1 = event.getRawX();
                        y1 = event.getRawY();

                        parms1.leftMargin = (int) (x1 - dx1);
                        parms1.topMargin = (int) (y1 - dy1);

                        parms1.rightMargin = 0;
                        parms1.bottomMargin = 0;
                        parms1.rightMargin = parms1.leftMargin + (5 * parms1.width);
                        parms1.bottomMargin = parms1.topMargin + (10 * parms1.height);

                        view1.setLayoutParams(parms1);

                        x2 = event.getRawX();
                        y2 = event.getRawY();

                        parms2.leftMargin = (int) (x2 - dx2);
                        parms2.topMargin = (int) (y2 - dy2);

                        parms2.rightMargin = 0;
                        parms2.bottomMargin = 0;
                        parms2.rightMargin = parms2.leftMargin + (5 * parms2.width);
                        parms2.bottomMargin = parms2.topMargin + (10 * parms2.height);

                        view2.setLayoutParams(parms2);
                    }
                    else if (mode == ZOOM)
                    {
                        if (event.getPointerCount() == 2)
                        {
                            //
                        }
                    }
                    break;

                default:
                    break;
            }
            return true;
        }
    };

    View.OnTouchListener rotateOnTouch = new View.OnTouchListener()
    {
        float angle = 0;
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            final ImageView view = (ImageView) v;
            ((BitmapDrawable) view.getDrawable()).setAntiAlias(true);

            switch (event.getAction() & MotionEvent.ACTION_MASK)
            {
                case MotionEvent.ACTION_POINTER_DOWN:

                    d = rotation(event);
                    break;

                case MotionEvent.ACTION_MOVE:

                    if (event.getPointerCount() == 2)
                    {
                        newRot = rotation(event);
                        float r = newRot - d;
                        angle = r;
                        view.animate().rotationBy(angle).setDuration(0).setInterpolator(new LinearInterpolator()).start();
                    }
                    break;

                default:
                    break;
            }
            return true;
        }
    };

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    private float rotation(MotionEvent event) {
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }

    */
}

