package dev.davidsth.config.service

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import dev.davidsth.config.annotation.Config
import dev.davidsth.config.processor.AnnotationProcessor
import java.io.File
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.tools.Diagnostic

class AnnotationProcessorService(
        private val processingEnv: ProcessingEnvironment?) {

    fun process(roundEnv: RoundEnvironment): Boolean {
        val elements = roundEnv.getElementsAnnotatedWith(Config::class.java)

        if (elements.isEmpty()) return false

        val annotatedClass = elements.first()
        val pack = processingEnv!!.elementUtils.getPackageOf(annotatedClass).toString()
        val className = annotatedClass.simpleName.toString()
        generateClass(className, pack)
        return true
    }

    private fun generateClass(className: String, pack: String) {
        val fileName = "${className}_Config"

        var buildClass = TypeSpec.objectBuilder(fileName)

        val configs = ConfigUtils.load()

        configs.forEach { (key, value) ->
            buildClass = buildClass.addProperty(
                    PropertySpec.builder("$key", String::class).initializer("%S", "$value").build()
            )
        }

        val kaptKotlinGeneratedDir = File(processingEnv!!.options[AnnotationProcessor.KAPT_KOTLIN_GENERATED_OPTION_NAME])
        kaptKotlinGeneratedDir.deleteRecursively()
        kaptKotlinGeneratedDir.mkdir()

        FileSpec.builder(pack, fileName).addType(buildClass.build()).build().writeTo(kaptKotlinGeneratedDir)
    }

    fun error(msg: String, vararg args: Any) {
        this.processingEnv!!.messager.printMessage(
                Diagnostic.Kind.ERROR,
                String.format(msg, *args)
        )
    }

}
