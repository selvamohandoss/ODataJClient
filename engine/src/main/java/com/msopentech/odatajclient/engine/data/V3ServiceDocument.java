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
package com.msopentech.odatajclient.engine.data;

import java.net.URI;
import java.util.List;

/**
 * REST resource for an <tt>ODataServiceDocument</tt>.
 *
 * @see ODataServiceDocument
 */
public interface V3ServiceDocument {

    public interface TLEntitySet {

        String getName();

        String getTitle();

        String getHref();
    }

    /**
     * Gets base URI.
     *
     * @return base URI.
     */
    URI getBaseURI();

    /**
     * Gets top level entity sets.
     *
     * @return top level entity sets.
     */
    List<? extends TLEntitySet> getTLEntitySets();

    /**
     * Gets top level entity set with given name.
     *
     * @param name entity set name
     * @return entity set with given name if found, otherwise null
     */
    TLEntitySet getTLEntitySetByName(String name);

    /**
     * Gets top level entity set with given title.
     *
     * @param title entity set title
     * @return entity set with given title if found, otherwise null
     */
    TLEntitySet getTLEntitySetByTitle(String title);
}
