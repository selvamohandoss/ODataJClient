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
package com.msopentech.odatajclient.engine.data.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msopentech.odatajclient.engine.data.V3ServiceDocument;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public abstract class AbstractServiceDocument implements V3ServiceDocument {

    public static class TLEntitySet implements V3ServiceDocument.TLEntitySet {

        private String name;

        private String title;

        @JsonProperty("url")
        private String href;

        @Override
        public String getName() {
            return name;
        }

        public void setName(final String name) {
            this.name = name;
        }

        @Override
        public String getTitle() {
            return title;
        }

        public void setTitle(final String title) {
            this.title = title;
        }

        @Override
        public String getHref() {
            return href;
        }

        public void setHref(final String href) {
            this.href = href;
        }

        @Override
        public boolean equals(final Object obj) {
            return EqualsBuilder.reflectionEquals(this, obj);
        }

        @Override
        public int hashCode() {
            return HashCodeBuilder.reflectionHashCode(this);
        }

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
        }
    }

    @Override
    public TLEntitySet getTLEntitySetByName(final String name) {
        TLEntitySet result = null;
        for (V3ServiceDocument.TLEntitySet tlEntitySet : getTLEntitySets()) {
            if (name.equals(tlEntitySet.getName())) {
                result = (TLEntitySet) tlEntitySet;
            }
        }
        return result;
    }

    @Override
    public TLEntitySet getTLEntitySetByTitle(final String title) {
        TLEntitySet result = null;
        for (V3ServiceDocument.TLEntitySet tlEntitySet : getTLEntitySets()) {
            if (title.equals(tlEntitySet.getTitle())) {
                result = (TLEntitySet) tlEntitySet;
            }
        }
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
