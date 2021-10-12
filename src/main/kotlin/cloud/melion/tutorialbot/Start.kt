package cloud.melion.tutorialbot

import org.javacord.api.AccountType
import org.javacord.api.DiscordApi
import org.javacord.api.DiscordApiBuilder

fun main(args: Array<String>) {
    println("Bot startet.")

    val api: DiscordApi = DiscordApiBuilder().apply {
        setToken("")
        accountType = AccountType.BOT
        setAllIntents()
    }.login().join()

    TutorialBot(api)
}