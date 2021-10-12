package cloud.melion.tutorialbot.interfaces

import org.javacord.api.interaction.SlashCommandOption

interface HasOptions : ISlashCommand {
    fun getOptions(): List<SlashCommandOption>
}