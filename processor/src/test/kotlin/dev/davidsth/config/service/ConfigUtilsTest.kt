package dev.davidsth.config.service

import io.mockk.mockkObject
import net.oddpoet.expect.expect
import net.oddpoet.expect.extension.equal
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


object ConfigUtilsTest: Spek({
    describe("load") {
        it("returns a key value map from yml") {
            mockkObject(ConfigUtils)

            val result = ConfigUtils.load()

            expect(result["name"]).to.equal("david")
            expect(result["language"]).to.equal("kotlin")
            expect(result["id"]).to.equal(1)
        }
    }

})