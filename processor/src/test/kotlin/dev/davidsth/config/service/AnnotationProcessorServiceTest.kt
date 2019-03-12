package dev.davidsth.config.service

import dev.davidsth.config.annotation.Config
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import net.oddpoet.expect.expect
import net.oddpoet.expect.extension.equal
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import org.spekframework.spek2.style.specification.xdescribe
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment

object AnnotationProcessorServiceTest : Spek({
    xdescribe("process") {
        val processingEnv = mockk<ProcessingEnvironment>()
        val service = spyk(AnnotationProcessorService(processingEnv))
        val roundEnv = mockk<RoundEnvironment>()
        context("when no annotations exist") {
            it("should return false") {
                every { roundEnv.getElementsAnnotatedWith(Config::class.java) } returns setOf()

                val result = service.process(roundEnv)

                expect(result).to.equal(false)
            }
        }

        context("when annotations exist") {
            it("calls generateClass") {
                every { roundEnv.getElementsAnnotatedWith(Config::class.java) } returns setOf()

                val result = service.process(roundEnv)

                expect(result).to.equal(false)
            }
        }
    }
})