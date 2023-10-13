# HotelApp

## Use Case

**finding room**
- find room by number of beds, view of sea, balcony, conditional cost or any attibutes(we can use universal and not overrided filter).
- we can combine these filters, chaining them
- we can use limit, count, flatMap or anything else with standart Stream interface.

**find room by booking**
- find ordered/free room in any dates/period
- flatMap stream to Book element accessible you to filtering by any attributes of oder or guest
    - like type of vacation, orderID, name of guest etc.
- you can combine finding by rooms and finding by booking, and of course chaining it
