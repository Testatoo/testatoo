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
package org.testatoo.bundle.html5.components.list

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.Testatoo
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.$
import static org.testatoo.core.dsl.Actions.on
import static org.testatoo.core.dsl.Actions.visit
import static org.testatoo.core.dsl.Key.CTRL

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ListBoxTest {

    @BeforeClass
    public static void setup() {
        Testatoo.config.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        visit 'http://localhost:8080/components.html'
    }

    @AfterClass
    public static void tearDown() { Testatoo.config.evaluator.close() }

    @Test
    public void should_have_expected_behaviours() {
        ListBox listBox = $('#cities') as ListBox

        listBox.should { have label('Cities list') }
        listBox.should { have 6.items }
        listBox.should { have items('Montreal', 'Quebec', 'Montpellier', 'New York', 'Casablanca', 'Munich') }

        listBox.should { have selectedItems('New York', 'Munich') }

        listBox.should { have 3.visibleItems }
        listBox.should { be multiSelectable }

        listBox.items[0].should { be enabled }
        listBox.item('Quebec').should { be disabled }

        CTRL.click listBox.items[3]
        CTRL.click listBox.items[5]

        CTRL.click listBox.items[0]
        CTRL.click listBox.items[2]

        listBox.should { have selectedItems('Montreal', 'Montpellier') }
        CTRL.click listBox.item('Montreal')
        listBox.should { have selectedItems('Montpellier') }

        on listBox select 'Montreal', 'New York'
        listBox.should { have selectedItems('Montreal', 'Montpellier', 'New York') }

        listBox = $('#planets') as ListBox
        listBox.should { be singleSelectable }

        listBox.should { have groupItems('Cat-1', 'Cat-2') }

        GroupItem group = listBox.groupItems[0]
        group.should { have value('Cat-1') }
        group.should { have 4.items }

        assert group.items.size == 4

        group.should { have items('Mercury', 'Venus', 'Earth', 'Mars') }
        group.items[0].should { have value('Mercury') }
    }
}