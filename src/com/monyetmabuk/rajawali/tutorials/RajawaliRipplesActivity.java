package com.monyetmabuk.rajawali.tutorials;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RajawaliRipplesActivity extends RajawaliExampleActivity implements OnTouchListener {
	private RajawaliRipplesRenderer mRenderer;
	private Point mScreenSize;
	private float oldX, oldY;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mRenderer = new RajawaliRipplesRenderer(this);
		mRenderer.setSurfaceView(mSurfaceView);
		super.setRenderer(mRenderer);
		
		mSurfaceView.setOnTouchListener(this);
		
		Display display = getWindowManager().getDefaultDisplay();
		mScreenSize = new Point();
		mScreenSize.x = display.getWidth();
		mScreenSize.y = display.getHeight();
		
		LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setGravity(Gravity.BOTTOM);
		
        TextView tv = new TextView(this);
        tv.setText("Touch Me.");
        tv.setTextColor(0xffffffff);
        ll.addView(tv);
//        mLayout.addView(ll);
        
		initLoader();
	}

	public boolean onTouch(View v, MotionEvent event) {
		final float threshold = 180;
		boolean makeRipple = false;
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			makeRipple = true;
		} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			if (Math.abs(event.getX() - oldX) > threshold
					|| Math.abs(event.getY() - oldY) > threshold) {
				makeRipple = true;
			}
		}
		if (makeRipple) {
			mRenderer.addRipple(event.getX() / mScreenSize.x, 1.0f - (event.getY() / mScreenSize.y));
			oldX = event.getX();
			oldY = event.getY();
			return true;
		}
		return super.onTouchEvent(event);
	}
}
