package gy.roach.asciidoc.extensions

import org.asciidoctor.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.File

class LatexBlockProcessorTest {
    private var asciidoctor: Asciidoctor = Asciidoctor.Factory.create()

    init {
        asciidoctor.requireLibrary("asciidoctor-diagram")
    }

    @Test
    fun basicTest() {
        val attributes = HashMap<String, Any>()
        val dir = File("src/main/asciidoc/basic.adoc")
        val target = File("target/basic.html")
        attributes["backend"] = "html5" // (1)

        val opts = HashMap<String, Any>()
        opts["attributes"] = attributes // (2)
        opts["in_place"] = true

        asciidoctor.convertFile(
            dir,
            Options.builder()
                .inPlace(true)
                .docType("book")
                .backend("html5")
                .attributes(
                    Attributes.builder()
                        .sectionNumbers(true)
                        .copyCss(true)
                        .icons(Attributes.FONT_ICONS)
                        .sourceHighlighter("rouge")
                        .tableOfContents(true)
                        .tableOfContents(Placement.TOP).build()
                )
                .safe(SafeMode.UNSAFE)
                .toFile(target)
                .build()
        )
        assertTrue(target.exists())
    }
}