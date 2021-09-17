package eam.edu.co.prestamolibro.prestamolibro.modelo
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "tbl_editorial")
data class Editorial(
    @Id
    @Column(name = "editorialCode")
    val editorialCode: Long,

    @Column(name = "editorialName")
    var editorialName: String
): Serializable
