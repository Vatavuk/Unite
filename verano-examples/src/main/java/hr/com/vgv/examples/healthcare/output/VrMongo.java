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
package hr.com.vgv.examples.healthcare.output;

import com.mongodb.MongoClient;
import hr.com.vgv.verano.AppContext;
import hr.com.vgv.verano.components.VrComponent;
import hr.com.vgv.verano.instances.VrCloseableInstance;
import hr.com.vgv.verano.props.AppPropsOf;
import hr.com.vgv.verano.wiring.ProfileWire;

/**
 * Mongo component.
 *
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class VrMongo extends VrComponent<MongoClient> {

    public VrMongo(final AppContext context) {
        super(context,
            new VrCloseableInstance<>(
                new DevMongo(new AppPropsOf(context)),
                new ProfileWire("dev")
            ),
            new VrCloseableInstance<>(
                new EmbeddedMongo(),
                new ProfileWire("test")
            )
        );
    }
}
