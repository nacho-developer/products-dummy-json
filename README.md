# Manejo de API DummyJson

Este proyecto esta creado con Java 17 y Spring Boot, usando Web-Flux para reactividad

se ha usado esta API [dummyjson](https://dummyjson.com){:target="_blank"} para obtener los datos en formato JSON

## Funcionamiento

### Endpoint 1: 
  - Dada una lista de usuarios debe devolver una lista de todos los productos que existen en los carts de dichos usuarios. Para obtener los carts de cada usuario hay que     llamar a: https://dummyjson.com/carts/user/x Siendo x el id de usuario (La lista de usuarios disponibles se puede obtener en https://dummyjson.com/users).
  - Dicha lista debe ir agrupada por el id y la descripción del producto y debe mostrar la cantidad total de productos y el precio total. También la media del descuento     aplicado para ese producto en todos los carros.

### Endpoint 2: 
  - Debe devolver la siguiente información acerca de los productos que devuelve la llamada a: https://dummyjson.com/products
      * Producto con el precio más alto
      * Producto con el precio más bajo
      * Precio medio de los productos
      * Número de productos por marca
      * Número de productos por categoría
