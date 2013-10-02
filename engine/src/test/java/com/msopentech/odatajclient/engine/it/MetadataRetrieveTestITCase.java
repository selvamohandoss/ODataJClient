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
package com.msopentech.odatajclient.engine.it;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.msopentech.odatajclient.engine.communication.ODataClientErrorException;
import com.msopentech.odatajclient.engine.communication.request.retrieve.ODataMetadataRequest;
import com.msopentech.odatajclient.engine.communication.request.retrieve.ODataRetrieveRequestFactory;
import com.msopentech.odatajclient.engine.communication.response.ODataRetrieveResponse;
import com.msopentech.odatajclient.engine.data.metadata.EdmMetadata;
import com.msopentech.odatajclient.engine.data.metadata.EdmType;
import com.msopentech.odatajclient.engine.data.metadata.edm.EntityType;
import com.msopentech.odatajclient.engine.format.ODataPubFormat;

public class MetadataRetrieveTestITCase extends AbstractTest {

    private void retreiveMetadataTest(final ODataPubFormat reqFormat, final String acceptFormat) {
        // testing entity types which are not open
        final ODataMetadataRequest req = ODataRetrieveRequestFactory.getMetadataRequest(testDefaultServiceRootURL);
        req.setFormat(reqFormat);
        req.setAccept(acceptFormat);
        try {
            final ODataRetrieveResponse<EdmMetadata> res = req.execute();
            final EdmMetadata metadata = res.getBody();
            assertNotNull(metadata);
            assertEquals(24, metadata.getSchemas().get(0).getEntityContainers().get(0).getEntitySets().size());
            final EdmType productCollection =
                    new EdmType(metadata, "Collection(Microsoft.Test.OData.Services.AstoriaDefaultService.Product)");
            assertTrue(productCollection.isCollection());
            assertFalse(productCollection.isSimpleType());
            assertFalse(productCollection.isEnumType());
            assertFalse(productCollection.isComplexType());
            assertTrue(productCollection.isEntityType());
            final EntityType type = productCollection.getEntityType();
            assertNotNull(type);
            assertFalse(type.isOpenType());
            assertEquals("Product", type.getName());
            final EdmType stream = new EdmType(metadata, "Edm.Stream");
            assertNotNull(stream);
            assertTrue(stream.isSimpleType());
            assertFalse(stream.isCollection());
            assertFalse(stream.isEnumType());
            assertFalse(stream.isComplexType());
            assertFalse(stream.isEntityType());
            final EdmType customerCollection =
                    new EdmType(metadata, "Collection(Microsoft.Test.OData.Services.AstoriaDefaultService.Customer)");
            final EntityType customer = customerCollection.getEntityType();
            assertNotNull(type);
            assertFalse(customer.isOpenType());
            assertEquals("Customer", customer.getName());

            //testing open types
            final ODataMetadataRequest req1 = ODataRetrieveRequestFactory.getMetadataRequest(testOpenTypeServiceRootURL);
            req.setFormat(reqFormat);
            req.setAccept(acceptFormat);
            final ODataRetrieveResponse<EdmMetadata> res1 = req1.execute();
            final EdmMetadata metadata1 = res1.getBody();
            List<EntityType> types = metadata1.getSchema(0).getEntityTypes();
            for (int i = 0; i < types.size(); i++) {
                assertTrue(types.get(0).isOpenType());
            }
        } catch (ODataClientErrorException e) {
            assertEquals(415, e.getStatusLine().getStatusCode());
        }
    }
    //unsupported media type. 415 error

    @Test
    public void jsonTest() {
        retreiveMetadataTest(ODataPubFormat.JSON, "application/json");
    }
    //unsupported media type. 415 error

    @Test
    public void atomTest() {
        retreiveMetadataTest(ODataPubFormat.ATOM, "application/atom+xml");
    }

    @Test
    public void xmlTest() {
        retreiveMetadataTest(ODataPubFormat.JSON, "application/xml");
    }
    //unsupported media type. 415 error

    @Test
    public void fullMetadataTest() {
        retreiveMetadataTest(ODataPubFormat.JSON_FULL_METADATA, "application/json");
    }
    //unsupported media type. 415 error

    @Test
    public void noMetadataTest() {
        retreiveMetadataTest(ODataPubFormat.JSON_NO_METADATA, "application/json");
    }
    //null format test

    @Test
    public void nullAcceptTest() {
        retreiveMetadataTest(ODataPubFormat.JSON_NO_METADATA, null);
    }
    //null format test

    @Test
    public void nullFormatTest() {
        retreiveMetadataTest(null, "application/json");
    }
    //null format test

    @Test
    public void atomWithNoAcceptTest() {
        retreiveMetadataTest(ODataPubFormat.ATOM, null);
    }
    //null format test

    @Test
    public void noFormatTest() {
        retreiveMetadataTest(null, "application/xml");
    }
}
