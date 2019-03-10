package dev.davidsth.config.processor

import dev.davidsth.config.service.AnnotationProcessorService
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.TypeElement

object AnnotationProcessorTest : Spek({
    val processingEnv = mockk<ProcessingEnvironment>()
    val service = AnnotationProcessorService(processingEnv)
    val roundEnv = spyk<RoundEnvironment>()
    val annotations = mockk<MutableSet<out TypeElement>>()

    describe("process") {
        it("should let service handle processing") {
            val processor = AnnotationProcessor()

            processor.process(annotations, roundEnv)

            verify(exactly = 1) { service.process(roundEnv, processingEnv) }
        }
    }
})
