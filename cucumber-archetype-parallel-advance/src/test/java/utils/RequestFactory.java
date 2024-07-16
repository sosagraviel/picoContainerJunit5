package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import utils.constants.DataConstant;

import java.util.Objects;

import static io.restassured.config.EncoderConfig.encoderConfig;

@Getter
public class RequestFactory {
    private String token;
    static final String headerTargetAmz = "x-amz-target";
    static final String contentTypeSso = "application/x-amz-json-1.1";
    static final String headerApiVersion = "x-api-version";

    /**
     * make Request
     *
     * @return A RequestSpecification with authenticated true
     */
    public RequestSpecification makeRequest() {
        return makeRequest(true);
    }

    /**
     * make Request
     *
     * @param apiVersion is the specific number of api versions
     * @return A RequestSpecification with authenticated true and the specific number of api versions
     */
    public RequestSpecification makeRequest(Float apiVersion) {
        return makeRequest(true, apiVersion, "");
    }

    /**
     * make Request
     *
     * @param authenticated is if the user needs to be logged in the system
     * @return A RequestSpecification with authenticated value and the specific number of api versions
     */
    public RequestSpecification makeRequest(Boolean authenticated) {
        return makeRequest(authenticated, 1.0f, "");
    }

    /**
     * make Request
     *
     * @param mfaCode is if the user needs to be logged in the system
     * @return A RequestSpecification with authenticated value and the specific number of api versions
     */
    public RequestSpecification makeRequest(String mfaCode) {
        return makeRequest(false, 1.0f, mfaCode);
    }

    /**
     * make Request
     *
     * @param authenticated is false when to need be logged Everything else will be true
     * @param apiVersion    is the api version
     * @return A RequestSpecification with authenticated value and the specific number of api versions
     */
    public RequestSpecification makeRequest(Boolean authenticated, Float apiVersion, String mfaValidationCode) {
        RequestSpecification request = RestAssured.given();
        request.baseUri( DataConstant.BASE_URL);
        String loginAuth = "AWSCognitoIdentityProviderService.InitiateAuth";
        String loginVerificationAuth = "AWSCognitoIdentityProviderService.RespondToAuthChallenge";
        String xAmzTarget = Objects.equals(mfaValidationCode, "") ? loginAuth : loginVerificationAuth;

        request = request.config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs(contentTypeSso, ContentType.TEXT)))
                .contentType(contentTypeSso)
                .header(headerTargetAmz, xAmzTarget);
        if (Boolean.TRUE.equals(authenticated)) {
            request = request.contentType(ContentType.JSON)
                    .accept("*/*")
                    .auth().oauth2(getToken());
        }
        if (apiVersion > 1) {

            request = request.header(headerApiVersion, apiVersion);
        }
        return request;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

