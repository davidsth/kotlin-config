package dev.davidsth.config.processor

import dev.davidsth.config.service.AnnotationProcessorService
import io.mockk.every
import io.mockk.spyk
import io.mockk.verify
import net.oddpoet.expect.expect
import net.oddpoet.expect.extension.equal
import org.mockito.ArgumentMatchers.any
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import org.spekframework.spek2.style.specification.xdescribe
import javax.lang.model.element.TypeElement

object AnnotationProcessorTest : Spek({

    describe("annotationprocessor") {
        it("runs") {
            expect(1).to.equal(1)
        }
    }

    xdescribe("process") {
        it("should let service handle processing") {
            val service = spyk(AnnotationProcessorService(any()))
            val annotations = spyk<MutableSet<out TypeElement>>()
            every { service.process(any()) } returns any()
            val processor = spyk<AnnotationProcessor>()

            processor.process(annotations, any())

            verify(exactly = 1) { service.process(any()) }
        }
    }
})
