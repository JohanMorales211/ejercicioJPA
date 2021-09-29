package eam.edu.co.prestamolibro.prestamolibro.repositories

import eam.edu.co.prestamolibro.prestamolibro.modelo.Borrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.persistence.EntityManager
import javax.transaction.Transactional


@Component
@Transactional
class BorrowRepository {

    @Autowired
    lateinit var em: EntityManager

    fun findByUsario(id: String):List<Borrow>{
        val query = em.createQuery("SELECT borr FROM Borrow borr WHERE borr.user.identificaton=:identificaton")
        query.setParameter("identificaton",id)
        return query.resultList as List<Borrow>
    }

    fun findByBook(id: Long):List<Borrow>{
        val query = em.createQuery("SELECT borr FROM Borrow borr WHERE borr.book.identificatonBook=:identificatonBook")
        query.setParameter("identificatonBook",id)
        return query.resultList as List<Borrow>
    }

    fun create(borrow: Borrow){
        em.persist(borrow)
    }

    fun find(id:Long): Borrow?{
        return em.find(Borrow::class.java, id)
    }

    fun update(borrow: Borrow) {
        em.merge(borrow)
    }

    fun delete(id:Long) {
        val borrow = find(id)

        if (borrow!=null) {
            em.remove(borrow)
        }
    }
}

