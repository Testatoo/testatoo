/**
 * Copyright (C) 2016 Ovea (dev@ovea.com)
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
package org.testatoo.bundle.html5.heading

import org.testatoo.core.ByCss
import org.testatoo.core.component.Heading

import static org.testatoo.bundle.html5.helper.TextHelper.getText

/**
 * @author David Avenante (d.avenante@gmail.com)
 */

@ByCss('h3')
class H3 extends Heading {

    @Override
    String getText() {
        getText(this);
    }
}