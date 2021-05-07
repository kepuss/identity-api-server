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

package org.wso2.carbon.identity.api.server.ekyc.v1.core;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.api.server.common.error.APIError;
import org.wso2.carbon.identity.api.server.common.error.ErrorResponse;
import org.wso2.carbon.identity.api.server.ekyc.common.EKYCConstants;
import org.wso2.carbon.identity.api.server.ekyc.common.EKYCServiceHolder;
import org.wso2.carbon.identity.api.server.ekyc.v1.model.EKYCConfiguration;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementClientException;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementException;
import org.wso2.carbon.identity.configuration.mgt.core.exception.ConfigurationManagementServerException;
import org.wso2.carbon.identity.configuration.mgt.core.model.Resource;
import org.wso2.carbon.identity.user.ekyc.dto.EKYCConfigurationDTO;

import javax.ws.rs.core.Response;

import static org.wso2.carbon.identity.api.server.ekyc.common.EKYCConstants.CONFIG_MGT_ERROR_CODE_DELIMITER;
import static org.wso2.carbon.identity.api.server.ekyc.common.EKYCConstants.EKYC_ERROR_PREFIX;
import static org.wso2.carbon.identity.api.server.ekyc.common.EKYCConstants.ErrorMessage.ERROR_CODE_ERROR_ADDING_EKYC_CONFIGURATION;
import static org.wso2.carbon.identity.api.server.ekyc.common.EKYCConstants.ErrorMessage.ERROR_CODE_ERROR_DELETING_EKYC_CONFIGURATION;
import static org.wso2.carbon.identity.api.server.ekyc.common.EKYCConstants.ErrorMessage.ERROR_CODE_ERROR_GETTING_EKYC_CONFIGURATION;
import static org.wso2.carbon.identity.api.server.ekyc.common.EKYCConstants.ErrorMessage.ERROR_CODE_ERROR_UPDATING_EKYC_CONFIGURATION;
import static org.wso2.carbon.identity.user.ekyc.util.EKYCConfigurationMapper.buildEKYCConfigurationFromResource;
import static org.wso2.carbon.identity.user.ekyc.util.EKYCConfigurationMapper.buildResourceEKYCConfigurationDTO;
import static org.wso2.carbon.identity.user.ekyc.util.IDVConstants.EKYC_RESOURCE_TYPE;
import static org.wso2.carbon.identity.user.ekyc.util.IDVConstants.RESOURCE_NAME;

/**
 * Invoke internal OSGi service to perform notification sender management operations.
 */
public class EKYCConfigurationService {

    private static final Log log = LogFactory.getLog(EKYCConfigurationService.class);

    /**
     * Create an ekyc configuration resource.
     *
     * @param ekycConfiguration EKYCConfiguration.
     * @return EKYCConfiguration.
     */
    public EKYCConfiguration addEKYCConfiguration(EKYCConfiguration ekycConfiguration) {

        Resource ekycConfigurationResource = getEKYCConfigurationFromResource(ekycConfiguration);
        try {
            EKYCServiceHolder.getEkycConfigManager()
                    .addResource(EKYC_RESOURCE_TYPE, ekycConfigurationResource);
        } catch (ConfigurationManagementException e) {
            throw handleConfigurationMgtException(e, ERROR_CODE_ERROR_ADDING_EKYC_CONFIGURATION,
                    ekycConfiguration.getUrl());
        }
        return getResourceFromEKYCConfiguration(ekycConfigurationResource);
    }

    /**
     * Update an ekyc configuration resource.
     *
     * @param ekycConfiguration EKYCConfiguration.
     * @return EKYCConfiguration.
     */
    public EKYCConfiguration updateEKYCConfiguration(EKYCConfiguration ekycConfiguration) {

        Resource ekycConfigurationResource = getEKYCConfigurationFromResource(ekycConfiguration);
        try {
            EKYCServiceHolder.getEkycConfigManager()
                    .replaceResource(EKYC_RESOURCE_TYPE, ekycConfigurationResource);
        } catch (ConfigurationManagementException e) {
            throw handleConfigurationMgtException(e, ERROR_CODE_ERROR_UPDATING_EKYC_CONFIGURATION,
                    ekycConfiguration.getUrl());
        }
        return getResourceFromEKYCConfiguration(ekycConfigurationResource);
    }

    /**
     * Delete an ekyc configuration resource.
     */
    public void deleteEKYCConfiguration() {
        try {
            EKYCServiceHolder.getEkycConfigManager()
                    .deleteResource(EKYC_RESOURCE_TYPE, RESOURCE_NAME);
        } catch (ConfigurationManagementException e) {
            throw handleConfigurationMgtException(e, ERROR_CODE_ERROR_DELETING_EKYC_CONFIGURATION,
                    "");
        }
    }

    /**
     * Retrieve the ekyc configuration.
     *
     * @return EKYCConfiguration.
     */
    public EKYCConfiguration getEKYCConfiguration() {

        try {
            Resource resource = EKYCServiceHolder.getEkycConfigManager()
                    .getResource(EKYC_RESOURCE_TYPE, RESOURCE_NAME);
            return buildEKYCConfigurationFromEKYCConfigurationDTO(buildEKYCConfigurationFromResource(resource));
        } catch (ConfigurationManagementException e) {
            throw handleConfigurationMgtException(e, ERROR_CODE_ERROR_GETTING_EKYC_CONFIGURATION, RESOURCE_NAME);
        }
    }

    private Resource getEKYCConfigurationFromResource(EKYCConfiguration ekycConfiguration) {
        return buildResourceEKYCConfigurationDTO(buildEKYCConfigurationDTOFromEKYCConfiguration(ekycConfiguration));
    }

    private EKYCConfiguration getResourceFromEKYCConfiguration(Resource resource) {
        return buildEKYCConfigurationFromEKYCConfigurationDTO(buildEKYCConfigurationFromResource(resource));
    }


    private EKYCConfigurationDTO buildEKYCConfigurationDTOFromEKYCConfiguration(EKYCConfiguration ekycConfiguration) {
        return new EKYCConfigurationDTO(
                ekycConfiguration.getUrl(),
                ekycConfiguration.getApiKey(),
                ekycConfiguration.getApiSecret(),
                ekycConfiguration.getCallbackUrl(),
                ekycConfiguration.getServices(),
                ekycConfiguration.getClaimsMapping()
        );
    }

    private EKYCConfiguration buildEKYCConfigurationFromEKYCConfigurationDTO
            (EKYCConfigurationDTO ekycConfigurationDTO) {
        return new EKYCConfiguration(
                ekycConfigurationDTO.getUrl(),
                ekycConfigurationDTO.getApiKey(),
                ekycConfigurationDTO.getApiSecret(),
                ekycConfigurationDTO.getCallbackUrl(),
                ekycConfigurationDTO.getServices(),
                ekycConfigurationDTO.getClaimsMapping()
        );
    }

    private APIError handleConfigurationMgtException(ConfigurationManagementException e,
                                                     EKYCConstants.ErrorMessage errorEnum,
                                                     String data) {

        ErrorResponse errorResponse = getErrorBuilder(errorEnum, data).build(log, e, errorEnum.getDescription());
        Response.Status status;
        if (e instanceof ConfigurationManagementClientException) {
            if (e.getErrorCode() != null) {
                String errorCode = e.getErrorCode();
                errorCode = errorCode.contains(CONFIG_MGT_ERROR_CODE_DELIMITER) ? errorCode :
                        EKYC_ERROR_PREFIX + errorCode;
                errorResponse.setCode(errorCode);
            }
            errorResponse.setDescription(e.getMessage());
            status = Response.Status.BAD_REQUEST;
        } else if (e instanceof ConfigurationManagementServerException) {
            if (e.getErrorCode() != null) {
                String errorCode = e.getErrorCode();
                errorCode = errorCode.contains(CONFIG_MGT_ERROR_CODE_DELIMITER) ? errorCode :
                        EKYC_ERROR_PREFIX + errorCode;
                errorResponse.setCode(errorCode);
            }
            errorResponse.setDescription(e.getMessage());
            status = Response.Status.INTERNAL_SERVER_ERROR;
        } else {
            status = Response.Status.INTERNAL_SERVER_ERROR;
        }
        return new APIError(status, errorResponse);
    }

    /**
     * Return error builder.
     *
     * @param errorMsg Error Message information.
     * @return ErrorResponse.Builder.
     */
    private ErrorResponse.Builder getErrorBuilder(EKYCConstants.ErrorMessage errorMsg,
                                                  String data) {

        return new ErrorResponse.Builder().withCode(errorMsg.getCode()).withMessage(errorMsg.getMessage())
                .withDescription(includeData(errorMsg, data));
    }

    /**
     * Include context data to error message.
     *
     * @param error Error message.
     * @param data  Context data.
     * @return Formatted error message.
     */
    private static String includeData(EKYCConstants.ErrorMessage error, String data) {

        String message;
        if (StringUtils.isNotBlank(data)) {
            message = String.format(error.getDescription(), data);
        } else {
            message = error.getDescription();
        }
        return message;
    }
}
