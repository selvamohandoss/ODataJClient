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

import com.msopentech.odatajclient.engine.communication.response.ODataQueryResponse;
import java.io.InputStream;
import java.net.URI;
import java.util.concurrent.Future;

/**
 * This class implements an OData query request.
 * Get instance by using ODataRequestFactory.
 *
 * @see ODataRequestFactory#getQueryRequest(com.msopentech.odatajclient.engine.data.ODataURI)
 */
public class ODataQueryRequest extends ODataRequestImpl
        implements ODataBasicRequest<ODataQueryResponse>, ODataBatchableRequest {

    /**
     * Constructor.
     *
     * @param query query URI.
     */
    ODataQueryRequest(final URI query) {
        // set method .... If cofigured X-HTTP-METHOD header will be used.
        super(Method.GET);
        // set uri ...
        this.uri = query;
    }

    @Override
    public ODataQueryResponse execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InputStream rowExecute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Future<ODataQueryResponse> asyncExecute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
