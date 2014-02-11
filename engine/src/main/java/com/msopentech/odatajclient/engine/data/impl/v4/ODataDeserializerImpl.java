/**
 * Copyright © Microsoft Open Technologies, Inc.
 *
 * All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * THIS CODE IS PROVIDED *AS IS* BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION
 * ANY IMPLIED WARRANTIES OR CONDITIONS OF TITLE, FITNESS FOR A
 * PARTICULAR PURPOSE, MERCHANTABILITY OR NON-INFRINGEMENT.
 *
 * See the Apache License, Version 2.0 for the specific language
 * governing permissions and limitations under the License.
 */
package com.msopentech.odatajclient.engine.data.impl.v4;

import com.msopentech.odatajclient.engine.client.ODataClient;
import com.msopentech.odatajclient.engine.data.V3ServiceDocument;
import com.msopentech.odatajclient.engine.data.impl.AbstractODataDeserializer;
import com.msopentech.odatajclient.engine.metadata.edm.v4.Edmx;
import java.io.IOException;
import java.io.InputStream;

public class ODataDeserializerImpl extends AbstractODataDeserializer {

    private static final long serialVersionUID = 8593081342440470415L;

    public ODataDeserializerImpl(final ODataClient client) {
        super(client);
    }

    @Override
    public Edmx toMetadata(final InputStream input) {
        try {
            return getXmlMapper().readValue(input, Edmx.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not parse as Edmx document", e);
        }
    }

    @Override
    protected V3ServiceDocument toServiceDocumentFromXML(final InputStream input) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected V3ServiceDocument toServiceDocumentFromJSON(final InputStream input) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected JSONEntry toJSONEntry(final InputStream input) {
        try {
            return getObjectMapper().readValue(input, JSONEntry.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("While deserializing JSON entry", e);
        }
    }
}
