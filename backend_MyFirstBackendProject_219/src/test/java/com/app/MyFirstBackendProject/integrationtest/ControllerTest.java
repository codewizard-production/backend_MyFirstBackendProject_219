package com.app.MyFirstBackendProject.integrationtest;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.app.MyFirstBackendProject.SpringApp;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.element.Node;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "spring.config.location=classpath:application-test.yml" })
class ControllerTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private final ObjectMapper mapper = new ObjectMapper();

  @Autowired
  private WebApplicationContext context;
  @LocalServerPort
  private int port;

  @BeforeEach
  void setup() {
    RestAssuredMockMvc.webAppContextSetup(context);
  }

  
  
   private JsonNode getJSONFromFile(String filePath) throws IOException {
    try(InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath)){
      JsonNode jsonNode = mapper.readValue(in, JsonNode.class);
      return jsonNode;
    }
    catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  
  private String getPayload(String filePath) throws IOException {
	  String jsonString = mapper.writeValueAsString( getJSONFromFile(filePath) );
	  return jsonString;
  }

  @Test
  void testRetrieveServiceDocument() {
    final String xml = given()
        .accept(ContentType.XML)
        .when()
        .get("/MyFirstBackendProject/")
        .then()
        .statusCode(HttpStatusCode.OK.getStatusCode())
        .contentType(ContentType.XML)
        .extract()
        .asString();

    final XmlPath path = new XmlPath(xml);
    final Collection<Node> n = ((Node) ((Node) path.get("service")).get("workspace")).get("collection");
    assertNotNull(n);
    assertFalse(n.isEmpty());
  }

  @Test
  void  testRetrieveMetadataDocument() {
    final String xml = given()
        .when()
        .get("/MyFirstBackendProject/$metadata")
        .then()
        .statusCode(HttpStatusCode.OK.getStatusCode())
        .contentType(ContentType.XML)
        .extract()
        .asString();

    final XmlPath path = new XmlPath(xml);
    final Node n = ((Node) ((Node) path.get("edmx:Ed mx")).get("DataServices")).get("Schema");
    assertNotNull(n);
    assertEquals("MyFirstBackendProject", n.getAttribute("Namespace"));
    assertNotNull(n.get("EntityContainer"));
  }

	

	
  @Test
  void  testCreateDepartmentInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("DepartmentInstance.json"))
        .when()
        .post("/MyFirstBackendProject/Departments")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsDepartment() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("DepartmentInstance.json"))
        .when()
        .post("/MyFirstBackendProject/Departments")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/MyFirstBackendProject/Departments?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).DeptID", equalTo("'<<replace_with_keyFieldValue>>'"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/MyFirstBackendProject/Departments/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateCollegeInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("CollegeInstance.json"))
        .when()
        .post("/MyFirstBackendProject/Colleges")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsCollege() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("CollegeInstance.json"))
        .when()
        .post("/MyFirstBackendProject/Colleges")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/MyFirstBackendProject/Colleges?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).CollegeID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/MyFirstBackendProject/Colleges/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreatePreviousEducationInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("PreviousEducationInstance.json"))
        .when()
        .post("/MyFirstBackendProject/PreviousEducations")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsPreviousEducation() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("PreviousEducationInstance.json"))
        .when()
        .post("/MyFirstBackendProject/PreviousEducations")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/MyFirstBackendProject/PreviousEducations?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).EducationID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/MyFirstBackendProject/PreviousEducations/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateStudentInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("StudentInstance.json"))
        .when()
        .post("/MyFirstBackendProject/Students")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsStudent() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("StudentInstance.json"))
        .when()
        .post("/MyFirstBackendProject/Students")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/MyFirstBackendProject/Students?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).StudentID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/MyFirstBackendProject/Students/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateCourseInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("CourseInstance.json"))
        .when()
        .post("/MyFirstBackendProject/Courses")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsCourse() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("CourseInstance.json"))
        .when()
        .post("/MyFirstBackendProject/Courses")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/MyFirstBackendProject/Courses?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).CourseID", equalTo("'<<replace_with_keyFieldValue>>'"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/MyFirstBackendProject/Courses/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateLecturerInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("LecturerInstance.json"))
        .when()
        .post("/MyFirstBackendProject/Lecturers")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsLecturer() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("LecturerInstance.json"))
        .when()
        .post("/MyFirstBackendProject/Lecturers")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/MyFirstBackendProject/Lecturers?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).StaffID", equalTo("'<<replace_with_keyFieldValue>>'"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/MyFirstBackendProject/Lecturers/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateSubjectInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("SubjectInstance.json"))
        .when()
        .post("/MyFirstBackendProject/Subjects")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsSubject() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("SubjectInstance.json"))
        .when()
        .post("/MyFirstBackendProject/Subjects")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/MyFirstBackendProject/Subjects?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).SubjectID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/MyFirstBackendProject/Subjects/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
           
       
  
  
  
  
 
  @AfterEach
  void  teardown() {
    jdbcTemplate.execute("DELETE FROM myfirstbackendproject.Department");
    jdbcTemplate.execute("DELETE FROM myfirstbackendproject.College");
    jdbcTemplate.execute("DELETE FROM myfirstbackendproject.PreviousEducation");
    jdbcTemplate.execute("DELETE FROM myfirstbackendproject.Student");
    jdbcTemplate.execute("DELETE FROM myfirstbackendproject.Course");
    jdbcTemplate.execute("DELETE FROM myfirstbackendproject.Lecturer");
    jdbcTemplate.execute("DELETE FROM myfirstbackendproject.Subject");
     jdbcTemplate.execute("DELETE FROM myfirstbackendproject.DepartmentBelongsto");
     jdbcTemplate.execute("DELETE FROM myfirstbackendproject.CourseContains");
     jdbcTemplate.execute("DELETE FROM myfirstbackendproject.CollegeAdmissioned");
     jdbcTemplate.execute("DELETE FROM myfirstbackendproject.CollegeCollegeStaff");
     jdbcTemplate.execute("DELETE FROM myfirstbackendproject.LecturerTeaches");
     jdbcTemplate.execute("DELETE FROM myfirstbackendproject.CollegeCoursesOffered");
     jdbcTemplate.execute("DELETE FROM myfirstbackendproject.StudentEducationDetails");

    RestAssuredMockMvc.reset();
  }
}
