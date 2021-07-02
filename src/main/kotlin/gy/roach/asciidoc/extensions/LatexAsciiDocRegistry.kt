package gy.roach.asciidoc.extensions

import org.asciidoctor.Asciidoctor
import org.asciidoctor.jruby.extension.spi.ExtensionRegistry

class LatexAsciiDocRegistry : ExtensionRegistry {
    override fun register(asciidoctor: Asciidoctor) {
        val reg = asciidoctor.javaExtensionRegistry()
        reg.block(LatexBlockProcessor::class.java)
    }
}