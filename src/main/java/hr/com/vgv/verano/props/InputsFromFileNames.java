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

import org.cactoos.Input;
import org.cactoos.collection.Mapped;
import org.cactoos.io.InputOf;
import org.cactoos.io.ResourceOf;
import org.cactoos.iterable.IterableEnvelope;

/**
 * Inputs retrieved from file names.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class InputsFromFileNames extends IterableEnvelope<Input> {

    /**
     * Ctor.
     * @param names File names
     */
    public InputsFromFileNames(final Iterable<String> names) {
        super(() -> new Mapped<>(ResourceOf::new, names));
    }

    /**
     * Ctor.
     * @param path File path
     * @param names File names
     */
    public InputsFromFileNames(final String path,
        final Iterable<String> names) {
        super(() -> new Mapped<>(
            InputOf::new,
            new Mapped<>(
                input -> String.format("%s/%s", path, input),
                names
            )
        ));
    }
}
