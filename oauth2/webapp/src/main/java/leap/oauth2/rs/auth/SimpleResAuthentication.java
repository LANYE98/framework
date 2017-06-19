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
package leap.oauth2.rs.auth;

import leap.core.security.ClientPrincipal;
import leap.core.security.UserPrincipal;
import leap.oauth2.rs.token.ResAccessToken;
import leap.web.security.authc.AbstractAuthentication;
import leap.core.security.Authentication;

public class SimpleResAuthentication extends AbstractAuthentication implements Authentication, ResAuthentication {
	
    protected final ResAccessToken  credentials;
    protected final UserPrincipal   user;
    protected final ClientPrincipal client;

	public SimpleResAuthentication(ResAccessToken credentials, UserPrincipal user, ClientPrincipal client) {
        this.credentials = credentials;
        this.user   = user;
        this.client = client;
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

    @Override
	public ResAccessToken getCredentials() {
		return credentials;
	}

	@Override
	public UserPrincipal getUser() {
		return user;
	}

	@Override
    public ClientPrincipal getClient() {
	    return client;
    }

}