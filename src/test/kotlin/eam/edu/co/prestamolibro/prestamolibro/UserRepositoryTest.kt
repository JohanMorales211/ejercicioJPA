package eam.edu.co.prestamolibro.prestamolibro

import eam.edu.co.prestamolibro.prestamolibro.modelo.Editorial
import eam.edu.co.prestamolibro.prestamolibro.modelo.User
import eam.edu.co.prestamolibro.prestamolibro.repositories.UserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun testCreateUser() {

        userRepository.create(User("123456789","Alfonso","Hernandez"))

        //asersiones, o las verificaciones
        val user = entityManager.find(User::class.java,  "123456789")
        Assertions.assertNotNull(user)
        Assertions.assertEquals("Alfonso", user.name)
        Assertions.assertEquals("Hernandez", user.lastName)
    }

    @Test
    fun testDelete(){
        //prerequisitos

        entityManager.persist(User("123456789","Alfonso","Hernandez"))

        //ejecucion de la preuba
        userRepository.delete("123456789")

        //assersiones
        val user = entityManager.find(User::class.java, "123456789")
        Assertions.assertNull(user)
    }

    @Test
    fun findTest() {
        entityManager.persist(User("123456789","Alfonso","Hernandez"))

        val user = userRepository.find("123456789")

        Assertions.assertNotNull(user)
        Assertions.assertEquals("123456789", user?.identificaton)
        Assertions.assertEquals("Alfonso", user?.name)
        Assertions.assertEquals("Hernandez", user?.lastName)
    }

    @Test
    fun testUpdate() {
        //prerequisito
        entityManager.persist(User("123456789","Alfonso","Hernandez"))

        //ejecutando...
        val user = entityManager.find(User::class.java, "123456789")
        user.name = "Johan"
        user.lastName = "Morales"

        userRepository.update(user)

        //assersiones
        val userToAssert = entityManager.find(User::class.java, "123456789")
        Assertions.assertEquals("Johan", userToAssert.name)
        Assertions.assertEquals("Morales", userToAssert.lastName)
    }
}