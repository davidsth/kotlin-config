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
        private val processingEnv: ProcessingEnvironment) {

    fun process(roundEnv: RoundEnvironment, processingEnv: ProcessingEnvironment): Boolean {
        val elements = roundEnv.getElementsAnnotatedWith(Config::class.java)

        if (elements.isEmpty()) return false

        val annotatedClass = elements.first()
        val pack = processingEnv.elementUtils.getPackageOf(annotatedClass).toString()
        val className = annotatedClass.simpleName.toString()
        generateClass(className, pack)
        return true
    }

    private fun generateClass(className: String, pack: String) {
        val fileName = "Generated_$className"

        val buildClass = TypeSpec.classBuilder(fileName)
                .addProperty(PropertySpec.builder("name", String::class)
                        .initializer("%S", "david")
                        .build()).build()

        val kaptKotlinGeneratedDir = File(processingEnv.options[AnnotationProcessor.KAPT_KOTLIN_GENERATED_OPTION_NAME])
        kaptKotlinGeneratedDir.mkdir()

        FileSpec.builder(pack, className).addType(buildClass).build().writeTo(kaptKotlinGeneratedDir)
    }

    fun error(msg: String, vararg args: Any) {
        this.processingEnv.messager.printMessage(
                Diagnostic.Kind.ERROR,
                String.format(msg, *args)
        )
    }

}
