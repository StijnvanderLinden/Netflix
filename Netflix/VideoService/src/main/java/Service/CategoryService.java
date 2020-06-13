package main.java.Service;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;
import main.java.Model.Category;

@ApplicationScoped
@Transactional(REQUIRED)
public class CategoryService {

    @PersistenceContext
    EntityManager em;

    @Transactional(SUPPORTS)
    public List<Category> findAllCategories() {
        Query query = em.createQuery("SELECT c FROM Category c LEFT JOIN c.videos");
        List<Category> categories = query.getResultList();
        ArrayList<Category> entities = new ArrayList<>();
        for(Category category : categories){
            boolean exists = false;
            for(Category entity : entities){
                if(entity.id == category.id){
                    exists = true;
                }
            }
            if(!exists){
                entities.add(category);
            }
        }
        return entities;
    }

    @Transactional(SUPPORTS)
    public Category findCategoryById(Long id) {
        return Category.findById(id);
    }

    public Category persistCategory(@Valid Category category) {
        Category.persist(category);
        return category;
    }

    public Category updateCategory(@Valid Category category) {
        Category entity = Category.findById(category.id);
        entity.name = category.name;
        return entity;
    }
    public void deleteCategory(Long id) {
        Category category = Category.findById(id);
        category.delete();
    }
}
