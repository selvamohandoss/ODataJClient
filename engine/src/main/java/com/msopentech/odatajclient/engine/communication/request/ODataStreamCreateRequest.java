/*
 * Copyright 2013 MS OpenTech.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.msopentech.odatajclient.engine.communication.request;

import com.msopentech.odatajclient.engine.communication.request.ODataRequest.Method;
import com.msopentech.odatajclient.engine.communication.request.ODataStreamCreateRequest.StreamCreateRequestPayload;
import com.msopentech.odatajclient.engine.communication.response.ODataStreamCreateResponse;
import java.io.InputStream;
import java.io.PipedOutputStream;
import java.net.URI;
import java.util.concurrent.Future;

/**
 * This class implements an OData stream create request.
 * Get instance by using ODataRequestFactory.
 *
 * @see ODataRequestFactory#getStreamRequest(com.msopentech.odatajclient.engine.data.ODataURI, java.io.InputStream)
 */
public class ODataStreamCreateRequest
        extends ODataStreamedRequestImpl<ODataStreamCreateResponse, StreamCreateRequestPayload>
        implements ODataBatchableRequest {

    /**
     * Constructor.
     *
     * @param targetURI target URI.
     * @param stream stream to be created.
     */
    ODataStreamCreateRequest(final URI targetURI, final InputStream stream) {
        super(Method.POST);
        this.uri = targetURI;
    }

    @Override
    public StreamCreateRequestPayload execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public class StreamCreateRequestPayload extends ODataStreamingManagement<ODataStreamCreateResponse> {

        private StreamCreateRequestPayload() {
        }

        private StreamCreateRequestPayload(final PipedOutputStream os) {
            super(os);
        }

        @Override
        public ODataStreamCreateResponse getResponse() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Future<ODataStreamCreateResponse> asyncResponse() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
