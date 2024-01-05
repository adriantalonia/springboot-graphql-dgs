package com.graph.product.specification;

import com.graph.product.entity.Manufacturer;
import com.graph.product.entity.Serie;
import org.springframework.data.jpa.domain.Specification;

public class SeriesSpecification extends BaseSpecification {

    public static final String FIELD_NAME = "name";
    public static final String FIELD_MANUFACTURER = "manufacturer";

    public static Specification<Serie> seriesNameContainsIgnoreCase(String keyword) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(FIELD_NAME)),
                        contains(keyword.toLowerCase()))
        );
    }

    public static Specification<Manufacturer> manufacturerOriginCountryNameContainsIgnoreCase(String keyword) {
        return ((root, query, criteriaBuilder) -> {
            var joinManufacturer = root.join(FIELD_MANUFACTURER);

            return criteriaBuilder.like(
                    criteriaBuilder.lower(joinManufacturer.get(
                            ManufacturerSpecification.FIELD_ORIGIN_COUNTRY
                    )),
                    contains(keyword.toLowerCase())
            );
        });
    }
}
