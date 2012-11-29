/*
 * Copyright (C) 2012 eXo Platform SAS.
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
package com.mto.asm.helloworld;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 11/29/12
 */
public class ByteClassLoader extends ClassLoader
{
   final Map<String, byte[]> resources;

   public ByteClassLoader(String name, byte[] bytes, ClassLoader parent)
   {
      this(parent);
      resources.put(name, bytes);
   }

   public ByteClassLoader(Map<String, byte[]> _resources, ClassLoader parent)
   {
      this(parent);
      resources.putAll(_resources);
   }

   public ByteClassLoader(ClassLoader parent)
   {
      super(parent);
      resources = new HashMap<String, byte[]>();
   }

   @Override
   protected Class<?> findClass(String name) throws ClassNotFoundException
   {
      byte[] bytes = resources.get(name);

      if (bytes != null)
      {
         return defineClass(name, bytes, 0, bytes.length);
      }
      else
      {
         return super.findClass(name);
      }
   }
}
