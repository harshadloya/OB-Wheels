package edu.binghamton.hloya1.obwheels;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

/**
 * Created by hloya on 3/29/2017.
 */

public class TraditionalObWheelScreen extends Fragment
{
    ImageView outer_move_zoom_rotate;
    ImageView inner_move_zoom_rotate;

    float scalediffX;
    float scalediffY;
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;
    private float oldDist = 1f;
    private float d = 0f;
    private float newRot = 0f;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.traditionalobwheel_screen, container, false);

        inner_move_zoom_rotate = (ImageView) view.findViewById(R.id.obWheel_inner);
        outer_move_zoom_rotate = (ImageView) view.findViewById(R.id.obWheel_outer);

        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, height);
        inner_move_zoom_rotate.setLayoutParams(layoutParams);

        inner_move_zoom_rotate.setOnTouchListener(rotateOnTouch);
        outer_move_zoom_rotate.setOnTouchListener(rotateOnTouch);

        // Working on this - Not Complete
        // inner_move_zoom_rotate.setOnTouchListener(panAndZoomOnTouch);

        return view;
    }

    View.OnTouchListener panAndZoomOnTouch = new View.OnTouchListener()
    {

        RelativeLayout.LayoutParams parms;
        int startwidth;
        int startheight;
        float dx = 0, dy = 0, x = 0, y = 0;
        float angle = 0;

        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            final ImageView view = (ImageView) v;

            ((BitmapDrawable) view.getDrawable()).setAntiAlias(true);

            switch (event.getAction() & MotionEvent.ACTION_MASK)
            {
                case MotionEvent.ACTION_DOWN:
                    parms = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    startwidth = parms.width;
                    startheight = parms.height;
                    dx = event.getRawX() - parms.leftMargin;
                    dy = event.getRawY() - parms.topMargin;
                    mode = DRAG;
                    break;

                case MotionEvent.ACTION_MOVE:
                    if (mode == DRAG)
                    {
                        x = event.getRawX();
                        y = event.getRawY();

                        parms.leftMargin = (int) (x - dx);
                        parms.topMargin = (int) (y - dy);

                        parms.rightMargin = 0;
                        parms.bottomMargin = 0;
                        parms.rightMargin = parms.leftMargin + (5 * parms.width);
                        parms.bottomMargin = parms.topMargin + (10 * parms.height);

                        view.setLayoutParams(parms);
                    }
                    else if (mode == ZOOM)
                    {
                        if (event.getPointerCount() == 2)
                        {
                           //newRot = rotation(event);
                            //float r = newRot - d;
                            // angle = r;

                         //   x = event.getRawX();
                         //   y = event.getRawY();
/*
                            float newDist = spacing(event);

                            if (newDist > 10f)
                            {
                                float scaleX = newDist / oldDist * view.getScaleX();
                                float scaleY = newDist / oldDist * view.getScaleY();
                                if (scaleX > 0.6 && scaleY > 0.6)
                                {
                                    scalediffX = scaleX;
                                    scalediffY = scaleY;
                                    view.setScaleX(scaleX);
                                    view.setScaleY(scaleY);

                                }
                            }


                            //  view.animate().rotationBy(angle).setDuration(0).setInterpolator(new LinearInterpolator()).start();
 */

                            x = event.getRawX();
                            y = event.getRawY();

                            parms.leftMargin = (int) ((x - dx) + scalediffX);
                            parms.topMargin = (int) ((y - dy) + scalediffY);

                            parms.rightMargin = 0;
                            parms.bottomMargin = 0;
                            parms.rightMargin = parms.leftMargin + (5 * parms.width);
                            parms.bottomMargin = parms.topMargin + (10 * parms.height);

                            view.setLayoutParams(parms);
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
}
