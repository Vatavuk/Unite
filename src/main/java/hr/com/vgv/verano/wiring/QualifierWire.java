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
package hr.com.vgv.verano.wiring;

import hr.com.vgv.verano.AppContext;
import hr.com.vgv.verano.Props;
import hr.com.vgv.verano.Wire;
import hr.com.vgv.verano.props.QualifiersPropsOf;
import org.cactoos.scalar.And;

/**
 * Qualifier wire.
 * Wire is active if the right qualifier is set for given component name
 * in qualifiers.xml file.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class QualifierWire implements Wire {

    /**
     * Qualifier value.
     */
    private final String value;

    /**
     * Ctor.
     * @param cls Class
     */
    public QualifierWire(final Class<?> cls) {
        this(cls.getSimpleName());
    }

    /**
     * Ctor.
     * @param qualifier Qualifier
     */
    public QualifierWire(final String qualifier) {
        this.value = qualifier;
    }

    @Override
    public Boolean isActive(final AppContext context, final String component)
        throws Exception {
        final Props props = new QualifiersPropsOf(context);
        final String xpath = String.format("//class[@name='%s']", component);
        return new And(
            () -> props.has(xpath),
            () -> props.value(String.format("%s/qualifier", xpath))
            .equals(this.value)
        ).value();
    }

    @Override
    public String toString() {
        return this.value;
    }
}
