package eam.edu.co.prestamolibro.prestamolibro.repositories

import eam.edu.co.prestamolibro.prestamolibro.modelo.Editorial
import eam.edu.co.prestamolibro.prestamolibro.modelo.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Component
@Transactional
class UserRepository {

    @Autowired
    lateinit var em: EntityManager

    fun create(user: User){
        em.persist(user)
    }

    fun find(id:String): User?{
        return em.find(User::class.java, id)
    }

    fun update(user: User) {
        em.merge(user)
    }

    fun delete(id:String) {
        val user = find(id)

        if (user!=null) {
            em.remove(user)
        }
    }
}