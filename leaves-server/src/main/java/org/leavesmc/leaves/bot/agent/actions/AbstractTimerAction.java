package org.leavesmc.leaves.bot.agent.actions;

import net.minecraft.server.level.ServerPlayer;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.leavesmc.leaves.bot.agent.AbstractBotAction;
import org.leavesmc.leaves.command.CommandArgument;
import org.leavesmc.leaves.command.CommandArgumentResult;
import org.leavesmc.leaves.command.CommandArgumentType;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public abstract class AbstractTimerAction<E extends AbstractTimerAction<E>> extends AbstractBotAction<E> {

    public AbstractTimerAction(String name, Supplier<E> creator) {
        this(name, CommandArgument.of(CommandArgumentType.INTEGER, CommandArgumentType.INTEGER, CommandArgumentType.INTEGER), creator);
    }

    public AbstractTimerAction(String name, CommandArgument argument, Supplier<E> creator) {
        super(name, argument, creator);
        this.setSuggestion(0, Pair.of(Collections.singletonList("0"), "[TickDelay]"));
        this.setSuggestion(1, Pair.of(Collections.singletonList("20"), "[TickInterval]"));
        this.setSuggestion(2, Pair.of(List.of("1", "-1"), "[DoNumber]"));
    }

    @Override
    public void loadCommand(@Nullable ServerPlayer player, @NotNull CommandArgumentResult result) {
        this.setInitialTickDelay(result.readInt(0)).setInitialTickInterval(result.readInt(20)).setInitialNumber(result.readInt(1));
    }
}
