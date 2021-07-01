package com.roach.asciidoc.extensions

import org.apache.commons.io.IOUtils
import org.asciidoctor.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File
import java.io.FileInputStream
import java.util.HashMap

internal class LatexBlockProcessorTest {
    private final var asciidoctor: Asciidoctor = Asciidoctor.Factory.create()

    init {
        asciidoctor.requireLibrary("asciidoctor-diagram")
    }
    @Test
    fun basicTest(){
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
                        .tableOfContents(Placement.TOP)
                )
                .safe(SafeMode.UNSAFE)
                .build())
        //asciidoctor.convertDirectory(AsciiDocDirectoryWalker(d).scan(), OptionsBuilder.options().backend("pdf").get())

        //val html = IOUtils.toString(FileInputStream(target), "UTF-8")
        //return ResponseEntity.ok(html)
    }
}