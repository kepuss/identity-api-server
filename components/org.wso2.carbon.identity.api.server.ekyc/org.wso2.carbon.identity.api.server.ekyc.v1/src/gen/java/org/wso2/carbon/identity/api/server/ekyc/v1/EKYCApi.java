/*
* Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package org.wso2.carbon.identity.api.server.ekyc.v1;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.wso2.carbon.identity.api.server.ekyc.v1.model.EKYCConfiguration;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/ekyc")
@Api(description = "The ekyc API")

public class EKYCApi {

    @Autowired
    private EKYCApiService delegate;

    @Valid
    @POST
    @Path("/")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Create an ekyc configuration", notes = "This API provides the capability to create an ekyc connection.<br>   <b>Permission required:</b> <br>     * /permission/admin/manage/identity/configmgt/add <br>   <b>Scope required:</b> <br>     * internal_config_mgt_add ", response = EKYCConfiguration.class, authorizations = {
        @Authorization(value = "BasicAuth"),
        @Authorization(value = "OAuth2", scopes = {
            
        })
    }, tags={ "EKYC", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful Response", response = EKYCConfiguration.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Void.class),
        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
        @ApiResponse(code = 405, message = "Method Not Allowed.", response = Error.class),
        @ApiResponse(code = 409, message = "Conflict", response = Error.class),
        @ApiResponse(code = 500, message = "Server Error", response = Error.class)
    })
    public Response createEKYCConfiguration(@ApiParam(value = "" ) @Valid EKYCConfiguration ekycConfiguration) {

        return delegate.createEKYCConfiguration(ekycConfiguration );
    }

    @Valid
    @GET
    @Path("/")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Get ekyc configuration", notes = "This API provides the capability to get an ekyc configuration. <br>   <b>Permission required:</b> <br>     * /permission/admin/manage/identity/configmgt/update <br>   <b>Scope required:</b> <br>     * internal_config_mgt_update ", response = EKYCConfiguration.class, authorizations = {
            @Authorization(value = "BasicAuth"),
            @Authorization(value = "OAuth2", scopes = {

            })
    }, tags={ "EKYC", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Response", response = EKYCConfiguration.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Void.class),
            @ApiResponse(code = 404, message = "Not Found", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not Allowed.", response = Error.class),
            @ApiResponse(code = 500, message = "Server Error", response = Error.class)
    })
    public Response getEKYCConfiguration( @ApiParam(value = "" ,required=true) @Valid EKYCConfiguration ekycConfiguration) {

        return delegate.getEKYCConfiguration(ekycConfiguration);
    }

    @Valid
    @PUT
    @Path("/")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Update ekyc configuration", notes = "This API provides the capability to update an ekyc configuration.", response = EKYCConfiguration.class, authorizations = {
            @Authorization(value = "BasicAuth"),
            @Authorization(value = "OAuth2", scopes = {

            })
    }, tags={ "EKYC", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Response", response = EKYCConfiguration.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Void.class),
            @ApiResponse(code = 404, message = "Not Found", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not Allowed.", response = Error.class),
            @ApiResponse(code = 500, message = "Server Error", response = Error.class)
    })
    public Response updateEKYCConfiguration( @ApiParam(value = "" ,required=true) @Valid EKYCConfiguration ekycConfiguration) {
        return delegate.updateEKYCConfiguration(ekycConfiguration);
    }

    @Valid
    @DELETE
    @Path("/")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Delete ekyc configuration", notes = "This API provides the capability to delete an ekyc configuration.", response = Void.class, authorizations = {
            @Authorization(value = "BasicAuth"),
            @Authorization(value = "OAuth2", scopes = {

            })
    }, tags={ "EKYC", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Response", response = Void.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Void.class),
            @ApiResponse(code = 404, message = "Not Found", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not Allowed.", response = Error.class),
            @ApiResponse(code = 500, message = "Server Error", response = Error.class)
    })
    public Response deleteEKYCConfiguration() {
        return delegate.deleteEKYCConfiguration();
    }

}
