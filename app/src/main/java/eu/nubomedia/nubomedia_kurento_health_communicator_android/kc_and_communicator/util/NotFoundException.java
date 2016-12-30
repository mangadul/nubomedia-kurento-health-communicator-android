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

package eu.nubomedia.nubomedia_kurento_health_communicator_android.kc_and_communicator.util;

import com.kurento.agenda.datamodel.pojo.KhcNotFoundInfo;

public class NotFoundException extends Exception {

	private static final long serialVersionUID = 5716499083177110722L;

	private KhcNotFoundInfo info;

	public NotFoundException() {
		super();
	}

	public KhcNotFoundInfo getInfo() {
		return info;
	}

	public void setInfo(KhcNotFoundInfo info) {
		this.info = info;
	}

	public NotFoundException(String detailMessage) {
		super(detailMessage);
	}

	public NotFoundException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public NotFoundException(Throwable throwable) {
		super(throwable);
	}

}