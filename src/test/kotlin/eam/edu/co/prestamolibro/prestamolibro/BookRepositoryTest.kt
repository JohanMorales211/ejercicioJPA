package eam.edu.co.prestamolibro.prestamolibro

import eam.edu.co.prestamolibro.prestamolibro.modelo.Author
import eam.edu.co.prestamolibro.prestamolibro.modelo.AuthorBook
import eam.edu.co.prestamolibro.prestamolibro.modelo.Book
import eam.edu.co.prestamolibro.prestamolibro.modelo.Editorial
import eam.edu.co.prestamolibro.prestamolibro.repositories.BookRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class BookRepositoryTest {

    @Autowired
    lateinit var bookRespository: BookRepository

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun testCreateBook() {
        val editorial = Editorial(50L,"Cocacola1")

        bookRespository.create(Book("20","200P","Consejos de Amor",editorial))

        //asersiones, o las verificaciones
        val book = entityManager.find(Book::class.java,  "20")
        Assertions.assertNotNull(book)
        Assertions.assertEquals("20", book.identificatonBook)
        Assertions.assertEquals("200P", book.isbBook)
        Assertions.assertEquals("Consejos de Amor", book.nameBook)
        Assertions.assertEquals(50L, book.editorial.editorialCode)
    }

    @Test
    fun testDelete(){
        //prerequisitos
        val editorial = Editorial(50L,"Cocacola1")

        entityManager.persist(Book("20","200P","Consejos de Amor",editorial))

        //ejecucion de la preuba
        bookRespository.delete("20")

        //assersiones
        val book = entityManager.find(Book::class.java, "20")
        Assertions.assertNull(book)
    }

    @Test
    fun findTest() {
        val editorial = Editorial(50L,"Cocacola1")

        entityManager.persist(Book("20","200P","Consejos de Amor",editorial))

        val book = bookRespository.find("20")

        Assertions.assertNotNull(book)
        Assertions.assertEquals("20", book?.identificatonBook)
        Assertions.assertEquals("200P", book?.isbBook)
        Assertions.assertEquals("Consejos de Amor", book?.nameBook)
        Assertions.assertEquals(50L, book?.editorial?.editorialCode)
    }

    @Test
    fun testUpdate() {
        //prerequisito
        val editorial = Editorial(50L,"Cocacola1")

        entityManager.persist(Book("20","200P","Consejos de Amor",editorial))

        //ejecutando...
        val book = entityManager.find(Book::class.java, "20")
        book.nameBook = "Rico en 10 minutos"
        book.isbBook = "500L"
        book.editorial.editorialName = "Cocacola2"

        bookRespository.update(book)

        //assersiones
        val bookToAssert = entityManager.find(Book::class.java, "20")
        Assertions.assertEquals("Rico en 10 minutos", bookToAssert.nameBook)
        Assertions.assertEquals("500L", bookToAssert.isbBook)
        Assertions.assertEquals("Cocacola2", bookToAssert.editorial.editorialName)
    }
}