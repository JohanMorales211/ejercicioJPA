package eam.edu.co.prestamolibro.prestamolibro

import eam.edu.co.prestamolibro.prestamolibro.modelo.Book
import eam.edu.co.prestamolibro.prestamolibro.modelo.Editorial
import eam.edu.co.prestamolibro.prestamolibro.repositories.BookRepository
import eam.edu.co.prestamolibro.prestamolibro.repositories.EditorialRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class EditorialRepositoryTest {

    @Autowired
    lateinit var editorialRepository: EditorialRepository

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun testCreateEditorial() {

        editorialRepository.create(Editorial(5050L,"Nueva empresa editorial"))

        //asersiones, o las verificaciones
        val editorial = entityManager.find(Editorial::class.java,  5050L)
        Assertions.assertNotNull(editorial)
        Assertions.assertEquals(5050L, editorial.editorialCode)
        Assertions.assertEquals("Nueva empresa editorial", editorial.editorialName)
    }

    @Test
    fun testDelete(){
        //prerequisitos

        entityManager.persist(Editorial(5050L,"Nueva empresa editorial"))

        //ejecucion de la preuba
        editorialRepository.delete(5050L)

        //assersiones
        val editorial = entityManager.find(Editorial::class.java, 5050L)
        Assertions.assertNull(editorial)
    }

    @Test
    fun findTest() {
        entityManager.persist(Editorial(5050L,"Nueva empresa editorial"))

        val editorial = editorialRepository.find(5050L)

        Assertions.assertNotNull(editorial)
        Assertions.assertEquals(5050L, editorial?.editorialCode)
        Assertions.assertEquals("Nueva empresa editorial", editorial?.editorialName)
    }

    @Test
    fun testUpdate() {
        //prerequisito
        entityManager.persist(Editorial(5050L,"Nueva empresa editorial"))

        //ejecutando...
        val editorial = entityManager.find(Editorial::class.java, 5050L)
        editorial.editorialName = "Empresa que saca editoriales"

        editorialRepository.update(editorial)

        //assersiones
        val editorialToAssert = entityManager.find(Editorial::class.java, 5050L)
        Assertions.assertEquals("Empresa que saca editoriales", editorialToAssert.editorialName)
    }
}