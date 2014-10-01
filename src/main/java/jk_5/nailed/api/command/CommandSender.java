package jk_5.nailed.api.command;

import jk_5.nailed.api.chat.BaseComponent;

/**
 * No description given
 *
 * @author jk-5
 */
public interface CommandSender {

    String getName();

    void sendMessage(BaseComponent... component);

    BaseComponent getDescriptionComponent();
}
