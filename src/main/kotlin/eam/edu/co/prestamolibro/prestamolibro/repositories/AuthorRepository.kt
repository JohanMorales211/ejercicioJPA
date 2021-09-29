package eam.edu.co.prestamolibro.prestamolibro.repositories

import eam.edu.co.prestamolibro.prestamolibro.modelo.Author
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Component
@Transactional
class AuthorRepository {

    //inyeccion de depencia...... el framework se encarga de asignarle valor a la depencia

    @Autowired //esta anotacion indica que springboot se encargara de instanciar esta clase.
    lateinit var em: EntityManager //clase que nos da JPA para manipular las entidades.

    fun create(author: Author){
        em.persist(author) //inserta en la tabla que define la entidad.
    }

    //? quiere decir q algo puede ser null
    fun find(id:Long): Author?{
        //se el envia la clase que quiero buscar y el valor de la llave primaria que quiero buscar.
        return em.find(Author::class.java, id) //busca en la bd por llave primaria
    }

    fun update(author: Author) {
        em.merge(author) //actualizar un registro sobre la BD
    }

    fun delete(id: Long) {
        //buscan por id la entidad que quiero borrar
        val author = find(id)

        //solo puedo borrar una persona que exista...
        if (author!=null) {
            //borra la entidad de la BD, recibe por parametro la entidad a borrrar
            em.remove(author)
        }
    }

}