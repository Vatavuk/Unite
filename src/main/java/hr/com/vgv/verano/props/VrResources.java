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

import java.util.ArrayList;
import java.util.Collection;
import org.cactoos.Input;
import org.cactoos.io.ResourceOf;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;

/**
 * Application resources.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class VrResources extends IterableEnvelope<Input> {

    /**
     * Ctor.
     * @param prefix Resource name prefix
     */
    public VrResources(final String prefix) {
        this(prefix, new IterableOf<>());
    }

    /**
     * Ctor.
     * @param prefix Resource name prefix
     * @param suffixes Resource name suffixes.
     */
    public VrResources(final String prefix, final Iterable<String> suffixes) {
        super(() -> {
            final String format = "properties";
            final Collection<String> resources = new ArrayList<>(0);
            for (final String suffix: suffixes) {
                resources.add(
                    String.format("%s-%s.%s", prefix, suffix, format)
                );
            }
            return new Joined<Input>(
                new IterableOf<>(
                    new ResourceOf(String.format("%s.%s", prefix, format))
                ),
                new ResourcesOf(resources)
            );
        });
    }
}