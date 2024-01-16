package mikolaj.project.backendapp.persistence;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import mikolaj.project.backendapp.enums.Sport;

@Converter(autoApply = true)
public class SportConverter implements AttributeConverter<Sport, String> {
    @Override
    public String convertToDatabaseColumn(Sport sport) {
        return sport.getName();
    }
    @Override
    public Sport convertToEntityAttribute(String s) {
        return Sport.fromValue(s);
    }
}
