package org.zerock.apiserver.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItinerary is a Querydsl query type for Itinerary
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItinerary extends EntityPathBase<Itinerary> {

    private static final long serialVersionUID = 781749062L;

    public static final QItinerary itinerary = new QItinerary("itinerary");

    public final StringPath areName = createString("areName");

    public final DatePath<java.time.LocalDate> createAt = createDate("createAt", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<String, StringPath> placeName = this.<String, StringPath>createList("placeName", String.class, StringPath.class, PathInits.DIRECT2);

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final StringPath userId = createString("userId");

    public QItinerary(String variable) {
        super(Itinerary.class, forVariable(variable));
    }

    public QItinerary(Path<? extends Itinerary> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItinerary(PathMetadata metadata) {
        super(Itinerary.class, metadata);
    }

}

