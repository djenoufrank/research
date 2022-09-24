package g55301.webg6.pae;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.annotation.Annotation;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Méthodes utilitaires pour tester les annotations de validations posées sur
 * les entités.
 * 
 * @author MCD
 */
@Component
public class BeanValidationUtil<T> {

    @Autowired

    private Validator validator;

    /**
     * Teste qu’une entité est bien valide.
     * Le test qui appelle cette méthode échoue si l’entité n’est pas valide.
     * 
     * @param entity l’entité a tester.
     */
    public void assertIsValid(T entity) {
        assertTrue(validator.validate(entity).isEmpty());
    }

    /**
     * Teste qu’une entité possède une violation de contrainte.
     * Le test qui appelle cette méthode réussit si l’entité viole
     * la contrainte indiquée par les paramètres et uniquement celle-là.
     * 
     * @param entity             l’entité à tester.
     * @param invalidField       l’attribut qui n’est pas valide.
     * @param violatedConstraint l’annotation de l’attribut qui n’est pas respectée.
     */
    public void assertHasError(T entity, String invalidField, Class<? extends Annotation> violatedConstraint) {
        Set<ConstraintViolation<T>> violations = validator.validate(entity);
        assertEquals(1, violations.size());
        ConstraintViolation<T> violation = violations.iterator().next();
        assertEquals(invalidField, violation.getPropertyPath().toString());
        assertEquals(violatedConstraint, violation.getConstraintDescriptor().getAnnotation().annotationType());
    }

}
