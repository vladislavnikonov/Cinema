create table if not exists halls
(
    id            bigserial primary key,
    serial_number integer unique not null,
    seats_number  integer        not null
);

create table if not exists films
(
    id               bigserial primary key,
    title            varchar(64) unique not null,
    release_year     integer            not null,
    age_restrictions integer            not null,
    description      text,
    poster           text
);

create table if not exists sessions
(
    id           bigserial primary key,
    ticket_cost  integer not null,
    session_date timestamp with time zone,
    hall_id      integer references halls (id),
    film_id      integer references films (id)
);

create table if not exists users
(
    id    bigserial primary key,
    login varchar(64) unique not null
);

create table if not exists authentications
(
    id                   bigserial primary key,
    authentications_date timestamp with time zone,
    ip_address           varchar(64),
    user_id              bigserial references users (id)
);

create table if not exists messages
(
    id      bigserial primary key,
    message text not null,
    user_id bigserial references users (id),
    film_id bigserial references cinema_films (id)
);

