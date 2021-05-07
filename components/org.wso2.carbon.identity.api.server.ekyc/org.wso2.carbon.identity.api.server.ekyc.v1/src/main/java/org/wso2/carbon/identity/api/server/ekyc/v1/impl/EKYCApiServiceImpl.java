/*
 * Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.api.server.ekyc.v1.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.wso2.carbon.identity.api.server.ekyc.v1.EKYCApiService;
import org.wso2.carbon.identity.api.server.ekyc.v1.core.EKYCConfigurationService;
import org.wso2.carbon.identity.api.server.ekyc.v1.model.EKYCConfiguration;

import javax.ws.rs.core.Response;

/**
 * Implementation of ekyc configuration REST API.
 */
public class EKYCApiServiceImpl extends EKYCApiService {

    @Autowired
    private EKYCConfigurationService ekycManagementService;

    @Override
    public Response createEKYCConfiguration(EKYCConfiguration ekycConfiguration) {
        EKYCConfiguration ekycConfigurationResult = ekycManagementService.addEKYCConfiguration(ekycConfiguration);
        return Response.ok(ekycConfigurationResult).build();
    }

    @Override
    public Response getEKYCConfiguration(EKYCConfiguration ekycConfiguration) {
        return Response.ok(ekycManagementService.getEKYCConfiguration()).build();
    }

    @Override
    public Response updateEKYCConfiguration(EKYCConfiguration ekycConfiguration) {
        return Response.ok(ekycManagementService.updateEKYCConfiguration(ekycConfiguration)).build();
    }

    @Override
    public Response deleteEKYCConfiguration() {
        ekycManagementService.deleteEKYCConfiguration();
        return Response.ok().build();
    }
}
