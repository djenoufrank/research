package g55301.webg6.pae.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import g55301.webg6.pae.model.Course;
import g55301.webg6.pae.model.CourseDB;
import g55301.webg6.pae.model.Student;
import g55301.webg6.pae.model.StudentDB;
import lombok.Data;

//@Slf4j
@Service // pr un bean qui n'a pas de route
@Data
public class Pae {
    // private static final Logger log =
    // LoggerFactory.getLogger("webg6.pae.business.Pae");
    @Autowired
    private CourseDB courseDB;
    @Autowired
    private StudentDB studentDB;

    public List<Course> getCourse() {
        System.out.println("test 2 " + courseDB.findAll());
        return (List<Course>) courseDB.findAll();
    }

    public void addCourse(Course course) {
        courseDB.save(course);
    }

    public List<Student> getStudents() {
        return (List<Student>) studentDB.findAll();
    }

    public void addStudent(Student student) {
        studentDB.save(student);
    }

    public List<Course> retrieveCoursesByStudent(int studentId) {// liste des id des cours de letudiant
        return new ArrayList<>(studentDB.findById(studentId).get().getCourses());// studentDB.findById(studentId).get().getCourses()
                                                                                 // retourne un set. donc je le cast en
                                                                                 // list
    }

    public List<Student> retrieveStudentsByCourse(String courseId) {// liste des id des cours de letudiant
        return new ArrayList<>(courseDB.findById(courseId).get().getStudents());
    }

    public Course getCourseById(String course_id) {
        return courseDB.findById(course_id).get();
    }

    public Student getStudentById(int stuId) {
        return studentDB.findById(stuId).get();
    }

    public void addCourseToStudent(int studentId, String courseId) {
        if (studentId != 0) {
            if (studentDB.existsById(studentId)) {
                Student student = studentDB.findById(studentId).get();
                Course course = courseDB.findById(courseId).get();
                student.getCourses().add(course);
                studentDB.save(student);// le save fait le insert et le update a la fois. donc a toute modification de
                                        // l'objet ou son attribut il faut l'utiliser
            }
        }
    }

    public void addStudentToCourse(int studentId, String courseId) {
        if (courseId != null) {
            if (courseDB.existsById(courseId)) {
                courseDB.findById(courseId).get().getStudents().add(getStudentById(studentId));
            }
        }
    }
}
