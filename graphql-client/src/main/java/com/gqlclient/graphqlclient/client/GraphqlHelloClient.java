package com.gqlclient.graphqlclient.client;

import com.apollographql.apollo3.ApolloClient;
import com.apollographql.apollo3.rx3.Rx3Apollo;
import com.gqlclient.graphqlclient.AddHelloMutation;
import com.gqlclient.graphqlclient.DeleteHelloMutation;
import com.gqlclient.graphqlclient.ReplaceHelloTextMutation;
import com.gqlclient.graphqlclient.type.HelloInput;

import java.util.List;
import java.util.Objects;

public class GraphqlHelloClient {

    private final ApolloClient apolloClient;

    public GraphqlHelloClient(String serverEndPoint) {
        this.apolloClient = new ApolloClient.Builder().serverUrl(serverEndPoint).build();
    }

    public int addHello(HelloInput helloInput) {
        var mutation = apolloClient.mutation(new AddHelloMutation(helloInput));
        var mutationResponse = Rx3Apollo.single(mutation);

        return Objects.requireNonNull(mutationResponse.blockingGet().data).addHello;
    }

    public List<ReplaceHelloTextMutation.ReplaceHelloText> replaceHelloText(HelloInput helloInput) {
        var mutation = apolloClient.mutation(new ReplaceHelloTextMutation(helloInput));
        var mutationResponse = Rx3Apollo.single(mutation);

        return Objects.requireNonNull(mutationResponse.blockingGet().data).replaceHelloText;
    }


    public int deleteHello(int helloNumber) {
        var mutation = apolloClient.mutation(new DeleteHelloMutation(helloNumber));
        var mutationResponse = Rx3Apollo.single(mutation);

        return Objects.requireNonNull(mutationResponse.blockingGet().data).deleteHello;
    }


}
