package jk_5.nailed.api.event;

import jk_5.nailed.api.command.CommandCallable;
import jk_5.nailed.api.command.fluent.DispatcherNode;

/**
 * Use this event if you want to register commands to be available ingame (using / commands in chat)
 * There are 2 ways of doing this. You can register a command class for using the {@link jk_5.nailed.api.command.Command} annotation.
 * The second way is registering a {@link jk_5.nailed.api.command.CommandCallable} with the aliases, if you want a more controlled way of handling the commands.
 *
 * @author jk-5
 */
public class RegisterCommandsEvent extends PlatformEvent {

    private final DispatcherNode dispatcherNode;

    public RegisterCommandsEvent(DispatcherNode dispatcherNode) {
        this.dispatcherNode = dispatcherNode;
    }

    public void registerCommandClass(Object obj){
        dispatcherNode.registerMethods(obj);
    }

    public void registerCallable(CommandCallable callable, String... aliases){
        dispatcherNode.register(callable, aliases);
    }

    public SubcommandRegistrar subcommand(String... aliases){
        return new SubcommandRegistrar(dispatcherNode.group(aliases));
    }

    public static final class SubcommandRegistrar {

        private final DispatcherNode dispatcherNode;

        private SubcommandRegistrar(DispatcherNode dispatcherNode) {
            this.dispatcherNode = dispatcherNode;
        }

        public void registerCommandClass(Object obj){
            dispatcherNode.registerMethods(obj);
        }

        public void registerCallable(CommandCallable callable, String... aliases){
            dispatcherNode.register(callable, aliases);
        }

        public SubcommandRegistrar subcommand(String... aliases){
            return new SubcommandRegistrar(dispatcherNode.group(aliases));
        }
    }
}
