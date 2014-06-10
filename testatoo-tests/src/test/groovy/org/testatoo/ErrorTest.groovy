/**
 * Copyright (C) 2013 Ovea (dev@ovea.com)
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
package org.testatoo

import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.Testatoo
import org.testatoo.core.component.*
import org.testatoo.core.component.input.EmailField
import org.testatoo.core.component.list.*
import org.testatoo.core.evaluator.Evaluator
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.junit.Assert.fail
import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Key.*
import static org.testatoo.core.property.Properties.*
import static org.testatoo.core.state.States.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ErrorTest {

    @BeforeClass
    public static void setup() {
        Testatoo.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        open 'http://localhost:8080/error.html'
    }

    @AfterClass
    public static void tearDown() { evaluator.close() }

    @Test
    public void not_supported_state_support() {
        EmailField email = $('#email') as EmailField
        try {
            assertThat email is checked
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Component EmailField:email does not support state Checked'
        }
    }

    @Test
    public void not_supported_property_support() {
        EmailField email = $('#email') as EmailField
        try {
            assertThat email has reference('reference')
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Component EmailField:email does not support property Reference'
        }
    }

    @Test
    public void call_is_with_property() {
        EmailField email = $('#email') as EmailField
        try {
            assertThat email is text('')
            fail()
        } catch (IllegalArgumentException e) {
            assert e.message == 'Cannot use property matcher (Text equals to ) for state'
        }
    }

    @Test
    public void cannot_submit_form_if_no_submit_button_available() {
        Form form = $('#form') as Form
        try {
            submit form
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Cannot submit form without submit button'
        }
    }

    @Test
    public void cannot_reset_form_if_no_reset_button_available() {
        Form form = $('#form') as Form
        try {
            reset form
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Cannot reset form without reset button'
        }
    }

    @Test
    public void exception_is_thrown_when_wait_until_condition_is_not_reached() {
        Button button = $('#inexisting_button') as Button;
        try {
            waitUntil 2.seconds, {
                button.is(available)
            }
            fail()
        } catch (RuntimeException e) {
            assert e.message == "Unable to reach the condition within 2 seconds (Component defined by jQuery expression \$('#inexisting_button') not found.)"
        }
    }

    @Test
    public void exception_is_thrown_on_invalid_click_sequence() {
        Form form = $('#form') as Form
        try {
            // TODO Math why intelliJ waring
            [CTRL, 'test', ALT].click form
            fail()
        } catch (IllegalArgumentException e) {
            assert e.message == 'Cannot type a modifier after some text'
        }

        try {
            evaluator.click('form', Evaluator.MouseButton.RIGHT, Evaluator.MouseClick.DOUBLE)
            fail()
        } catch (IllegalArgumentException e) {
            assert e.message == 'Invalid click sequence'
        }
    }

    @Test
    public void test_hidden_state_on_visible_component_throw_exception() {
        DropDown dropDown = $('#elements') as DropDown
        try {
            assertThat dropDown is hidden
            fail()
        } catch (AssertionError e) {
            assert e.message == 'Component DropDown with id elements expected hidden but was visible'
        }
    }

    @Test
    public void cannot_unselect_disabled_option() {
        DropDown dropDown = $('#elements') as DropDown
        assertThat dropDown.items[0] is disabled
        try {
            on dropDown unselect 'Helium'
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Item Helium is disabled and cannot be unselected'
        }
    }

    @Test
    public void equals_to_matcher_on_list_items() {
        DropDown dropDown = $('#elements') as DropDown
        try {
            assertThat dropDown has items.equalsTo('Val1')
            fail()
        } catch (AssertionError e) {
            assert e.message == "Expected Items '[Val1]' but was '[Helium, Boron, Polonium, Calcium, Radium]'"
        }

        try {
            assertThat dropDown has items.equalsTo(['Val1', 'Val2'])
            fail()
        } catch (AssertionError e) {
            assert e.message == "Expected Items '[Val1, Val2]' but was '[Helium, Boron, Polonium, Calcium, Radium]'"
        }
    }

    @Test
    public void equals_to_matcher() {
        DropDown dropDown = $('#elements') as DropDown
        try {
            assertThat dropDown.items[0] has value.equalsTo('Val_1')
        } catch (AssertionError e) {
            assert e.message == "Expected Value 'Val_1' but was 'Helium'"
        }

        try {
            assertThat dropDown.items[0] has value.equalsTo('Val_1', 'val_2')
            fail()
        } catch (AssertionError e) {
            assert e.message == "Expected one of Value '[Val_1, val_2]' but was 'Helium'"
        }
    }

    @Test
    public void containing_matcher() {
        DropDown dropDown = $('#elements') as DropDown
        try {
            assertThat dropDown has items.containing('Val_1')
        } catch (AssertionError e) {
            assert e.message == "Expected Items containing 'Val_1' but was '[Helium, Boron, Polonium, Calcium, Radium]'"
        }

        try {
            assertThat dropDown has items.containing('Val_1', 'Val_2')
        } catch (AssertionError e) {
            assert e.message == "Expected one of Items containing '[Val_1, Val_2]' but was '[Helium, Boron, Polonium, Calcium, Radium]'"
        }
    }

    @Test
    public void missing_component_error() {
        DropDown dropDown = $('#elements') as DropDown
        try {
            assertThat dropDown is missing
        } catch (AssertionError e) {
            assert e.message == 'Component DropDown with id elements expected missing but was available'
        }
    }

    @Test
    public void multiple_elements_selector_throw_exception() {
        try {
            Item item = $('#elements option') as Item;
            assertThat item is visible
            fail()
        } catch (ComponentException e) {
            e.message == "Component defined by jQuery expression \$('#elements option') is not unique: got 5"
        }
    }

    // try to check component that not support checked state
    // cannot select already selected item
}
