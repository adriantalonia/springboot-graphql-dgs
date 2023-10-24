package com.gqlclient.graphqlclient.client;

import com.apollographql.apollo3.ApolloClient;
import com.apollographql.apollo3.api.Optional;
import com.apollographql.apollo3.rx3.Rx3Apollo;
import com.gqlclient.graphqlclient.BooksByReleasedQuery;
import com.gqlclient.graphqlclient.BooksQuery;

import java.util.List;
import java.util.Objects;

public class GraphqlBookClient {

    private final ApolloClient apolloClient;

    public GraphqlBookClient(String serverEndPoint) {
        this.apolloClient = new ApolloClient.Builder().serverUrl(serverEndPoint).build();
    }

    public List<BooksQuery.Book> allBooks() {
        var query = apolloClient.query(new BooksQuery());
        var queryResponse = Rx3Apollo.single(query);

        return Objects.requireNonNull(queryResponse.blockingGet().data).books;
    }

    public List<BooksByReleasedQuery.BooksByReleased> booksByReleased(
            int releaseYear,
            boolean releasePrintedEdition
    ) {
        var query = apolloClient.query(
                new BooksByReleasedQuery(
                        Optional.present(releaseYear),
                        Optional.present(releasePrintedEdition)
                )
        );

        var queryResponse = Rx3Apollo.single(query);

        return Objects.requireNonNull(queryResponse.blockingGet().data).booksByReleased;
    }

}
