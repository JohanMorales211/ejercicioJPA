package eam.edu.co.prestamolibro.prestamolibro.modelo
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "tbl_authorBooks")
data class AuthorBook(
    @Id
    @Column(name = "id")
    val id: Long,

    @ManyToOne
    @JoinColumn(name = "id_Author")
    var author: Author,

    @ManyToOne
    @JoinColumn(name = "id_Book")
    var book: Book

): Serializable
