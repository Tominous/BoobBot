package bot.boobbot.utils

import bot.boobbot.BoobBot
import net.dv8tion.jda.api.JDAInfo
import java.text.MessageFormat


/**
 * Created by Tom on 10/5/2017.
 */

object Formats {
    const val BOOT_BANNER = "(.Y.)"
    const val LEWD_EMOTE = "<:Ass:520247323343978509>"
    const val DISCORD_EMOTE = "<:discord:486939267365470210>"
    const val BOT_EMOTE = "<:bot:486939322151338005>"
    const val LINK_EMOTE = "\uD83D\uDD17"
    const val MAGIC_EMOTE = "✨"
    const val INFO_EMOTE = "<:info:486945488080338944>"
    const val PATREON_EMOTE = "<:p_:475801484282429450>"

    //const val PAYPAL_EMOTE = "<:paypal:486945242369490945>"
    val LING_MSG = String.format(
        "\n%s **Join the Community**: [https://discord.boob.bot](https://discord.boob.bot)"
        + "\n%s **Need Support?**: [https://discord.gg/wFfFRb3Qbr](https://discord.gg/wFfFRb3Qbr)"
                + "\n%s **Add the Bot**: [https://bot.boob.bot](https://bot.boob.bot)"
                + "\n%s **Visit the Website**: [https://boob.bot](https://boob.bot)"
                //+ "\n%s **Paypal**: [https://paypal.boob.bot](https://paypal.boob.bot)"
                + "\n%s **Unlock cool shit**: [https://www.patreon.com/OfficialBoobBot](https://www.patreon.com/OfficialBoobBot)",
        DISCORD_EMOTE,
        "\uD83C\uDD98",
        BOT_EMOTE,
        LINK_EMOTE,
        PATREON_EMOTE
    )

    val tag = arrayOf(
        "Amateur",
        "Anal Masturbation",
        "Anal Sex",
        "Animated",
        "Asian",
        "Bareback",
        "Bathroom",
        "BBW",
        "Behind the Scenes",
        "Big Ass",
        "Big Cock",
        "Big Tits",
        "Bikini",
        "Bisexual",
        "Black-haired",
        "Blonde",
        "Blowjob",
        "Bondage",
        "Boots",
        "Brunette",
        "Bukkake",
        "Car",
        "Cartoon",
        "Caucasian",
        "Celebrity",
        "CFNM",
        "Chubby",
        "Compilation",
        "Couple",
        "Cream Pie",
        "Cum Shot",
        "Cum Swap",
        "Deepthroat",
        "Domination",
        "Double Penetration",
        "Ebony",
        "Facial",
        "Fat",
        "Female-Friendly",
        "Femdom",
        "Fetish",
        "Fisting",
        "Footjob",
        "Funny",
        "Gagging",
        "Gangbang",
        "Gay",
        "Gay Couple",
        "Gay Group Sex",
        "German",
        "Glamour",
        "Glasses",
        "Glory Hole",
        "Granny",
        "Group Sex",
        "Gym",
        "Hairy",
        "Handjob",
        "Hentai",
        "High Heels",
        "Hospital",
        "Indian",
        "Interracial",
        "Japanese",
        "Kissing",
        "Latex",
        "Latin",
        "Lesbian",
        "Licking Vagina",
        "Lingerie",
        "Maid",
        "Massage",
        "Masturbation",
        "Mature",
        "Midget",
        "MILF",
        "Muscular",
        "Nurse",
        "Office",
        "Oral Sex",
        "Oriental",
        "Outdoor",
        "Pantyhose",
        "Party",
        "Peeing",
        "Piercings",
        "Police",
        "Pool",
        "Pornstar",
        "Position 69",
        "POV",
        "Pregnant",
        "Public",
        "Redhead",
        "Rimming",
        "Romantic",
        "Russian",
        "School",
        "Secretary",
        "Shaved",
        "Shemale",
        "Skinny",
        "Small Tits",
        "Solo Gay",
        "Solo Girl",
        "Solo Male",
        "Spanking",
        "Spectacular",
        "Spycam",
        "Squirting",
        "Stockings",
        "Strap-on",
        "Striptease",
        "Swallow",
        "Tattoos",
        "Teen",
        "Threesome",
        "Titfuck",
        "Toilet",
        "Toys",
        "Tribbing",
        "Uniform",
        "Vaginal Masturbation",
        "Vaginal Sex",
        "Vintage",
        "Wanking",
        "Webcam",
        "Young & Old"
    )

    val readyFormat by lazy {
        val shardManager = BoobBot.shardManager
        val jda = shardManager.shards[0]

        String.format(
            "Logging in %s\n"
                    + "Oauth link: %s\n"
                    + "JDA Version: %s\n"
                    + "Docs halp: http://home.dv8tion.net:8080/job/JDA/javadoc/\n"
                    + "Logged in as: %s (%s)\n"
                    + "Shards: %d\n",
            BOOT_BANNER,
            BoobBot.inviteUrl,
            JDAInfo.VERSION,
            jda.selfUser.name,
            jda.selfUser.id,
            shardManager.shardsTotal
        )
    }

    fun error(text: String): String {
        return MessageFormat.format("\uD83D\uDEAB {0}", text)
    }

    fun info(text: String): String {
        return MessageFormat.format("{1} {0}", text, INFO_EMOTE)
    }

    fun monospaced(items: List<String>): String {
        return items.joinToString("`, `", prefix = "`", postfix = "`")
    }

    fun progressPercentage(remain: Int, total: Int): String {
        require(remain <= total)
        val maxBareSize = 10 // 10unit for 100%
        val remainPercent = 100 * remain / total / maxBareSize
        val defaultChar = '◯'
        val icon = "⬤"
        val bare = String(CharArray(maxBareSize)).replace('\u0000', defaultChar) + "]"
        val bareDone = StringBuilder()
        bareDone.append("[")
        for (i in 0 until remainPercent) {
            bareDone.append(icon)
        }
        val bareRemain = bare.substring(remainPercent, bare.length)
        return "\r" + bareDone + bareRemain + " " + remainPercent * 10 + "%"
    }


    fun countryCodeToEmote(countryCode: String): String {
        val code = if (countryCode.equals("uk", true)) "GB" else countryCode.toUpperCase()
        val offset = 127397

        return buildString {
            for (e in code) {
                appendCodePoint(e.toInt() + offset)
            }
        }
    }

    fun getRemainingCoolDown(x: Long): String {
        val y = 60 * 60 * 1000
        val h = x / y
        val m = (x - (h * y)) / (y / 60)
        val s = (x - (h * y) - (m * (y / 60))) / 1000
        var r = ""
        if (h > 0) r += "$h Hours "
        if (m > 0) r += "$m Minutes "
        if (s > 0) r += "$s Seconds "
        return r

    }
}
