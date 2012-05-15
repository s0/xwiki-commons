/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.diff;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public abstract class AbstractDelta<E> implements Delta<E>
{
    private Chunk<E> previous;

    private Chunk<E> next;

    public AbstractDelta(Chunk<E> previous, Chunk<E> next)
    {
        this.previous = previous;
        this.next = next;
    }

    @Override
    public Chunk<E> getPrevious()
    {
        return this.previous;
    }

    public void setPrevious(Chunk<E> previous)
    {
        this.previous = previous;
    }

    @Override
    public Chunk<E> getNext()
    {
        return this.next;
    }

    public void setNext(Chunk<E> next)
    {
        this.next = next;
    }

    @Override
    public int hashCode()
    {
        HashCodeBuilder builder = new HashCodeBuilder();

        builder.append(getPrevious());
        builder.append(getNext());

        return builder.toHashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Delta) {
            Delta<E> otherDelta = (Delta<E>) obj;

            return ObjectUtils.equals(getPrevious(), otherDelta.getPrevious())
                && ObjectUtils.equals(getNext(), otherDelta.getNext());
        }

        return false;
    }
}
