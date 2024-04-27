# API Documentation

## Seed Events

### Description

Seeds random events

### Path
POST `/api/events/seed?number=(number of event)`

### Response

HTTP Status 201
```
(no content)
```

## Get Events

### Description

Get events

### Path
GET `/api/events/sort=?(TRUE/FALSE)`

### Response

HTTP Status 200
```
[
    {
        "id": "4f212d31-2581-43ac-87b1-28d5d89c0e0d",
        "name": "Event 1",
        "eventDate": "2024-04-04 04:04:04"
    },
    {
        "id": "76fc8215-0d9d-4676-af06-69583a5ebeef",
        "name": "2024-04-03 04:04:04"
    }
]
```


## Create Order

### Description

Create order ticket reservation 

### Path
POST `/api/order`

### Request

```
{
    "email": "user@mail.com",
    "phone": "user@mail.com",
    "eventId": "fa6b78aa-b8b2-4627-bbfc-9fa1da9492ba",
    "saleEventid": "77ed711d-baa5-4d72-b461-6648fc777537",
    "identities": [
        {
            "name": "John Doe 1",
            "identity_number": 123456,
            "identity_type": "KTP"
        },
        {
            "name": "John Doe 1",
            "identity_number": 123456,
            "identity_type": "PASSPORT"
        }
    ],
    "orderDate": "2024-04-04 16:24"
}
```

### Response

HTTP Status 201
```
{
    "id": "e25cbce4-5ea3-46db-ad16-aac015b8c20b",
    "status": "PENDING"
}
```

## Find Order

### Description

Get order ticket reservation 

### Path
GET `/api/order/{id}`

### Response

HTTP Status 200
```
{
    "email": "user@mail.com",
    "phone": "user@mail.com",
    "event": {
        "name": "Event 1",
        "date": "2020-02-02 02:02:02"
    }
    "identities": [
        {
            "name": "John Doe 1",
            "identity_number": 123456,
            "identity_type": "KTP"
        },
        {
            "name": "John Doe 1",
            "identity_number": 123456,
            "identity_type": "PASSPORT"
        }
    ],
    "orderDate": "2024-04-04 16:24",
    "status": "PAID"
}
```

## Update Order Status

### Description

Update order ticket reservation status 

### Path
PATCH `/api/order/:id/status`

### Request
```
{
    "status": "PAID"
}
```

### Notes
- `status` between `PAID`/`REJECTED`

### Response

HTTP Status 204
```
(no content)
```