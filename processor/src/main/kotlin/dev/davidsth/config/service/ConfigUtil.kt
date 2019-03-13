package dev.davidsth.config.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

object ConfigUtils {
    private val profileType = System.getProperty("profiles.active", "dev")

    fun getResourcesPath(): String {
        return System.getProperty("user.dir") + "/src/main/resources/"
    }

    fun getFilePath(): Path? {

        val configFileName = when (profileType) {
            "prod" -> "application-prod.yml"
            else -> "application-default.yml"
        }

        // Get the first file found
        if (File(getResourcesPath()).listFiles() == null) return null

        val filePath = File(getResourcesPath()).listFiles { _, name ->
            name == configFileName
        }.first()

        return filePath.toPath()
    }

    @JvmStatic
    fun load(): HashMap<*, *> {
        val path = getFilePath()

        val mapper = ObjectMapper(YAMLFactory()) // Enable YAML parsing
        mapper.registerModule(KotlinModule()) // Enable Kotlin support
        return Files.newBufferedReader(path).use {
            mapper.readValue(it)
        }
    }
}