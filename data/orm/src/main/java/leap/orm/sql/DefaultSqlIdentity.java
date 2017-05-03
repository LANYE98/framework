/*
 * Copyright 2016 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *       http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package leap.orm.sql;

import java.util.Objects;

public class DefaultSqlIdentity implements SqlIdentity{
    protected String key;
    protected String datasource;

    public DefaultSqlIdentity(String key, String datasource) {
        this.key = key;
        this.datasource = datasource;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String identity(){
        StringBuilder identity = new StringBuilder("[");
        if(key != null && key.length()>0){
            identity.append("key=");
            identity.append(key);
        }
        if(datasource != null && datasource.length()>0){
            identity.append(", ");
            identity.append("datasource=");
            identity.append(datasource);
        }
        identity.append("]");
        return identity.toString();
    }

    @Override
    public String getAttribute(String attr) {
        try {
            Object value = this.getClass().getDeclaredField(attr).get(this);
            return value == null? null: Objects.toString(value);
        } catch (IllegalAccessException e) {
            return null;
        } catch (NoSuchFieldException e) {
            return null;
        }
    }
}
