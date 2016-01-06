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
package org.testatoo.bundle.html5.components.input

import org.testatoo.core.ByCss
import org.testatoo.core.component.field.RangeField

import static org.testatoo.bundle.html5.components.helper.RangeHelper.*
import static org.testatoo.core.Testatoo.getConfig

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@ByCss('input[type=range]')
class InputTypeRange extends RangeField implements Input {

    Number getValue() {
        config.evaluator.eval(id, "it.val()") as BigDecimal
    }

    void setValue(Object value) {
        config.evaluator.eval(id, "it.val(" + value + ")")
    }

    @Override
    Number getMinimum() {
        getMinimun(this) as BigDecimal
    }

    @Override
    Number getMaximum() {
        getMaximum(this) as BigDecimal
    }

    @Override
    Number getStep() {
        getStep(this)
    }

    @Override
    boolean isInRange() {
        isInRange(this)
    }

    @Override
    boolean isOutOfRange() {
        isOutOfRange(this)
    }
}
