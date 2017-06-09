/*
 * Copyright 2013 the original author or authors.
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
package leap.orm.sql.ast;

import java.io.IOException;

import leap.lang.params.Params;
import leap.orm.sql.PreparedBatchSqlStatementBuilder;
import leap.orm.sql.SqlContext;
import leap.orm.sql.SqlStatementBuilder;

public class Tag extends AstNode {

    protected final String name;
    protected final String content;

    public Tag(String name, String content) {
        this.name = name;
        this.content = content;
    }

    @Override
    protected void toString_(Appendable buf) throws IOException {
        buf.append("@").append(name).append("{").append(content).append("}");
    }

	@Override
    protected void buildStatement_(SqlStatementBuilder stm, Params params) throws IOException {
		throw new IllegalStateException("Not implemented");	    
    }

	@Override
    protected void prepareBatchStatement_(SqlContext context, PreparedBatchSqlStatementBuilder stm,Object[] params) throws IOException {
		throw new IllegalStateException("Not implemented");	    
    }

}
