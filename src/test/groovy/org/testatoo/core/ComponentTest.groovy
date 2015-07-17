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
package org.testatoo.core

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.bundle.html5.Button
import org.testatoo.bundle.html5.Paragraph
import org.testatoo.bundle.html5.Radio
import org.testatoo.bundle.html5.Section
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator
import org.testatoo.core.property.Label
import org.testatoo.core.state.Checked
import org.testatoo.core.state.States

import static org.junit.Assert.fail
import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.property.Properties.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ComponentTest {

    @BeforeClass
    public static void setup() {
        evaluator = new WebDriverEvaluator(new FirefoxDriver())
        scan 'org.testatoo.component'
        open 'http://localhost:8080/components.html'
    }

    @AfterClass
    public static void tearDown() { evaluator.close() }

    @Test
    public void should_identify_component_by_css() {

    }

    @Test
    public void should_identify_component_by_js() {

    }

    @Test
    public void component_without_identifier_fail() {
        try {
            ($('#button') as UnidentifiedComponent).should { be States.enabled }
            fail()
        } catch (e) {
            assert e instanceof ComponentException
            assert e.message == "Missing @Identifier annotation on type $UnidentifiedComponent.name" as String
        }
    }

    @Test
    public void top_level_component_identifier_is_used() {
        ($('#button') as CustomButton).should { be States.enabled }
    }

    @Test
    public void on_bad_component_definition_an_error_it_thrown() {
        try {
            ($('#radio') as Button).should { be States.enabled }
            fail()
        } catch (e) {
            assert e instanceof ComponentException
            assert e.message == "Expected a $Button.simpleName for component with id 'radio', but was: $Radio.simpleName" as String
        }
    }

    @Test
    public void should_evaluate_component_equality() {
        Radio radio_1 = $('#radio') as Radio

        // The selector select the same component as radio_1
        Radio radio_2 = $('[type=radio]:checked') as Radio
        Radio radio_3 = $('#otherRadio') as Radio

        assert radio_1 == radio_2
        assert radio_1 != radio_3
    }

    @Test
    public void should_be_able_to_find_nested_component() {
        Section section = $('#section') as Section
        Paragraph paragraph = section.find('p:first')[0] as Paragraph

        paragraph.should { have text('Paragraph 1')}
    }

    @Test
    public void the_hashCode_of_a_component_is_based_on_its_id() {
        Radio radio_1 = $('#radio') as Radio
        assert radio_1.hashCode() == radio_1.id.hashCode()
    }

    @Test
    public void can_evaluate_state_and_property() {
        Radio checked_radio = $('[type=radio]:checked') as Radio

        assert checked_radio.hasState(Checked)
        assert checked_radio.valueFor(Label) == 'Radio label checked'

    }

    static class CustomButton extends Button {}
    static class UnidentifiedComponent extends Component {}
}