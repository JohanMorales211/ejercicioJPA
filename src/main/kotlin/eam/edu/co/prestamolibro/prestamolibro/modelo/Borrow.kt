package eam.edu.co.prestamolibro.prestamolibro.modelo
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tbl_boorow")
data class Borrow(

    @Id
    @Column(name = "identificationBoorow")
    val idBoorow: Long,

    @ManyToOne
    @JoinColumn(name = "id_Book")
    var book: Book,

    @ManyToOne
    @JoinColumn(name = "id_User")
    var user: User,

    @Column(name = "dateTime")
    var dateTime: Date

): Serializable
