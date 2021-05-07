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

package org.wso2.carbon.identity.api.server.ekyc.common;

import org.wso2.carbon.identity.configuration.mgt.core.ConfigurationManager;

/**
 * Service holder class for ekyc configurations.
 */
public class EKYCServiceHolder {

    private static ConfigurationManager ekycConfigManager;

    /**
     * Get ConfigurationManager OSGi service.
     *
     * @return NotificationSenderConfig Manager.
     */
    public static ConfigurationManager getEkycConfigManager() {

        return ekycConfigManager;
    }

    /**
     * Set ConfigurationManager OSGi service.
     *
     * @param ekycConfigManager Configuration Manager.
     */
    public static void setEkycConfigManager(ConfigurationManager ekycConfigManager) {

        EKYCServiceHolder.ekycConfigManager = ekycConfigManager;
    }
}
