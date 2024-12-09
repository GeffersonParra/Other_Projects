package World
enum class TileType(val id: Int, val collidable: Boolean, val nombre: String, val damage: Double = 0.0) {
    SKY(1, false, "Sky"),
    SHADOWEDGROUND1(2, true, "ShGround1"),
    SHADOWEDGROUND2(3, true, "ShGround2"),
    SHADOWEDGROUND3(4, true, "ShGround3"),
    SHADOWEDGROUND4(18, false, "ShGround4"),
    SHADOWEDGROUND5(19, false, "ShGround5"),
    SHADOWEDGROUND6(20, false, "ShGround6"),
    SHADOWEDGROUND7(34, false, "ShGround7"),
    SHADOWEDGROUND8(35, false, "ShGround8"),
    SHADOWEDGROUND9(36, false, "ShGround9"),
    GROUND1(5, true, "Ground1"),
    GROUND2(6, true, "Ground2"),
    GROUND3(7, true, "Ground3"),
    GROUND4(21, false, "Ground4"),
    GROUND5(22, false, "Ground5"),
    GROUND6(23, false, "Ground6"),
    GROUND7(37, false, "Ground7"),
    GROUND8(38, false, "Ground8"),
    GROUND9(39, false, "Ground9"),
    PORTALRED1(8, true, "Portalred1"),
    PORTALRED2(9, true, "Portalred2"),
    PORTALRED3(24, true, "Portalred3"),
    PORTALRED4(25, true, "Portalred4"),
    PORTALBLUE1(10, true, "Portalblue1"),
    PORTALBLUE2(11, true, "Portalblue2"),
    PORTALBLUE3(26, true, "Portalblue3"),
    PORTALBLUE4(27, true, "Portalblue4"),
    LAVA1(12, true, "Lava1"),
    LAVA2(13, true, "Lava2"),
    LAVA3(14, true, "Lava3"),
    LAVA4(28, true, "Lava4"),
    LAVA5(29, true, "Lava5"),
    LAVA6(30, true, "Lava6"),
    AGUA1(15, true, "Agua1"),
    AGUA2(16, true, "Agua2"),
    AGUA3(17, true, "Agua3"),
    AGUA4(31, true, "Agua4"),
    AGUA5(32, true, "Agua5"),
    AGUA6(33, true, "Agua6");



    private val tileMap: HashMap<Int, TileType> = hashMapOf()

    init {
        TileType.values().forEach { tileType ->
            tileMap[tileType.id] = tileType
        }
    }

    fun getTileTypeById(id: Int): TileType? {
        return tileMap[id]
    }

}