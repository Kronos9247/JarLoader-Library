package com.kronos9247.jarloader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

/*
 *  Copyright (C) 2017  Rafael Orman
 *	
 *  This file is a part of the Jar-Loader Library (JLL).
 *  The library helps programmer to load runnable jars easily
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
public class JarClassLoader extends ClassLoader {
	
	/**
	 * 
	 * @param file is the jar-file from which you want to get the class
	 * @param path this is the path from which you want to get the class
	 * @return a class
	 * @throws IOException
	 */
	public Class<?> loadClass(JarFile file, String path) throws IOException {
		return LoadClass(file, file.getEntry(path));
	}
	
	/**
	 * 
	 * @param file is the jar-file from which you want to get the class
	 * @param entry is here to see the structure and read the jar a.k.a zip archive
	 * @return a class
	 * @throws IOException
	 */
	public Class<?> loadClass(JarFile file, ZipEntry entry) throws IOException {
		return LoadClass(file, entry);
	}
	
	//returns a class from a jar-file
	private Class<?> LoadClass(JarFile file, ZipEntry entry) throws IOException {
		byte[] classContent = loadClassData(entry.getSize(), file.getInputStream( entry ));
		
		return defineClass(null, classContent, 0, classContent.length);
	}
	
	//this method loads the content of the class
	private byte[] loadClassData(long length, InputStream stream) throws IOException {
		byte bufferFile[] = new byte[(int) length];
        BufferedInputStream byteBuffer = new BufferedInputStream(stream);
        byteBuffer.read(bufferFile, 0, bufferFile.length);
        byteBuffer.close();
		
		return bufferFile;
    }

}
