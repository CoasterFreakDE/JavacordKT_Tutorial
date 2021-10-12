package cloud.melion.tutorialbot.interfaces

import org.javacord.api.event.interaction.SlashCommandCreateEvent

interface ISlashCommand {
    fun perform(slashCommandCreateEvent: SlashCommandCreateEvent)
}