package cloud.melion.tutorialbot.annotations

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class LoadSlashCommand(val name: String, val description: String, val options: Boolean = false)
