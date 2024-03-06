create table if not exists category
(
    id   bigserial primary key,
    name varchar(255)
);

create table if not exists product
(
    id          bigserial primary key,
    name        varchar(255),
    description varchar(255),
    price       float4,
    category_id bigint not null,
    constraint fk_product_category_id foreign key (category_id) references category (id)
);