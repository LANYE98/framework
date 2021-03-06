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
package leap.orm.mapping;

import leap.lang.Args;
import leap.lang.Strings;
import leap.orm.enums.CascadeDeleteAction;

import java.util.List;

public class RelationMapping {

    protected final String              name;             //relation's name
    protected final RelationType        type;             //relation's type
    protected final String              inverseRelationName;
    protected final String              targetEntityName; //target entity's name
    protected final String              joinEntityName;     //join entity's name
    protected final boolean             optional;         //is the relation optional ?
    protected final boolean             logical;
    protected final boolean             remote;
    protected final boolean             embedded;
    protected final String              embeddedFileName;
    protected final CascadeDeleteAction onCascadeDelete;
    protected final boolean             autoGenerated;
    protected final JoinFieldMapping[]  joinFields;

	public RelationMapping(String name, RelationType type,
                           String inverseRelationName, String targetEntityName, String joinEntityName,
                           boolean optional, boolean logical, boolean remote,
                           boolean embedded, String embeddedFileName,
                           CascadeDeleteAction onCascadeDelete,
                           boolean autoGenerated, List<JoinFieldMapping> joinFields) {
		Args.notEmpty(name,"name");
		Args.notNull(type,"type");
		Args.notEmpty(targetEntityName,"targetEntityName");
        Args.notNull(onCascadeDelete, "onCascadeDelete");

        if(!embedded) {
            Args.notEmpty(inverseRelationName, "inverseRelationName");

            if(type == RelationType.MANY_TO_MANY && Strings.isEmpty(joinEntityName)){
                throw new IllegalStateException("The joinEntityName must be specified of relation '" + name + "'");
            }
        }else {
            if(Strings.isEmpty(embeddedFileName)) {
                throw new IllegalStateException("The embeddedFileName must be specified of embedded relation '" + name + "'");
            }
        }

		this.name       	  = name;
		this.type       	  = type;
        this.inverseRelationName = inverseRelationName;
		this.targetEntityName = targetEntityName;
		this.joinEntityName   = joinEntityName;
		this.optional    	  = optional;
        this.logical          = logical;
        this.remote           = remote;
        this.embedded         = embedded;
        this.embeddedFileName = embeddedFileName;
        this.onCascadeDelete  = onCascadeDelete;
        this.autoGenerated    = autoGenerated;
		this.joinFields  	  = null == joinFields ? new JoinFieldMapping[]{} : joinFields.toArray(new JoinFieldMapping[joinFields.size()]);
    }

    /**
     * Required. Returns the name of relation.
     */
	public String getName() {
		return name;
	}

    /**
     * Required. Returns the relation type.
     */
	public RelationType getType() {
		return type;
	}

    /**
     * Returns true if the type is {@link RelationType#MANY_TO_ONE}
     */
    public boolean isManyToOne() {
        return RelationType.MANY_TO_ONE.equals(type);
    }

    /**
     * Returns true if the type is {@link RelationType#MANY_TO_MANY}
     */
    public boolean isManyToMany() {
        return RelationType.MANY_TO_MANY.equals(type);
    }

    /**
     * Returns true if the type is {@link RelationType#ONE_TO_MANY}
     */
    public boolean isOneToMany() {
        return RelationType.ONE_TO_MANY.equals(type);
    }

    /**
     * Required. The inverse relation name in the target entity.
     */
    public String getInverseRelationName() {
        return inverseRelationName;
    }

    /**
     * Required. Returns the target entity's name.
     */
	public String getTargetEntityName() {
		return targetEntityName;
	}

    /**
     * Returns true if the relation is optional. Valid on in *-to-one relation.
     */
	public boolean isOptional() {
		return optional;
	}

    public boolean isLogical() {
        return logical;
    }

    public boolean isRemote() {
        return remote;
    }

    public boolean isEmbedded() {
        return embedded;
    }

    public String getEmbeddedFileName() {
        return embeddedFileName;
    }

    public CascadeDeleteAction getOnCascadeDelete() {
        return onCascadeDelete;
    }

    /**
     * Returns true if the relation is auto generated.
     */
    public boolean isAutoGenerated() {
        return autoGenerated;
    }

    /**
	 * Valid for *-to-one relation only.
	 */
	public JoinFieldMapping[] getJoinFields() {
		return joinFields;
	}

    /**
     * Valid for many-to-many relation only.
     */
    public String getJoinEntityName() {
        return joinEntityName;
    }

    public boolean isSetNullOnCascadeDelete() {
        return null != onCascadeDelete && onCascadeDelete == CascadeDeleteAction.SET_NULL;
    }

    @Override
    public String toString() {
        return name + ":" + type;
    }
}