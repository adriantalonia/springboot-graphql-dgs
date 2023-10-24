package com.gqlclient.graphqlclient;

import com.gqlclient.graphqlclient.client.GraphqlBookClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GraphqlBookClientTests {

    private final String SERVER_ENDPOINT = "http://localhost:8080/graphql";
    private final GraphqlBookClient client = new GraphqlBookClient(SERVER_ENDPOINT);

    @Test
    void testAllBooks() {
        var books = client.allBooks();

        assertNotNull(books);
        assertFalse(books.isEmpty());
    }

    @Test
    void testBooksByReleased() {
        var books = client.booksByReleased(2020, true);

        assertNotNull(books);
        assertFalse(books.isEmpty());
    }

}
