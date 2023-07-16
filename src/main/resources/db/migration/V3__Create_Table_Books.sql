CREATE TABLE IF NOT EXISTS public.books
(
    id        bigserial,
    author      varchar,
    launch_date date NOT NULL,
    price       decimal(65, 2) NOT NULL,
    title       text,
    CONSTRAINT books_pkey PRIMARY KEY (id)
);
