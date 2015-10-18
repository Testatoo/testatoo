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
package org.testatoo.core.internal

import org.testatoo.bundle.html5.components.list.Item
import org.testatoo.core.Component
import org.testatoo.core.input.Key
import org.testatoo.core.support.MultiSelector

import java.time.Duration

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.MouseModifiers.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class GroovyExtensions {

    public static Duration getSeconds(Number self) { Duration.ofSeconds(self.longValue()) }

    public static Collection<?> plus(Key a, Key b) { [a, b] }

    public static Collection<?> plus(Key a, String b) { [a, b] }

    static void click(Key key, Component c) { click([key], c) }

    static void rightClick(Key key, Component c) { rightClick([key], c) }

    static void click(Collection<Key> keys, Component c) {
        config.evaluator.click(c.id, [LEFT, SINGLE], keys)
    }

    static void rightClick(Collection<Key> keys, Component c) {
        config.evaluator.click(c.id, [RIGHT, SINGLE], keys)
    }

    // TODO Math rework
//    static void select(MultiSelector selector, String... values) {
//        selector.select(values)
//    }
//
//    static void select(MultiSelector selector, Item... items) {
//        selector.select(items)
//    }
//
//    static void select(Component selector, String value) {
//        selector.select(value)
//    }
//
//    static void select(Component selector, Item item) {
//        selector.select(item)
//    }
//
//    static void unselect(Component selector, String... values) {
//        selector.unselect(values)
//    }
//
//    static void unselect(Component selector, Item... items) {
//        selector.unselect(items)
//    }
//
//    static void unselect(Component selector, String value) {
//        selector.unselect(value)
//    }
//
//    static void unselect(Component selector, Item item) {
//        selector.unselect(item)
//    }

    // TODO Math used for the should DSL level
//    static boolean asBoolean(Block block) {
//        Blocks.run(block)
//        return true
//    }
}
