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

package leap.web.api.meta.desc;

/**
 * Created by kael on 2016/11/8.
 */
interface ApiDescContainer {
    /**
     * Returns an {@link OperationDescSet} of this controller
     */
    OperationDescSet getOperationDescSet(Object controller);

    void addOperationDescSet(Object controller,OperationDescSet set);

    ModelDesc getModelDesc(Class<?> modelType);

    void addModelDesc(Class<?> modelType, ModelDesc desc);

    CommonDescContainer.Parameter getCommonParameter(Class<?> type);
}