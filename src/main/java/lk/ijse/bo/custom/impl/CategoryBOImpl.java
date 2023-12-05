package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CategoryBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CategoryDAO;
import lk.ijse.dto.CategoryDto;
import lk.ijse.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryBOImpl implements CategoryBO {
    CategoryDAO categoryDAO= (CategoryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CATEGORY);
    @Override
    public List<CategoryDto> getAllCategories() {
        List<CategoryDto>categoryDtos=new ArrayList<>();
        for (Category category:categoryDAO.getAll()) {
            categoryDtos.add(new CategoryDto(
                    category.getId(),
                    category.getName()
            ));
        }
        return categoryDtos;
    }

    @Override
    public String getNewCategoryId() {
        String id=categoryDAO.getNewId();
        Integer idInt=Integer.parseInt(id.replace("Ca",""))+1;
        return String.format("Ca%03d",idInt);
    }

    @Override
    public boolean saveCategory(CategoryDto categoryDto) {
        return categoryDAO.save(new Category(
                categoryDto.getId(),
                categoryDto.getName()
        ));
    }

    @Override
    public CategoryDto getCategory(String text) {
        Category category=categoryDAO.get(text);
        if (category!=null){
            return new CategoryDto(
                    category.getId(),
                    category.getName()
            );
        }
        return null;
    }

    @Override
    public boolean updateCategory(CategoryDto categoryDto) {
        return categoryDAO.update(new Category(
                categoryDto.getId(),
                categoryDto.getName()
        ));
    }

    @Override
    public boolean deleteCategory(CategoryDto categoryDto) {
        return categoryDAO.delete(new Category(
                categoryDto.getId(),
                categoryDto.getName()
        ));
    }
}
