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
package com.msopentech.odatajclient.engine.data.impl.v3;

import com.msopentech.odatajclient.engine.client.ODataClient;
import com.msopentech.odatajclient.engine.data.impl.AbstractODataDeserializer;
import com.msopentech.odatajclient.engine.data.impl.AbstractServiceDocument.TLEntitySet;
import com.msopentech.odatajclient.engine.metadata.edm.v3.Edmx;
import com.msopentech.odatajclient.engine.utils.ODataConstants;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ODataDeserializerImpl extends AbstractODataDeserializer {

    private static final long serialVersionUID = -8221085862548914611L;

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
    protected XMLServiceDocument toServiceDocumentFromXML(final InputStream input) {
        final Element service = toDOM(input);

        final XMLServiceDocument serviceDoc = new XMLServiceDocument();
        serviceDoc.setBaseURI(URI.create(service.getAttribute(ODataConstants.ATTR_XMLBASE)));

        final NodeList collections = service.getElementsByTagName(ODataConstants.ELEM_COLLECTION);
        for (int i = 0; i < collections.getLength(); i++) {
            final Element collection = (Element) collections.item(i);

            final NodeList title = collection.getElementsByTagName(ODataConstants.ATOM_ATTR_TITLE);
            if (title.getLength() != 1) {
                throw new IllegalArgumentException("Invalid collection element found");
            }

            final TLEntitySet tlEntitySet = new TLEntitySet();
            tlEntitySet.setName(title.item(0).getTextContent());
            tlEntitySet.setHref(collection.getAttribute(ODataConstants.ATTR_HREF));
            serviceDoc.getTLEntitySets().add(tlEntitySet);
        }

        return serviceDoc;
    }

    @Override
    protected JSONServiceDocument toServiceDocumentFromJSON(final InputStream input) {
        try {
            return getObjectMapper().readValue(input, JSONServiceDocument.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("While deserializing JSON service document", e);
        }
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
