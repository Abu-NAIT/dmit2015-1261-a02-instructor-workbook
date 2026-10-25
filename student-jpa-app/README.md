
# Week 5

Follow the code in the **`student-jpa-demo`** folder.



### Lesson 14: Jakarta Persistence (JPA) and H2 Database

In this lesson, we move from **in-memory/Firebase storage** to a **relational database using Jakarta Persistence (JPA)**.
---

#### 1. Create the Jakarta EE Project and Configure WildFly

Create a new project in IntelliJ IDEA:

```text
Project name:student-jpa-app
Project type: Jakarta EE
Build system: Maven
Jakarta EE: Web Profile
```

We use **WildFly** instead of Tomcat because the application now uses Jakarta EE services such as JPA, `EntityManager`, and transactions.

There are two ways to configure WildFly:

##### Option 1 — Install and Select WildFly in IntelliJ

For **Application Server**, select **WildFly**.

If WildFly is not already configured:

1. Click **New / Browse** beside Application Server.
2. Browse to your extracted WildFly folder.
3. Select the WildFly installation folder.
4. IntelliJ will recognize and add the server.

##### Option 2 — Use the WildFly Maven Plugin
> **Assignment 3 uses this approach:** configure WildFly through Maven and run the application using `mvn wildfly:dev`.

Instead of manually downloading and selecting WildFly in IntelliJ, configure the **WildFly Maven Plugin** in `pom.xml`.https://lms.nait.ca/d2l/le/lessons/191328/topics/6536257

The plugin provisions/downloads the required WildFly version:


Start WildFly and deploy the application from terminal using:

```bash
mvn wildfly:dev
```

**What it does:** Maven provisions the required WildFly server, starts it, and deploys the application.



---

#### 2. Project Configuration

Add the required Maven dependencies.

Create/update:

```text
pom.xml : https://lms.nait.ca/d2l/le/lessons/191328/topics/6528712 and plugins from step1 option 2 
web.xml: https://lms.nait.ca/d2l/le/lessons/191328/topics/6529717
```

These files configure the dependencies and Jakarta EE application.

---

#### 3. Configure the H2 Database

Create:

```text
src/main/java/dmit2015/config/ApplicationConfig.java
```

**Template:**

```text
DMIT2015 Jakarta Persistence ApplicationConfig
```

Use the H2 file-based database:

```text
jdbc:h2:file:./data/Lesson12DemoDB;
```

The DataSource is:

```text
H2DatabaseDS
```

**What it does:** Configures the H2 database connection used by the application.

---

#### 4. Configure JPA

Create:

```text
src/main/resources/META-INF/persistence.xml
```

**Template:**

```text
DMIT2015 Jakarta Persistence persistence.xml
```

It uses the `H2DatabaseDS` DataSource created in `ApplicationConfig.java`.

JPA uses **Hibernate** as the persistence provider.

Important development setting:

```text
drop-and-create
```

This drops and recreates the database tables when the application starts, so existing data can be lost.

When the entity structure is stable, it can be changed to:

```text
none
```

to preserve existing data.

**What it does:** Configures JPA/Hibernate and tells it which database connection to use.

---

#### 5. Create the Student JPA Entity

Create:

```text
src/main/java/dmit2015/model/Student.java
```

**Template:**

```text
DMIT2015 Jakarta Persistence Entity Class
```

**Template values:**

```text
EntityName:          Student
PrimaryKeyDataType:  Long
CsvColumnLength:     leave default
```

Important annotations:

```text
@Entity          → Maps Student to a database table
@Id              → Primary key
@GeneratedValue  → Automatically generates the ID
@Version         → Tracks record versions
@NotBlank        → Validates required fields
@PrePersist      → Sets timestamps when creating
@PreUpdate       → Updates timestamp when updating
```

The `of()` method uses `Faker` to generate sample Student data.

**What it does:** Turns the Java `Student` class into a JPA entity that can be stored in the H2 database.

---

#### 6. Create the Student Service Interface

Create:

```text
src/main/java/dmit2015/service/StudentService.java
```

**Template:**

```text
DMIT2015 Model Service template
```

The service interface defines CRUD operations such as:

```text
Create
Find
Find All
Update
Delete
```

**What it does:** Defines the operations available for Student data without containing database code.

---

#### 7. Create the JPA Service

Create:

```text
src/main/java/dmit2015/service/StudentJpaService.java
```

**Template:**

```text
DMIT2015 Model Service Interface Jakarta Persistence Implementation
```

**Template values:**

```text
ModelClass:          Student
PrimaryKeyDataType:  Long
primaryKey:          id
```

The service uses:

```java
@PersistenceContext
private EntityManager entityManager;
```

`EntityManager` performs the database operations:

```text
persist() → Create
find()    → Read
JPQL      → Read All
merge()   → Update
remove()  → Delete
```

Update `updateStudent()` with the Student properties:

```java
existingStudent.setFirstName(student.getFirstName());
existingStudent.setLastName(student.getLastName());
existingStudent.setCourseSection(student.getCourseSection());

student = entityManager.merge(existingStudent);
```

`@Transactional` is used for operations that change database data.

**What it does:** Implements `StudentService` CRUD operations using JPA and `EntityManager`.

---

#### 8. Create the Student CRUD Backing Bean

Create the `view` package:

```text
src/main/java/dmit2015/view/
```

Create:

```text
StudentCrudView.java
```

**Template:**

```text
DMIT2015 Faces CRUD Backing Bean
```

**Template values:**

```text
ModelClass:               Student
cdiServiceInstanceName:   studentService
PrimaryKeyDataType:       Long
```

Update the injected service:

```java
@Inject
@Named("jakartaPersistenceStudentService")
private StudentService studentService;
```

**What it does:** Connects the JSF page to `StudentService`. The backing bean does not access `EntityManager` or H2 directly.

---

#### 9. Create the Faces Layout

Create:

```text
src/main/webapp/WEB-INF/faces-templates/layout.xhtml
```

**Template:**

```text
DMIT2015 Faces Template
```

**What it does:** Provides the shared layout for the Faces pages, including navigation, messages, styles, footer, and page content.

---

#### 10. Create the Student CRUD Page

Create:

```text
src/main/webapp/students/manage-students.xhtml
```

**Template:**

```text
DMIT2015 Faces CRUD Page
```

**Template values:**

```text
File name:          manage-students
ModelClass:         Student
PrimaryKeyField:    id
```

Replace the generated DataTable placeholder fields with:

```text
firstName
lastName
courseSection
```

Also replace the Create/Edit dialog placeholder fields with:

```text
firstName
lastName
courseSection
```

**What it does:** Displays and manages Student records stored in the H2 database.

---

#### 11. Create the Home Page

Create:

```text
src/main/webapp/index.xhtml
```

**Template:**

```text
DMIT2015 Faces Composition Page
```

**Template values:**

```text
File name:   index
PAGE TITLE:  Home
```

**What it does:** Creates the application's home page using the shared Faces layout.

---

#### 12. Create Sample Student Data

Create the initializer using:

```text
DMIT2015 Jakarta Persistence Entity Class Initializer
```

Import the Student model:

```java
import dmit2015.model.Student;
```

Change:

```java
if (studentJpaService.count() == 0) {
```

to:

```java
if (studentJpaService.getAllStudents().isEmpty()) {
```

Inside the `try` block, generate 22 sample students:

```java
var faker = new Faker();

for (int count = 1; count <= 22; count++) {
    Student currentStudent = Student.of(faker);
    studentJpaService.createStudent(currentStudent);
}
```

Change:

```java
logger.info("Created " + studentJpaService.count() + " records.");
```

to:

```java
logger.info("Created " + studentJpaService.getAllStudents().size() + " records.");
```

**What it does:** Uses DataFaker to automatically create sample Student records when the database is empty.

---

#### 13. View the H2 Database

Run the application and open the H2 Console:

```text
http://localhost:8080/student-jpa-app-1.0-SNAPSHOT/h2-console/
```

Use the H2 Console to view the database tables and Student records created by JPA.

---

