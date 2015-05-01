package com.glacier.earthquake.monitor.server.crawler.core;

import org.jsoup.nodes.Document;

/**
 * Created by glacier on 15-5-1.
 */
public class PageProcessor {

    private Document document;

    public PageProcessor(Document document) {
        this.document = document;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
