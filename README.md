# RESTful_WebService_JAX-RS
Implementation by JAX-RS of RESTful Web service in Java. 

Implementation of a RESTful Web service to manage an eShop exposing the following operations listed as methods in a Java interface:
- public Items getItemList();
- public Response createItem(Item item);
- public Item getItemDetails(String code);
- public void deleteItem(String code);
- public Response orderItem(String code);
- public Order getOrder(String code, int oid);
