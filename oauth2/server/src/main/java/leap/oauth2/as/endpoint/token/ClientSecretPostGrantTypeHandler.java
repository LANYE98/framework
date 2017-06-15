/*
 *
 *  * Copyright 2013 the original author or authors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *  
 */

package leap.oauth2.as.endpoint.token;

import leap.core.annotation.Inject;
import leap.lang.http.HTTP;
import leap.oauth2.OAuth2Errors;
import leap.oauth2.OAuth2Params;
import leap.oauth2.as.authc.SimpleAuthzAuthentication;
import leap.oauth2.as.client.AuthzClient;
import leap.oauth2.as.client.AuthzClientCredentials;
import leap.oauth2.as.client.SamplingAuthzClientCredentials;
import leap.oauth2.as.token.AuthzAccessToken;
import leap.oauth2.as.token.AuthzTokenManager;
import leap.web.Request;
import leap.web.Response;

import java.util.function.Consumer;

import static leap.oauth2.Oauth2MessageKey.INVALID_REQUEST_INVALID_HTTP_METHOD;

/**
 * grant_type=client_secret_post
 */
public class ClientSecretPostGrantTypeHandler extends AbstractGrantTypeHandler {
    protected @Inject AuthzTokenManager tokenManager;
    
    @Override
    public void handleRequest(Request request, Response response, OAuth2Params params,
                              Consumer<AuthzAccessToken> callback) throws Throwable {
        if(!HTTP.Method.valueOf(request.getMethod()).isPost()){
            handleError(request,response,params,
                    getOauth2Error(key -> OAuth2Errors.invalidRequestError(request,key,"this grant_type only accept post method."),INVALID_REQUEST_INVALID_HTTP_METHOD,"POST"));
            return;
        }
        AuthzClientCredentials credentials = new SamplingAuthzClientCredentials(params.getClientId(),params.getClientSecret());

        AuthzClient client = validateClientSecret(request, response,credentials);
        
        if(client == null){
            return;
        }

        callback.accept(tokenManager.createAccessToken(new SimpleAuthzAuthentication(params, client)));
    }
}
