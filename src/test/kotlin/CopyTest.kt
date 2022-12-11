import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CopyTest {
    @Test
    fun test() {
        abstract class TestSuperBase {
            var superId: String? = null
        }
        @Copy("id")
        abstract class TestBase : TestSuperBase() {
            var id: String? = null
        }
        data class SomeEntity(val name: String, val age: Int) : TestBase()

        val entity = SomeEntity("David", 24)
        entity.id = "42"
        entity.superId = "19"

        val entityCopy = entity.copy(name = "Milan")

        assertEquals("Milan", entityCopy.name)
        assertEquals(24, entityCopy.age)
        assertEquals("42", entityCopy.id)
        assertEquals(null, entityCopy.superId)
    }
}