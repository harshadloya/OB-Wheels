package com.drstein.hloya1.obwheels;

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
import android.widget.RelativeLayout;

/**
 * Created by hloya on 3/29/2017.
 */

public class TraditionalObWheelScreen extends Fragment {
    private RelativeLayout relativeLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.traditionalobwheel_screen, container, false);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.inner_trial_circular_pic);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.outer_orange_cropped);

        relativeLayout = (RelativeLayout) view.findViewById(R.id.eddSettingDetailsScreen);

        View customImageView = new CustomImageView(getContext(), getResizedBitmap(bitmap, 0.25f, 0.25f), getResizedBitmap(bitmap2, 0.25f, 0.25f));

        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(width, height);
        lp.topMargin = 100;
        lp.bottomMargin = 100;
        lp.leftMargin = 16;
        lp.rightMargin = 16;

        relativeLayout.addView(customImageView, lp);
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
}

