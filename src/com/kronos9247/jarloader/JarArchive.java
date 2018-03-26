package com.kronos9247.jarloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
public class JarArchive {
	
	private Class<?> mainClass;
	
	/**
	 * @param mainClass is main class of the jar file
	 */
	JarArchive(Class<?> mainClass) {
		this.mainClass = mainClass;
	}
	
	/**
	 * 
	 * @param jclass is the class from where you take the method
	 * @param methodName is the name of the method
	 * @param parameters is a array of all the types in form of classes that are casted to objects
	 * @return the method
	 */
	protected Method getMethod(Class<?> jclass, String methodName, Class<?>... parameters) {
		Method method = null;
		
		try {
			method = jclass.getMethod(methodName, parameters);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
		return method;
	}
	
	/**
	 * {@literal This function calls the main method from the jar-file with arguments}
	 * @param args is the argument list of the program start
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method main = getMethod(mainClass, "main", String[].class);
		
		main.invoke(null, ((Object) args));
	}
	
	/**
	 * {@literal This function calls the main method from the jar-file without arguments}
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public void main() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		main(new String[0]);
	}

}
