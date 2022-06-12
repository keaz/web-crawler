package com.kasun.process;

import com.kasun.dto.HTMLObject;
import com.kasun.exception.SearchException;
import lombok.extern.log4j.Log4j2;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.ByteBuffersDirectory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Log4j2
@Service
public record SearcherLuceneImpl(StandardAnalyzer analyzer) implements Searcher {

    @Override
    public Optional<String> search(HTMLObject html, String searchText) {

        var memoryIndex = new ByteBuffersDirectory();
        var indexWriterConfig = new IndexWriterConfig(analyzer);

        try (var writer = new IndexWriter(memoryIndex, indexWriterConfig)) {

            Document document = new Document();
            document.add(new TextField("title", html.title(), Field.Store.NO));
            document.add(new TextField("body", html.body(), Field.Store.YES));
            document.add(new TextField("url", html.url(), Field.Store.YES));
            writer.addDocument(document);
        } catch (IOException e) {
            throw new SearchException("Failed to write Document",e);
        }

        try {
            return searchIndex(memoryIndex, "body", searchText);
        } catch (ParseException | IOException e) {
            throw new SearchException("Failed to search on document on",e);
        }

    }

    private Optional<String> searchIndex(ByteBuffersDirectory memoryIndex, String inField, String queryString) throws ParseException, IOException {
        var query = new QueryParser(inField, analyzer)
                .parse(queryString);

        var indexReader = DirectoryReader.open(memoryIndex);
        var searcher = new IndexSearcher(indexReader);
        var topDocs = searcher.search(query, 10);

        if (topDocs.scoreDocs.length == 0) {
            return Optional.empty();
        }

        return Optional.of(searcher.doc(topDocs.scoreDocs[0].doc).get("url"));
    }

}
