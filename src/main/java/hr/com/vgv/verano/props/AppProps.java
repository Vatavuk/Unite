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
import java.io.IOException;
import java.util.List;
import org.cactoos.Func;
import org.cactoos.Input;
import org.cactoos.collection.Mapped;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.StickyIterable;
import org.cactoos.list.ListOf;
import org.cactoos.scalar.Or;
import org.cactoos.scalar.Ternary;

/**
 * Application properties.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (500 lines)
 */
public final class AppProps implements Props {

    /**
     * Cached properties.
     */
    private final StickyIterable<Props> properties;

    /**
     * Ctor.
     * @param args Application arguments
     */
    public AppProps(final String... args) {
        this(
            new InputsFromFileNames(
                new PropertyFileNames("app", new ProfileNames(args))
            )
        );
    }

    /**
     * Ctor.
     * @param inputs Inputs
     */
    public AppProps(final Iterable<Input> inputs) {
        this.properties = new StickyIterable<>(
            new Mapped<>(BasicProps::new, inputs)
        );
    }

    @Override
    public String value(final String property) throws Exception {
        final List<String> values = new ListOf<>(
            new Mapped<>(
                input -> input.value(property),
                new Filtered<>(
                    input -> input.has(property),
                    this.properties
                )
            )
        );
        return new Ternary<>(
            () -> !values.isEmpty(),
            () -> values.get(values.size() - 1),
            () -> {
                throw new IOException(
                    String.format(
                        "Property %s not found on classpath", property
                    )
                );
            }
        ).value();
    }

    @Override
    public String value(final String property, final String defaults)
        throws Exception {
        return new Ternary<>(
            () -> this.has(property),
            () -> this.value(property),
            () -> defaults
        ).value();
    }

    @Override
    public Iterable<String> values(final String property) throws Exception {
        return new IterableOf<>(this.value(property).split(","));
    }

    @Override
    public boolean has(final String property) throws Exception {
        return new Or(
            (Func<Props, Boolean>) props -> props.has(property),
            this.properties
        ).value();
    }
}
