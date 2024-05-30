package com.ntr.hrank;

import java.time.OffsetDateTime;

/**
 * Both fields will be init when the constructor is called -> Val v = new Val(id);
 * 1. odt will be init right before constructor execution.
 * 2. id will be init during constructor execution.
 * Instance initialization process:
 * 1. Superclass Constructor Execution: If there is a superclass, its constructor is executed first.
 * 2. Instance Variables Initialization and Instance Initializers: Instance variables are initialized in the order they are
 *      declared in the class, and any instance initializers are executed.
 *      This occurs before the constructor’s own code is executed.
 * 3. Constructor Execution: Finally, the constructor of the class itself is executed.
 */
public class Val {

    // initialized when constructor is called, but before the constructor execution itself.
    private final OffsetDateTime odt = OffsetDateTime.now();

    // initialized during the constructor execution.
    private final String id;

    public Val(String id) {
        this.id = id;
    }


}
