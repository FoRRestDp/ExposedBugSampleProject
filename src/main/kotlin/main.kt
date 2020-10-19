import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.math.BigDecimal
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object Categories : IntIdTable() {
    override val primaryKey = PrimaryKey(id)
    val name: Column<String> = varchar("name", 20)
    val isHidden: Column<Boolean> = bool("hide").default(false)
}

object Items : IntIdTable() {
    override val primaryKey = PrimaryKey(id)
    val category = reference("category", Categories)
    val name: Column<String> = varchar("name", 200)
    val description: Column<String?> = text("description").nullable()
    val telegramImage: Column<String?> = varchar("image_tlg", 200).nullable()
    val imageLink: Column<String?> = varchar("img", 200).nullable()
    val price: Column<BigDecimal?> = decimal("price", 9, 2).nullable()
    val isHidden: Column<Boolean> = bool("hide").default(false)
}

class Category(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Category>(Categories)
    var name by Categories.name
    var isHidden by Categories.isHidden
}

class Item(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Item>(Items)
    var category by Category referencedOn Categories.id
    var name by Items.name
    var description by Items.description
    var imageTelegram by Items.telegramImage
    var img by Items.imageLink
    var price by Items.price
    var isHidden by Items.isHidden
}

fun main() {
    Database.connect(
        url = "jdbc:postgresql://localhost:5432/<databasename>",
        driver = "org.postgresql.Driver",
        user = "username",
        password = ""
    )

    transaction {
        Items.selectAll()
    }
}