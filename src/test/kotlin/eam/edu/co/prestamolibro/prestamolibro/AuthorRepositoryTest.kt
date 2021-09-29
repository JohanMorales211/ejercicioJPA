package eam.edu.co.prestamolibro.prestamolibro

import eam.edu.co.prestamolibro.prestamolibro.modelo.Author
import eam.edu.co.prestamolibro.prestamolibro.repositories.AuthorRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional


class AuthorRepositoryTest {

    @Autowired
    lateinit var authorRepository: AuthorRepository

    @Autowired
    lateinit var entityManager: EntityManager

    //requisitos para una buena prueba unitaria
    //1. la preuba debe ser repetible
    //2. las pruebas deben independientes entre si
    //3. la prueba siempre debe dar el mismo resultado ante los mismo parametros (deterministica)
    @Test
    fun testCreateAuthor() {
        //prerequisitos
        //que la persona no exista

        //la ejecucion de la prueba.. llamar el metodo que estoy probando
        authorRepository.create(Author(3L, "Arcesio","Marulanda"))

        //asersiones, o las verificaciones
        val author = entityManager.find(Author::class.java,  3L)
        Assertions.assertNotNull(author)
        Assertions.assertEquals(3L, author.id)
        Assertions.assertEquals("Arcesio", author.name)
        Assertions.assertEquals("Marulanda", author.lastName)
    }

    @Test
    fun testDelete(){
        //prerequisitos
        entityManager.persist(Author(3L, "Arcesio","Marulanda"))

        //ejecucion de la preuba
        authorRepository.delete(3L)

        //assersiones
        val author = entityManager.find(Author::class.java, 3L)
        Assertions.assertNull(author)
    }

    @Test
    fun findTest() {
        entityManager.persist(Author(3L, "Arcesio","Marulanda"))

        val author = authorRepository.find(3L)

        Assertions.assertNotNull(author)
        Assertions.assertEquals("Arcesio", author?.name)
    }

    @Test
    fun testUpdate() {
        //prerequisito
        entityManager.persist(Author(3L, "Arcesio","Marulanda"))

        //ejecutando...
        val author = entityManager.find(Author::class.java, 3L)
        author.name = "Gladys"
        author.lastName = "Ortiz"

        authorRepository.update(author)

        //assersiones
        val authorToAssert = entityManager.find(Author::class.java, 3L)
        Assertions.assertEquals("Gladys", authorToAssert.name)
        Assertions.assertEquals("Ortiz", authorToAssert.lastName)
    }
}