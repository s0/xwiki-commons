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

import java.util.List;

public class InsertDelta<E> extends AbstractDelta<E>
{
    public InsertDelta(Chunk<E> original, Chunk<E> revised)
    {
        super(original, revised);
    }

    @Override
    public void apply(List<E> target) throws PatchException
    {
        verify(target);

        int index = getPrevious().getIndex();
        List<E> elements = getNext().getElements();
        for (int i = 0; i < elements.size(); i++) {
            target.add(index + i, elements.get(i));
        }
    }

    @Override
    public void restore(List<E> target)
    {
        int index = getNext().getIndex();
        int size = getNext().size();
        for (int i = 0; i < size; i++) {
            target.remove(index);
        }
    }

    @Override
    public void verify(List<E> target) throws PatchException
    {
        if (getPrevious().getIndex() > target.size()) {
            throw new PatchException("Incorrect patch for delta: delta original position > target size");
        }

    }

    @Override
    public TYPE getType()
    {
        return Delta.TYPE.INSERT;
    }
}
