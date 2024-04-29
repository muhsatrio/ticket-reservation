# API Documentation

## Seed Events

### Description

Seeds random events

### Path
POST `/api/events?number=(number of event)`

### Response

HTTP Status 201
```
(no content)
```

## Get Events

### Description

Get events

### Path
GET `/api/events`

### Response

HTTP Status 200
```
[
    {
        "id": "22b9e43a-5739-45b1-8ec3-a879dbe785cd",
        "name": "Event 527",
        "eventDate": "2025-09-10T17:00:00.000+00:00",
        "qty": 100000,
        "saleEvents": []
    },
    {
        "id": "3bfadae9-0dcd-441a-acad-57d4b268d7c6",
        "name": "Event 26",
        "eventDate": "2025-05-13T17:00:00.000+00:00",
        "qty": 100000,
        "saleEvents": [
            {
                "id": "c1392fa2-a9ae-45db-acb5-94d54c34ee21",
                "startDate": "2024-02-02T00:00:00.000+00:00",
                "endDate": "2024-02-02T00:00:00.000+00:00",
                "qty": 50000
            },
            {
                "id": "8512f2c1-fb65-4359-9c73-fb6dd5fcb95b",
                "startDate": "2024-02-02T02:02:02.000+00:00",
                "endDate": "2024-02-02T02:02:02.000+00:00",
                "qty": 50000
            }
        ]
    }
]
```

## Create Sale Event

### Description

Get events

### Path
POST `/api/saleEvents`

### Request

```
{
    "eventId": "4f212d31-2581-43ac-87b1-28d5d89c0e0d",
    "startDate": "2024-04-04 04:04:04",
    "endDate": "2024-04-04 04:04:04",
    "qty": 100,
    "price": 1150000
}
```

### Response

HTTP Status 201
```
(no content)
```

HTTP Status 404
```
{
    "message": "eventId not found"
}
```

HTTP Status 400
```
{
    "message": "qty has reached the maximum limit for eventId"
}
```


## Create Transaction

### Description

Create transaction ticket reservation 

### Path
POST `/api/transactions`

### Request

```
{
    "email": "user@mail.com",
    "phone": "user@mail.com",
    "saleEventid": "77ed711d-baa5-4d72-b461-6648fc777537",
    "identities": [
        {
            "name": "John Doe 1",
            "identityNumber": 123456,
            "identityType": "KTP"
        },
        {
            "name": "John Doe 1",
            "identityNumber": 123456,
            "identityType": "PASSPORT"
        }
    ]
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

## Find Transaction

### Description

Get transaction ticket reservation 

### Path
GET `/api/transactions/{id}`

### Response

HTTP Status 200
```
{
    "email": "user@mail.com",
    "phone": "user@mail.com",
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
    "status": "APPROVED",
    "totalPayment": 20000
}
```

## Update Transaction Status

### Description

Update transaction ticket reservation status 

### Path
PATCH `/api/transactions/:id`

### Request
```
{
    "status": "APPROVED"
}
```

### Notes
- `status` between `APPROVED`/`REJECTED`

### Response

HTTP Status 204
```
(no content)
```