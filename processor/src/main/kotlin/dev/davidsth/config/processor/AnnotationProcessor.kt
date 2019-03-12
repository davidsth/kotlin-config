package dev.davidsth.config.processor

import dev.davidsth.config.annotation.Config
import dev.davidsth.config.service.AnnotationProcessorService
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedOptions
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement


@SupportedOptions(AnnotationProcessor.KAPT_KOTLIN_GENERATED_OPTION_NAME)
class AnnotationProcessor : AbstractProcessor() {

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(Config::class.java.canonicalName)
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    override fun process(annotations: MutableSet<out TypeElement>, roundEnv: RoundEnvironment): Boolean {
        val annotationProcessorService = AnnotationProcessorService(processingEnv)
        val elements = roundEnv.getElementsAnnotatedWith(Config::class.java)

        if (elements.isEmpty()) return false

        try {
            annotationProcessorService.process(roundEnv)
        }
        catch(e: Exception) {
            annotationProcessorService.error("AnnotationProcessorService: $e")
        }
        return false
    }
}