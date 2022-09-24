package g55301.webg6.pae.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import g55301.webg6.pae.business.Pae;
import g55301.webg6.pae.model.Course;
import g55301.webg6.pae.model.Student;

//@Slf4j
@RestController
@RequestMapping(path = "/api")
public class restExO1 {

    @Autowired
    private Pae pae;

    @GetMapping("/courseList")
    public List<Course> hello() {
        return pae.getCourse();
    }

    @GetMapping("/studentList")
    public List<Student> hello2() {
        return pae.getStudents();
    }

    @GetMapping("/students/{studentId}/courses")
    public List<Course> retrieveCoursesForStudent(@PathVariable Integer studentId) {
        return pae.retrieveCoursesByStudent(studentId);
    }

    @GetMapping("/courses/{courseId}/students")
    public List<Student> retrieveStudentsForCourse(@PathVariable String courseId) {
        return pae.retrieveStudentsByCourse(courseId);
    }

    @GetMapping("/studentsAddCourse/{stuId}/{courId}")
    public void studentsAddCourse(@PathVariable String courId, @PathVariable Integer stuId) {
        pae.addCourseToStudent(stuId, courId);
    }
}
