package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.CategoryDto;

import java.util.List;

public interface CategoryBO extends SuperBO {
    List<CategoryDto> getAllCategories();

    String getNewCategoryId();

    boolean saveCategory(CategoryDto categoryDto);

    CategoryDto getCategory(String text);

    boolean updateCategory(CategoryDto categoryDto);

    boolean deleteCategory(CategoryDto text);
}
