package eam.edu.co.prestamolibro.prestamolibro.repositories


import eam.edu.co.prestamolibro.prestamolibro.modelo.Book
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.persistence.EntityManager
import javax.transaction.Transactional


@Component
@Transactional
class BookRepository {

    @Autowired
    lateinit var em: EntityManager

    fun create(book: Book){
        em.persist(book)
    }

    fun find(id:String): Book?{
        return em.find(Book::class.java, id)
    }

    fun update(book: Book) {
        em.merge(book)
    }

    fun delete(id: String) {
        val book = find(id)

        if (book!=null) {
            em.remove(book)
        }
    }
}