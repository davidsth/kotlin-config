package dev.davidsth.marvel

import dev.davidsth.config.annotation.Config

@Config
class MarvelUniverse {
    init {
        println("name: ${MarvelUniverse_Config.name}")
        println("language: ${MarvelUniverse_Config.language}")
        println("id: ${MarvelUniverse_Config.id}")
    }
}

fun main() {
    MarvelUniverse()
}


