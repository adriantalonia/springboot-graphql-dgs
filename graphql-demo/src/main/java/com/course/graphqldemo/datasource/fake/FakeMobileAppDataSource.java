package com.course.graphqldemo.datasource.fake;

import com.course.graphql.generated.types.*;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class FakeMobileAppDataSource {

    @Autowired
    private Faker faker;

    public static final List<MobileApp> MOBILE_APPS_LIST = new ArrayList<>();

    @PostConstruct
    private void postConstruct() throws MalformedURLException {
        for (int i = 0; i < 20; i++) {
            var addresses = new ArrayList<Address>();
            var author = Author.newBuilder().addresses(addresses)
                    .name(faker.book().author())
                    .originCountry(faker.country().name())
                    .build();
            var mobileApp = MobileApp.newBuilder()
                    .name(faker.app().name())
                    .author(author)
                    .platform(randomMobileAppPlatform())
                    //.appID(UUID.randomUUID().toString())
                    //.downloader(faker.number().numberBetween(1, 1_500_00))
                    //.homepage(new URL("https://" + faker.internet().url()))
                    //.category(MobileAppCategory.values()[faker.random().nextInt(MobileAppCategory.values().length)])
                    .build();

            for (int j = 0; j < ThreadLocalRandom.current().nextInt(1, 3); j++) {
                var address = Address.newBuilder()
                        .country(faker.address().country())
                        .city(faker.address().cityName())
                        .country(faker.address().country())
                        .street(faker.address().streetAddress())
                        .zipCode(faker.address().zipCode())
                        .build();
                addresses.add(address);
            }
            MOBILE_APPS_LIST.add(mobileApp);
        }
    }

    private List<String> randomMobileAppPlatform() {
        return switch (ThreadLocalRandom.current().nextInt(10) % 3) {
            case 0 -> List.of("android");
            case 1 -> List.of("ios");
            default -> List.of("ios", "android");
        };
    }
}
