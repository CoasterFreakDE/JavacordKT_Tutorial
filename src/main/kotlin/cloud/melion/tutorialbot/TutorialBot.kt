package cloud.melion.tutorialbot

import cloud.melion.tutorialbot.annotations.LoadSlashCommand
import cloud.melion.tutorialbot.interfaces.HasOptions
import cloud.melion.tutorialbot.interfaces.ISlashCommand
import org.javacord.api.DiscordApi
import org.javacord.api.interaction.SlashCommandBuilder
import org.javacord.api.util.logging.ExceptionLogger
import org.reflections8.Reflections

class TutorialBot(val api: DiscordApi) {

    private val slashCommands = mutableMapOf<String, ISlashCommand>()

    init {
        registerCommands()
    }

    private fun registerCommands() {
        val commands = mutableListOf<SlashCommandBuilder>()

        val reflections = Reflections("cloud.melion.tutorialbot.commands")
        for(clazz in reflections.getTypesAnnotatedWith(LoadSlashCommand::class.java)) {
            try {
                val annotation = clazz.getAnnotation(LoadSlashCommand::class.java)
                val name = annotation.name
                val description = annotation.description
                val options = annotation.options

                val builder = SlashCommandBuilder()
                    .setName(name)
                    .setDescription(description)

                val slashCommand = clazz.getConstructor().newInstance() as ISlashCommand

                if (options) {
                    val slashOptions = (slashCommand as HasOptions).getOptions()
                    for (option in slashOptions) {
                        builder.addOption(option)
                    }
                }

                slashCommands[name] = slashCommand
                println("Command $name geladen.")
                commands.add(builder)
            } catch (exception: InstantiationException) {
                exception.printStackTrace()
            } catch (exception: IllegalAccessException) {
                exception.printStackTrace()
            }
        }

        api.bulkOverwriteGlobalSlashCommands(commands).thenAccept {
            slashes -> println("${slashes.size} Commands geladen.")
        }.exceptionally(ExceptionLogger.get())
    }
}