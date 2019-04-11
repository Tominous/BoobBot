package bot.boobbot.models

import bot.boobbot.BoobBot
import bot.boobbot.flight.AsyncCommand
import bot.boobbot.flight.Context
import bot.boobbot.misc.Formats
import bot.boobbot.misc.createHeaders
import bot.boobbot.misc.json
import kotlinx.coroutines.future.await

abstract class SendCommand(private val category: String, private val endpoint: String) : AsyncCommand {

    private val headers = createHeaders(Pair("Key", BoobBot.config.bbApiKey))

    override suspend fun executeAsync(ctx: Context) {
        val user = ctx.message.mentionedUsers.firstOrNull() ?: ctx.author

        if (user.idLong == BoobBot.selfId) {
            return ctx.send("Don't you fucking touch me whore, i will end you.")
        }

        if (user.isBot) {
            return ctx.send("Bots can't appreciate $category, whore.")
        }

        val isUserReceivingNudes = BoobBot.database.getCanUserReceiveNudes(user.id)

        if (!isUserReceivingNudes) {
            return ctx.send("wtf, **${user.asTag}** opted out of receiving nudes. What a whore")
        }

        val url = BoobBot.requestUtil.get("https://boob.bot/api/v2/img/$endpoint", headers)
            .await()?.json()?.getString("url")
            ?: return ctx.send("wtf, api down?")

        ctx.dmUserAsync(user, "${Formats.LEWD_EMOTE} $url")
            ?: return ctx.send("wtf, I can't DM **${user.asTag}**?")

        ctx.send("Good job ${ctx.author.asMention}")
    }

}
