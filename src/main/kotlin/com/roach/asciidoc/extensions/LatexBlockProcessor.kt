package com.roach.asciidoc.extensions

import net.sourceforge.plantuml.FileFormat
import net.sourceforge.plantuml.FileFormatOption
import net.sourceforge.plantuml.SourceStringReader
import org.asciidoctor.ast.ContentModel
import org.asciidoctor.ast.StructuralNode
import org.asciidoctor.extension.BlockProcessor
import org.asciidoctor.extension.Contexts
import org.asciidoctor.extension.Name
import org.asciidoctor.extension.Reader
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.nio.charset.Charset


@Name("latex")
@Contexts(Contexts.LISTING)
@ContentModel(ContentModel.COMPOUND)
class LatexBlockProcessor :   BlockProcessor(){
    override fun process(parent: StructuralNode, reader: Reader, attributes: MutableMap<String, Any>): Any? {
        val content = reader.read()

        val sourceReader = SourceStringReader(content)
        val filename = attributes.getOrDefault("2", "${System.currentTimeMillis()}_unk")

        val parentf = File("${reader.dir}/images/")
        if(!parentf.exists()) {
            parentf.mkdirs()
        }
        val f = File("${reader.dir}/images/$filename.svg")
        if(f.exists()) {
            f.delete()
        }
        val os = FileOutputStream(f)

        sourceReader.outputImage(os, FileFormatOption(FileFormat.SVG))

        //val desc = reader.generateImage(os, FileFormatOption(FileFormat.SVG))
        os.close()

        val contentLines = mutableListOf<String>()
        contentLines.add(".$filename");
        contentLines.add("image::images/$filename.svg[Interactive,opts=interactive]");

        parseContent(parent, contentLines)
       // val svg = String(os.toByteArray(), Charset.forName("UTF-8"))
        return null
    }
}