package eam.edu.co.prestamolibro.prestamolibro.repositories

import eam.edu.co.prestamolibro.prestamolibro.modelo.AuthorBook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Component
@Transactional
class AuthorBookRespository {

    @Autowired
    lateinit var em: EntityManager

    fun create(authorBook: AuthorBook){
        em.persist(authorBook)
    }

    fun find(id:Long): AuthorBook?{
        return em.find(AuthorBook::class.java, id)
    }

    fun update(authorBook: AuthorBook) {
        em.merge(authorBook)
    }

    fun delete(id: Long) {
        val authorBook = find(id)

        if (authorBook!=null) {
            em.remove(authorBook)
        }
    }
}