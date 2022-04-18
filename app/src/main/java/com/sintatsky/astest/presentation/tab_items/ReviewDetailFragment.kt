package com.sintatsky.astest.presentation.tab_items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.sintatsky.astest.R


class ReviewDetailFragment : Fragment() {
    private lateinit var link: String
    private lateinit var viewBrowser: View
    private lateinit var webView: WebView
    private var webViewBundle: Bundle? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        link = arguments?.getString(LINK_EXTRA).toString()
        viewBrowser = inflater.inflate(R.layout.fragment_review_detail, container, false)
        webView = viewBrowser.findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true
        if (webViewBundle == null) {
            webView.loadUrl(link)
        } else {
            webView.restoreState(webViewBundle!!)
        }
        return viewBrowser
    }

    companion object {
        private const val LINK_EXTRA = "LINK_EXTRA"
        fun newInstance(link: String?) =
            ReviewDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(LINK_EXTRA, link)
                }
            }
    }
}