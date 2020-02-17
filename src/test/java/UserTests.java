import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import my.reqres.User;
import my.reqres.UserPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTests {

    private static RequestSpecification spec;
    private static RequestSpecification spec2;

    @BeforeAll
    public static void init() {
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
//                .setProxy("gate.swissre.com", 9443)
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();

        spec2 = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
//                .setProxy("gate.swissre.com", 9443)
                .setBaseUri("https://reqres.in/api")
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }


    @Test
    public void testPageCount () {
        UserPage users = given()
                .spec(spec2)
                .when()
                .get("unknown")
                .then()
                .statusCode(200)
                .extract().as(UserPage.class);
        boolean pagesCount = users.getTotal_pages() >= 2;
        assertEquals(true, pagesCount, "Page count is incorrect: "+users.getTotal_pages());

    }

    @Test
    public void testName () {
        UserPage users = given()
                .spec(spec2)
                .when()
                .get("unknown")
                .then()
                .statusCode(200)
                .extract().as(UserPage.class);
        int count = 0;
        for (int i = 0; i < users.getData().length; i++) {
            String name = users.getData()[i].getName();
            if (name.equals("true red")) {
                count++;
            }
        }
        assertEquals(1, count, "No such name or more items with such name exists");
    }



    @Test
    public void testLoginUnsuccesfulMessage () {
        String payload = "{\n" +
                "    \"email\": \"peter@klaven\"\n" +
                "}";
        UserResponse response = given()
                .spec(spec2)
                .urlEncodingEnabled(true)
                .body(payload)
                .when()
                .post("login")
                .then().statusCode(400)
                .and()
                .extract().as(UserResponse.class);
        assertEquals("Missing password", response.getError(), "Incorrect error message dispalayed!");
    }

        @Test
        public void someTest () {
            User[] users = given()
                    .spec(spec)
                    .when()
                    .get("users")
                    .then()
                    .statusCode(200)
                    .extract().as(User[].class);
            assertEquals(1, users[0].getId());
            assertEquals("Bret", users[0].getUsername());
        }


    @Test
    public void someTestpost () {
        Post[] users = given()
                .spec(spec)
                .when()
                .get("posts")
                .then()
                .statusCode(200)
                .extract().as(Post[].class);
        assertEquals(1, users[0].getId());
        assertEquals("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", users[0].getTitle());
    }

    @Test
    public void someTestTodo () {
        Todo[] users = given()
                .spec(spec)
                .when()
                .get("todos")
                .then()
                .statusCode(200)
                .extract().as(Todo[].class);
        assertEquals(1, users[0].getId());
        assertEquals(false, users[0].isCompleted());
    }


    @Test
    public void someTestpost1 () {
        Post post = given()
                .spec(spec)
                .when()
                .get("posts/1")
                .then()
                .statusCode(200)
                .extract().as(Post.class);
        assertEquals(1, post.getId());
        assertEquals("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", post.getTitle());
    }

    @Test
    public void someTestTodos1 () {
        Todo todos = given()
                .spec(spec)
                .when()
                .get("todos/1")
                .then()
                .statusCode(200)
                .extract().as(Todo.class);
        assertEquals(1, todos.getId());
        assertEquals("delectus aut autem", todos.getTitle());
        assertEquals(false, todos.isCompleted());
    }


    @Test
    public void someTestpostAgain () {
        String payload = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
        UserResponse response = given()
                .spec(spec2)
                .urlEncodingEnabled(true)
                .body(payload)
                .when()
                .post("users")
                .then().statusCode(201)
                .and()
                .extract().as(UserResponse.class);

        System.out.println(response.createdAt);
    }

    @Test
    public void someTestLogin () {
        String payload = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}";
        UserResponse response = given()
                .spec(spec2)
                .urlEncodingEnabled(true)
                .body(payload)
                .when()
                .post("login")
                .then().statusCode(200)
                .and()
                .extract().as(UserResponse.class);
        assertEquals(false, response.getToken().isEmpty());
    }


    @Test
    public void someTestnew () {
        UserPage users = given()
                .spec(spec2)
                .when()
                .get("users?page=2")
                .then()
                .statusCode(200)
                .extract().as(UserPage.class);
//        assertEquals(1, users[0].getId());
//        assertEquals("Bret", users[0].getUsername());
        assertEquals(7, users.getData()[0].getId());
        assertEquals("Michael", users.getData()[0].getFirst_name());


    }

}




