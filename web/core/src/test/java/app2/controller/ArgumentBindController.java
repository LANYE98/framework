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

package app2.controller;

import leap.lang.json.JsonParsable;
import leap.lang.json.JsonValue;
import leap.web.annotation.DefaultValue;
import leap.web.annotation.Path;
import leap.web.annotation.RequestParam;
import leap.web.annotation.ResolveByJson;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by kael on 2017/2/19.
 */
public class ArgumentBindController {
    
    public boolean testDate(Date date, Timestamp timestamp){
        if(date == null || timestamp == null){
            return false;
        }
        return true;
    }
    
    public String testJsonArgumentResolver(@ResolveByJson List<Map<String, Object>> listMap, 
                                           @ResolveByJson Map<String, Map<String, Object>> map,
                                           @ResolveByJson JsonParseAbleImpl impl){
        if(listMap == null || listMap.isEmpty() || listMap.get(0).get("name") == null){
            return "";
        }
        if(map == null || map.isEmpty() || map.get("impl") == null || map.get("impl").get("name") == null){
            return "";
        }
        if(impl == null || impl.getExt() == null){
            return "";
        }
        return listMap.get(0).get("name").toString();
    }
    
    public boolean testJsonParseAble(JsonParseAbleImpl json){
        if(json.ext != null && json.ext.equals("ext")){
            return true;
        }
        return false;
    }
    
    public boolean testDefaultValue(@DefaultValue("string") String string,
                                    @DefaultValue("string1") String string1,
                                    @DefaultValue("1") int integer, 
                                    @DefaultValue("false") boolean bool){
        if("string".equals(string) && 1 == integer && false == bool && "str".equals(string1)){
            return true;
        }
        return false;
    }
    
    public static class JsonParseAbleImpl implements JsonParsable{
        
        private String name;
        private String ext;
        
        @Override
        public void parseJson(JsonValue json) {
            this.name = json.asJsonObject().get("name");
            this.ext = "ext";
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getExt() {
            return ext;
        }

        public void setExt(String ext) {
            this.ext = ext;
        }
    }
    
}
