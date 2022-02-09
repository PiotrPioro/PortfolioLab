package pl.coderslab.charity.converter;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.repository.CategoryRepository;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CategoryConverter implements Converter<String, Optional<Category>> {

    private final CategoryRepository categoryRepository;

    @Override
    public Optional<Category> convert(String source) {
        return categoryRepository.findById(Long.parseLong(source));
    }
}
