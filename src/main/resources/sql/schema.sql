create table if not exists cinema_halls
(
    id bigserial primary key,
    serial_number integer unique not null,
    seats_number integer not null
);

create table if not exists cinema_films
(
    id bigserial primary key,
    title varchar(64) unique not null,
    release_year integer not null,
    age_restrictions integer not null,
    description text,
    poster text
);

create table if not exists cinema_sessions
(
    id bigserial primary key,
    ticket_cost integer not null,
    session_date timestamp with time zone,
    hall_id integer references cinema_halls(id),
    film_id integer references cinema_films(id)
);

-- last

create table if not exists users
(
    id bigserial primary key,
    login varchar(64) unique not null
);

create table if not exists cinema_authentications
(
    id bigserial primary key,
    user_id bigserial references users(id),
    authentications_date timestamp with time zone,
    ip_address varchar(64)
);

create table if not exists cinema_messages
(
    id bigserial primary key,
    message text not null,
    author_id bigserial references users(id),
    message_date timestamp without time zone,
    film_id bigserial references cinema_films(id)
)

