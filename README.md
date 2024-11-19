# CrudOperation
Springboot H2 database CRUD operation

Accepts reviews and stores them
localhost:8080/add

Fetch all reviews if no filters are applied.
localhost:8080/

Reviews can be filtered by date, store type or rating. 
localhost:8080/filter?rating=1&store=Amazon

Allows to get average monthly ratings per store. 
localhost:8080/average/store=Amazon

Allows to get total ratings for each category. Meaning, how many 5*, 4*, 3* and so on 
localhost:8080//rating/store=Amazon
