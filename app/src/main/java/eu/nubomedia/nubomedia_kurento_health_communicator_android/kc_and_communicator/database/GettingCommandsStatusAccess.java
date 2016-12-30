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

package eu.nubomedia.nubomedia_kurento_health_communicator_android.kc_and_communicator.database;

import android.content.Context;

import eu.nubomedia.nubomedia_kurento_health_communicator_android.kc_and_communicator.ui.activity.Preferences;
import eu.nubomedia.nubomedia_kurento_health_communicator_android.kc_and_communicator.util.ConstantKeys;


/* TODO(mparis): review */
public class GettingCommandsStatusAccess {

	private static String accessStatus = ConstantKeys.STRING_DEFAULT;
	private static String createInstance = ConstantKeys.STRING_DEFAULT;
	private static GettingCommandsStatusAccess classAccess = null;

	public static GettingCommandsStatusAccess getInstance(Context ctx) {
		if (classAccess == null) {
			synchronized (createInstance) {
				if (classAccess == null) {
					classAccess = new GettingCommandsStatusAccess();
				}
			}
		}

		return classAccess;
	}

	public boolean getCommandsStatus(Context ctx) {
		boolean isFail = true;
		synchronized (accessStatus) {
			isFail = Preferences.isGettingsCommandFail(ctx);
		}

		return isFail;
	}

	public void setCommandsStatus(Context ctx, boolean status) {
		synchronized (accessStatus) {
			Preferences.setGettingsCommandStatus(ctx, status);
		}
	}

}
