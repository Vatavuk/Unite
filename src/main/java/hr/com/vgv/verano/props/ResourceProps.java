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
package hr.com.vgv.verano.props;

import hr.com.vgv.verano.Props;
import java.util.ArrayList;
import java.util.Collection;
import org.cactoos.Input;
import org.cactoos.Scalar;
import org.cactoos.scalar.SolidScalar;

/**
 * Properties fetched from resources.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class ResourceProps implements Props {

    /**
     * Resource properties.
     */
    private final Scalar<Iterable<Props>> resources;

    /**
     * Ctor.
     * @param suffixes Resource name suffixes
     */
    public ResourceProps(final Collection<String> suffixes) {
        this("app", suffixes);
    }

    /**
     * Ctor.
     * @param prefix Resource name prefix
     * @param suffixes Resource name suffixes
     */
    public ResourceProps(final String prefix, final Iterable<String> suffixes) {
        this(new VrResources(prefix, suffixes));
    }

    /**
     * Ctor.
     * @param inputs Inputs
     */
    public ResourceProps(final Iterable<Input> inputs) {
        this(
            new SolidScalar<>(
                () -> {
                    final Collection<Props> collection =
                        new ArrayList<>(0);
                    for (final Input inp : inputs) {
                        collection.add(new DefaultProps(inp));
                    }
                    return collection;
                }
            )
        );
    }

    /**
     * Ctor.
     * @param res List of resource properties
     */
    public ResourceProps(final Scalar<Iterable<Props>> res) {
        this.resources = res;
    }

    @Override
    public String value(final String property) throws Exception {
        this.resources.value();
        throw new UnsupportedOperationException();
    }

    @Override
    public String value(final String property, final String defaults)
        throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<String> values(final String property) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean has(final String property) throws Exception {
        throw new UnsupportedOperationException();
    }
}