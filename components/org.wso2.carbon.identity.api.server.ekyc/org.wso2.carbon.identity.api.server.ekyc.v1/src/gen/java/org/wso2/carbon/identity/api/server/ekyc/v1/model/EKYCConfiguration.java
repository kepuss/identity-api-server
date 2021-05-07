package org.wso2.carbon.identity.api.server.ekyc.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.List;

@ApiModel(description = "EKYC Configuration")
public class EKYCConfiguration {
    private String url;
    private String apiKey;
    private String apiSecret;
    private String callbackUrl;
    private List<String> services;
    private Map<String,String> claimsMapping;

    public EKYCConfiguration() {
    }

    public EKYCConfiguration(String url, String apiKey, String apiSecret, String callbackUrl, List<String> services, Map<String,String> claimsMapping) {
        this.url = url;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.callbackUrl = callbackUrl;
        this.services = services;
        this.claimsMapping = claimsMapping;
    }

    @ApiModelProperty(example = "EKYCHub", required = true, value = "")
    @JsonProperty("url")
    @Valid
    @NotNull(message = "Property url cannot be null.")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @ApiModelProperty(example = "EKYCHub", required = true, value = "")
    @JsonProperty("apiKey")
    @Valid
    @NotNull(message = "Property apiKey cannot be null.")
    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @ApiModelProperty(example = "EKYCHub", required = true, value = "")
    @JsonProperty("apiSecret")
    @Valid
    @NotNull(message = "Property apiSecret cannot be null.")
    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    @ApiModelProperty(example = "EKYCHub", required = true, value = "")
    @JsonProperty("services")
    @Valid
    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }

    @ApiModelProperty(example = "EKYCHub", required = true, value = "")
    @JsonProperty("callbackUrl")
    @Valid
    @NotNull(message = "Property callbackUrl cannot be null.")
    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    @ApiModelProperty(example = "EKYCHub", required = true, value = "")
    @JsonProperty("claimsMapping")
    @Valid
    public Map<String, String> getClaimsMapping() {
        return claimsMapping;
    }

    public void setClaimsMapping(Map<String, String> claimsMapping) {
        this.claimsMapping = claimsMapping;
    }
}
