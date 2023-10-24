package com.gqlclient.graphqlclient;

import com.gqlclient.graphqlclient.client.GraphqlHelloClient;
import com.gqlclient.graphqlclient.type.HelloInput;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphqlHelloClientTest {

    private final String SERVER_ENDPOINT = "http://localhost:8080/graphql";
    private final GraphqlHelloClient client = new GraphqlHelloClient(SERVER_ENDPOINT);

    @Test
    void testAddHello() {
        var newHello = HelloInput.builder().text("New Hello")
                .number(ThreadLocalRandom.current().nextInt()).build();

        var mutationResult = client.addHello(newHello);
        assertTrue(mutationResult > 0);
    }

    @Test
    void testReplaceHelloText() {
        var replacementHello = HelloInput
                .builder()
                .text("Replacement Hello")
                .number(195)
                .build();

        var mutationResult = client.replaceHelloText(replacementHello);
        assertNotNull(mutationResult);
    }

    @Test
    void testDeleteHello() {
        var mutationResult = client.deleteHello(4995);
        assertTrue(mutationResult > 0);
    }

}
