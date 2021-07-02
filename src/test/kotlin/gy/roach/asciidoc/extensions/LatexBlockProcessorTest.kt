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
        val src = File("src/main/asciidoc/basic.adoc")
        val target = File("example/basic.html")
        convert(src, target, "html5")
        assertTrue(target.exists())
    }

    @Test
    fun basicPdfTest() {
        val src = File("src/main/asciidoc/basic.adoc")
        val target = File("example/basic.pdf")
        convert(src, target, "pdf")
        assertTrue(target.exists())
    }

    private fun convert(src: File, target: File, backend: String) {
        val attributes = HashMap<String, Any>()
        attributes["backend"] = backend // (1)
        val opts = HashMap<String, Any>()
        opts["attributes"] = attributes // (2)
        opts["in_place"] = true
        asciidoctor.convertFile(
            src,
            Options.builder()
                .inPlace(true)
                .docType("book")
                .backend(backend)
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
    }
}