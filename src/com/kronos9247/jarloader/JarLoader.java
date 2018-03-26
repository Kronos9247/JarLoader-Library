package com.kronos9247.jarloader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

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
public final class JarLoader {
	
	public static final JarClassLoader LOADER = new JarClassLoader();
	
	/**
	 * 
	 * @param file represents the jar-file
	 * @return the path to the main class
	 * @throws IOException
	 */
	private static String loadMetaData(JarFile file) throws IOException {
		Manifest manifest = file.getManifest();
		
		return manifest.getMainAttributes().getValue("Main-Class");
	}
	
	/**
	 * 
	 * @param loader
	 * @param file
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static Class<?> loadClass(JarClassLoader loader, JarFile file, String path) throws IOException {
		return loader.loadClass(file, path);
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static JarArchive load(URL url) throws IOException, URISyntaxException {
		JarFile file = new JarFile(new File(url.toURI()));
		String mClass = loadMetaData(file);
		
		Class<?> mainClass = loadClass(LOADER, file, mClass.replace(".", "/") + ".class");
		
		return new JarArchive(mainClass);
	}
	
	/**
	 * {@literal This is a example}
	 * @param args
	 */
	public static void main(String[] args) {
		URL stream = JarLoader.class.getResource("/plu-in.jar");
		
		try {
			JarArchive archive = load(stream);
			archive.main();
		} catch (IOException | URISyntaxException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
