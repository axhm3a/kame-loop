/**
 *
 *   functional game loop
 *   in Kotlin
 *
 */
fun createGameLoop(callbacks: List<(Long) -> Unit>): (Long) -> Long {

    val gameloop: (Long) -> Long = { delta:Long ->

        val start = System.currentTimeMillis()

        callbacks.forEach { it(delta) }

        val end = System.currentTimeMillis()

        end - start
    }

    return gameloop
}

fun main(args: Array<String>) {

    val callbacks: List<(Long) -> Unit> = listOf(
        { l: Long -> println(l) },
        { Thread.sleep(500) }
    )

    val l = createGameLoop(callbacks);

    generateSequence{ Unit }.fold(0L){ n, _ -> l(n) }
}