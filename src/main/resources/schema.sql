create table product_quality_b2b
(
    audience varchar(255) not null,
    model_id bigint       not null,
    platform varchar(255) not null,
    content  jsonb,
    constraint "product_quality_b2bPK"
        primary key (audience, model_id, platform)
);