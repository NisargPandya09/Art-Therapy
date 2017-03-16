package csulb.hw3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by nisar on 14/03/2017.
 */

public class CustomDrawableView extends View {

    public int width;
    public  int height;
    private Bitmap mBitmap;
    private Canvas  mCanvas;
    private Paint   mBitmapPaint;
    Context context;

    private Paint paint = new Paint() ;
    private Path path = new Path();


    public CustomDrawableView(Context context) {
        super(context);
 //       init(null,0);

      //  mBitmapPaint = new Paint(Paint.DITHER_FLAG);

//        paint = new Paint();
//        path = new Path();
//        paint.setAntiAlias(true);
//        paint.setColor(Color.GREEN);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeJoin(Paint.Join.MITER);
//       // paint.setStrokeCap(Paint.Cap.ROUND);
//        paint.setStrokeWidth(4f);
    }

    public CustomDrawableView(Context context, AttributeSet attrs) {
        super(context, attrs);
   //     init(attrs,0);

    }

//    public CustomDrawableView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//     //   init(attrs,defStyleAttr);
//
//    }


//    public void init(AttributeSet attributeSet, int defStyleAttr)
//
//    {
//
//        paint.setColor(Color.BLACK);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(10f);
//    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       // canvas.drawBitmap( mBitmap, 0, 0, mBitmapPaint);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(14f);
        canvas.drawPath(path,paint);
    }


    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    private void touch_start(float x, float y) {
//        path.reset();
        path.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            path.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;
//
//            path.reset();
 //           path.addCircle(mX, mY, 30, Path.Direction.CW);
        }
    }

    private void touch_up() {
        path.lineTo(mX, mY);

        // commit the path to our offscreen
        mCanvas.drawPath(path,  paint);
        // kill this so we don't double draw
  //      path.reset();
    }

    @Override

    public boolean onTouchEvent(MotionEvent event)
    {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                invalidate();
                break;
        }
        return true;
    }

    public void clearCanvas() {

        path.reset();

        invalidate();

    }
}
