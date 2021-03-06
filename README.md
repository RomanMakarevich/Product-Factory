#### **Makarevich Roman Product Factory**

##### **Overview**

Приложение для автоматизации закупок товаров у завода.

##### **User Story**

Начнём с работы "клиента" с системой.

**MRPF-1 Как "Клиент" я хочу зарегистрироваться в системе, и, если такого пользователя не найдено, регистрируюсь, 
       для возможности войти в систему.**

Request:

POST /product-factory-app/customer/sign-up


{

      "email" : "vasya@email.com",
      "password" : "qwerty",
      "fio" : "Пупкин Василий Иванович",
      "companyName" : "Пивной бар №1",
      "adress" : "г. Минск, ул. Пивная, 1",
      "accountNumber" : "1111 2222 3333 4444" 
      
}

Response: 201 CREATED

{

    "token" : "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2YXN5YUBlbWFpbC5jb20iLCJleHAiOjE1ODE4MTY1OTQsImlhdCI6MTU4MTc4MDU5NH0
    .zLBFfajJ1RuyIaTuYpsa-YdjdZP1DIIpxLWbOZS6YGo"
 
}


**MRPF-2 Как "Клиент", будучи ранее зарегистрированным пользователем, я хочу войти в систему, 
       и, если такой пользователь существует и пароль совпадает, войти в систему, для последующей работы с системой.**

Request:

POST /product-factory-app/customer/sign-in

{

    "email" : "vasya@email.com",
    "password" : "qwerty"
  
}

Response: 200 OK 

{

    "token" : "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2YXN5YUBlbWFpbC5jb20iLCJleHAiOjE1ODE4MTY1OTQsImlhdCI6MTU4MTc4MDU5NH0
        .zLBFfajJ1RuyIaTuYpsa-YdjdZP1DIIpxLWbOZS6YGo"
  
}


**MRPF-3 Как "Клиент" я хочу получить список доступных видов тар(кегель, банка, бутылка) и их количество на складе, 
       чтобы увидеть ассортимент и, в результате, получаю его.**

Request:

GET /product-factory-app/products

Response: 200 OK

{

       "id" : 1,
       "productName" : "keg",
       "material" : "steel",
       "weight" : 7.1,
       "cost" : 100.0   
}



**MRPF-4 Как "Клиент" я хочу добавить товар в корзину, для последующего оформления заказа. И у меня получается это сделать.**

Request:

POST /product-factory-app/user/{userId}/basket/{productId}


Response: 200 OK


**MRPF-5 Как "Клиент" я хочу оформить заказ, для приобритения товара. и у меня получается это сделать. 
       При оформлении заказа корзина очищается и показывается, что заказ оформлен.**

Request:

POST /product-factory-app/user/{userId}/basket/

Response: 200 OK

{

    "id" : 1,
    "fio" : "Пупкин Василий Иванович",
    "companyName" : "Пивной бар №1",
    "adress" : "г. Минск, ул. Пивная, 1",
    "accountNumber" : "1111 2222 3333 4444",
    "product" : "keg", 
    "numberOfProduct": 100,
    "totalCost" : 1000 

}

Предполагается, что пользователь "Работник" зарегестрирован заранте.

**MRPF-6 Как "Работник" я хочу произвести поступление товара на склад, для последующей продажи и у меня получается это сделать.**
       

Request:


PUT /product-factory-app/products/{productId}
 
  {
  
      "productName" : "keg",
      "material" : "steel",
      "weight" : 7.1,
      "cost" : 100
      
  }

Response: 200 OK


**MRPF-7 Как "Работник" я хочу просмотреть список заказов, для последующей обработки и у меня получается это сделать.**

Request:

GET /product-factory-app/orders/

Response: 200 OK

  {
  
     "id":1,
     "fio":"Пупкин Василий Иванович",
     "companyName":"Пивной бар №1",
     "address":"г. Минск, ул. Пивная, 1",
     "accountNumber":"1111 2222 3333 4444",
     "basketList":[
        {
           "productDTO":{
              "productId":0,
              "productName":"keg",
              "material":"steel",
              "weight":7.1,
              "cost":100.0
           },
           "numberOfProduct":100
        }
     ],
     "totalCost":10000.00
     
  }


**MRPF-8 Как "Работник" я хочу обработать заказ, чтобы выставить счёт клиенту.**

Request:

POST /product-factory-app/orders/{orderId}

Response: 200 OK

[

  {
  
      "orderId" : 1,
      "customer" : {
      "fio" : "Пупкин Василий Иванович",
      "companyName" : "Пивной бар №1",
      "adress" : "г. Минск, ул. Пивная, 1",
      "accountNumber" : "1111 2222 3333 4444"
      
      },
      "seller" : {
      "companyName" : "Завод тары для пива", 
      "adress" : "г. Минск, ул. Предприятий связанных с пивом",
      "accountNumber" : "2222 6666 4444 8888"
      },
      
      "product" : "keg", 
      "numberOfProduct": 100,
      "totalCost" : 1000  
         
  }
  
]


**MRPF-9 Как работник я хочу завести новую позицию товара.**

Request:

POST /product-factory-app/products

{
  
      "productName" : "bank",
      "material" : "steel",
      "weight" : 0.3,
      "cost" : 5.0
      
}

Response: 201 CREATED


**MRPF-10 Как клиент я хочу удалить позицыю из корзины.**

Request:

PUT /product-factory-app/user/{userId}/basket/{productId}

Response: 200 OK

**MRPF-11 Как работник, я хочу получить заказ с id=1.**

Request:

GET /product-factory-app/orders/{orderId}

Response: 200 OK

{
  
     "id":1,
     "fio":"Пупкин Василий Иванович",
     "companyName":"Пивной бар №1",
     "address":"г. Минск, ул. Пивная, 1",
     "accountNumber":"1111 2222 3333 4444",
     "basketList":[
        {
           "productDTO":{
              "productId":0,
              "productName":"keg",
              "material":"steel",
              "weight":7.1,
              "cost":100.0
           },
           "numberOfProduct":100
        }
     ],
     "totalCost":10000.00
     
  }
  
**MRPF-12 Как клиент, я хочу получить продукт по id**

Request:

GET /product-factory-app/products/{productId}

{

"productId" : 1

}

Response: 200 OK

{

       "id" : 1,
       "productName" : "keg",
       "material" : "steel",
       "weight" : 7.1,
       "cost" : 100.0   
}
