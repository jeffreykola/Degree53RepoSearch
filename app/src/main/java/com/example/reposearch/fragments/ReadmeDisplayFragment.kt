package com.example.reposearch.fragments

import android.os.Bundle
import android.text.Spanned
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.reposearch.R
import io.noties.markwon.Markwon
import org.commonmark.node.Node


class ReadmeDisplayFragment : Fragment() {

    lateinit var markwon: Markwon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        markwon = Markwon.create(context!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_readme_display, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val readmeInfo: String = getEncodedContent()
        val decodedReadmeInfo:ByteArray = Base64.decode(readmeInfo, Base64.DEFAULT)

        val styledMarkdown: Spanned = getMarkdownContent(String(decodedReadmeInfo))

        markwon.setParsedMarkdown(view!!.findViewById(R.id.displayMarkdownContent), styledMarkdown);
    }

    fun getMarkdownContent(decodedMarkdown: String): Spanned {
        var node: Node = markwon.parse(decodedMarkdown)
        return markwon.render(node)
    }




    private fun getEncodedContent(): String{
       val bundle = this.arguments!!
       return bundle.getString("base64content")!!
    }


}