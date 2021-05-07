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

/**
 * EKYC related constant class.
 */
public class EKYCConstants {

    public static final String EKYC_RESOURCE_TYPE = "EKYC_CONFIGURATION";
    public static final String RESOURCE_NAME = "ekyc";

    public static final String EKYC_ERROR_PREFIX = "EKYC-";
    public static final String CONFIG_MGT_ERROR_CODE_DELIMITER = "_";

    /**
     * Enums for error messages.
     */
    public enum ErrorMessage {

        ERROR_CODE_ERROR_ADDING_EKYC_CONFIGURATION("88001", "Unable to add ekyc configuration",
                "Server encountered an error while adding the ekyc configuration resource: %s"),

        ERROR_CODE_ERROR_GETTING_EKYC_CONFIGURATION("88002", "Unable to get ekyc configuration",
                "Server encountered an error while getting the ekyc configuration resource: %s"),

        ERROR_CODE_ERROR_UPDATING_EKYC_CONFIGURATION("88003", "Unable to update ekyc configuration",
                "Server encountered an error while updating the ekyc configuration resource: %s"),

        ERROR_CODE_ERROR_DELETING_EKYC_CONFIGURATION("88004", "Unable to delete ekyc configuration",
                "Server encountered an error while deleting the ekyc configuration resource: %s");

        private final String code;
        private final String message;
        private final String description;

        ErrorMessage(String code, String message, String description) {

            this.code = code;
            this.message = message;
            this.description = description;
        }

        public String getCode() {

            return EKYC_ERROR_PREFIX + code;
        }

        public String getMessage() {

            return message;
        }

        public String getDescription() {

            return description;
        }

        @Override
        public String toString() {

            return code + " | " + message;
        }
    }
}
