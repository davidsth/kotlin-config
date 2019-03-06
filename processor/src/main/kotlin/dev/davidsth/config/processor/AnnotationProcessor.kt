package dev.davidsth.config.processor

import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.TypeSpec
import dev.davidsth.config.annotation.Config
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.TypeElement
import kotlin.reflect.full.companionObjectInstance

@Suppress("unused")
@AutoService(Processor::class)
class AnnotationProcessor: AbstractProcessor() {

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        println("getSupportedAnnotationTypes")
        return mutableSetOf(Config::class.java.canonicalName)
    }

    override fun process(annotations: MutableSet<out TypeElement>, roundEnv: RoundEnvironment): Boolean {

        println("AnnotationProcessor.process")
        val elements = roundEnv.getElementsAnnotatedWith(Config::class.java)

        println(elements)


        val element = elements.first()
        println("class name: ${element.simpleName}")

        val packageName = processingEnv.elementUtils.getPackageOf(element).toString()

        println("fields: ${element.javaClass.fields}")

        val singletonInstance = element::class.objectInstance

        return false
    }

}