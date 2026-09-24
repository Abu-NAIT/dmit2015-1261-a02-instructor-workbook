# DMIT2015 Fall 2026 Term Workbook Repository

This repository contains course work that is **not submitted for marks**, including:

- Examples completed in class
- Code from slides
- Exercises and practice activities

Clone this repository to keep track of your work and easily share code with the instructor.

## Week 1

Follow the code in the **`dmit2015-javase-demo`** folder.

## Week 2

Follow the code in the **`dmit2015-faces-demo`** folder.

### Lesson 4: Introduction to Jakarta Faces

**Coding files:**
- `HelloBean.java`
- `GreetingBean.java`
- `hello.xhtml`
- `index.xhtml`

**Topics:** Jakarta Faces, Maven WAR projects, Tomcat 11, Facelets, CDI managed beans, EL binding, and PrimeFaces.

### Lesson 5: JSF Components, Form Binding & Validation

**Coding files:**
- `StudentFormBean.java`
- `student-form.xhtml`

**Topics:** JSF form components, bean property binding, `@ViewScoped`, validation, `process="@form"`, and `update="@form"`.

### Lesson 6: Collections, Data Tables, Navigation & JSF Lifecycle

**Coding files:**

- `StudentFormBean.java`
- `RegistrationBean.java`
- `StudentInfo.java`
- `student-form.xhtml`
- `registration.xhtml`
- `registration-success.xhtml`

**Topics:** Collections and data tables, JSF navigation, action methods, implicit navigation, redirects with `faces-redirect=true`, JSF lifecycle phases, and validation behavior.

## Week 3–4

Follow the code in the **`dmit2015-faces-firebase-demo`** folder.

### Lesson 7: Architecture and Strategy Pattern

**Setup files:**
- Import/download project template from Brightspace
- `pom.xml` --added code from [https://lms.nait.ca/d2l/le/lessons/191328/topics/6251889](https://lms.nait.ca/d2l/le/lessons/191328/topics/6491233)
- `beans.xml` -- added code from https://lms.nait.ca/d2l/le/lessons/191328/topics/6491524
- `web.xml` — added configuration from Brightspace

**Template files:**
- `WEB-INF/faces-templates/layout.xhtml` — Used --DMIT Faces layout template--
- `webapp/resources/styles.css`

**Pages:**
- `index.xhtml`  — Used --DMIT Minimal Composition Page template--
- `aboutus.xhtml`  — Used --DMIT Minimal Composition Page template--

**Topics:** Application architecture, Strategy Pattern, CDI, service interfaces and implementations, in-memory services, Firebase introduction, Lombok, Jakarta Validation, Faces messages, OmniFaces Messages, and Facelets templates.

### Lesson 8: Development Templates and In-Memory CRUD

**Model:**
- `model/Student.java` — created from scratch; Student domain model

**Service:**
- `service/StudentService.java`
  - Template: **Model Service Interface**
  - Model class: `Student`
  - ID type: `String`
- `service/MemoryStudentService.java` — created from scratch; in-memory CRUD implementation

**View:**
- `view/StudentCrudView.java`
  - Template: **Faces CRUD Backing Bean**
  - Model class: `Student`
  - CDI: `memoryStudentService`
  - ID type: `String`

**CRUD Page:**
- `students/manage-students.xhtml`
  - Template: **CRUD Page**
  - Manage: `Students`
  - Model class: `Student`
  - ID type: `String`
  - Customized the generated `manage-students.xhtml`

**Topics:** IntelliJ file templates, Project Lombok, DataFaker, Jakarta Validation, JSF templates and composition, CRUD operations, managed beans, service interfaces, and in-memory service implementations.
