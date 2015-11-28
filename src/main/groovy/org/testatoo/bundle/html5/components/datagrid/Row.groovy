/**
 * Copyright (C) 2014 Ovea (dev@ovea.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.bundle.html5.components.datagrid

import org.testatoo.core.ByCss
import org.testatoo.core.Component
import org.testatoo.core.Testatoo


/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@ByCss('tr')
class Row extends Component {

    List<Cell> getCells() {
        Testatoo.config.evaluator.getMetaInfo("\$('#${id}').find('td')").collect { it as Cell }
    }

    String getTitle() {
        Testatoo.config.evaluator.eval(id, "it.find('th:first').text().trim()")
    }
}