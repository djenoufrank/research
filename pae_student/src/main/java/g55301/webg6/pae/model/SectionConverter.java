package g55301.webg6.pae.model;

import java.util.stream.Stream;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class SectionConverter implements AttributeConverter<Section, String> {

    @Override
    public String convertToDatabaseColumn(Section section) {
        if (section == null) {
            return null;
        }
        return section.name();
    }

    @Override
    public Section convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(Section.values())
                .filter(c -> c.name().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
