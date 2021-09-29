package eam.edu.co.prestamolibro.prestamolibro.repositories


import eam.edu.co.prestamolibro.prestamolibro.modelo.Editorial
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Component
@Transactional
class EditorialRepository {

    @Autowired
    lateinit var em: EntityManager

    fun create(editorial: Editorial){
        em.persist(editorial)
    }

    fun find(id:Long): Editorial?{
        return em.find(Editorial::class.java, id)
    }

    fun update(editorial: Editorial) {
        em.merge(editorial)
    }

    fun delete(id: Long) {
        val editorial = find(id)

        if (editorial!=null) {
            em.remove(editorial)
        }
    }
}