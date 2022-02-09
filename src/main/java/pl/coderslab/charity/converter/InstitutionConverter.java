package pl.coderslab.charity.converter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entity.Institution;
import java.util.Optional;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.charity.repository.InstitutionRepository;

@Component
@AllArgsConstructor
public class InstitutionConverter implements Converter<String, Optional<Institution>> {

    private final InstitutionRepository institutionRepository;

    @Override
    public Optional<Institution> convert(String source) {
        return institutionRepository.findById(Long.parseLong(source));
    }
}
