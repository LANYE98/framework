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

package leap.orm.event;

import leap.orm.OrmContext;
import leap.orm.mapping.EntityMapping;
import leap.orm.mapping.Mappings;
import leap.orm.value.EntityWrapper;

public class EntityEventWithWrapperImpl extends EntityEventBase implements CreateEntityEvent,UpdateEntityEvent {

    private final EntityWrapper entity;
    private final Object        id;

    public EntityEventWithWrapperImpl(OrmContext context, EntityMapping em, EntityWrapper entity, Object id) {
        super(context, em);
        this.entity  = entity;
        this.id      = null == id ? Mappings.getId(em, entity) : id;
    }

    @Override
    public Object getId() {
        return id;
    }

    @Override
    public EntityWrapper getEntity() {
        return entity;
    }

}