package com.emazon.stockservice.application.usecase.create;

import com.emazon.stockservice.application.exceptions.CategoryAlreadyExistsException;
import com.emazon.stockservice.domain.models.Category;
import com.emazon.stockservice.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateCategoryUseCase implements ICreateCategoryUseCase {

    private final ICategoryPersistencePort categoryPersistencePort;

    @Override
    public Category execute(String name, String description) {
        // Verificar si la categoría ya existe
        if (categoryPersistencePort.existsByName(name)) {
            throw new CategoryAlreadyExistsException("Category name already exists");
        }

        // Crear la entidad de dominio
        Category category = new Category(name, description);

        // Guardar la entidad de dominio en el puerto de persistencia
        return categoryPersistencePort.save(category);
    }
}
