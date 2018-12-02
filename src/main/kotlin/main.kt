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

    val callbacks = listOf<(Long) -> Unit>(
        {l: Long -> println(l)},
        {l: Long -> Thread.sleep(500)}
    )

    val l = createGameLoop(callbacks);

    generateSequence(0L){ it + 1 }.fold(0L){ n, _ -> l(n) }
}