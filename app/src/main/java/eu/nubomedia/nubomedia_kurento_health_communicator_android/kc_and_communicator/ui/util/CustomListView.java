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

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class CustomListView extends ListView {

	public CustomListView(Context context) {
		super(context);
	}

	public CustomListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld) {
		super.onSizeChanged(xNew, yNew, xOld, yOld);

		if (getLastVisiblePosition() == getCount() - 1) {
			setSelection(getCount());
		}
	}
}