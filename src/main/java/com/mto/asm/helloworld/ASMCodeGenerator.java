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

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

/**
 * @author <a href="hoang281283@gmail.com">Minh Hoang TO</a>
 * @date 11/28/12
 */
public class ASMCodeGenerator
{
   /**
    * Generate the byte code of a simple HelloWorld program
    *
    * public class HelloWorld
    * {
    *    public void sayHello()
    *    {
    *       System.out.println("Hello World");
    *    }
    * }
    * @return
    */
   public byte[] generateHelloWorld()
   {
      ClassWriter cw = new ClassWriter(0);
      MethodVisitor mv;

      cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, "com/mto/asm/helloworld/HelloWorld", null, "java/lang/Object", null);

      cw.visitSource("HelloWorld.java", null);

      mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
      mv.visitCode();
      Label l0 = new Label();
      mv.visitLabel(l0);
      mv.visitLineNumber(25, l0);
      mv.visitVarInsn(ALOAD, 0);
      mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
      mv.visitInsn(RETURN);
      Label l1 = new Label();
      mv.visitLabel(l1);
      mv.visitLocalVariable("this", "Lcom/mto/asm/helloworld/HelloWorld;", null, l0, l1, 0);
      mv.visitMaxs(1, 1);
      mv.visitEnd();

      mv = cw.visitMethod(ACC_PUBLIC, "sayHello", "()V", null, null);
      mv.visitCode();
      l0 = new Label();
      mv.visitLabel(l0);
      mv.visitLineNumber(29, l0);
      mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
      mv.visitLdcInsn("Hello World");
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
      l1 = new Label();
      mv.visitLabel(l1);
      mv.visitLineNumber(30, l1);
      mv.visitInsn(RETURN);
      Label l2 = new Label();
      mv.visitLabel(l2);
      mv.visitLocalVariable("this", "Lcom/mto/asm/helloworld/HelloWorld;", null, l0, l2, 0);
      mv.visitMaxs(2, 1);
      mv.visitEnd();
      cw.visitEnd();

      return cw.toByteArray();
   }

}
