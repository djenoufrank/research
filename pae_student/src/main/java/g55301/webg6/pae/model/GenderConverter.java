package g55301.webg6.pae.model;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender gender) {// prend l'enum et envoi le nom de cet enum
        // il va etre renvoyer en string me permettant de faire les comparaisons entre
        // le M de gender et le M du value du teamlif
        if (gender == null) {
            return null;
        }
        return gender.name();
    }

    @Override
    public Gender convertToEntityAttribute(String code) {// on prend un string
        // on compare avec le nom de lenum puis on envoi l'enum
        if (code == null) {
            return null;
        }

        return Stream.of(Gender.values())
                .filter(c -> c.name().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
