package bot.boobbot.models

import bot.boobbot.flight.Command
import bot.boobbot.flight.Context
import net.dv8tion.jda.api.entities.Member

abstract class ModCommand : Command {

    fun resolveTargetAndReason(ctx: Context): Pair<Member?, String?> {
        val userArgument = ctx.args.take(1)
        val reasonArgument = ctx.args.drop(1)

        if (userArgument.isEmpty()) {
            return Pair(null, null)
        }

        val target = resolveTarget(ctx, userArgument[0])
            ?: return Pair(null, null)
        val reason = if (reasonArgument.isEmpty()) null else reasonArgument.joinToString(" ")

        return Pair(target, reason)
    }

    fun resolveTarget(ctx: Context, arg: String): Member? {
        if (ctx.guild == null) {
            throw IllegalStateException("Cannot resolve target when guild is null!")
        }

        return if (arg.length > 5 && arg[arg.length - 5] == '#') {
            ctx.guild.getMemberByTag(arg)
        } else if (arg.length > 17 && arg.toLongOrNull() != null) {
            ctx.guild.getMemberById(arg)
        } else {
            ctx.guild.getMembersByName(arg, false).firstOrNull()
        }
    }

}
