package bot.boobbot.commands.`fun`

import bot.boobbot.flight.Category
import bot.boobbot.flight.CommandProperties
import bot.boobbot.models.MemeAvatarCommand

@CommandProperties(description = "Wanted.", nsfw = false, category = Category.FUN, guildOnly = true)
class Wanted : MemeAvatarCommand("wanted")