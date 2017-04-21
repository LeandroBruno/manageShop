# manageShopTest

Project for rounding taxes up to the closer value (by scale of 0.05). 
The taxes are first applied to the single item and then the total is multiplied for the quantity of said item. 
This is to maintain consistency between the single and multiple item price.
Input validation is implemented through Hibernate tags and Spring validator on server side and angular messages on client side.

It has also a front end interface: to run it use the command: mvn spring-boot:run

And then open in the browser http://localhost:8080/manageCatalog/

