# kotlin-copy-plugin
Reimplement data class copy method to consider variables in subclasses.

https://github.com/AhmedMourad0/no-copy/blob/master/plugins/compiler-plugin/src/main/kotlin/dev/ahmedmourad/nocopy/compiler/NoCopySyntheticResolveExtension.kt

https://resources.jetbrains.com/storage/products/kotlinconf2018/slides/5_Writing%20Your%20First%20Kotlin%20Compiler%20Plugin.pdf

Idea is to implement something like this
```kotlin
@Entity
data class SomeEntity(val name: String, val age: Int) : EntityBase()


@Copy("entityId")
abstract class EntityBase : OtherBase() {
  var id: String? = null
}

abstract class OtherBase { 
  var someOtherVariable = 123
}
```

```
val someEntity = SomeEntity("David", 24)
someEntity.id = "100000"
someEntity.someOtherVariable = 100000

val coppiedEntity = someEntity.copy(name = "Milan")

assertEquals(coppiedEntity.name == "Milan") // As data class is expected to work normally
assertEquals(coppiedEntity.age == 24) // As data class is expected to work normally
assertEquals(coppiedEntity.id == "100000") // Because id variable is mentioned in @Copy annotation
assertEquals(coppiedEntity.someOtherVariable == null) // Because someOtherVariable is not mentioned in @Copy annotation, and OtherBase class doesn't have its own @Copy annotation

```
