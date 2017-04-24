/*
Referenced from https://www.codeproject.com/Articles/319401/Simple-Gestures-on-Android?msg=5031067
 */

package edu.binghamton.hloya1.obwheels;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Switch;

public class CustomImageView extends View implements OnTouchListener {

    private final Bitmap bitmap;
    private final Bitmap bitmap2;
    private final float width;
    private final float height;
    private final float width2;
    private final float height2;

    private Matrix transform = new Matrix();
    private Matrix transform2 = new Matrix();

    private Vector2D position = new Vector2D();
    private Vector2D position2 = new Vector2D();

    private float scale = 0.7f;
    private float angle = 0;
    private float angle2 = 0;

    private TouchManager touchManager = new TouchManager(2);

    private boolean isInitialized = false;
    private boolean isInitializedBitmap2 = false;

    private boolean isZoomOn = false;

    /*
    // Draw lines between the two touch points
    private Vector2D point1x = null;
    private Vector2D point1y = null;
    private Vector2D point2x = null;
    private Vector2D point2y = null;
    */

    public CustomImageView(Context context, Bitmap bitmap, Bitmap bitmap2) {
        super(context);

        this.bitmap = bitmap;
        this.width = bitmap.getWidth();
        this.height = bitmap.getHeight();

        this.bitmap2 = bitmap2;
        this.width2 = bitmap2.getWidth();
        this.height2 = bitmap2.getHeight();

        setOnTouchListener(this);
    }

    private static float getDegreesFromRadians(float angle) {
        return (float) (angle * 180.0 / Math.PI);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!isInitialized) {
            int w = getWidth();
            int h = getHeight();
            position.set(w / 2, h / 2);
            isInitialized = true;
        }

        if (!isInitializedBitmap2) {
            int w2 = getWidth();
            int h2 = getHeight();
            position2.set(w2 / 2, h2 / 2);
            isInitializedBitmap2 = true;
        }

        Paint paint = new Paint();

        transform.reset();
        transform.postTranslate(-width / 2.0f, -height / 2.0f);
        transform.postRotate(getDegreesFromRadians(angle));
        transform.postScale(scale, scale);
        transform.postTranslate(position.getX(), position.getY());

        transform2.reset();
        transform2.postTranslate(-width2 / 2.0f, -height2 / 2.0f);
        transform2.postRotate(getDegreesFromRadians(angle2));
        transform2.postScale(scale, scale);
        transform2.postTranslate(position2.getX(), position2.getY());

        canvas.drawBitmap(bitmap2, transform2, paint);
        canvas.drawBitmap(bitmap, transform, paint);
/*
        try {
			paint.setColor(0xFF007F00);
			canvas.drawCircle(point1x.getX(), point1x.getY(), 64, paint);
			paint.setColor(0xFF7F0000);
			canvas.drawCircle(point1y.getX(), point1y.getY(), 64, paint);

			paint.setColor(0xFFFF0000);
			canvas.drawLine(point2x.getX(), point2x.getY(), point2y.getX(), point2y.getY(), paint);
			paint.setColor(0xFF00FF00);
			canvas.drawLine(point1x.getX(), point1x.getY(), point1y.getX(), point1y.getY(), paint);
		}
		catch(NullPointerException e) {
			// Just being lazy here...
		}
*/
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //point1x = null;
        //point1y = null;
        //point2x = null;
        //point2y = null;

        try {
            View c = (View) v.getParent();
            Switch s = (Switch) c.findViewById(R.id.switch1);
            isZoomOn = s.isChecked();

            touchManager.update(event);

            if (touchManager.getPressCount() == 1) {
                //point1x = touchManager.getPoint(0);
                //point2x = touchManager.getPreviousPoint(0);
                position.add(touchManager.moveDelta(0));
                position2.add(touchManager.moveDelta(0));
            } else {
                if (touchManager.getPressCount() == 2) {
                    //point1x = touchManager.getPoint(0);
                    //point2x = touchManager.getPreviousPoint(0);
                    //point1y = touchManager.getPoint(1);
                    //point2y = touchManager.getPreviousPoint(1);

                    Vector2D current = touchManager.getVector(0, 1);
                    Vector2D previous = touchManager.getPreviousVector(0, 1);
                    float currentDistance = current.getLength();
                    float previousDistance = previous.getLength();

                    if (isZoomOn) {
                        if (previousDistance != 0 && (currentDistance > previousDistance + 3 || currentDistance < previousDistance - 3)) {
                            scale *= currentDistance / previousDistance;
                        }
                    } else {
                        angle -= Vector2D.getSignedAngleBetween(current, previous);

                        if (currentDistance > Math.max(bitmap.getHeight(), bitmap.getWidth()))
                            angle2 -= Vector2D.getSignedAngleBetween(current, previous);
                    }
                }
            }
            invalidate();
        } catch (Throwable t) {
        }
        return true;
    }

}