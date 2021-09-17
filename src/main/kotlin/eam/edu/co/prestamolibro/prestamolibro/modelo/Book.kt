package eam.edu.co.prestamolibro.prestamolibro.modelo
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "tbl_books")
data class Book(

    @Id
    @Column(name = "identificationBook")
    val identificatonBook: String,

    @Column(name = "isbnBook")
    var isbBook: String,

    @Column(name = "nameBook")
    var nameBook: String,

    //Aqui falta la lista cakorra esa

    @ManyToOne
    @JoinColumn(name = "id_Editorial")
    var editorial: Editorial

): Serializable
