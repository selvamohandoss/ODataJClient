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
package com.msopentech.odatajclient.engine.communication.request.cud;

import com.msopentech.odatajclient.engine.client.http.HttpMethod;
import com.msopentech.odatajclient.engine.communication.request.ODataBasicRequestImpl;
import com.msopentech.odatajclient.engine.communication.request.batch.ODataBatchableRequest;
import com.msopentech.odatajclient.engine.communication.response.ODataLinkOperationResponse;
import com.msopentech.odatajclient.engine.communication.response.ODataResponseImpl;
import com.msopentech.odatajclient.engine.data.ODataLink;
import com.msopentech.odatajclient.engine.data.ODataWriter;
import com.msopentech.odatajclient.engine.format.ODataFormat;
import java.io.InputStream;
import java.net.URI;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.InputStreamEntity;

/**
 * This class implements an update link OData request.
 * <p>
 * Get instance by using ODataCUDRequestFactory.
 *
 * @see ODataCUDRequestFactory#getLinkUpdateRequest(java.net.URI,
 * com.msopentech.odatajclient.engine.communication.request.UpdateType,
 * com.msopentech.odatajclient.engine.data.ODataLink)
 */
public class ODataLinkUpdateRequest extends ODataBasicRequestImpl<ODataLinkOperationResponse, ODataFormat>
        implements ODataBatchableRequest {

    /**
     * Entity to be linked.
     */
    private final ODataLink link;

    /**
     * Constructor.
     *
     * @param method request method.
     * @param targetURI entity URI.
     * @param link entity to be linked.
     */
    ODataLinkUpdateRequest(final HttpMethod method, final URI targetURI, final ODataLink link) {
        super(ODataFormat.class, method, targetURI);
        // set request body
        this.link = link;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public ODataLinkOperationResponse execute() {
        final InputStream input = getPayload();
        ((HttpEntityEnclosingRequestBase) request).setEntity(new InputStreamEntity(input, -1));

        try {
            return new ODataLinkUpdateResponseImpl(client, doExecute());
        } finally {
            IOUtils.closeQuietly(input);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected InputStream getPayload() {
        return ODataWriter.writeLink(link, ODataFormat.fromString(getContentType()));
    }

    /**
     * This class implements the response to an OData link operation request.
     */
    public class ODataLinkUpdateResponseImpl extends ODataResponseImpl implements ODataLinkOperationResponse {

        /**
         * Constructor.
         * <p>
         * Just to create response templates to be initialized from batch.
         */
        private ODataLinkUpdateResponseImpl() {
        }

        /**
         * Constructor.
         *
         * @param client HTTP client.
         * @param res HTTP response.
         */
        public ODataLinkUpdateResponseImpl(final HttpClient client, final HttpResponse res) {
            super(client, res);
        }
    }
}
