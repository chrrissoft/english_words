package com.chrrissoft.inglishwords.domian.gameplay.keyboard


data class Coordinates(
    val x: Float, val y: Float
) {
    companion object {

        private val onRowFinish = { n: Int, n1: Int, block: () -> Unit ->
            if (n == n1) {
                block()
                true
            } else false
        }

        object EnglishStructure {
            private val alphabet = listOf(
                "q", "w", "e", "r", "t", "y", "u", "i", "o", "p",
                "a", "s", "d", "f", "g", "h", "j", "k", "l",
                "z", "x", "c", "v", "b", "n", "m",
            )

            val coordinates = buildMap {
                var x = 0f
                var y = 0f

                alphabet.forEachIndexed { i, s ->

                    this[s] = Coordinates(x, y)

                    val onFirst = onRowFinish(i, 9) {
                        y += .25f
                        x = .05f
                    }

                    if (onFirst) return@forEachIndexed

                    val second = onRowFinish(i, 18) {
                        x = .15f
                        y += .25f
                    }

                    if (second) return@forEachIndexed

                    x += .1f
                }
            }
        }

        object SpanishStructure {
            private val alphabet = listOf(
                "q", "w", "e", "r", "t", "y", "u", "i", "o", "p",
                "a", "s", "d", "f", "g", "h", "j", "k", "l", "ñ",
                "z", "x", "c", "v", "b", "n", "m",
            )

            val coordinates = buildMap {
                var x = 0f
                var y = 0f

                alphabet.forEachIndexed { i, s ->

                    this[s] = Coordinates(x, y)

                    val onFirst = onRowFinish(i, 9) {
                        y += .25f
                        x = .0f
                    }

                    if (onFirst) return@forEachIndexed

                    val second = onRowFinish(i, 19) {
                        x = .15f
                        y += .25f
                    }

                    if (second) return@forEachIndexed

                    x += .1f
                }
            }
        }

        val magicCoordinates = Coordinates(0f, 0.5f)
        val deleteCoordinates = Coordinates(.85f, 0.5f)
        val spacerCoordinates = Coordinates(.0f, 0.75f)

    }
}
