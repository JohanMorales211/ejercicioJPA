package eam.edu.co.prestamolibro.prestamolibro

import eam.edu.co.prestamolibro.prestamolibro.modelo.Author
import eam.edu.co.prestamolibro.prestamolibro.modelo.AuthorBook
import eam.edu.co.prestamolibro.prestamolibro.modelo.Book
import eam.edu.co.prestamolibro.prestamolibro.modelo.Editorial
import eam.edu.co.prestamolibro.prestamolibro.repositories.AuthorBookRespository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class AuthorBookRespositoryTest {

    @Autowired
    lateinit var authorBookRespository: AuthorBookRespository

    @Autowired
    lateinit var entityManager: EntityManager



    @Test
    fun testCreateAuthorBook() {
        val author = Author(3L, "Arcesio","Marulanda")
        val editorial = Editorial(50L,"Cocacola1")
        val book = Book("10","150PP","Hacerse rico en 5 minutos",editorial)

        authorBookRespository.create(AuthorBook(1L, author,book))

        //asersiones, o las verificaciones
        val authorBook = entityManager.find(AuthorBook::class.java,  1L)
        Assertions.assertNotNull(authorBook)
        Assertions.assertEquals(1L, authorBook.id)
        Assertions.assertEquals(3L, authorBook.author.id)
        Assertions.assertEquals("10", authorBook.book.identificatonBook)
    }

    @Test
    fun testDelete(){
        //prerequisitos
        val author = Author(3L, "Arcesio","Marulanda")
        val editorial = Editorial(50L,"Cocacola1")
        val book = Book("10","150PP","Hacerse rico en 5 minutos",editorial)

        entityManager.persist(AuthorBook(1L, author,book))

        //ejecucion de la preuba
        authorBookRespository.delete(1L)

        //assersiones
        val authorBook = entityManager.find(AuthorBook::class.java, 3L)
        Assertions.assertNull(authorBook)
    }

    @Test
    fun findTest() {
        val author = Author(3L, "Arcesio","Marulanda")
        val editorial = Editorial(50L,"Cocacola1")
        val book = Book("10","150PP","Hacerse rico en 5 minutos",editorial)

        entityManager.persist(AuthorBook(1L, author,book))

        val authorBook = authorBookRespository.find(1L)

        Assertions.assertNotNull(authorBook)
        Assertions.assertEquals(1L, authorBook?.id)
        Assertions.assertEquals(3L, authorBook?.author?.id)
        Assertions.assertEquals("10", authorBook?.book?.identificatonBook)
    }

    @Test
    fun testUpdate() {
        //prerequisito
        val author = Author(3L, "Arcesio","Marulanda")
        val editorial = Editorial(50L,"Cocacola1")
        val book = Book("10","150PP","Hacerse rico en 5 minutos",editorial)

        entityManager.persist(AuthorBook(1L, author,book))

        //ejecutando...
        val authorBook = entityManager.find(AuthorBook::class.java, 1L)

        authorBook.author.name = "Feid"
        authorBook.author.lastName = "Hernandez"
        authorBook.book.nameBook = "50 años de soledad"
        authorBook.book.isbBook = "10-20"

        authorBookRespository.update(authorBook)

        //assersiones
        val authorBookToAssert = entityManager.find(AuthorBook::class.java, 1L)
        Assertions.assertEquals("Feid", authorBookToAssert.author.name)
        Assertions.assertEquals("Hernandez", authorBookToAssert.author.lastName)
        Assertions.assertEquals("50 años de soledad", authorBookToAssert.book.nameBook)
        Assertions.assertEquals("10-20", authorBookToAssert.book.isbBook)
    }
}