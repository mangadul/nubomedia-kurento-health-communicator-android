// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package eu.nubomedia.nubomedia_kurento_health_communicator_android.kc_and_communicator.ui.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class PullToRefreshLayout extends FrameLayout {

	private static final Logger log = LoggerFactory
			.getLogger(InstanceCreationUtils.class.getSimpleName());

	private static final boolean DEBUG = false;

	private PullToRefreshAttacher mPullToRefreshAttacher;
	private View mCurrentTouchTarget;

	public PullToRefreshLayout(Context context) {
		this(context, null);
	}

	public PullToRefreshLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public PullToRefreshLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * Set the {@link PullToRefreshAttacher} to be used with this layout. The
	 * view which is added to this layout will automatically be added as a
	 * refreshable-view in the attacher.
	 */
	public void setPullToRefreshAttacher(PullToRefreshAttacher attacher,
			PullToRefreshAttacher.OnRefreshListener refreshListener) {
		View view;
		for (int i = 0, z = getChildCount(); i < z; i++) {
			view = getChildAt(i);

			if (mPullToRefreshAttacher != null) {
				mPullToRefreshAttacher.removeRefreshableView(view);
			}

			if (attacher != null) {
				if (DEBUG) {
					log.debug("Adding View to Attacher: " + view);
				}
				attacher.addRefreshableView(view, null, refreshListener, false);
			}
		}

		mPullToRefreshAttacher = attacher;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		if (DEBUG) {
			log.debug("onInterceptTouchEvent. " + event.toString());
		}

		if (mPullToRefreshAttacher != null && getChildCount() > 0) {
			View target = getChildForTouchEvent(event);
			if (target != null
					&& mPullToRefreshAttacher.onInterceptTouchEvent(target,
							event)) {
				mCurrentTouchTarget = target;
				return true;
			}
		}
		// Reset Current Touch Target
		mCurrentTouchTarget = null;
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (DEBUG) {
			log.debug("onTouchEvent. " + event.toString());
		}

		if (mPullToRefreshAttacher != null) {
			// This is an edge-case. If the ViewGroup does not contain a valid
			// touch target then
			// Android calls onTouchEvent after onInterceptTouchEvent with
			// ACTION_DOWN event.
			// If that happens then we need to find the visible view and pass it
			// to the attacher as
			// usual.
			if (mCurrentTouchTarget == null
					&& event.getAction() == MotionEvent.ACTION_DOWN) {
				mCurrentTouchTarget = getChildForTouchEvent(event);
			}

			if (mCurrentTouchTarget != null) {
				return mPullToRefreshAttacher.onTouchEvent(mCurrentTouchTarget,
						event);
			}
		}
		// Reset Current Touch Target
		mCurrentTouchTarget = null;
		return super.onTouchEvent(event);
	}

	@Override
	protected void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		if (mPullToRefreshAttacher != null) {
			mPullToRefreshAttacher.onConfigurationChanged(newConfig);
		}
	}

	private View getChildForTouchEvent(MotionEvent event) {
		final float x = event.getX(), y = event.getY();
		View child;
		for (int z = getChildCount() - 1; z >= 0; z--) {
			child = getChildAt(z);
			if (child.isShown() && x >= child.getLeft()
					&& x <= child.getRight() && y >= child.getTop()
					&& y <= child.getBottom()) {
				if (DEBUG) {
					log.debug("Got Child for Touch Event: " + child);
				}
				return child;
			}
		}
		return null;
	}
}
