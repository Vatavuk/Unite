/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Vedran Grgo Vatavuk
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package hr.com.vgv.verano.components;

import hr.com.vgv.verano.VrAppContext;
import hr.com.vgv.verano.Wire;
import hr.com.vgv.verano.wire.ProfileWire;
import hr.com.vgv.verano.wire.QualifierWire;
import org.cactoos.iterable.IterableOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test case for {@link ComponentTemplate}.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 * @checkstyle JavadocMethodCheck (500 lines)
 */
public final class ComponentTemplateTest {

    @Test
    public void checksIfComponentIsActive() throws Exception {
        MatcherAssert.assertThat(
            new ComponentTemplateTest.CustomComponent().isActive(
                new VrAppContext(
                    String.format(
                        "--profile=%s", ComponentTemplateTest.profile()
                    )
                )
            ),
            Matchers.equalTo(true)
        );
    }

    @Test
    public void checksIfComponentIsActiveAgainstWires() throws Exception {
        MatcherAssert.assertThat(
            new ComponentTemplateTest.CustomComponent().isActive(
                new IterableOf<>(ComponentTemplateTest.qualifier())
            ),
            Matchers.equalTo(true)
        );
    }

    /**
     * Profile string.
     * @return String Profile
     */
    private static String profile() {
        return "test";
    }

    /**
     * Qualifier wire.
     * @return Wire Qualifier wire
     */
    private static Wire qualifier() {
        return new QualifierWire("qualifier");
    }

    /**
     * Custom component.
     */
    private static final class CustomComponent extends
        ComponentTemplate<Boolean> {

        /**
         * Ctor.
         */
        CustomComponent() {
            super(() -> new VrComponent<>(
                () -> true,
                new ProfileWire(ComponentTemplateTest.profile()),
                ComponentTemplateTest.qualifier()
                )
            );
        }
    }
}
