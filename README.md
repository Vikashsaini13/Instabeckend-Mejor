
# Instagram Application

##  Frameworks and Language Used

    -> Java 19
    -> Spring Boot
    -> MySQl

 
# Data Flow

### Controller

- #### UserController
    -> Handles the CRUD operations of User model.<br>
  *userSignUp()<br>
  *userSignIn()<br>
  *updateUserDetail()<br>
  *addPost()<br>
  *getPost()<br>
  *deletePost()<br>
  *addComment()<br>
  *deleteComment()<br>
  *addLike()<br>
  *deleteLike()<br>
  *countLike()

- #### PostController
    ->Handles the CRUD operatons of Post model.<br>
  

### Service

- #### UserService
    -> All the business logic for User model will be implemented by UserService.
- #### PatientService
    ->  All the business logic for Post model will be implemented by PostService.




# Repository

- ### IUserRepository
    -> It extents JpaRepository and contains all the CRUD operations for the User Model.

- ### IPostRepository
    -> It extents JpaRepository and contains all the CRUD operations for the Post Model.

# Database Design 
    -> There are two Entiries in this application - User and Post.
   

# Data Structure Used
    -> ArrayList.
    -> Java classes and Interfaces.

# Project Summary 
    -> This Instagram application contains user details and post details. by using this application a user can store his/her data and can post some stuff.
# Server Used
    -> http:localhost:8080/(required url);
    -> my-sql for mapping the database- http://localhost:8080/my-sql
