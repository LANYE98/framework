/*
 * Copyright 2014 the original author or authors.
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
package clientapp1;

import leap.core.annotation.Inject;
import leap.oauth2.wac.OAuth2WebAppConfigurator;
import leap.web.App;
import leap.web.config.WebConfigurator;

/**
 * oauth2 web app (login only).
 */
public class Global extends App {
    
    protected @Inject OAuth2WebAppConfigurator owc;

    @Override
    protected void configure(WebConfigurator c) {
        owc.enable()
           .setClientId("app1")
           .setClientSecret("app1_secret")
           .setServerUrl("https://localhost:8443/server");
    }
    
}