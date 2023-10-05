package com.course.graphqldemo;

import com.course.graphqldemo.client.StarwarsRestClient;
import com.course.graphqldemo.client.request.GraphqlRestRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class StarwarsRestClientTests {

    @Autowired
    private StarwarsRestClient client;

    @Test
    void testAsJson() throws Exception {
        var query = """
                query allPlanets {
                  allPlanets {
                    planets {
                      name
                      climates
                      terrains
                    }
                  }
                }
                """;

        var body = new GraphqlRestRequest();
        body.setQuery(query);

        var result = client.asJson(body, null);

        assertNotNull(result);
    }

    @Test
    void testAsJson_Invalid() throws Exception {
        var query = """
                query allPlanets {
                  allPlanetsxxxxx {
                    planets {
                      name
                      climates
                      terrains
                    }
                  }
                }
                """;

        var body = new GraphqlRestRequest();
        body.setQuery(query);

        assertThrows(
                RuntimeException.class,
                () -> {
                    var result = client.asJson(body, null);
                }
        );
    }
}
