/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package leap.oauth2.webapp.token;

import java.util.HashMap;
import java.util.Map;

import leap.core.BeanFactory;
import leap.core.annotation.Inject;
import leap.core.ioc.PostCreateBean;
import leap.lang.Result;
import leap.oauth2.webapp.OAuth2Config;

/**
 * The default implementation of {@link ResTokenManager}.
 */
public class DefaultResTokenManager implements ResTokenManager, PostCreateBean {
    
    protected @Inject BeanFactory  factory;
    protected @Inject OAuth2Config config;
    
    protected Map<String, ResAccessTokenStore> typedTokenStores = new HashMap<>();
    protected ResAccessTokenStore              bearerTokenStore = null;
    protected ResAccessTokenStore              jwtTokenStore    = null;
    
    @Override
    public Result<ResAccessTokenDetails> loadAccessTokenDetails(AccessToken token) {
        return getAccessTokenStore(token).loadAccessTokenDetails(token);
    }

    @Override
    public void removeAccessToken(AccessToken token) {
        getAccessTokenStore(token).removeAccessToken(token);
    }

    @Override
    public void postCreate(BeanFactory factory) throws Throwable {
        this.typedTokenStores.putAll(factory.getNamedBeans(ResAccessTokenStore.class));
    }
    
    protected ResAccessTokenStore getAccessTokenStore(AccessToken token) {
        ResAccessTokenStore store;
        
        if(token.isBearer()) {
            if(token instanceof SimpleJwtAccessToken){
                if(null == jwtTokenStore){
                    store = factory.getBean(ResBearerAccessTokenStore.class,"jwt");
                    if(null != store){
                        this.jwtTokenStore = store;
                        return store;
                    }
                }else{
                    store = this.jwtTokenStore;
                    return store;
                }
            }
            if(null == bearerTokenStore) {
                this.bearerTokenStore = factory.tryGetBean(ResBearerAccessTokenStore.class);
                if(null == bearerTokenStore) {
                    this.bearerTokenStore = factory.getBean(ResBearerAccessTokenStore.class, "remote");
                }
            }
            store = bearerTokenStore;
        }else{
            store = typedTokenStores.get(token.getType());
            if(null == store) {
                throw new IllegalStateException("No ResAccessTokenStore for token type '" + token.getType() + "'");
            }
        }
        return store;
    }
    
}