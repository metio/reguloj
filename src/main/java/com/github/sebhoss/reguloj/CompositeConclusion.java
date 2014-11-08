/*
 * Copyright © 2010 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.reguloj;

final class CompositeConclusion<TOPIC> implements Conclusion<TOPIC> {

    // private final Iterable<Conclusion<TOPIC>> conclusions;

    /**
     * @param conclusions
     */
    CompositeConclusion(final Iterable<Conclusion<TOPIC>> conclusions) {
        // this.conclusions = conclusions;
    }

    @Override
    public boolean apply(final TOPIC topic) {
        return false;
        // TODO: replace FluentIterable with Java 8 stream API
        // return FluentIterable.from(conclusions).filter(Conclusions.conlusionApplies(topic)).size() > 0;
    }

}
