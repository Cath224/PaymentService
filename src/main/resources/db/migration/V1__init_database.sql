create schema if not exists ateupeonding_payment;


DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM pg_type WHERE typname = 'product_type') THEN
            CREATE TYPE ateupeonding_payment.product_type AS enum
                (
                    'TIER',
                    'GOAL'
                    );
        END IF;
    END
$$;

create table if not exists ateupeonding_payment.payment_transactions_history
(
    id                uuid primary key         default gen_random_uuid(),
    user_id           uuid    not null,
    product_type      text    not null,
    product_id        uuid not null,
    amount            decimal not null         default 0.0,
    created_timestamp timestamp with time zone default now()
);

create index if not exists payment_transactions_history_user_id on ateupeonding_payment.payment_transactions_history(user_id);
create index if not exists payment_transactions_history_product_type_and_product_id on ateupeonding_payment.payment_transactions_history(product_type, product_id);