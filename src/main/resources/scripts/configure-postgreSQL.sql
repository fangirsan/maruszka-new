CREATE ROLE maruszka_dev_user NOSUPERUSER NOCREATEDB NOCREATEROLE NOINHERIT LOGIN PASSWORD 'maruszka';

CREATE DATABASE maruszka_dev_db
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE public.additive TO maruszka_dev_user;
GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE public.batch TO maruszka_dev_user;
GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE public.batch_comments TO maruszka_dev_user;
GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE public.batch_ingredient TO maruszka_dev_user;
GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE public.batch_mash_temperature TO maruszka_dev_user;
GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE public.beer_style TO maruszka_dev_user;
GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE public.country TO maruszka_dev_user;
GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE public.ingredient TO maruszka_dev_user;
GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE public.hop TO maruszka_dev_user;
GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE public.malt TO maruszka_dev_user;
GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE public.mash_temperature TO maruszka_dev_user;
GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE public.producer TO maruszka_dev_user;
GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE public.yeast TO maruszka_dev_user;

GRANT ALL ON SEQUENCE public.batch_comments_id_seq TO maruszka_dev_user;
GRANT ALL ON SEQUENCE public.batch_id_seq TO maruszka_dev_user;
GRANT ALL ON SEQUENCE public.beer_style_id_seq TO maruszka_dev_user;
GRANT ALL ON SEQUENCE public.country_id_seq TO maruszka_dev_user;
GRANT ALL ON SEQUENCE public.ingredient_id_seq TO maruszka_dev_user;
GRANT ALL ON SEQUENCE public.mash_temperature_id_seq TO maruszka_dev_user;
GRANT ALL ON SEQUENCE public.producer_id_seq TO maruszka_dev_user;


