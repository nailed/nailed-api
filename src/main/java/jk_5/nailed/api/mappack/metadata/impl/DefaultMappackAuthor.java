package jk_5.nailed.api.mappack.metadata.impl;

import javax.annotation.Nonnull;

import jk_5.nailed.api.mappack.metadata.MappackAuthor;
import jk_5.nailed.api.util.Checks;

/**
 * No description given
 *
 * @author jk-5
 */
public class DefaultMappackAuthor implements MappackAuthor {

    private final String name;
    private final String role;

    public DefaultMappackAuthor(@Nonnull String name, @Nonnull String role) {
        Checks.notNull(name, "name");
        Checks.notNull(role, "role");

        this.name = name;
        this.role = role;
    }

    @Nonnull
    @Override
    public String name() {
        return name;
    }

    @Nonnull
    @Override
    public String role() {
        return role;
    }
}
