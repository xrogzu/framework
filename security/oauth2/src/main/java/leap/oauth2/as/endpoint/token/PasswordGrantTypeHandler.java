/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package leap.oauth2.as.endpoint.token;

import java.util.function.Consumer;

import leap.core.annotation.Inject;
import leap.lang.Strings;
import leap.oauth2.OAuth2Params;
import leap.oauth2.OAuth2Errors;
import leap.oauth2.as.authc.AuthzAuthentication;
import leap.oauth2.as.OAuth2AuthzServerConfig;
import leap.oauth2.as.authc.SimpleAuthzAuthentication;
import leap.oauth2.as.client.AuthzClient;
import leap.oauth2.as.client.AuthzClientManager;
import leap.oauth2.as.client.AuthzClientValidator;
import leap.oauth2.as.token.AuthzAccessToken;
import leap.oauth2.as.token.AuthzTokenManager;
import leap.web.Request;
import leap.web.Response;
import leap.core.security.Authentication;
import leap.web.security.authc.AuthenticationManager;
import leap.web.security.authc.credentials.DefaultAuthenticateCredentialsContext;
import leap.web.security.user.SimpleUsernamePasswordCredentials;
import leap.web.security.user.UserManager;
import leap.web.security.user.UsernamePasswordCredentials;

import static leap.oauth2.Oauth2MessageKey.*;

/**
 * grant_type=password
 */
public class PasswordGrantTypeHandler extends AbstractGrantTypeHandler implements GrantTypeHandler {
	
    protected @Inject OAuth2AuthzServerConfig config;
    protected @Inject AuthzTokenManager       tokenManager;
    protected @Inject AuthzClientManager      clientManager;
    protected @Inject AuthenticationManager   authenticationManager;
    protected @Inject AuthzClientValidator    clientValidator;
    protected @Inject UserManager             userManager;
	

	@Override
    public void handleRequest(Request request, Response response, OAuth2Params params, Consumer<AuthzAccessToken> callback) throws Throwable{
		if(!config.isPasswordCredentialsEnabled()) {
			handleError(request,response,params,
					getOauth2Error(key -> OAuth2Errors.unsupportedGrantTypeError(request,key,null),ERROR_UNSUPPORTED_GRANT_TYPE_TYPE,"password"));
			return;
		}
		
		String username = params.getUsername();
		String password = params.getPassword();
		if(Strings.isEmpty(username)) {
			handleError(request,response,params,
					getOauth2Error(key -> OAuth2Errors.invalidRequestError(request,key,"username are requried."),INVALID_REQUEST_USERNAME_REQUIRED));
			return;
		}
		if(Strings.isEmpty(password)){
			handleError(request,response,params,
					getOauth2Error(key -> OAuth2Errors.invalidRequestError(request,key,"password are requried."),INVALID_REQUEST_PASSWORD_REQUIRED));
			return;
		}
		
		
		DefaultAuthenticateCredentialsContext context = new DefaultAuthenticateCredentialsContext(request.getValidation());

		SimpleUsernamePasswordCredentials credentials = new SimpleUsernamePasswordCredentials(username,password);
		
		//Authenticate user.
		Authentication authc = authenticationManager.authenticate(context, credentials);
		if(null == authc) {
			handleError(request,response,params,
					getOauth2Error(key -> OAuth2Errors.invalidGrantError(request,key,"invalid username or password"),INVALID_REQUEST_INVALID_USERNAME,credentials.getUsername()));
			return;
		}

		//Validates the client.
		String clientId = params.getClientId();
		if(Strings.isEmpty(clientId)){
			handleError(request,response,params,
					getOauth2Error(key -> OAuth2Errors.invalidRequestError(request,key,"client_id is required"),INVALID_REQUEST_CLIENT_ID_REQUIRED));
			return;
		}
		AuthzClient client = clientManager.loadClientById(clientId);
		if(client == null){
			handleError(request,response,params,
					getOauth2Error(key -> OAuth2Errors.invalidGrantError(request,key,"invalid client_id"),INVALID_REQUEST_INVALID_CLIENT,clientId));
			return;
		}
		
		AuthzAuthentication oauthAuthc = new SimpleAuthzAuthentication(params, client, userManager.getUserDetails(authc.getUser()));
		
		//Generate token.
		callback.accept(tokenManager.createAccessToken(oauthAuthc));
    }
}