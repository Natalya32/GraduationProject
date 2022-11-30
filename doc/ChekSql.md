# Проверка корректности внесения данных приложением.

## При простой покупке тура дебетовой картой:
### 1. Если карта "APPROVED"
```sql
select count(o.id)
from payment_entity p
left join order_entity o on o.payment_id=p.transaction_id
where p.status="APPROVED"
```

--Проверяем тем самым, что при запросе с данными карты со статусом "APPROVED"
выполняется запись в таблицу "ORDER_ENTITY".

### 2. Если карта "DECLINED"
```sql
select count(o.id)
from payment_entity p
left join order_entity o on o.payment_id=p.transaction_id
where p.status="DECLINED"
```

--Проверяем тем самым, что при запросе с данными карты со статусом "DECLINED"
НЕвыполняется запись в таблицу "ORDER_ENTITY".


## При покупке тура в кредит по данным дебетовой карты:
### 1. Если карта "APPROVED"
```sql
select count(o.id)
from credit_request_entity c
left join order_entity o on o.credit_id=c.bank_id
where c.status="APPROVED"
```

--Проверяем тем самым, что при запросе с данными карты со статусом "APPROVED"
выполняется запись в таблицу "ORDER_ENTITY".

### 2. Если карта "DECLINED"
```sql
select count(o.id)
from credit_request_entity c
left join order_entity o on o.credit_id=c.bank_id
where c.status="DECLINED"
```

--Проверяем тем самым, что при запросе с данными карты со статусом "DECLINED"
НЕ выполняется запись в таблицу "ORDER_ENTITY".
