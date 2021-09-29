package eam.edu.co.prestamolibro.prestamolibro

import eam.edu.co.prestamolibro.prestamolibro.modelo.Book
import eam.edu.co.prestamolibro.prestamolibro.modelo.Borrow
import eam.edu.co.prestamolibro.prestamolibro.modelo.Editorial
import eam.edu.co.prestamolibro.prestamolibro.modelo.User
import eam.edu.co.prestamolibro.prestamolibro.repositories.BorrowRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class BorrowRepositoryTest {

    @Autowired
    lateinit var borrowRepository: BorrowRepository

    @Autowired
    lateinit var entityManager: EntityManager


    @Test
    fun testFindByUser(){
        val user = User("1", "Rodriguez", "Javier")
        entityManager.persist(user)
        val editorial = Editorial(2L,"Castellana")
        entityManager.persist(editorial)
        val book = Book("1","178643438","Matematica Vectorial",editorial)
        val date1 = Date(2021,9,10)
        val date2 = Date(2021,9,15)
        entityManager.persist(book)
        entityManager.persist(Borrow(12345L,book,user,date1))
        entityManager.persist(Borrow(123456L,book,user,date2))

        //ejecutando pruebas
        val users = borrowRepository.findByUsario("1")

        //assertions
        Assertions.assertEquals(2,users.size)
    }


    @Test
    fun testCreateBorrow() {

        val editorial = Editorial(50L,"Cocacola1")
        val book = Book("10","150PP","Hacerse rico en 5 minutos",editorial)
        val userId = User("132456","Javier","Rodriguez")
        val date = Date(2021,9,10)

        borrowRepository.create(Borrow(12345L,book,userId,date))

        //asersiones, o las verificaciones
        val borrow = entityManager.find(Borrow::class.java,  12345L)
        Assertions.assertNotNull(borrow)
        Assertions.assertEquals(12345L, borrow.idBoorow)
        Assertions.assertEquals("10", borrow.book.identificatonBook)
        Assertions.assertEquals("132456", borrow.user.identificaton)
        Assertions.assertEquals(date, borrow.dateTime)
    }

    @Test
    fun testDelete(){
        //prerequisitos
        val editorial = Editorial(50L,"Cocacola1")
        val book = Book("10","150PP","Hacerse rico en 5 minutos",editorial)
        val userId = User("132456","Javier","Rodriguez")
        val date = Date(2021,9,10)

        entityManager.persist(Borrow(12345L,book,userId,date))

        //ejecucion de la preuba
        borrowRepository.delete(12345L)

        //assersiones
        val borrow = entityManager.find(Borrow::class.java, 12345L)
        Assertions.assertNull(borrow)
    }

    @Test
    fun findTest() {
        val editorial = Editorial(50L,"Cocacola1")
        val book = Book("10","150PP","Hacerse rico en 5 minutos",editorial)
        val userId = User("132456","Javier","Rodriguez")
        val date = Date(2021,9,10)

        entityManager.persist(Borrow(12345L,book,userId,date))

        val borrow = borrowRepository.find(12345L)

        Assertions.assertEquals(12345L, borrow?.idBoorow)
        Assertions.assertEquals("10", borrow?.book?.identificatonBook)
        Assertions.assertEquals("132456", borrow?.user?.identificaton)
        Assertions.assertEquals(date, borrow?.dateTime)
    }

    @Test
    fun testUpdate() {
        //prerequisito
        val editorial = Editorial(50L,"Cocacola1")
        val book = Book("10","150PP","Hacerse rico en 5 minutos",editorial)
        val userId = User("132456","Javier","Rodriguez")
        val date = Date(2021,9,10)

        entityManager.persist(Borrow(12345L,book,userId,date))

        //ejecutando...
        val borrow = entityManager.find(Borrow::class.java, 12345L)
        borrow.user.name = "Johan"
        borrow.user.lastName = "Morales"

        borrowRepository.update(borrow)

        //assersiones
        val borrowToAssert = entityManager.find(Borrow::class.java, 12345L)
        Assertions.assertEquals("Johan", borrowToAssert.user.name)
        Assertions.assertEquals("Morales", borrowToAssert.user.lastName)
    }
}