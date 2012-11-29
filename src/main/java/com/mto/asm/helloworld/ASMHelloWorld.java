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

import java.lang.reflect.Method;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 11/29/12
 */
public class ASMHelloWorld
{
   public static void main(String[] args)
   {
      //Gen the bytecode of HelloWorld
      byte[] bytecode = new ASMCodeGenerator().generateHelloWorld();

      final ClassLoader cl = Thread.currentThread().getContextClassLoader();
      final ClassLoader customLoader = new ByteClassLoader("com.mto.asm.helloworld.HelloWorld", bytecode, cl);
      try
      {
         Thread.currentThread().setContextClassLoader(customLoader);

         Class helloWorldClazz = customLoader.loadClass("com.mto.asm.helloworld.HelloWorld");
         Method sayHelloMethod = helloWorldClazz.getMethod("sayHello");

         sayHelloMethod.invoke(helloWorldClazz.newInstance());
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
      finally
      {
         Thread.currentThread().setContextClassLoader(cl);
      }
   }
}
