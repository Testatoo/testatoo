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
package org.testatoo.hamcrest.matcher.state

import org.hamcrest.Description
import org.hamcrest.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.support.state.CheckSupport

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.hamcrest.Matchers.unchecked

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class UnCheckedMatcherTest {
    @Test
    public void should_have_expected_matcher() {
        CheckSupport cmp = mock(CheckSupport)

        when(cmp.checked()).thenReturn(false)
        assertThat(cmp, is(unchecked()))

        when(cmp.checked()).thenReturn(true)
        try {
            assertThat(cmp, is(unchecked()))
            fail()
        } catch (AssertionError e) {
            Description description = new StringDescription();
            description.appendText('\nExpected: is unchecked')
                    .appendText('\n     but: is checked');

            assert e.message == description.toString()
        }
    }
}