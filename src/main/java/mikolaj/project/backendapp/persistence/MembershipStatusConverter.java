package mikolaj.project.backendapp.persistence;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import mikolaj.project.backendapp.enums.MembershipStatus;

//@Converter(autoApply = true)
@Converter
public class MembershipStatusConverter implements AttributeConverter<MembershipStatus, String> {
    @Override
    public String convertToDatabaseColumn(MembershipStatus membershipStatus) {
        return membershipStatus.getName();
    }
    @Override
    public MembershipStatus convertToEntityAttribute(String s) {
        return MembershipStatus.fromValue(s);
    }
}
