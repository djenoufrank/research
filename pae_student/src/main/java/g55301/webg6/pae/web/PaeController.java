package g55301.webg6.pae.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import g55301.webg6.pae.business.Pae;
import g55301.webg6.pae.model.Course;
import g55301.webg6.pae.model.Student;

@Controller
public class PaeController {
    @Autowired
    private Pae pae;

    @GetMapping("/private/courses")
    public String home(Model model) {
        model.addAttribute("newCourse", new Course());
        model.addAttribute("courses", pae.getCourse());
        return "courses";
    }

    @GetMapping("/private/students")
    public String studentsList(Model model) {
        model.addAttribute("newStudent", new Student());
        model.addAttribute("students", pae.getStudents());
        return "studentPage";
    }

    @PostMapping("/private/students")
    public String addStudent(@Valid @ModelAttribute Student newStudent, Errors errors, Model model) {
        if (errors.hasErrors()) {
            // System.out.print(errors.getAllErrors());
            return "redirect:/private/students";
        }
        System.out.println("pas eruerrr");
        System.out.println("etu est " + newStudent);
        pae.addStudent(newStudent);

        model.addAttribute("newStudent", new Student());

        model.addAttribute("students", pae.getStudents());

        return "studentPage";

    }

    @GetMapping("/private/coursess")
    public String specificCourse(@RequestParam(name = "course_id") String course_id, Model model) {// @requestParam(name
                                                                                                   // =

        Iterable<Course> l = pae.getCourse();

        for (Course course : l) {
            if (course.getCourse_id().equals(course_id)) {
                // si je le trouve je redirige vers welcome juste pr voir.
                return "welcome";// http://localhost:8080/courses?id=JLC
            }
        }

        return "course";
    }

    @PostMapping("/private/courses")
    public String doSomething(@Valid @ModelAttribute(name = "newCourse") Course newCourse, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("courses", pae.getCourse());
            return "courses";
        }
        pae.addCourse(newCourse);

        model.addAttribute("courses", pae.getCourse());

        return "courses";

    }

    @GetMapping("/private/theStudentCourses/{student_id}/courses")
    public String retrieveCoursesForStudent(@PathVariable(name = "student_id") Integer student_id, Model model) {
        model.addAttribute("studen", pae.getStudentById(student_id));
        model.addAttribute("coursess", pae.retrieveCoursesByStudent(student_id));
        model.addAttribute("allCourses", pae.getCourse());// pour le formulaire

        return "courseForStudent";
    }

    @GetMapping("/private/theCourseStudents/{course_id}/students")
    public String retrieveStudentsForCourse(@PathVariable(name = "course_id") String course_id, Model model) {
        model.addAttribute("cour", pae.getCourseById(course_id));
        model.addAttribute("studentss", pae.retrieveStudentsByCourse(course_id));
        model.addAttribute("allStudents", pae.getStudents());
        return "studentForCourse";
    }

    @PostMapping("/private/theStudentCourses/{nameOfThisStud}/courses")
    public String addCoursesForStudent(@RequestParam(value = "monAjout") String course_id,
            @ModelAttribute(name = "nameOfThisStud") Integer studenId, Model model) {
        pae.addCourseToStudent(studenId, course_id);
        return "redirect:/private/theStudentCourses/" + studenId + "/courses";
    }
}
