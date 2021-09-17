package eam.edu.co.prestamolibro.prestamolibro.modelo
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "tbl_users")
data class User(

    @Id
    @Column(name = "user_identification")
    val identificaton: String,

    @Column(name = "user_name")
    var name: String,

    @Column(name = "user_lastName")
    var lastName: String

): Serializable
