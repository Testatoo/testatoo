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
package org.testatoo.core.traits

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.bundle.html5.components.fields.EmailField
import org.testatoo.bundle.html5.components.fields.PasswordField
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.dsl.Actions.visit

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ValiditySupportTest {

    @BeforeClass
    public static void setup() {
        config.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        visit 'http://localhost:8080/traits.html'
    }

    @AfterClass
    public static void tearDown() { config.evaluator.close() }

    @Test
    public void should_have_expected_behaviours() {
        EmailField email = $('#email') as EmailField

        assert email.valid
        assert !email.invalid

        PasswordField password = $('#password') as PasswordField
        // Invalid cause required
        assert password.invalid
    }
}