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

import org.cactoos.Proc;
import org.cactoos.Scalar;
import org.cactoos.scalar.Ternary;

/**
 * Binary operation.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class Binary implements Scalar<Boolean> {

    /**
     * Condition.
     */
    private final Scalar<Boolean> condition;

    /**
     * Runnable.
     */
    private final Scalar<Boolean> scalar;

    /**
     * Ctor.
     * @param condition Condition
     * @param runnable Runnable
     */
    public Binary(final boolean condition, final Runnable runnable) {
        this(() -> condition, runnable);
    }

    /**
     * Ctor.
     * @param condition Condition
     * @param runnable Runnable
     */
    public Binary(final Scalar<Boolean> condition,
        final Runnable runnable) {
        this(condition, () -> {
            runnable.run();
            return true;
        });
    }

    /**
     * Ctor.
     * @param condition Condition
     * @param proc Proc
     */
    public Binary(final boolean condition, final Proc<Boolean> proc) {
        this(() -> condition, () -> {
            proc.exec(true);
            return true;
        });
    }

    /**
     * Ctor.
     * @param condition Condition
     * @param scalar Scalar
     */
    public Binary(final boolean condition, final Scalar<Boolean> scalar) {
        this(() -> condition, scalar);
    }

    /**
     * Ctor.
     * @param condition Condition
     * @param scalar Scalar
     */
    public Binary(final Scalar<Boolean> condition,
        final Scalar<Boolean> scalar) {
        this.condition = condition;
        this.scalar = scalar;
    }

    @Override
    public Boolean value() throws Exception {
        return new Ternary<>(
            this.condition,
            this.scalar,
            () -> false
        ).value();
    }
}
