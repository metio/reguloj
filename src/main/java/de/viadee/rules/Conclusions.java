/*
 * Project: rules
 * Package: de.viadee.rules
 * File   : Conclusions.java
 * Created: Nov 10, 2010 - 5:55:55 PM
 *
 *
 * Copyright 2010 viadee IT Unternehmensberatung GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package de.viadee.rules;

import java.util.Collection;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import de.viadee.rules.implementation.ConclusionBuilderImplementation;

/**
 * <p>Utility class which helps creating new {@link Conclusion conclusions}.</p>
 * 
 * @author 	Sebastian Hoß (sebastian.hoss@viadee.de)
 * @see		Conclusion
 * @since	1.0.0
 */
public final class Conclusions {
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                    CONSTRUCTORS                                                   *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	
	/**
	 * Hidden constructor.
	 */
	private Conclusions() {
		// do nothing
	}
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // *                                                      METHODS                                                      *
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	
	/**
	 * <p>Creates a new {@link ConclusionBuilder} which offers an easy to use fluent interface for creating new 
	 * {@link Conclusion conclusions}.</p>
	 * 
	 * @param <T>		The conclusions target type.
	 * @param commands	The initial commands to set (<b>may not be <code>null</code></b>).
	 * @return			A new conclusion builder.
	 */
	public static <T> ConclusionBuilder<T> conclude(final Collection<Command<T>> commands) {
		return new ConclusionBuilderImplementation<T>(Preconditions.checkNotNull(commands));
	}
	
	/**
	 * <p>Creates a new {@link ConclusionBuilder} which offers an easy to use fluent interface for creating new 
	 * {@link Conclusion conclusions}.</p>
	 * 
	 * @param <T>		The conclusions target type.
	 * @param command	The initial command to set (<b>may not be <code>null</code></b>).
	 * @return			A new conclusion builder.
	 */
	public static <T> ConclusionBuilder<T> conclude(final Command<T> command) {
		final Collection<Command<T>> commands = Lists.newArrayList();
		
		commands.add(Preconditions.checkNotNull(command));
		
		return conclude(commands);
	}

}
