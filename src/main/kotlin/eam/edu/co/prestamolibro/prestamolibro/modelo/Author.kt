package eam.edu.co.prestamolibro.prestamolibro.modelo
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import java.io.Serializable

@Entity
@Table(name = "tbl_authors")
data class Author(
    @Id
    @Column(name = "id")
    val id: Long,

    @Column(name = "name")
    var name: String,

    @Column(name = "lastName")
    var lastName: String

): Serializable
